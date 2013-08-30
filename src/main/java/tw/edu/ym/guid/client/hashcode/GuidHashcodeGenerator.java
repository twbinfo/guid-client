package tw.edu.ym.guid.client.hashcode;

import static com.google.common.collect.Lists.newArrayList;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import tw.edu.ym.guid.client.annotation.FactorExtractor;

/**
 * 
 * GuidHashcodeGenerator computes required hashcodes for GUID server to use.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class GuidHashcodeGenerator {

  private GuidHashcodeGenerator() {}

  /**
   * Returns hashcodes based on the input Objects.
   * 
   * @param objects
   *          an Array of Object
   * @return a List of hashcodes
   */
  public static List<String> compute(Object... objects) {
    if (newArrayList(objects).contains(null))
      throw new NullPointerException("Null argument is not allowed.");

    List<String> hashcodes = newArrayList();
    Map<Field, Object> factors = FactorExtractor.extract(objects);
    for (Definition def : Definition.values()) {
      StringBuilder sb = new StringBuilder("_");
      for (FieldSet fs : def.getDefinition()) {
        for (Field field : fs.getFields()) {
          sb.append(factors.get(field));
        }
        sb.append("_");
      }
      hashcodes.add(SHA520(sb.toString()));
    }
    return hashcodes;
  }

  private static String SHA520(String txt) {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("SHA-512");
    } catch (NoSuchAlgorithmException e) {
      Logger.getLogger(GuidHashcodeGenerator.class.getName()).log(Level.SEVERE,
          null, e);
    }
    byte[] digest = md.digest(txt.getBytes());
    return String.format("%0128x", new BigInteger(1, digest)) + "00";
  }

}
