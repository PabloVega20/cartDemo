package demo.onebox.domain.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class ValidationException extends Exception {
	private final String code;
	private final String field;

	public ValidationException(String code, String msg, String field){
		super(msg);
		this.code = code;
        this.field = field;
    }
}
