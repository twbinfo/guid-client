package tw.edu.ym.guid.client;

import static com.google.common.collect.Lists.newArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import tw.edu.ym.guid.client.field.Birthday;
import tw.edu.ym.guid.client.field.Gender;
import tw.edu.ym.guid.client.field.Name;
import tw.edu.ym.guid.client.field.NationalId;
import tw.edu.ym.guid.client.field.Sex;

import com.google.gson.Gson;

public final class GuidClient {

  private final String username;
  private final String password;
  private final String prefix;
  private final URI uri;

  public GuidClient(String username, String password, String prefix, URI uri) {
    this.username = username;
    this.password = password;
    this.prefix = prefix;
    this.uri = uri;
  }

  public List<String> create(PII pii) throws IOException {
    return query(new Gson().toJson(pii.getHashcodes()), "create");
  }

  public List<String> create(PII... piis) throws IOException {
    List<List<String>> hashsets = newArrayList();
    for (PII pii : piis)
      hashsets.add(pii.getHashcodes());
    return query(new Gson().toJson(hashsets), "create");
  }

  public List<String> query(PII pii) throws IOException {
    return query(new Gson().toJson(pii.getHashcodes()), "show");
  }

  public List<String> query(PII... piis) throws IOException {
    List<List<String>> hashsets = newArrayList();
    for (PII pii : piis)
      hashsets.add(pii.getHashcodes());
    return query(new Gson().toJson(hashsets), "show");
  }

  private List<String> query(String jsonHashes, String methed)
      throws IOException {
    DefaultHttpClient httpclient = new DefaultHttpClient();
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
    HttpResponse response = httpclient.execute(httpost);
    HttpEntity entity = response.getEntity();

    String json = getStringFromInputStream(entity.getContent());
    @SuppressWarnings("unchecked")
    List<String> result = new Gson().fromJson(json, List.class);
    return result;
  }

  private static String getStringFromInputStream(InputStream is) {
    BufferedReader br = null;
    StringBuilder sb = new StringBuilder();

    String line;
    try {
      br = new BufferedReader(new InputStreamReader(is));
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return sb.toString();
  }

  public static void main(String[] args) throws URISyntaxException, IOException {
    System.out
        .println(new GuidClient("guid1", "12345", "TEST", new URI(
            "http://localhost:3000")).create(new PII(new Name("MJ", "LI"),
            new Sex(Gender.MALE), new Birthday(1979, 7, 21), new NationalId(
                "E122371585"))));
  }

}
