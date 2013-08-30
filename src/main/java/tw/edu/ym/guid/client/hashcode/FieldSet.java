package tw.edu.ym.guid.client.hashcode;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.unmodifiableList;

import java.util.List;

import com.google.common.base.Objects;

/**
 * 
 * FieldSet groups Fields into a set.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class FieldSet {

  private final List<Field> fields;

  /**
   * Creates a FieldSet.
   * 
   * @param field
   *          a Field enum
   * @return a FieldSet
   */
  public static FieldSet single(Field field) {
    return new FieldSet(field);
  }

  /**
   * Creates a FieldSet.
   * 
   * @param fields
   *          Array of Field enum
   * @return a FieldSet
   */
  public static FieldSet combo(Field field1, Field field2, Field... fields) {
    return new FieldSet(field1, field2, fields);
  }

  private FieldSet(Field field1, Field field2, Field... fields) {
    List<Field> f = newArrayList(checkNotNull(field1), checkNotNull(field2));
    for (Field field : fields)
      f.add(checkNotNull(field));
    this.fields = f;
  }

  private FieldSet(Field field) {
    this.fields = newArrayList(checkNotNull(field));
  }

  /**
   * Returns a List of Field
   * 
   * @return a List of Field
   */
  public List<Field> getFields() {
    return unmodifiableList(fields);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof FieldSet) {
      FieldSet fs = (FieldSet) o;
      return Objects.equal(fields, fs.fields);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(fields);
  }

  @Override
  public String toString() {
    return fields.toString();
  }

}
