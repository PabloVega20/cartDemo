package demo.onebox.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Custom exception 404 not found
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class NotFoundException extends Exception {
	private final String code;

	/**
	 * Create a new NotFoundException
	 *
	 * @param code
	 * 		code of the exception
	 * @param msg
	 * 		message of the exception
	 */
	public NotFoundException(String code, String msg) {
		super(msg);
		this.code = code;
	}
}
