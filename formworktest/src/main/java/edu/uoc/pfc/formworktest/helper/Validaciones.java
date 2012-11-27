/**
 * 
 */
package edu.uoc.pfc.formworktest.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * @author Alberto Díaz en 25/11/2012
 */
public class Validaciones {

	private static Logger logger = Logger.getLogger(Validaciones.class);

	
	/**
	 * 
	 */
	private Validaciones() {
		
	}
	
	
	public static boolean isNifNie(Object valor) {

		String nif = (String) valor;
		logger.debug("NIF " + nif);
		
		// si es NIE, eliminar la x,y,z inicial para tratarlo como nif
		if (nif.toUpperCase().startsWith("X")
				|| nif.toUpperCase().startsWith("Y")
				|| nif.toUpperCase().startsWith("Z"))
			nif = nif.substring(1);

		Pattern nifPattern = Pattern
				.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
		Matcher m = nifPattern.matcher(nif);
		if (m.matches()) {
			String letra = m.group(2);
			// Extraer letra del NIF
			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
			int dni = Integer.parseInt(m.group(1));
			dni = dni % 23;
			String reference = letras.substring(dni, dni + 1);

			if (reference.equalsIgnoreCase(letra)) {
				logger.debug("son iguales. Es NIF. " + letra + " " + reference);
				return true;
			} else {
				logger.debug("NO son iguales. NO es NIF. " + letra + " "
						+ reference);
				return false;
			}
		} else
			return false;
	}
	
	public static String formatNif(String nif) {
		
		if (nif.length() >= 9) {
			return nif.substring(0, 9);
		}
		
		
		StringBuffer sb = new StringBuffer();
		if (Character.isLetter(nif.charAt(0))) {
			sb.append(nif.charAt(0));
			nif = nif.substring(1);
		}
		
		int cuantosCeros = 9 - sb.length() - nif.length();
		
		for (int i = 0; i < cuantosCeros; i++) {
			sb.append('0');
		}
		sb.append(nif);
		
		return sb.toString().toUpperCase();
	}
}
