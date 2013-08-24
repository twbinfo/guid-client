package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkArgument;
import tw.edu.ym.guid.client.util.NationalIdValidator;

public final class NationalId {

  private final String nationalId;

  public NationalId(String nationalId) {
    validate(nationalId);
    this.nationalId = nationalId;
  }

  private void validate(String nationalId) {
    checkArgument(NationalIdValidator.INSTANCE.validate(nationalId));
  }

  public String getNationalId() {
    return nationalId;
  }

  @Override
  public String toString() {
    return nationalId;
  }

}
