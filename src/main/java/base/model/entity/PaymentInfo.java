package base.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@NoArgsConstructor
public class PaymentInfo {
    private int amount;
    private String name;
    private int quantity;
    private String currency;
    private String successUrl;
    private String cancelUrl;
}
