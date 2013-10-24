package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkArgument;
import static tw.edu.ym.guid.client.hashcode.Field.giid;
import tw.edu.ym.guid.client.annotation.Factor;
import wmw.validate.TWNationalIdValidator;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

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
