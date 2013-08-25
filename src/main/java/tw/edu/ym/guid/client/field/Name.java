package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 
 * Name is a required field of GUID.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class Name {

  public static final String DEFAULT_MIDDLE_NAME = "NOTAPPLICABLE";

  private final String firstName;
  private final String lastName;
  private final String middleName;

  /**
   * Creates a Name.
   * 
   * @param firstName
   * @param lastName
   */
  public Name(String firstName, String lastName) {
    validate(firstName, lastName, null);
    this.firstName = firstName.trim().toUpperCase();
    this.lastName = lastName.trim().toUpperCase();
    this.middleName = DEFAULT_MIDDLE_NAME;
  }

  /**
   * Creates a Name.
   * 
   * @param firstName
   * @param lastName
   * @param middleName
   */
  public Name(String firstName, String lastName, String middleName) {
    validate(firstName, lastName, middleName);
    this.firstName = firstName.trim().toUpperCase();
    this.lastName = lastName.trim().toUpperCase();
    this.middleName = middleName.toUpperCase();
  }

  private void validate(String firstName, String lastName, String middleName) {
    checkNotNull(firstName);
    checkNotNull(lastName);
    if (middleName != null)
      checkNotNull(middleName);
    checkArgument(firstName.trim().length() > 0);
    checkArgument(lastName.trim().length() > 0);
    if (middleName != null)
      checkArgument(middleName.trim().length() > 0);
  }

  /**
   * Returns the first name.
   * 
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns the last name.
   * 
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Returns the middle name.
   * 
   * @return the middle name, default value is NOTAPPLICABLE
   */
  public String getMiddleName() {
    return middleName;
  }

  @Override
  public String toString() {
    if (DEFAULT_MIDDLE_NAME.equals(middleName))
      return firstName + " " + lastName;
    else
      return firstName + " " + middleName + " " + lastName;
  }

}
