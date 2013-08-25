package tw.edu.ym.guid.client;

import static com.google.common.collect.Lists.newArrayList;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import tw.edu.ym.guid.client.field.Birthday;
import tw.edu.ym.guid.client.field.Name;
import tw.edu.ym.guid.client.field.NationalId;
import tw.edu.ym.guid.client.field.Sex;

public class GuidClientTest {

  private GuidClient guidClient;

  private HttpClient mockClient;
  private HttpResponse mockResponse;
  private HttpEntity mockEntity;

  private PII pii;

  @Before
  public void setUp() throws Exception {
    guidClient =
        new GuidClient("test", "test", "TEST", new URI("http://localhost:3000"));

    mockClient = createMock(HttpClient.class);
    mockResponse = createMock(HttpResponse.class);
    mockEntity = createMock(HttpEntity.class);

    expect(mockClient.execute(EasyMock.anyObject(HttpPost.class))).andReturn(
        mockResponse);
    expect(mockResponse.getEntity()).andReturn(mockEntity);
    expect(mockEntity.getContent()).andReturn(
        new ByteArrayInputStream("[TEST-b94c05f3]".getBytes()));

    replay(mockClient);
    replay(mockResponse);
    replay(mockEntity);

    guidClient.setHttpClient(mockClient);

    pii =
        new PII(new Name("mj", "li"), Sex.MALE, new Birthday(1979, 7, 21),
            new NationalId("E122371585"));
  }

  @Test
  public void testCreate() throws IOException {
    assertEquals(newArrayList("TEST-b94c05f3"), guidClient.create(pii));
  }

  @Test
  public void testCreateWithList() throws IOException {
    assertEquals(newArrayList("TEST-b94c05f3"),
        guidClient.create(newArrayList(pii)));
  }

  @Test
  public void testCreateWithArray() throws IOException {
    assertEquals(newArrayList("TEST-b94c05f3"),
        guidClient.create(new PII[] { pii }));
  }

  @Test
  public void testQuery() throws IOException {
    assertEquals(newArrayList("TEST-b94c05f3"), guidClient.query(pii));
  }

  @Test
  public void testQueryWithList() throws IOException {
    assertEquals(newArrayList("TEST-b94c05f3"),
        guidClient.query(newArrayList(pii)));
  }

  @Test
  public void testQueryWithArray() throws IOException {
    assertEquals(newArrayList("TEST-b94c05f3"),
        guidClient.query(new PII[] { pii }));
  }

  @Test
  public void testToString() {
    assertEquals(
        "GuidClient{Username=test, Password=test, Prefix=TEST, URI=http://localhost:3000}",
        guidClient.toString());
  }

}
