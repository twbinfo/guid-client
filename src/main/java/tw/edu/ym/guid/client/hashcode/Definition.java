package tw.edu.ym.guid.client.hashcode;

import static tw.edu.ym.guid.client.hashcode.Field.cnob;
import static tw.edu.ym.guid.client.hashcode.Field.cob;
import static tw.edu.ym.guid.client.hashcode.Field.dob;
import static tw.edu.ym.guid.client.hashcode.Field.fn;
import static tw.edu.ym.guid.client.hashcode.Field.giid;
import static tw.edu.ym.guid.client.hashcode.Field.giidc;
import static tw.edu.ym.guid.client.hashcode.Field.ln;
import static tw.edu.ym.guid.client.hashcode.Field.mn;
import static tw.edu.ym.guid.client.hashcode.Field.mob;
import static tw.edu.ym.guid.client.hashcode.Field.sex;
import static tw.edu.ym.guid.client.hashcode.Field.yob;
import static tw.edu.ym.guid.client.hashcode.FieldSet.combo;
import static tw.edu.ym.guid.client.hashcode.FieldSet.single;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * 
 * Definition enum lists all definitions of hashcodes that GUID required.
 * 
 * @author Wei-Ming Wu
 * 
 */
public enum Definition {

  HASHCODE1(single(yob), single(dob), single(sex), combo(giidc, giid)),
  HASHCODE2(single(fn), single(ln), single(mn), combo(cnob, cob), //
      single(dob), single(mob), single(sex), single(giidc)), //
  HASHCODE3(single(fn), single(ln), single(yob), combo(cnob, cob), single(dob),
      single(mob), single(giidc));

  private List<FieldSet> definition;

  private Definition(FieldSet... fieldSets) {
    definition = ImmutableList.copyOf(fieldSets);
  }

  public List<FieldSet> getDefinition() {
    return definition;
  }

}
