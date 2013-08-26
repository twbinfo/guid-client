package tw.edu.ym.guid.client;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import tw.edu.ym.guid.client.field.Birthday;
import tw.edu.ym.guid.client.field.Birthplace;
import tw.edu.ym.guid.client.field.Name;
import tw.edu.ym.guid.client.field.NationalId;
import tw.edu.ym.guid.client.field.Nationality;
import tw.edu.ym.guid.client.field.Sex;

/**
 * 
 * HashcodeBuilder builds hashcodes for GUID server based on given demographical
 * information.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class Hashcode {

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

    public Hashcode build() {
      return new Hashcode(this);
    }

  }

  private Hashcode(Builder builder) {
    this.name = builder.name;
    this.sex = builder.sex;
    this.birthday = builder.birthday;
    this.nationalId = builder.nationalId;
    this.birthplace = builder.birthplace;
    this.nationality = builder.nationality;
  }

  /**
   * Computes hashcodes based on given demographical information.
   * 
   * @param name
   * @param sex
   * @param birthday
   * @param nationalId
   * @return hashcodes
   */
  public List<String> compute() {
    String str1 =
        "_" + birthday.getYearOfBirth() + "_" + birthday.getDayOfBirth() + "_"
            + sex.getSex() + "_" + getNationality()
            + nationalId.getNationalId() + "_";
    String str2 =
        "_" + name.getFirstName() + "_" + name.getLastName() + "_"
            + name.getMiddleName() + "_" + getNationalityOfBirth()
            + getBirthplace() + "_" + birthday.getDayOfBirth() + "_"
            + birthday.getMonthOfBirth() + "_" + sex.getSex() + "_"
            + getNationality() + "_";
    String str3 =
        "_" + name.getFirstName() + "_" + name.getLastName() + "_"
            + birthday.getYearOfBirth() + "_" + getNationalityOfBirth()
            + getBirthplace() + "_" + birthday.getDayOfBirth() + "_"
            + birthday.getMonthOfBirth() + "_" + getNationality() + "_";

    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("SHA-512");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    byte[] digest1 = md.digest(str1.getBytes());
    byte[] digest2 = md.digest(str2.getBytes());
    byte[] digest3 = md.digest(str3.getBytes());

    String hashcode1 =
        String.format("%0128x", new BigInteger(1, digest1)) + "00";
    String hashcode2 =
        String.format("%0128x", new BigInteger(1, digest2)) + "00";
    String hashcode3 =
        String.format("%0128x", new BigInteger(1, digest3)) + "00";

    return newArrayList(hashcode1, hashcode2, hashcode3);
  }

  private String getBirthplace() {
    return birthplace == null ? "TW" : birthplace.getBirthplace().toString();
  }

  private String getNationality() {
    return nationality == null ? "TW" : nationality.getNationality().toString();
  }

  private String getNationalityOfBirth() {
    return nationality == null ? "TW" : nationality.getNationalityOfBirth()
        .toString();
  }

}
