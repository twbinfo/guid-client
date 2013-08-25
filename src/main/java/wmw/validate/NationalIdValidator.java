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
package wmw.validate;

import static java.util.Collections.unmodifiableMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 
 * NationalIdValidator validates the National ID of Taiwan(R.O.C) citizens.
 * 
 */
public final class NationalIdValidator {

  public static final Map<Character, Integer> PREFIX =
      unmodifiableMap(new LinkedHashMap<Character, Integer>() {

        private static final long serialVersionUID = 883904263503948682L;

        {
          put('A', 10);
          put('B', 11);
          put('C', 12);
          put('D', 13);
          put('E', 14);
          put('F', 15);
          put('G', 16);
          put('H', 17);
          put('I', 34);
          put('J', 18);
          put('K', 19);
          put('L', 20);
          put('M', 21);
          put('N', 22);
          put('O', 35);
          put('P', 23);
          put('Q', 24);
          put('R', 25);
          put('S', 26);
          put('T', 27);
          put('U', 28);
          put('V', 29);
          put('W', 32);
          put('X', 30);
          put('Y', 31);
          put('Z', 33);
        }
      });

  /**
   * Validates the National ID of Taiwan(R.O.C) citizens.
   * 
   * @param nationalId
   *          the National ID of Taiwan(R.O.C) citizens.
   * @return true if the National ID is valid, false otherwise
   */
  public static boolean validate(String nationalId) {
    if (nationalId == null
        || !Pattern.compile("^[A-Z]\\d{9}$").matcher(nationalId).find())
      return false;

    List<Integer> ints = new ArrayList<Integer>();
    ints.add(PREFIX.get(nationalId.charAt(0)) / 10);
    ints.add(PREFIX.get(nationalId.charAt(0)) % 10);
    for (int i = 1; i < nationalId.length(); i++)
      ints.add(Integer.valueOf(String.valueOf(nationalId.charAt(i))));

    int sum = ints.get(0);
    for (int i = 1; i < ints.size() - 1; i++)
      sum += ints.get(i) * (10 - i);

    int mod = sum % 10;
    int checksum = mod == 0 ? mod : 10 - mod;

    return ints.get(ints.size() - 1) == checksum;
  }

}
