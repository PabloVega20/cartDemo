package es.fudontime.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Excepcion raiz lanzada por la aplicacion cuando existe un error general.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class NotFoundException extends Exception {

	/** Código de la excepción. */
	private final String code;

	/**
	 * Crea una nueva instancia de la clase GeneralException.java. <br>
	 *
	 * @param code
	 * 		código de la excepción a propagar.
	 * @param msg
	 * 		la cadena con el mensaje de la excepcion a propagar
	 */
	public NotFoundException(String code, String msg) {
		super(msg);
		this.code = code;
	}
}
