package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public final class Name {

  private static final String DEFAULT_MIDDLE_NAME = "NOTAPPLICABLE";

  private final String firstName;
  private final String lastName;
  private final String middleName;

  public Name(String firstName, String lastName) {
    checkNotNull(firstName);
    checkNotNull(lastName);
    checkArgument(firstName.trim().length() > 0);
    checkArgument(lastName.trim().length() > 0);
    this.firstName = firstName.trim().toUpperCase();
    this.lastName = lastName.trim().toUpperCase();
    this.middleName = DEFAULT_MIDDLE_NAME;
  }

  public Name(String firstName, String lastName, String middleName) {
    checkNotNull(firstName);
    checkNotNull(lastName);
    checkNotNull(middleName);
    checkArgument(firstName.trim().length() > 0);
    checkArgument(lastName.trim().length() > 0);
    checkArgument(middleName.trim().length() > 0);
    this.firstName = firstName.trim().toUpperCase();
    this.lastName = lastName.trim().toUpperCase();
    this.middleName = middleName.toUpperCase();
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

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
