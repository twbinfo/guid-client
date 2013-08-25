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
package tw.edu.ym.guid.client.field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class BirthdayTest {

  private Birthday birthday;

  @Before
  public void setUp() throws Exception {
    birthday = new Birthday(1987, 6, 5);
  }

  @Test
  public void testContructor() {
    assertTrue(birthday instanceof Birthday);
  }

  @Test
  public void testContructorWithInvalidYear() {
    new Birthday(1910, 6, 5);
    try {
      new Birthday(1909, 6, 5);
      fail();
    } catch (IllegalArgumentException e) {}
    new Birthday(2100, 6, 5);
    try {
      new Birthday(2101, 6, 5);
      fail();
    } catch (IllegalArgumentException e) {}
  }

  @Test
  public void testContructorWithInvalidMonth() {
    new Birthday(1910, 1, 5);
    try {
      new Birthday(1910, 0, 5);
      fail();
    } catch (IllegalArgumentException e) {}
    new Birthday(2100, 12, 5);
    try {
      new Birthday(2100, 13, 5);
      fail();
    } catch (IllegalArgumentException e) {}
  }

  @Test
  public void testContructorWithInvalidDay() {
    for (int month : new int[] { 1, 3, 5, 7, 8, 10, 12 }) {
      new Birthday(1910, month, 1);
      try {
        new Birthday(1910, month, 0);
        fail();
      } catch (IllegalArgumentException e) {}
      new Birthday(1910, month, 31);
      try {
        new Birthday(2100, month, 32);
        fail();
      } catch (IllegalArgumentException e) {}
    }
    for (int month : new int[] { 4, 6, 9, 11 }) {
      new Birthday(1910, month, 1);
      try {
        new Birthday(1910, month, 0);
        fail();
      } catch (IllegalArgumentException e) {}
      new Birthday(1910, month, 30);
      try {
        new Birthday(2100, month, 31);
        fail();
      } catch (IllegalArgumentException e) {}
    }
    for (int[] md : new int[][] { new int[] { 1912, 29 },
        new int[] { 1910, 28 } }) {
      new Birthday(md[0], 2, 1);
      try {
        new Birthday(md[0], 2, 0);
        fail();
      } catch (IllegalArgumentException e) {}
      new Birthday(md[0], 2, md[1]);
      try {
        new Birthday(md[0], 2, md[1] + 1);
        fail();
      } catch (IllegalArgumentException e) {}

    }
  }

  @Test
  public void testGetYearOfBirth() {
    assertEquals(1987, birthday.getYearOfBirth());
  }

  @Test
  public void testGetMonthOfBirth() {
    assertEquals(6, birthday.getMonthOfBirth());
  }

  @Test
  public void testGetDayOfBirth() {
    assertEquals(5, birthday.getDayOfBirth());
  }

  @Test
  public void testToString() {
    assertEquals("1987/06/05", birthday.toString());
    assertEquals("2000/10/10", new Birthday(2000, 10, 10).toString());
  }

}
