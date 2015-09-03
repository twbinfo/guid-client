package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkArgument;
import static tw.edu.ym.guid.client.hashcode.Field.dob;
import static tw.edu.ym.guid.client.hashcode.Field.mob;
import static tw.edu.ym.guid.client.hashcode.Field.yob;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import tw.edu.ym.guid.client.annotation.Factor;

/**
 * 
 * Birthday is a required field of GUID.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class Birthday implements Comparable<Birthday> {

  private final int yearOfBirth;
  private final int monthOfBirth;
  private final int dayOfBirth;

  /**
   * Creates a Birthday.
   * 
   * @param yearOfBirth
   * @param monthOfBirth
   * @param dayOfBirth
   */
  public Birthday(int yearOfBirth, int monthOfBirth, int dayOfBirth) {
    validate(yearOfBirth, monthOfBirth, dayOfBirth);
    this.yearOfBirth = yearOfBirth;
    this.monthOfBirth = monthOfBirth;
    this.dayOfBirth = dayOfBirth;
  }

  private void validate(int yearOfBirth, int monthOfBirth, int dayOfBirth) {
    checkArgument(yearOfBirth >= 1910 && yearOfBirth <= 2100,
        "Year of birth must be between 1910 and 2100.");
    checkArgument(monthOfBirth >= 1 && monthOfBirth <= 12,
        "Month of birth must be between 1 and 12.");
    switch (monthOfBirth) {
      case 1:
        checkArgument(dayOfBirth >= 1 && dayOfBirth <= 31,
            "Day of birth must be between 1 and 31.");
        break;
      case 2:
        if (yearOfBirth % 4 == 0)
          checkArgument(dayOfBirth >= 1 && dayOfBirth <= 29,
              "Day of birth must be between 1 and 29.");
        else
          checkArgument(dayOfBirth >= 1 && dayOfBirth <= 28,
              "Day of birth must be between 1 and 28.");
        break;
      case 3:
        checkArgument(dayOfBirth >= 1 && dayOfBirth <= 31,
            "Day of birth must be between 1 and 31.");
        break;
      case 4:
        checkArgument(dayOfBirth >= 1 && dayOfBirth <= 30,
            "Day of birth must be between 1 and 30.");
        break;
      case 5:
        checkArgument(dayOfBirth >= 1 && dayOfBirth <= 31,
            "Day of birth must be between 1 and 31.");
        break;
      case 6:
        checkArgument(dayOfBirth >= 1 && dayOfBirth <= 30,
            "Day of birth must be between 1 and 30.");
        break;
      case 7:
        checkArgument(dayOfBirth >= 1 && dayOfBirth <= 31,
            "Day of birth must be between 1 and 31.");
        break;
      case 8:
        checkArgument(dayOfBirth >= 1 && dayOfBirth <= 31,
            "Day of birth must be between 1 and 31.");
        break;
      case 9:
        checkArgument(dayOfBirth >= 1 && dayOfBirth <= 30,
            "Day of birth must be between 1 and 30.");
        break;
      case 10:
        checkArgument(dayOfBirth >= 1 && dayOfBirth <= 31,
            "Day of birth must be between 1 and 31.");
        break;
      case 11:
        checkArgument(dayOfBirth >= 1 && dayOfBirth <= 30,
            "Day of birth must be between 1 and 30.");
        break;
      case 12:
        checkArgument(dayOfBirth >= 1 && dayOfBirth <= 31,
            "Day of birth must be between 1 and 31.");
        break;
      default:
        throw new IllegalArgumentException("Day of birth is invalid.");
    }
  }

  /**
   * Returns the year of birth.
   * 
   * @return the year of birth
   */
  @Factor(field = yob)
  public int getYearOfBirth() {
    return yearOfBirth;
  }

  /**
   * Returns the month of birth.
   * 
   * @return the month of birth
   */
  @Factor(field = mob)
  public int getMonthOfBirth() {
    return monthOfBirth;
  }

  /**
   * Returns the day of birth.
   * 
   * @return the day of birth
   */
  @Factor(field = dob)
  public int getDayOfBirth() {
    return dayOfBirth;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof Birthday)) return false;
    Birthday castOther = (Birthday) other;
    return Objects.equal(yearOfBirth, castOther.yearOfBirth)
        && Objects.equal(monthOfBirth, castOther.monthOfBirth)
        && Objects.equal(dayOfBirth, castOther.dayOfBirth);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(yearOfBirth, monthOfBirth, dayOfBirth);
  }

  @Override
  public String toString() {
    return yearOfBirth + "/" + String.format("%02d", monthOfBirth) + "/"
        + String.format("%02d", dayOfBirth);
  }

  @Override
  public int compareTo(Birthday that) {
    return ComparisonChain.start().compare(this.yearOfBirth, that.yearOfBirth)
        .compare(this.monthOfBirth, that.monthOfBirth)
        .compare(this.dayOfBirth, that.dayOfBirth).result();
  }

}
