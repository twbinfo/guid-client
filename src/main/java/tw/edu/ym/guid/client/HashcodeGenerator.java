package tw.edu.ym.guid.client;

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
 * HashcodeGenerator computes hashcodes for GUID server based on given
 * demographical information.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class HashcodeGenerator {

  private HashcodeGenerator() {};

  public static List<String> compute(Name name, Sex sex, Birthday birthday,
      NationalId nationalId, Birthplace birthplace, Nationality nationality) {
    String str1 =
        "_" + birthday.getYearOfBirth() + "_" + birthday.getDayOfBirth() + "_"
            + sex.getSex() + "_" + nationality.getNationality()
            + nationalId.getNationalId() + "_";
    String str2 =
        "_" + name.getFirstName() + "_" + name.getLastName() + "_"
            + name.getMiddleName() + "_" + nationality.getNationalityOfBirth()
            + birthplace.getBirthplace() + "_" + birthday.getDayOfBirth() + "_"
            + birthday.getMonthOfBirth() + "_" + sex.getSex() + "_"
            + nationality.getNationality() + "_";
    String str3 =
        "_" + name.getFirstName() + "_" + name.getLastName() + "_"
            + birthday.getYearOfBirth() + "_"
            + nationality.getNationalityOfBirth() + birthplace.getBirthplace()
            + "_" + birthday.getDayOfBirth() + "_" + birthday.getMonthOfBirth()
            + "_" + nationality.getNationality() + "_";

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

}
