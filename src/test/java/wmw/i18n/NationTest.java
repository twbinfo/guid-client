package wmw.i18n;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NationTest {

  @Test
  public void testGetName() {
    assertEquals("Taiwan", Nation.TW.getName());
  }

}
