package tw.edu.ym.guid.client.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class NameTest {

  private Name name;

  @Before
  public void setUp() throws Exception {
    name = new Name("mj", "li");
  }

  @Test
  public void testContructor() {
    assertTrue(name instanceof Name);
  }

  @Test(expected = NullPointerException.class)
  public void testContructorWithNullFirstName() {
    new Name(null, "li");
  }

  @Test(expected = NullPointerException.class)
  public void testContructorWithNullLastName() {
    new Name("mj", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testContructorWithEmptyFirstName() {
    new Name("", "li");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testContructorWithEmptyLastName() {
    new Name("mj", "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testContructorWithEmptyMiddleName() {
    name = new Name("mj", "", "li");
  }

  @Test
  public void testGetFirstName() {
    assertEquals("MJ", name.getFirstName());
    name = new Name(" mj ", "li");
    assertEquals("MJ", name.getFirstName());
  }

  @Test
  public void testGetLastName() {
    assertEquals("LI", name.getLastName());
    name = new Name("mj", " li ");
    assertEquals("LI", name.getLastName());
  }

  @Test
  public void testGetMiddleName() {
    assertEquals(Name.DEFAULT_MIDDLE_NAME, name.getMiddleName());
    name = new Name("mj", "Michael", "li");
    assertEquals("MICHAEL", name.getMiddleName());
  }

  @Test
  public void testEquals() {
    assertTrue(new Name("mj", "li").equals(name));
    assertFalse(new Name("mjm", "li").equals(name));
    assertFalse(new Name("mj", "m", "li").equals(name));
    assertFalse(new Name("mj", "m", "lee").equals(new Name("mj", "m", "li")));
    assertFalse(name.equals(null));
  }

  @Test
  public void testHashCode() {
    assertEquals(name.hashCode(), new Name("mj", "li").hashCode());
    assertNotEquals(name.hashCode(), new Name("mjm", "li").hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("MJ LI", name.toString());
    name = new Name("mj", "michael", "li");
    assertEquals("MJ MICHAEL LI", name.toString());
  }

  @Test
  public void testCompareTo() {
    assertTrue(new Name("mj", "li").compareTo(new Name("amy", "li")) > 0);
    assertTrue(
        new Name("mj", "a", "li").compareTo(new Name("mj", "b", "li")) < 0);
    assertTrue(new Name("mj", "li").compareTo(new Name("mj", "wang")) < 0);
    assertTrue(
        new Name("mj", "m", "li").compareTo(new Name("mj", "m", "li")) == 0);
  }

}
