/**
 * 
 */
package edu.dnaprocessing;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

@Retention(RUNTIME)
@Documented
/**
 * Marker annotation for those REST controllers what should be advised
 * by the global DNAtorControllerAdvice
 */
public @interface ExceptionHandling {

}
