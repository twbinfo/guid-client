package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static tw.edu.ym.guid.client.hashcode.Field.fn;
import static tw.edu.ym.guid.client.hashcode.Field.ln;
import static tw.edu.ym.guid.client.hashcode.Field.mn;

import java.util.Map;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import tw.edu.ym.guid.client.annotation.Factor;

/**
 * 
 * Name is a required field of GUID.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class Name implements Comparable<Name> {

  public enum NamePart {
    FIRST_NAME, LAST_NAME, MIDDLE_NAME;
  }

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

  /**
   * Creates a Name.
   * 
   * @param name
   */
  public Name(Map<NamePart, String> name) {
    validate(name.get(NamePart.FIRST_NAME), name.get(NamePart.LAST_NAME),
        name.get(NamePart.MIDDLE_NAME));
    this.firstName = name.get(NamePart.FIRST_NAME).trim().toUpperCase();
    this.lastName = name.get(NamePart.LAST_NAME).trim().toUpperCase();
    this.middleName = name.get(NamePart.MIDDLE_NAME) != null
        ? name.get(NamePart.MIDDLE_NAME).trim().toUpperCase()
        : DEFAULT_MIDDLE_NAME;
  }

  private void validate(String firstName, String lastName, String middleName) {
    checkNotNull(firstName, "First name can't be null.");
    checkNotNull(lastName, "Last name can't be null.");
    checkArgument(firstName.trim().length() > 0, "First name is empty.");
    checkArgument(lastName.trim().length() > 0, "Last name is empty.");
    if (middleName != null)
      checkArgument(middleName.trim().length() > 0, "Middle name is empty.");
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
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof Name)) return false;
    Name castOther = (Name) other;
    return Objects.equal(firstName, castOther.firstName)
        && Objects.equal(lastName, castOther.lastName)
        && Objects.equal(middleName, castOther.middleName);
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
