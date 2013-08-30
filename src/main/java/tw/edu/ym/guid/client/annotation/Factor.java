package tw.edu.ym.guid.client.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import tw.edu.ym.guid.client.hashcode.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Factor {

  Field field();

}
