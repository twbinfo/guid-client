package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkNotNull;
import static tw.edu.ym.guid.client.hashcode.Field.cnob;
import static tw.edu.ym.guid.client.hashcode.Field.giidc;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import tw.edu.ym.guid.client.annotation.Factor;
import wmw.i18n.Nation;

/**
 * 
 * Nationality is a optional field of GUID.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class Nationality implements Comparable<Nationality> {

  private final Nation nationality;
  private final Nation nationalityOfBirth;

  /**
   * Returns a default nationality of TW(Taiwan).
   * 
   * @return a nationality of TW
   */
  public static Nationality getDefault() {
    return new Nationality(Nation.TW);
  }

  /**
   * Creates a Nationality.
   * 
   * @param nationality
   *          a Nation
   */
  public Nationality(Nation nationality) {
    this.nationality = checkNotNull(nationality, "Nationality can't be null.");
    this.nationalityOfBirth =
        checkNotNull(nationality, "Nationality of birth can't be null.");
  }

  /**
   * Creates a Nationality.
   * 
   * @param nationality
   *          a Nation
   * @param nationalityOfBirth
   *          a Nation
   */
  public Nationality(Nation nationality, Nation nationalityOfBirth) {
    this.nationality = checkNotNull(nationality, "Nationality can't be null.");
    this.nationalityOfBirth =
        checkNotNull(nationalityOfBirth, "Nationality of birth can't be null.");
  }

  /**
   * Returns the nation of nationality.
   * 
   * @return a Nation
   */
  @Factor(field = giidc)
  public Nation getNationality() {
    return nationality;
  }

  /**
   * Returns the nation of nationality of birth.
   * 
   * @return a Nation
   */
  @Factor(field = cnob)
  public Nation getNationalityOfBirth() {
    return nationalityOfBirth;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof Nationality)) return false;
    Nationality castOther = (Nationality) other;
    return Objects.equal(nationality, castOther.nationality)
        && Objects.equal(nationalityOfBirth, castOther.nationalityOfBirth);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(nationality, nationalityOfBirth);
  }

  @Override
  public String toString() {
    return "Nationality=" + nationality + ", " + "NationalityOfBirth="
        + nationalityOfBirth;
  }

  @Override
  public int compareTo(Nationality that) {
    return ComparisonChain.start().compare(this.nationality, that.nationality)
        .compare(this.nationalityOfBirth, that.nationalityOfBirth).result();
  }

}
