package tw.edu.ym.guid.client.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class NationalIdTest {

  private NationalId nationalId;

  @Before
  public void setUp() throws Exception {
    nationalId = new NationalId("A123456789");
  }

  @Test
  public void testContructor() {
    assertTrue(nationalId instanceof NationalId);
  }

  @Test
  public void testGetNationalId() {
    assertEquals("A123456789", nationalId.getNationalId());
  }

  @Test
  public void testToString() {
    assertEquals("A123456789", nationalId.toString());
  }

}
