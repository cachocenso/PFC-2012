/**
 * 
 */
package edu.uoc.pfc.formwork.infraestructura;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación para inyectar la sesión HTTP en las clases controladoras
 * del framework. Dado un objeto @see IController el framework inyectará
 * la sesión en el atributo marcado con la anotacion @Session.
 * 
 * @author Alberto Díaz en 23/11/2012
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Session {

}
