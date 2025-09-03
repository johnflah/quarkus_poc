/*
package org.acme.payments.controller;
import com.jof.api.PaymentsApi;
import com.jof.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.instancio.Instancio;

@ApplicationScoped
@Path("/payments")
public class PaymentApiController implements PaymentsApi {
    @Override
    public Response paymentsPost( PaymentRequest paymentRequest) {
        return Response.ok(generateRandomPaymentResponse(paymentRequest)).build();
    }

    private PaymentResponse generateRandomPaymentResponse(PaymentRequest paymentRequest) {
        int x = (int) ((Math.random() * 10d)/10d % 3);

        if (x == 0) {
        return Instancio.create(SuccessPaymentResponse.class);
        } else if (x == 1) {
            return Instancio.create(FailurePaymentResponse.class);
        }
            return Instancio.create(PendingPaymentResponse.class);
    }
}
*/
