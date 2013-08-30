package tw.edu.ym.guid.client.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import tw.edu.ym.guid.client.hashcode.Field;

/**
 * 
 * FactorExtractor extracts factors from annotated methods of input Objects.
 * 
 * @author Wei-Ming Wu
 * 
 */
public final class FactorExtractor {

  private FactorExtractor() {}

  /**
   * Returns a Map of Field-Value(Object) which is extracted from annotated
   * methods of input Objects.
   * 
   * @param objects
   *          an Array of Object
   * @return a Map of Field-Value
   */
  public static Map<Field, Object> extract(Object... objects) {
    for (Object o : objects) {
      if (o == null)
        throw new NullPointerException("Null argument is not allowed.");
    }

    Map<Field, Object> factors = new HashMap<Field, Object>();
    for (Object o : objects) {
      @SuppressWarnings("rawtypes")
      Class testClass = o.getClass();
      for (Method m : testClass.getDeclaredMethods()) {
        if (m.isAnnotationPresent(Factor.class)) {
          Factor factor = m.getAnnotation(Factor.class);
          try {
            factors.put(factor.field(), m.invoke(o));
          } catch (InvocationTargetException wrappedExc) {
            Throwable exc = wrappedExc.getCause();
            Logger.getLogger(FactorExtractor.class.getName()).log(Level.SEVERE,
                m + " failed: " + exc, wrappedExc);
          } catch (Exception exc) {
            Logger.getLogger(FactorExtractor.class.getName()).log(Level.SEVERE,
                "INVALID @Factor: " + m + exc, exc);
          }
        }
      }
    }
    return factors;
  }

}
