package tw.edu.ym.guid.client;

import java.util.List;

import tw.edu.ym.guid.client.field.Birthday;
import tw.edu.ym.guid.client.field.Name;
import tw.edu.ym.guid.client.field.NationalId;
import tw.edu.ym.guid.client.field.Sex;

import com.google.common.base.Objects;

/**
 * 
 * PII contains a patient's personal information and generates hashcodes by
 * them.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class PII {

  private final Name name;
  private final Sex sex;
  private final Birthday birthday;
  private final NationalId nationalId;

  /**
   * Creates a PII.
   * 
   * @param name
   * @param sex
   * @param birthday
   * @param nationalId
   */
  public PII(Name name, Sex sex, Birthday birthday, NationalId nationalId) {
    this.name = name;
    this.sex = sex;
    this.birthday = birthday;
    this.nationalId = nationalId;
  }

  /**
   * Returns hashcodes based on this PII.
   * 
   * @return hashcodes
   */
  public List<String> getHashcodes() {
    return HashcodeBuilder.build(name, sex, birthday, nationalId);
  }

  /**
   * Returns the full name of this patient
   * 
   * @return the full name of this patient
   */
  public Name getName() {
    return name;
  }

  /**
   * Returns the sex of this patient
   * 
   * @return the sex of this patient
   */
  public Sex getSex() {
    return sex;
  }

  /**
   * Returns the birthday of this patient
   * 
   * @return the birthday of this patient
   */
  public Birthday getBirthday() {
    return birthday;
  }

  /**
   * Returns the National ID of this patient
   * 
   * @return the National ID of this patient
   */
  public NationalId getNationalId() {
    return nationalId;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this.getClass()).add("Name", name)
        .add("Sex", sex).add("Birthday", birthday)
        .add("NationalId", nationalId).toString();
  }

}
