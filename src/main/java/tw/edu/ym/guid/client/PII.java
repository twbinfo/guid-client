package tw.edu.ym.guid.client;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import tw.edu.ym.guid.client.field.Birthday;
import tw.edu.ym.guid.client.field.Birthplace;
import tw.edu.ym.guid.client.field.Name;
import tw.edu.ym.guid.client.field.NameSplitter;
import tw.edu.ym.guid.client.field.NationalId;
import tw.edu.ym.guid.client.field.Nationality;
import tw.edu.ym.guid.client.field.Sex;
import tw.edu.ym.guid.client.hashcode.GuidHashcodeGenerator;

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

  /**
   * 
   * PII.Builder is designed to build a PII.
   * 
   * @author Wei-Ming Wu
   * 
   */
  public static class Builder {

    private final Name name;
    private final Sex sex;
    private final Birthday birthday;
    private final NationalId nationalId;
    private Birthplace birthplace;
    private Nationality nationality;

    /**
     * Creates a PII.Builder.
     * 
     * @param name
     * @param sex
     * @param birthday
     * @param nationalId
     */
    public Builder(Name name, Sex sex, Birthday birthday,
        NationalId nationalId) {
      this.name = checkNotNull(name, "Name can't be null.");
      this.sex = checkNotNull(sex, "Sex can't be null.");
      this.birthday = checkNotNull(birthday, "Birthday can't be null.");
      this.nationalId = checkNotNull(nationalId, "NationalId can't be null.");
    }

    /**
     * Creates a PII.Builder.
     * 
     * @param name
     * @param sex
     * @param birthday
     * @param nationalId
     */
    public Builder(String fullname, Sex sex, Birthday birthday,
        NationalId nationalId) {
      this.name = NameSplitter.split(fullname);
      this.sex = checkNotNull(sex, "Sex can't be null.");
      this.birthday = checkNotNull(birthday, "Birthday can't be null.");
      this.nationalId = checkNotNull(nationalId, "NationalId can't be null.");
    }

    /**
     * Adds Birthplace to this Builder.
     * 
     * @param birthplace
     * @return this Builder
     */
    public Builder birthplace(Birthplace birthplace) {
      this.birthplace = birthplace;
      return this;
    }

    /**
     * Adds Nationality to this Builder.
     * 
     * @param birthplace
     * @return this Builder
     */
    public Builder nationality(Nationality nationality) {
      this.nationality = nationality;
      return this;
    }

    /**
     * Creates a PII.
     * 
     * @return a PII
     */
    public PII build() {
      return new PII(this);
    }

  }

  private PII(Builder builder) {
    this.name = builder.name;
    this.sex = builder.sex;
    this.birthday = builder.birthday;
    this.nationalId = builder.nationalId;
    this.birthplace = builder.birthplace == null ? Birthplace.getDefault()
        : builder.birthplace;
    this.nationality = builder.nationality == null ? Nationality.getDefault()
        : builder.nationality;
  }

  /**
   * Returns hashcodes for GUID.
   * 
   * @return hashcodes
   */
  public List<String> getHashcodes() {
    return GuidHashcodeGenerator.compute(name, sex, birthday, nationalId,
        birthplace, nationality);
  }

  /**
   * Returns the full name of this patient.
   * 
   * @return the full name of this patient
   */
  public Name getName() {
    return name;
  }

  /**
   * Returns the sex of this patient.
   * 
   * @return the sex of this patient
   */
  public Sex getSex() {
    return sex;
  }

  /**
   * Returns the birthday of this patient
   * 
   * @return the birthday of this patient.
   */
  public Birthday getBirthday() {
    return birthday;
  }

  /**
   * Returns the National ID of this patient.
   * 
   * @return the National ID of this patient
   */
  public NationalId getNationalId() {
    return nationalId;
  }

  /**
   * Returns the Birthplace of this patient.
   * 
   * @return the Birthplace of this patient
   */
  public Birthplace getBirthplace() {
    return birthplace;
  }

  /**
   * Returns the Nationality of this patient.
   * 
   * @return the Nationality of this patient
   */
  public Nationality getNationality() {
    return nationality;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof PII)) return false;
    PII castOther = (PII) other;
    return Objects.equal(name, castOther.name)
        && Objects.equal(sex, castOther.sex)
        && Objects.equal(birthday, castOther.birthday)
        && Objects.equal(nationalId, castOther.nationalId)
        && Objects.equal(birthplace, castOther.birthplace)
        && Objects.equal(nationality, castOther.nationality);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(name, sex, birthday, nationalId, birthplace,
        nationality);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this.getClass()).add("Name", name)
        .add("Sex", sex).add("Birthday", birthday).add("NationalId", nationalId)
        .add("Birthplace", birthplace).addValue(nationality).toString();
  }

  @Override
  public int compareTo(PII that) {
    return ComparisonChain.start().compare(this.name, that.name)
        .compare(this.sex, that.sex).compare(this.birthday, that.birthday)
        .compare(this.nationalId, that.nationalId)
        .compare(this.birthplace, that.birthplace)
        .compare(this.nationality, that.nationality).result();
  }

}
