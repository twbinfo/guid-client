package tw.edu.ym.guid.client.field;

import static com.google.common.base.Preconditions.checkArgument;

public final class Birthday {

  private final int yearOfBirth;
  private final int monthOfBirth;
  private final int dayOfBirth;

  public Birthday(int yearOfBirth, int monthOfBirth, int dayOfBirth) {
    validate(yearOfBirth, monthOfBirth, dayOfBirth);
    this.yearOfBirth = yearOfBirth;
    this.monthOfBirth = monthOfBirth;
    this.dayOfBirth = dayOfBirth;
  }

  private void validate(int yearOfBirth, int monthOfBirth, int dayOfBirth) {
    checkArgument(yearOfBirth >= 1910 && yearOfBirth <= 2100);
    checkArgument(monthOfBirth >= 1 && monthOfBirth <= 12);
    switch (monthOfBirth) {
    case 1:
      checkArgument(dayOfBirth >= 1 && dayOfBirth <= 31);
      break;
    case 2:
      if (yearOfBirth % 4 == 0)
        checkArgument(dayOfBirth >= 1 && dayOfBirth <= 29);
      else
        checkArgument(dayOfBirth >= 1 && dayOfBirth <= 28);
      break;
    case 3:
      checkArgument(dayOfBirth >= 1 && dayOfBirth <= 31);
      break;
    case 4:
      checkArgument(dayOfBirth >= 1 && dayOfBirth <= 30);
      break;
    case 5:
      checkArgument(dayOfBirth >= 1 && dayOfBirth <= 31);
      break;
    case 6:
      checkArgument(dayOfBirth >= 1 && dayOfBirth <= 30);
      break;
    case 7:
      checkArgument(dayOfBirth >= 1 && dayOfBirth <= 31);
      break;
    case 8:
      checkArgument(dayOfBirth >= 1 && dayOfBirth <= 31);
      break;
    case 9:
      checkArgument(dayOfBirth >= 1 && dayOfBirth <= 30);
      break;
    case 10:
      checkArgument(dayOfBirth >= 1 && dayOfBirth <= 31);
      break;
    case 11:
      checkArgument(dayOfBirth >= 1 && dayOfBirth <= 30);
      break;
    case 12:
      checkArgument(dayOfBirth >= 1 && dayOfBirth <= 31);
      break;
    default:
      throw new IllegalArgumentException();
    }
  }

  public int getYearOfBirth() {
    return yearOfBirth;
  }

  public int getMonthOfBirth() {
    return monthOfBirth;
  }

  public int getDayOfBirth() {
    return dayOfBirth;
  }

  @Override
  public String toString() {
    return yearOfBirth + "/" + "monthOfBirth" + "/" + dayOfBirth;
  }

}
