package tw.edu.ym.guid.client;

public enum OptionalField implements Field {

  SEX("sex", "F"), MIDDLE_NAME("mn", "NOTAPPLICABLE"), NATIONALITY("giidc",
      "TW"), CONUNTRY_OF_BIRTH("cob", "TW"), NATIONALITY_OF_BIRTH("cnob", "TW");

  private final String fieldId;
  private final String defaultValue;

  private OptionalField(String fieldId, String defaultValue) {
    this.fieldId = fieldId;
    this.defaultValue = defaultValue;
  }

  public String getFieldId() {
    return fieldId;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

}
