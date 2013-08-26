package tw.edu.ym.guid.client.field;

/**
 * 
 * Sex is a required field of GUID.
 * 
 * @author Wei-Ming Wu
 * 
 */
public enum Sex {

  FEMALE("F"), MALE("M");

  private String sex;

  private Sex(String sex) {
    this.sex = sex;
  }

  /**
   * Returns the gender.
   * 
   * @return M if male, F otherwise
   */
  public String getSex() {
    return sex;
  }

}
