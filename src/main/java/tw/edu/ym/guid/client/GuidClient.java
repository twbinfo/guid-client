package tw.edu.ym.guid.client;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
import static org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.BasicClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import wmw.util.InputStreamUtil;

import com.google.common.base.Objects;
import com.google.gson.Gson;

/**
 * 
 * GuidClient can create and query GUIDs from a GUID server.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class GuidClient {

  private static final String API_ROOT = "guid";

  private enum Action {

    AUTH("authenticate"), QUERY("show"), CREATE("create");

    private String action;

    private Action(String action) {
      this.action = action;
    }

    @Override
    public String toString() {
      return action;
    }

  }

  private HttpClient httpClient;

  private final URI uri;
  private final String username;
  private final String password;
  private final String prefix;

  /**
   * Creates a GuidClient.
   * 
   * @param uri
   *          of the server host
   * @param username
   *          to login server
   * @param password
   *          to login server
   */
  public GuidClient(URI uri, String username, String password) {
    this.uri = checkNotNull(uri);
    this.username = checkNotNull(username);
    this.password = checkNotNull(password);
    this.prefix = "";
  }

  /**
   * Creates a GuidClient.
   * 
   * @param uri
   *          of the server host
   * @param username
   *          to login server
   * @param password
   *          to login server
   * @param prefix
   *          of GUIDs
   */
  public GuidClient(URI uri, String username, String password, String prefix) {
    this.uri = checkNotNull(uri);
    this.username = checkNotNull(username);
    this.password = checkNotNull(password);
    this.prefix = checkNotNull(prefix);
  }

  void setHttpClient(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  /**
   * Authenticates a user account.
   * 
   * @return true if authenticated, false otherwise
   * @throws IOException
   */
  public boolean authenticate() throws IOException {
    if (httpClient == null)
      httpClient =
          new DefaultHttpClient(
              getSSLClientConnectionManager(uri.getPort() == -1 ? 443
                  : uri.getPort()));

    HttpGet httpGet =
        new HttpGet("https://" + uri.getHost()
            + (uri.getPort() == -1 ? "" : ":" + uri.getPort()) + "/" + API_ROOT
            + "/" + Action.AUTH);
    httpGet.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials(
        username, password), "US-ASCII", false));

    HttpResponse response = checkStatusCode(httpClient.execute(httpGet));
    HttpEntity entity = response.getEntity();

    String json = InputStreamUtil.toString(entity.getContent());
    Boolean result = new Gson().fromJson(json, Boolean.class);
    return result;
  }

  /**
   * Creates GUID by given PII.
   * 
   * @param pii
   *          a PII
   * @return a GUID
   * @throws IOException
   */
  public String create(PII pii) throws IOException {
    return request(new Gson().toJson(pii.getHashcodes()), Action.CREATE).get(0);
  }

  /**
   * Creates GUID by given PIIs.
   * 
   * @param piis
   *          Array of PIIs
   * @return List of GUIDs
   * @throws IOException
   */
  public List<String> create(PII... piis) throws IOException {
    return create(Arrays.asList(piis));
  }

  /**
   * Creates GUID by given PIIs.
   * 
   * @param piis
   *          List of PIIs
   * @return List of GUIDs
   * @throws IOException
   */
  public List<String> create(List<PII> piis) throws IOException {
    List<List<String>> hashsets = newArrayList();
    for (PII pii : piis)
      hashsets.add(pii.getHashcodes());
    return request(new Gson().toJson(hashsets), Action.CREATE);
  }

  /**
   * Queries GUID by given PII. No GUID created if GUID can not be found.
   * 
   * @param piis
   *          a PIIs
   * @return a GUID
   * @throws IOException
   */
  public String query(PII pii) throws IOException {
    return request(new Gson().toJson(pii.getHashcodes()), Action.QUERY).get(0);
  }

  /**
   * Queries GUID by given PIIs. No GUID created if GUID can not be found.
   * 
   * @param piis
   *          Array of PIIs
   * @return List of GUIDs
   * @throws IOException
   */
  public List<String> query(PII... piis) throws IOException {
    return query(Arrays.asList(piis));
  }

  /**
   * Queries GUID by given PIIs. No GUID created if GUID can not be found.
   * 
   * @param piis
   *          List of PIIs
   * @return List of GUIDs
   * @throws IOException
   */
  public List<String> query(List<PII> piis) throws IOException {
    List<List<String>> hashsets = newArrayList();
    for (PII pii : piis)
      hashsets.add(pii.getHashcodes());
    return request(new Gson().toJson(hashsets), Action.QUERY);
  }

  private List<String> request(String jsonHashes, Action action)
      throws IOException {
    if (httpClient == null)
      httpClient =
          new DefaultHttpClient(
              getSSLClientConnectionManager(uri.getPort() == -1 ? 443
                  : uri.getPort()));

    HttpPost httpPost =
        new HttpPost("https://" + uri.getHost()
            + (uri.getPort() == -1 ? "" : ":" + uri.getPort()) + "/" + API_ROOT
            + "/" + action);
    httpPost
        .addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials(
            username, password), "US-ASCII", false));

    List<NameValuePair> nvps = newArrayList();
    nvps.add(new BasicNameValuePair("prefix", prefix));
    nvps.add(new BasicNameValuePair("hashes", jsonHashes));

    try {
      httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
    }
    HttpResponse response = checkStatusCode(httpClient.execute(httpPost));
    HttpEntity entity = response.getEntity();

    String json = InputStreamUtil.toString(entity.getContent());
    @SuppressWarnings("unchecked")
    List<String> result = new Gson().fromJson(json, List.class);
    return result;
  }

  private HttpResponse checkStatusCode(HttpResponse response) {
    if (response.getStatusLine().getStatusCode() != 200)
      throw new GuidClientException(response.getStatusLine().toString());
    return response;
  }

  private ClientConnectionManager getSSLClientConnectionManager(int port) {
    SSLContext sslContext = null;
    try {
      sslContext = SSLContext.getInstance("SSL");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    try {
      sslContext.init(null, new TrustManager[] { new X509TrustManager() {

        public X509Certificate[] getAcceptedIssuers() {
          return null;
        }

        public void
            checkClientTrusted(X509Certificate[] certs, String authType) {}

        public void
            checkServerTrusted(X509Certificate[] certs, String authType) {}

      } }, new SecureRandom());
    } catch (KeyManagementException e) {
      e.printStackTrace();
    }

    SSLSocketFactory sf =
        new SSLSocketFactory(sslContext, ALLOW_ALL_HOSTNAME_VERIFIER);
    Scheme httpsScheme = new Scheme("https", port, sf);
    SchemeRegistry schemeRegistry = new SchemeRegistry();
    schemeRegistry.register(httpsScheme);

    return new BasicClientConnectionManager(schemeRegistry);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this.getClass()).add("Username", username)
        .add("Password", password).add("Prefix", prefix).add("URI", uri)
        .toString();
  }

  private final class GuidClientException extends RuntimeException {

    private static final long serialVersionUID = 5792652535991137347L;

    public GuidClientException(String msg) {
      super(msg);
    }

  }

  public static void main(String[] srg) {
    System.out.println(new Gson().fromJson("true", Boolean.class));
  }
}
