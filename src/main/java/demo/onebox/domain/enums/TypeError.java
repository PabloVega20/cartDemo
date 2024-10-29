package demo.onebox.domain.enums;

import lombok.Getter;

@Getter
public enum TypeError {
    ERROR_GENERIC("Err_0001", "Your request could not be processed, please use the tracking code to report this error."),
    ERROR_PRODUCT_NO_EXISTS("Err_0002", "The inserted product do not exist"),
    ERROR_CART_NO_EXISTS("Err_0003", "The inserted id do not match a cart"),
    ERROR_PRODUCT_ID_EXISTS("Err_0004", "The inserted product id already exists");

    private final String value;
    private final String description;

    TypeError(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
