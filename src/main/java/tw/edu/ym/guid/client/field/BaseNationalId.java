package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static tw.edu.ym.guid.client.hashcode.Field.giid;
import tw.edu.ym.guid.client.annotation.Factor;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

public class BaseNationalId implements NationalId {

  private final String nationalId;

  public BaseNationalId(String nationalId) {
    checkNotNull(nationalId, "NationalId can't be null.");
    checkArgument(nationalId.trim().length() > 0, "NationalId can't be empty.");
    this.nationalId = nationalId.trim();
  }

  @Factor(field = giid)
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
  public int compareTo(NationalId that) {
    return ComparisonChain.start()
        .compare(getNationalId(), that.getNationalId()).result();
  }

}
