package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;

public class BaseNationalId implements NationalId {

  private final String nationalId;

  public BaseNationalId(String nationalId) {
    checkNotNull(nationalId);
    checkArgument(nationalId.trim().length() > 0);
    this.nationalId = nationalId.trim();
  }

  @Override
  public String getNationalId() {
    return nationalId;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof NationalId) {
      NationalId id = (NationalId) o;
      return Objects.equal(getNationalId(), id.getNationalId());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getNationalId());
  }

  @Override
  public String toString() {
    return getNationalId();
  }

  @Override
  public int compareTo(NationalId arg0) {
    return getNationalId().compareTo(arg0.getNationalId());
  }

}
