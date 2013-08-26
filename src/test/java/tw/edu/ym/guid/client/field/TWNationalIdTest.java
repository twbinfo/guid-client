package tw.edu.ym.guid.client.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TWNationalIdTest {

  private TWNationalId nationalId;

  @Before
  public void setUp() throws Exception {
    nationalId = new TWNationalId("A123456789");
  }

  @Test
  public void testContructor() {
    assertTrue(nationalId instanceof TWNationalId);
  }

  @Test
  public void testInterface() {
    assertTrue(nationalId instanceof NationalId);
  }

  @Test
  public void testGetNationalId() {
    assertEquals("A123456789", nationalId.getNationalId());
  }

  @Test
  public void testEquals() {
    assertTrue(nationalId.equals(new TWNationalId("A123456789")));
    assertFalse(nationalId.equals(null));
  }

  @Test
  public void testHashCode() {
    assertEquals(nationalId.hashCode(),
        new TWNationalId("A123456789").hashCode());
    assertNotEquals(nationalId.hashCode(),
        new TWNationalId("A987654310").hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("A123456789", nationalId.toString());
  }

  @Test
  public void testCompareTo() {
    assertTrue(nationalId.compareTo(new TWNationalId("A987654310")) < 0);
  }

}
