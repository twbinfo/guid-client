package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkNotNull;
import wmw.i18n.Nation;

import com.google.common.base.Objects;

public final class Nationality implements Comparable<Nationality> {

  private final Nation nationality;
  private final Nation nationalityOfBirth;

  public Nationality(Nation nationality) {
    this.nationality = checkNotNull(nationality);
    this.nationalityOfBirth = checkNotNull(nationality);
  }

  public Nationality(Nation nationality, Nation nationalityOfBirth) {
    this.nationality = checkNotNull(nationality);
    this.nationalityOfBirth = checkNotNull(nationalityOfBirth);
  }

  public Nation getNationality() {
    return nationality;
  }

  public Nation getNationalityOfBirth() {
    return nationalityOfBirth;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Nationality) {
      Nationality nt = (Nationality) o;
      return Objects.equal(nationality, nt.nationality)
          && Objects.equal(nationalityOfBirth, nt.nationalityOfBirth);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(nationality, nationalityOfBirth);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this.getClass())
        .add("Nationality", nationality)
        .add("NationalityOfBirth", nationalityOfBirth).toString();
  }

  @Override
  public int compareTo(Nationality arg0) {
    int diff = 0;
    if ((diff = nationality.compareTo(arg0.nationality)) != 0)
      return diff;
    if ((diff = nationalityOfBirth.compareTo(arg0.nationalityOfBirth)) != 0)
      return diff;
    return 0;
  }

}
