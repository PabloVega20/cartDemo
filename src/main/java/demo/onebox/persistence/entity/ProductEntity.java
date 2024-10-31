package demo.onebox.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductEntity {
    private Integer id;
    private String description;
    private Integer amount;
}
