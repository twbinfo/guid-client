package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static tw.edu.ym.guid.client.hashcode.Field.fn;
import static tw.edu.ym.guid.client.hashcode.Field.ln;
import static tw.edu.ym.guid.client.hashcode.Field.mn;
import tw.edu.ym.guid.client.annotation.Factor;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * 
 * Name is a required field of GUID.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class Name implements Comparable<Name> {

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
   * @param middleName
   * @param lastName
   */
  public Name(String firstName, String middleName, String lastName) {
    validate(firstName, lastName, middleName);
    this.firstName = firstName.trim().toUpperCase();
    this.lastName = lastName.trim().toUpperCase();
    this.middleName = middleName.trim().toUpperCase();
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
  @Factor(field = fn)
  public String getFirstName() {
    return firstName;
  }

  /**
   * Returns the last name.
   * 
   * @return the last name
   */
  @Factor(field = ln)
  public String getLastName() {
    return lastName;
  }

  /**
   * Returns the middle name.
   * 
   * @return the middle name, default value is NOTAPPLICABLE
   */
  @Factor(field = mn)
  public String getMiddleName() {
    return middleName;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Name) {
      Name name = (Name) o;
      return Objects.equal(firstName, name.firstName)
          && Objects.equal(lastName, name.lastName)
          && Objects.equal(middleName, name.middleName);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(firstName, lastName, middleName);
  }

  @Override
  public String toString() {
    if (DEFAULT_MIDDLE_NAME.equals(middleName))
      return firstName + " " + lastName;
    else
      return firstName + " " + middleName + " " + lastName;
  }

  @Override
  public int compareTo(Name that) {
    return ComparisonChain.start().compare(this.firstName, that.firstName)
        .compare(this.middleName, that.middleName)
        .compare(this.lastName, that.lastName).result();
  }

}
