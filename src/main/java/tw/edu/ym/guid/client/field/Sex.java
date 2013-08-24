package tw.edu.ym.guid.client.field;

public class Sex {

  private Gender sex;

  public Sex(Gender sex) {
    this.sex = sex;
  }

  public String getSex() {
    return toString();
  }

  @Override
  public String toString() {
    return sex == null ? Gender.FEMALE.toString() : sex.toString();
  }

}
