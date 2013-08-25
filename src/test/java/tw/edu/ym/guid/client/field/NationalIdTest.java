package tw.edu.ym.guid.client.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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
  public void testEquals() {
    assertTrue(nationalId.equals(new NationalId("A123456789")));
    assertFalse(nationalId.equals(null));
  }

  @Test
  public void testHashCode() {
    assertEquals(nationalId.hashCode(), new NationalId("A123456789").hashCode());
    assertNotEquals(nationalId.hashCode(),
        new NationalId("A987654310").hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("A123456789", nationalId.toString());
  }

  @Test
  public void testCompareTo() {
    assertTrue(nationalId.compareTo(new NationalId("A987654310")) < 0);
  }

}
