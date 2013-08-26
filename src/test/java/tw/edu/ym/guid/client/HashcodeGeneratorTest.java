package tw.edu.ym.guid.client;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tw.edu.ym.guid.client.field.Birthday;
import tw.edu.ym.guid.client.field.Birthplace;
import tw.edu.ym.guid.client.field.Name;
import tw.edu.ym.guid.client.field.Nationality;
import tw.edu.ym.guid.client.field.Sex;
import tw.edu.ym.guid.client.field.TWNationalId;

public class HashcodeGeneratorTest {

  private Name name;
  private Sex sex;
  private Birthday birthday;
  private TWNationalId nationalId;
  private Birthplace birthplace;
  private Nationality nationality;

  @Before
  public void setUp() throws Exception {
    name = new Name("mj", "li");
    sex = Sex.MALE;
    birthday = new Birthday(1979, 7, 21);
    nationalId = new TWNationalId("E122371585");
    birthplace = Birthplace.getDefault();
    nationality = Nationality.getDefault();
  }

  @Test
  public void testBuild() {
    List<String> hashcodes =
        HashcodeGenerator.compute(name, sex, birthday, nationalId, birthplace,
            nationality);
    assertEquals(3, hashcodes.size());
    assertEquals(
        "f3daf55c7999e106cebb8733d24a8baa25b1a684154d601de5398cabde4d2da50072215f81ab0879f59ae29551b0442cbef37dd35931757f8745ca3d455caa9500",
        hashcodes.get(0));
    assertEquals(
        "67d8e811a0e028f65047d4f76f1728c4323757caf9a8cc0035fa9e6743e190137fca233c0322e17db8f6a3f045225d5f36dbc2d93a39a0f176c6a6515060fab600",
        hashcodes.get(1));
    assertEquals(
        "631b789d57692ee913d59d59375a6045cbe194e6d8d896e4007ce4a39b6dd8446b6b5f08a0b088751a9566556040511917791dfeb70ac612a54b2106f907ea1400",
        hashcodes.get(2));
  }

}
