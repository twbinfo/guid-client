package tw.edu.ym.guid.client;

import java.util.List;

import tw.edu.ym.guid.client.field.Birthday;
import tw.edu.ym.guid.client.field.Name;
import tw.edu.ym.guid.client.field.NationalId;
import tw.edu.ym.guid.client.field.Sex;

import com.google.common.base.Objects;

public final class PII {

  private final Name name;
  private final Sex sex;
  private final Birthday birthday;
  private final NationalId nationalId;

  public PII(Name name, Sex sex, Birthday birthday, NationalId nationalId) {
    this.name = name;
    this.sex = sex;
    this.birthday = birthday;
    this.nationalId = nationalId;
  }

  public List<String> getHashcodes() {
    return HashcodeBuilder.build(name, sex, birthday, nationalId);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this.getClass()).add("Name", name)
        .add("Sex", sex).add("Birthday", birthday)
        .add("NationalId", nationalId).toString();
  }

}
