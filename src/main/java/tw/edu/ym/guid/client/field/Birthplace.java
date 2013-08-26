package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkNotNull;
import wmw.i18n.Nation;

import com.google.common.base.Objects;

public final class Birthplace implements Comparable<Birthplace> {

  private final Nation birthplace;

  public Birthplace(Nation birthplace) {
    this.birthplace = checkNotNull(birthplace);
  }

  public Nation getBirthplace() {
    return birthplace;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Birthplace) {
      Birthplace bp = (Birthplace) o;
      return Objects.equal(birthplace, bp.birthplace);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(birthplace);
  }

  @Override
  public String toString() {
    return birthplace.toString();
  }

  @Override
  public int compareTo(Birthplace arg0) {
    return birthplace.toString().compareTo(arg0.birthplace.toString());
  }

}
