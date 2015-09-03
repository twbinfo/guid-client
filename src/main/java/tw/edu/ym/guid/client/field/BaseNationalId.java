package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static tw.edu.ym.guid.client.hashcode.Field.giid;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import tw.edu.ym.guid.client.annotation.Factor;

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
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof NationalId)) return false;
    NationalId castOther = (NationalId) other;
    return Objects.equal(nationalId, castOther.getNationalId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(nationalId);
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
