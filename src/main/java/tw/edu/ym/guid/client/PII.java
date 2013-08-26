package tw.edu.ym.guid.client;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import tw.edu.ym.guid.client.field.Birthday;
import tw.edu.ym.guid.client.field.Birthplace;
import tw.edu.ym.guid.client.field.Name;
import tw.edu.ym.guid.client.field.NationalId;
import tw.edu.ym.guid.client.field.Nationality;
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
public final class PII implements Comparable<PII> {

  private final Name name;
  private final Sex sex;
  private final Birthday birthday;
  private final NationalId nationalId;
  private final Birthplace birthplace;
  private final Nationality nationality;

  public static class Builder {

    private final Name name;
    private final Sex sex;
    private final Birthday birthday;
    private final NationalId nationalId;
    private Birthplace birthplace;
    private Nationality nationality;

    public Builder(Name name, Sex sex, Birthday birthday, NationalId nationalId) {
      this.name = checkNotNull(name);
      this.sex = checkNotNull(sex);
      this.birthday = checkNotNull(birthday);
      this.nationalId = checkNotNull(nationalId);
    }

    public Builder birthplace(Birthplace birthplace) {
      this.birthplace = birthplace;
      return this;
    }

    public Builder nationality(Nationality nationality) {
      this.nationality = nationality;
      return this;
    }

    public PII build() {
      return new PII(this);
    }

  }

  private PII(Builder builder) {
    this.name = builder.name;
    this.sex = builder.sex;
    this.birthday = builder.birthday;
    this.nationalId = builder.nationalId;
    this.birthplace = builder.birthplace;
    this.nationality = builder.nationality;
  }

  /**
   * Returns hashcodes based on this PII.
   * 
   * @return hashcodes
   */
  public List<String> getHashcodes() {
    return new Hashcode.Builder(name, sex, birthday, nationalId)
        .birthplace(birthplace).nationality(nationality).build().compute();
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
  public boolean equals(Object o) {
    if (o instanceof PII) {
      PII pii = (PII) o;
      return Objects.equal(name, pii.name) && Objects.equal(sex, pii.sex)
          && Objects.equal(birthday, pii.birthday)
          && Objects.equal(nationalId, pii.nationalId);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name, sex, birthday, nationalId);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this.getClass()).add("Name", name)
        .add("Sex", sex).add("Birthday", birthday)
        .add("NationalId", nationalId).toString();
  }

  @Override
  public int compareTo(PII o) {
    int diff = 0;
    if ((diff = name.compareTo(o.name)) != 0)
      return diff;
    if ((diff = sex.compareTo(o.sex)) != 0)
      return diff;
    if ((diff = birthday.compareTo(o.birthday)) != 0)
      return diff;
    if ((diff = nationalId.compareTo(o.nationalId)) != 0)
      return diff;
    return 0;
  }

}
