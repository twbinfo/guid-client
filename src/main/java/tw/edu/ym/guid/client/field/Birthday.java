package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkArgument;
import static net.sf.rubycollect4j.RubyCollections.range;
import static tw.edu.ym.guid.client.hashcode.Field.dob;
import static tw.edu.ym.guid.client.hashcode.Field.mob;
import static tw.edu.ym.guid.client.hashcode.Field.yob;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import net.sf.rubycollect4j.RubyDate;
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
    int currentYear = RubyDate.current().year();
    checkArgument(
        range(currentYear - 150, currentYear + 1).includeʔ(yearOfBirth),
        "Year of birth must be between " + (currentYear - 150) + " and "
            + (currentYear + 1));
    checkArgument(range(1, 12).includeʔ(monthOfBirth),
        "Month of birth must be between 1 and 12");
    switch (monthOfBirth) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        checkArgument(range(1, 31).includeʔ(dayOfBirth),
            "Day of birth must be between 1 and 31");
        break;
      case 2:
        if (yearOfBirth % 4 == 0 && yearOfBirth % 100 != 0)
          checkArgument(range(1, 29).includeʔ(dayOfBirth),
              "Day of birth must be between 1 and 29");
        else
          checkArgument(range(1, 28).includeʔ(dayOfBirth),
              "Day of birth must be between 1 and 28");
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        checkArgument(range(1, 30).includeʔ(dayOfBirth),
            "Day of birth must be between 1 and 30");
        break;
      default:
        throw new IllegalArgumentException("Day of birth is invalid");
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
