package tw.edu.ym.guid.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import tw.edu.ym.guid.client.field.Birthday;
import tw.edu.ym.guid.client.field.Name;
import tw.edu.ym.guid.client.field.Sex;
import tw.edu.ym.guid.client.field.TWNationalId;

public class PIITest {

  private PII pii;
  private Name name;
  private Sex sex;
  private Birthday birthday;
  private TWNationalId nationalId;

  @Before
  public void setUp() throws Exception {
    name = new Name("mj", "li");
    sex = Sex.MALE;
    birthday = new Birthday(1979, 7, 21);
    nationalId = new TWNationalId("E122371585");
    pii = new PII.Builder(name, sex, birthday, nationalId).build();
  }

  @Test
  public void testConstructor() {
    assertTrue(pii instanceof PII);
  }

  @Test
  public void testGetHashcodes() {
    assertEquals(new Hashcode.Builder(name, sex, birthday, nationalId).build()
        .compute(), pii.getHashcodes());
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
  public void testEquals() {
    assertTrue(pii.equals(new PII.Builder(name, sex, birthday, nationalId)
        .build()));
    assertFalse(pii.equals(new PII.Builder(new Name("a", "b"), sex, birthday,
        nationalId).build()));
    assertFalse(pii.equals(new PII.Builder(name, Sex.FEMALE, birthday,
        nationalId)));
    assertFalse(pii.equals(new PII.Builder(name, sex,
        new Birthday(1980, 7, 21), nationalId).build()));
    assertFalse(pii.equals(new PII.Builder(name, sex, birthday,
        new TWNationalId("A123456789")).build()));
    assertFalse(pii.equals(null));
  }

  @Test
  public void testHashCode() {
    assertEquals(pii.hashCode(), new PII.Builder(name, sex, birthday,
        nationalId).build().hashCode());
    assertNotEquals(pii.hashCode(), new PII.Builder(name, Sex.FEMALE, birthday,
        nationalId).build().hashCode());
  }

  @Test
  public void testToString() {
    assertEquals(
        "PII{Name=MJ LI, Sex=M, Birthday=1979/07/21, NationalId=E122371585}",
        pii.toString());
  }

  @Test
  public void testCompareTo() {
    assertTrue(pii.compareTo(new PII.Builder(new Name("a", "b"), sex, birthday,
        nationalId).build()) > 0);
    assertTrue(pii.compareTo(new PII.Builder(name, Sex.FEMALE, birthday,
        nationalId).build()) > 0);
    assertTrue(pii.compareTo(new PII.Builder(name, sex, new Birthday(1980, 7,
        21), nationalId).build()) < 0);
    assertTrue(pii.compareTo(new PII.Builder(name, sex, birthday,
        new TWNationalId("A123456789")).build()) > 0);
    assertTrue(pii.compareTo(new PII.Builder(name, sex, birthday, nationalId)
        .build()) == 0);
  }

}
