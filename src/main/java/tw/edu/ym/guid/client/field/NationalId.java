package tw.edu.ym.guid.client.field;

/**
 * 
 * NationalId defines a common method of upon all National IDs.
 * 
 * @author Wei-Ming Wu
 * 
 */
public interface NationalId extends Comparable<NationalId> {

  /**
   * Returns a National ID.
   * 
   * @return a National ID
   */
  String getNationalId();

}
