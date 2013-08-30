package tw.edu.ym.guid.client.hashcode;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static tw.edu.ym.guid.client.hashcode.Field.fn;
import static tw.edu.ym.guid.client.hashcode.Field.ln;
import static tw.edu.ym.guid.client.hashcode.Field.mn;
import static tw.edu.ym.guid.client.hashcode.FieldSet.combo;
import static tw.edu.ym.guid.client.hashcode.FieldSet.single;

import org.junit.Before;
import org.junit.Test;

public class FieldSetTest {

  private FieldSet fs;

  @Before
  public void setUp() throws Exception {
    fs = single(fn);
  }

  @Test
  public void testConstructor() {
    assertTrue(fs instanceof FieldSet);
  }

  @Test
  public void testSingle() {
    fs = single(fn);
    assertTrue(fs instanceof FieldSet);
  }

  @Test
  public void testCombo() {
    fs = combo(fn, ln, mn);
    assertTrue(fs instanceof FieldSet);
  }

  @Test
  public void testGetFields() {
    assertEquals(newArrayList(fn), fs.getFields());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetFieldsImmutability() {
    fs.getFields().add(ln);
  }

  @Test
  public void testEquals() {
    assertTrue(fs.equals(single(fn)));
    assertFalse(fs.equals(combo(fn, ln)));
    assertFalse(fs.equals(null));
  }

  @Test
  public void testHashCode() {
    assertEquals(fs.hashCode(), single(fn).hashCode());
    assertNotEquals(fs.hashCode(), combo(fn, ln).hashCode());
  }

  @Test
  public void testToString() {
    assertEquals(fs.toString(), "[fn]");
    assertEquals(combo(fn, ln).toString(), "[fn, ln]");
  }

}
