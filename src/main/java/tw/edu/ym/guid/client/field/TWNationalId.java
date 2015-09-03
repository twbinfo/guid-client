package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkArgument;
import static tw.edu.ym.guid.client.hashcode.Field.giid;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import tw.edu.ym.guid.client.annotation.Factor;
import wmw.validate.TWNationalIdValidator;

/**
 * 
 * NationalId is a required field of GUID.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class TWNationalId implements NationalId {

  private final String nationalId;

  /**
   * Creates a NationalId.
   * 
   * @param nationalId
   *          a National ID of Taiwan(R.O.C)
   */
  public TWNationalId(String nationalId) {
    checkArgument(TWNationalIdValidator.validate(nationalId),
        "NationalId is invalid.");
    this.nationalId = nationalId;
  }

  /**
   * Returns a National ID.
   * 
   * @return a National ID
   */
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
