package tw.edu.ym.guid.client;

import static com.google.common.collect.Lists.newArrayList;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
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

  private HttpClient httpClient;

  private final String username;
  private final String password;
  private final String prefix;
  private final URI uri;

  /**
   * Creates a GuidClient.
   * 
   * @param username
   *          to login server
   * @param password
   *          to login server
   * @param prefix
   *          of GUIDs
   * @param uri
   *          of the server host
   */
  public GuidClient(String username, String password, String prefix, URI uri) {
    this.username = username;
    this.password = password;
    this.prefix = prefix;
    this.uri = uri;
  }

  void setHttpClient(HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  /**
   * Creates GUID by given PII.
   * 
   * @param pii
   *          a PII
   * @return List of GUIDs
   * @throws IOException
   */
  public List<String> create(PII pii) throws IOException {
    return resuest(new Gson().toJson(pii.getHashcodes()), "create");
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
    return resuest(new Gson().toJson(hashsets), "create");
  }

  /**
   * Queries GUID by given PII. No GUID created if GUID can not be found.
   * 
   * @param piis
   *          a PIIs
   * @return List of GUIDs
   * @throws IOException
   */
  public List<String> query(PII pii) throws IOException {
    return resuest(new Gson().toJson(pii.getHashcodes()), "show");
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
    return resuest(new Gson().toJson(hashsets), "show");
  }

  private List<String> resuest(String jsonHashes, String methed)
      throws IOException {
    if (httpClient == null)
      httpClient = new DefaultHttpClient();
    HttpPost httpost =
        new HttpPost(uri.getScheme() + "://" + uri.getHost()
            + (uri.getPort() == -1 ? "" : ":" + uri.getPort()) + "/guid/"
            + methed);
    List<NameValuePair> nvps = newArrayList();
    nvps.add(new BasicNameValuePair("username", username));
    nvps.add(new BasicNameValuePair("password", password));
    nvps.add(new BasicNameValuePair("prefix", prefix));
    nvps.add(new BasicNameValuePair("hashes", jsonHashes));

    try {
      httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    HttpResponse response = httpClient.execute(httpost);
    HttpEntity entity = response.getEntity();

    String json = InputStreamUtil.toString(entity.getContent());
    @SuppressWarnings("unchecked")
    List<String> result = new Gson().fromJson(json, List.class);
    return result;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this.getClass()).add("Username", username)
        .add("Password", password).add("Prefix", prefix).add("URI", uri)
        .toString();
  }

}
