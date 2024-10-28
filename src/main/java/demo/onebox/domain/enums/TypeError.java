package es.fudontime.domain.enums;

import lombok.Getter;

@Getter
public enum TypeError {
    ERROR_GENERIC("Err_0001", "No se ha podido procesar su petición, utilice el código de seguimiento para informar de dicho error"),
    ERROR_USER_ROLE_NO_EXISTS("Err_0002", "No existe el rol del usuario solicitado"),
    ERROR_USER_NO_EXISTS("Err_0003", "No existe el usuario solicitado"),
    ERROR_PRODUCT_NO_EXISTS("Err_0004", "No existe el producto solicitado"),
    ERROR_TYPE_CART_NO_EXISTS("Err_0005", "No existe el tipo de carrito solicitado"),
    ERROR_CART_NO_EXISTS("Err_0006", "No existe el carrito solicitado"),
    ERROR_TABLE_NO_EXISTS("Err_0007", "No existe la mesa solicitada"),
    ERROR_USER_PRODUCT_NO_EXISTS("Err_0008", "No existe la relación usuario-producto solicitada"),
    ERROR_CART_PRODUCT_NO_EXISTS("Err_0009", "No existe la relación carrito-producto solicitada"),
    ERROR_CUSTOMER_NO_EXISTS("Err_0010", "No existe el cliente solicitado"),
    ERROR_BOOKING_NO_EXISTS("Err_0011", "No existe la reserva solicitada"),
    ERROR_PAYMENT_METHOD_NO_EXISTS("Err_0012", "No existe el método de pago solicitado"),
    ERROR_PAYMENT_NO_EXISTS("Err_0013", "No existe el pago solicitado"),
    ERROR_ROLE_NO_EXISTS("Err_0014", "No existe el rol de usuario introducido"),


    ERROR_USER_CART_ALREADY_ACTIVE("Err_0015","Ya existe una cesta/pedido activo para este usuario"),
    ERROR_NAME_ALREADY_EXISTS("Err_0016","El nombre indicado ya existe"),
    ERROR_EMAIL_ALREADY_EXISTS("Err_0017","El email indicado ya existe"),
    ERROR_PHONE_ALREADY_EXISTS("Err_0018","El número de télefono indicado ya existe"),
    ERROR_CART_PRODUCT_ALREADY_EXISTS("Err_0019","El producto ya está añadido");

    private final String value;
    private final String description;

    TypeError(String value, String description) {
        this.value = value;
        this.description = description;
    }

}
