package demo.onebox.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductObj {
    private Integer id;
    private String description;
    private BigDecimal amount;
}
