package tw.edu.ym.guid.client.field;

import static tw.edu.ym.guid.client.hashcode.Field.sex;
import tw.edu.ym.guid.client.annotation.Factor;

/**
 * 
 * Sex is a required field of GUID.
 * 
 * @author Wei-Ming Wu
 * 
 */
public enum Sex {

  FEMALE("F"), MALE("M");

  private String gender;

  private Sex(String sex) {
    this.gender = sex;
  }

  /**
   * Returns the gender.
   * 
   * @return M if male, F otherwise
   */
  @Factor(field = sex)
  public String getSex() {
    return gender;
  }

}
