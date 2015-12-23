/*
 *
 * @author Wei-Ming Wu
 *
 *
 * Copyright 2013 Wei-Ming Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static tw.edu.ym.guid.client.field.ComboLastName.hasComboLastName;

/**
 * 
 * NameSplitter splits Chinese name into first name & last name.
 * 
 */
public final class NameSplitter {

  private NameSplitter() {}

  /**
   * Creates a Name.
   * 
   * @param name
   *          a Chinese name
   * @return a Name
   */
  public static Name split(String name) {
    name = checkNotNull(name).replaceAll("\\s+", " ").trim();
    checkArgument(name.replaceAll("\\s+", "").length() >= 2, "Name too short.");

    String[] fullName;
    if ((fullName = name.split(" ")).length == 2)
      return new Name(fullName[0], fullName[1]);

    if ((fullName = name.split("・")).length == 2)
      return new Name(fullName[1], fullName[0]);

    if (name.length() == 4)
      return new Name(name.substring(2), name.substring(0, 2));

    if (hasComboLastName(name))
      return new Name(name.substring(2, name.length()), name.substring(0, 2));
    else
      return new Name(name.substring(1, name.length()), name.substring(0, 1));
  }

}
