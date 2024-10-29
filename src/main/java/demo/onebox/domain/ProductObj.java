package demo.onebox.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductObj {
    private Integer id;
    private String description;
    private Integer amount;
}
