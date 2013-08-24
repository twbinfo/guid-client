package tw.edu.ym.guid.client;

import static tw.edu.ym.guid.client.OptionalField.CONUNTRY_OF_BIRTH;
import static tw.edu.ym.guid.client.OptionalField.MIDDLE_NAME;
import static tw.edu.ym.guid.client.OptionalField.NATIONALITY;
import static tw.edu.ym.guid.client.OptionalField.NATIONALITY_OF_BIRTH;
import static tw.edu.ym.guid.client.OptionalField.SEX;
import static tw.edu.ym.guid.client.RequiredField.DAY_OF_BIRTH;
import static tw.edu.ym.guid.client.RequiredField.FIRST_NAME;
import static tw.edu.ym.guid.client.RequiredField.LAST_NAME;
import static tw.edu.ym.guid.client.RequiredField.MONTH_OF_BIRTH;
import static tw.edu.ym.guid.client.RequiredField.NATIONAL_ID;
import static tw.edu.ym.guid.client.RequiredField.YEAR_OF_BIRTH;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public enum Hashes {

  HASHCODE1(new Field[] { YEAR_OF_BIRTH }, new Field[] { DAY_OF_BIRTH },
      new Field[] { SEX }, new Field[] { NATIONALITY, NATIONAL_ID }),
  HASHCODE2(new Field[] { FIRST_NAME }, new Field[] { LAST_NAME },
      new Field[] { MIDDLE_NAME }, new Field[] { NATIONALITY_OF_BIRTH,
          CONUNTRY_OF_BIRTH }, new Field[] { DAY_OF_BIRTH },
      new Field[] { MONTH_OF_BIRTH }, new Field[] { SEX },
      new Field[] { NATIONALITY }), HASHCODE3(new Field[] { FIRST_NAME },
      new Field[] { LAST_NAME }, new Field[] { YEAR_OF_BIRTH }, new Field[] {
          NATIONALITY_OF_BIRTH, CONUNTRY_OF_BIRTH },
      new Field[] { DAY_OF_BIRTH }, new Field[] { MONTH_OF_BIRTH },
      new Field[] { NATIONALITY });

  private final List<List<Field>> fields;

  private Hashes(Field[]... fieldGroups) {
    List<List<Field>> fields = Lists.newArrayList();
    for (Field[] group : fieldGroups)
      fields.add(ImmutableList.copyOf(group));
    this.fields = ImmutableList.copyOf(fields);
  }

  public List<List<Field>> getFields() {
    return fields;
  }

}
