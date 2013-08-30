package tw.edu.ym.guid.client.annotation;

import static org.junit.Assert.assertEquals;
import static tw.edu.ym.guid.client.field.Sex.MALE;
import static tw.edu.ym.guid.client.hashcode.Field.fn;
import static tw.edu.ym.guid.client.hashcode.Field.ln;
import static tw.edu.ym.guid.client.hashcode.Field.sex;

import java.util.Map;

import org.junit.Test;

import tw.edu.ym.guid.client.field.Name;
import tw.edu.ym.guid.client.hashcode.Field;

public class FactorExtractorTest {

  @Test
  public void testExtract() {
    Map<Field, Object> factors =
        FactorExtractor.extract(new Name("MJ", "LI"), MALE);
    assertEquals(factors.get(fn), "MJ");
    assertEquals(factors.get(ln), "LI");
    assertEquals(factors.get(sex), "M");
  }

  @Test(expected = NullPointerException.class)
  public void testExtractException() {
    FactorExtractor.extract(MALE, null);
  }

}
