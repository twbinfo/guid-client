package tw.edu.ym.guid.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import tw.edu.ym.guid.client.field.Birthday;
import tw.edu.ym.guid.client.field.Name;
import tw.edu.ym.guid.client.field.NationalId;
import tw.edu.ym.guid.client.field.Sex;

public class PIITest {

  private PII pii;
  private Name name;
  private Sex sex;
  private Birthday birthday;
  private NationalId nationalId;

  @Before
  public void setUp() throws Exception {
    name = new Name("mj", "li");
    sex = Sex.MALE;
    birthday = new Birthday(1979, 7, 21);
    nationalId = new NationalId("E122371585");
    pii = new PII(name, sex, birthday, nationalId);
  }

  @Test
  public void testConstructor() {
    assertTrue(pii instanceof PII);
  }

  @Test
  public void testGetHashcodes() {
    assertEquals(HashcodeBuilder.build(name, sex, birthday, nationalId),
        pii.getHashcodes());
  }

  @Test
  public void testGetName() {
    assertTrue(name == pii.getName());
  }

  @Test
  public void testGetSex() {
    assertTrue(sex == pii.getSex());
  }

  @Test
  public void testGetBirthday() {
    assertTrue(birthday == pii.getBirthday());
  }

  @Test
  public void testGetNationalId() {
    assertTrue(nationalId == pii.getNationalId());
  }

  @Test
  public void testToString() {
    assertEquals(
        "PII{Name=MJ LI, Sex=M, Birthday=1979/07/21, NationalId=E122371585}",
        pii.toString());
  }

}
