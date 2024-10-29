package demo.onebox.persistence.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductEntity {
    private Integer id;
    private String description;
    private BigDecimal amount;
}
