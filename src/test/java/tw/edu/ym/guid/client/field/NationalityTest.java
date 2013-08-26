package tw.edu.ym.guid.client.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import wmw.i18n.Nation;

public class NationalityTest {

  private Nationality nationality;

  @Before
  public void setUp() throws Exception {
    nationality = new Nationality(Nation.TW);
  }

  @Test
  public void testConstructor() {
    assertTrue(nationality instanceof Nationality);
  }

  @Test
  public void testGetDefault() {
    assertEquals(nationality, Nationality.getDefault());
  }

  @Test
  public void testGetNationality() {
    assertEquals(Nation.TW, nationality.getNationality());
  }

  @Test
  public void testGetNationalityOfBirth() {
    assertEquals(Nation.TW, nationality.getNationalityOfBirth());
  }

  @Test
  public void testEquals() {
    assertTrue(nationality.equals(new Nationality(Nation.TW)));
    assertFalse(nationality.equals(new Nationality(Nation.TW, Nation.US)));
    assertFalse(nationality.equals(new Nationality(Nation.US, Nation.TW)));
    assertFalse(nationality.equals(null));
  }

  @Test
  public void testHashCode() {
    assertEquals(nationality.hashCode(), new Nationality(Nation.TW).hashCode());
    assertNotEquals(nationality.hashCode(), new Nationality(Nation.TW,
        Nation.US).hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("Nationality=TW, NationalityOfBirth=TW",
        nationality.toString());
  }

  @Test
  public void testCompareTo() {
    assertTrue(new Nationality(Nation.TW).compareTo(new Nationality(Nation.AD)) > 0);
    assertTrue(new Nationality(Nation.TW, Nation.TW).compareTo(new Nationality(
        Nation.TW, Nation.US)) < 0);
    assertTrue(new Nationality(Nation.TW, Nation.TW).compareTo(new Nationality(
        Nation.TW, Nation.TW)) == 0);
  }

}
