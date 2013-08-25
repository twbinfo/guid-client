package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkArgument;
import wmw.validate.NationalIdValidator;

import com.google.common.base.Objects;

/**
 * 
 * NationalId is a required field of GUID.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class NationalId implements Comparable<NationalId> {

  private final String nationalId;

  /**
   * Creates a NationalId.
   * 
   * @param nationalId
   *          a National ID of Taiwan(R.O.C)
   */
  public NationalId(String nationalId) {
    validate(nationalId);
    this.nationalId = nationalId;
  }

  private void validate(String nationalId) {
    checkArgument(NationalIdValidator.validate(nationalId));
  }

  /**
   * Returns a National ID.
   * 
   * @return a National ID
   */
  public String getNationalId() {
    return nationalId;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof NationalId) {
      NationalId id = (NationalId) o;
      return Objects.equal(nationalId, id.nationalId);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(nationalId);
  }

  @Override
  public String toString() {
    return nationalId;
  }

  @Override
  public int compareTo(NationalId o) {
    return nationalId.compareTo(o.nationalId);
  }

}
