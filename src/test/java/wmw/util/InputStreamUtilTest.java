/**
 * 
 * @author Wei-Ming Wu
 * 
 * 
 *         Copyright 2013 Wei-Ming Wu
 * 
 *         Licensed under the Apache License, Version 2.0 (the "License"); you
 *         may not use this file except in compliance with the License. You may
 *         obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *         implied. See the License for the specific language governing
 *         permissions and limitations under the License.
 * 
 */
package wmw.util;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InputStreamUtilTest {

  private InputStream is;

  @Before
  public void setUp() throws Exception {
    is = this.getClass().getClassLoader().getResourceAsStream("lines.txt");
  }

  @After
  public void tearDown() throws Exception {
    is.close();
  }

  @Test
  public void testToString() {
    assertEquals("abcdef", InputStreamUtil.toString(is));
  }

  @Test
  public void testToStringWithLineSeparator() {
    String lineSeparator = System.getProperty("line.separator");
    assertEquals("a" + lineSeparator + "bc" + lineSeparator + "def"
        + lineSeparator, InputStreamUtil.toString(is, true));
  }

  @Test
  public void testToStringList() {
    assertEquals(newArrayList("a", "bc", "def"),
        InputStreamUtil.toStringList(is));
  }

  @Test
  public void testToStringArray() {
    assertArrayEquals(new String[] { "a", "bc", "def" },
        InputStreamUtil.toStringArray(is));
  }

}
