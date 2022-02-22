package base.service;

import base.model.entity.PaymentInfo;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckoutService {
    public CheckoutService(@Value("${stripe.key.secret}") String secretKey) {
        Stripe.apiKey = secretKey;
    }

    public PaymentIntent checkouPayment(PaymentInfo paymentInfo) throws StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("cart");
        Map<String,Object> params = new HashMap<>();
        params.put("amount",paymentInfo.getAmount());
        params.put("currency",paymentInfo.getCurrency());
        params.put("payment_method_types",paymentMethodTypes);
      return PaymentIntent.create(params);
    }
}
