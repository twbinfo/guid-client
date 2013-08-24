package tw.edu.ym.guid.client;

public enum RequiredField implements Field {

  FIRST_NAME("fn"), LAST_NAME("ln"), YEAR_OF_BIRTH("yob"),
  MONTH_OF_BIRTH("mob"), DAY_OF_BIRTH("dob"), NATIONAL_ID("giid");

  private final String fieldId;

  private RequiredField(String fieldId) {
    this.fieldId = fieldId;
  }

  public String getFieldId() {
    return fieldId;
  }

}
