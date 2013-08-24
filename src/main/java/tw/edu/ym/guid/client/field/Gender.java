package tw.edu.ym.guid.client.field;

public enum Gender {

  MALE("M"), FEMALE("F");

  private String gender;

  private Gender(String gender) {
    this.gender = gender;
  }

  @Override
  public String toString() {
    return gender;
  }

}
