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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * InputStreamUtil converts any InputStream to String, Array of String or List
 * of String.
 * 
 */
public final class InputStreamUtil {

  private InputStreamUtil() {}

  /**
   * Converts an InputStream to String.
   * 
   * @param is
   *          an InputStream
   * @return a String
   */
  public static String toString(InputStream is) {
    return toString(is, false);
  }

  /**
   * 
   * @param is
   *          an InputStream
   * @param lineSeparator
   *          true to keep line separators, false otherwise
   * @return a String
   */
  public static String toString(InputStream is, final boolean lineSeparator) {
    final StringBuilder sb = new StringBuilder();

    eachLine(is, new LineOperator() {

      @Override
      public void doLine(String line) {
        sb.append(line);
        if (lineSeparator)
          sb.append(System.getProperty("line.separator"));
      }

    });

    return sb.toString();
  }

  /**
   * Converts an InputStream to List of String.
   * 
   * @param is
   *          an InputStream
   * @return a List of String
   */
  public static List<String> toStringList(InputStream is) {
    final List<String> list = new ArrayList<String>();

    eachLine(is, new LineOperator() {

      @Override
      public void doLine(String line) {
        list.add(line);
      }

    });

    return list;
  }

  /**
   * Converts an InputStream to Array of String.
   * 
   * @param is
   *          an InputStream
   * @return an Array of String
   */
  public static String[] toStringArray(InputStream is) {
    List<String> list = toStringList(is);
    return list.toArray(new String[list.size()]);
  }

  /**
   * Reads an InputStream line by line and processes each line with a
   * LineOperator.
   * 
   * @param is
   *          an InputStream
   * @param operator
   *          a LineOperator
   */
  public static void eachLine(InputStream is, LineOperator operator) {
    if (is == null || operator == null)
      throw new NullPointerException();

    BufferedReader br = null;
    String line;
    try {
      br = new BufferedReader(new InputStreamReader(is));
      while ((line = br.readLine()) != null)
        operator.doLine(line);
    } catch (IOException e) {
      Logger.getLogger(InputStreamUtilTest.class.getName()).log(Level.SEVERE,
          null, e);
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          Logger.getLogger(InputStreamUtilTest.class.getName()).log(
              Level.SEVERE, null, e);
        }
      }
    }
  }
}
