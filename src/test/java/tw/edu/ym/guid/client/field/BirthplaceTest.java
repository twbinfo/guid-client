package tw.edu.ym.guid.client.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import wmw.i18n.Nation;

public class BirthplaceTest {

  private Birthplace birthplace;

  @Before
  public void setUp() throws Exception {
    birthplace = new Birthplace(Nation.TW);
  }

  @Test
  public void testConstructor() {
    assertTrue(birthplace instanceof Birthplace);
  }

  @Test
  public void testGetBirthplace() {
    assertEquals(Nation.TW, birthplace.getBirthplace());
  }

  @Test
  public void testEquals() {
    assertTrue(birthplace.equals(new Birthplace(Nation.TW)));
    assertFalse(birthplace.equals(new Birthplace(Nation.US)));
    assertFalse(birthplace.equals(null));
  }

  @Test
  public void testHashCode() {
    assertEquals(birthplace.hashCode(), new Birthplace(Nation.TW).hashCode());
    assertNotEquals(birthplace.hashCode(),
        new Birthplace(Nation.US).hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("TW", birthplace.toString());
  }

  @Test
  public void testCompareTo() {
    assertTrue(
        new Birthplace(Nation.TW).compareTo(new Birthplace(Nation.US)) < 0);
    assertTrue(
        new Birthplace(Nation.TW).compareTo(new Birthplace(Nation.AD)) > 0);
  }

}
