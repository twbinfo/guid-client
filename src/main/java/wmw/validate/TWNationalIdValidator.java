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

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * TWNationalIdValidator validates the National ID of Taiwan(R.O.C) citizens.
 * 
 */
public final class TWNationalIdValidator {

  private static final String PREFIX = "ABCDEFGHJKLMNPQRSTUVXYWZIO";

  private TWNationalIdValidator() {}

  /**
   * Validates the National ID of Taiwan(R.O.C) citizens.
   * 
   * @param nationalId
   *          the National ID of Taiwan(R.O.C) citizens.
   * @return true if the National ID is valid, false otherwise
   */
  public static boolean validate(String nationalId) {
    if (nationalId == null || !nationalId.matches("^[A-Z][12]\\d{8}$"))
      return false;

    List<Integer> ints = new ArrayList<Integer>();
    ints.add(PREFIX.indexOf(nationalId.charAt(0)) / 10 + 1);
    ints.add(PREFIX.indexOf(nationalId.charAt(0)) % 10);
    for (String c : nationalId.substring(1).split("(?!^)")) {
      ints.add(Integer.valueOf(c));
    }

    int sum = ints.get(0);
    for (int i = 1; i < ints.size() - 1; i++) {
      sum += ints.get(i) * (10 - i);
    }

    int checksum = (10 - sum % 10) % 10;
    return ints.get(ints.size() - 1) == checksum;
  }

}
