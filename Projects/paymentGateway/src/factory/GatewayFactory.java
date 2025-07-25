package factory;

import gateway.PaymentGateway;
import proxy.PaymentGatewayProxy;
import gateway.PaytmGateway;
import gateway.RazorpayGateway;
import model.GatewayType;

public class GatewayFactory {
    private static final GatewayFactory instance = new GatewayFactory();

    private GatewayFactory() {}

    public static GatewayFactory getInstance() {
        return instance;
    }

    public PaymentGateway getGateway(GatewayType type) {
        if (type == GatewayType.PAYTM) {
            PaymentGateway paymentGateway = new PaytmGateway();
            return new PaymentGatewayProxy(paymentGateway, 3);
        } else {
            PaymentGateway paymentGateway = new RazorpayGateway();
            return new PaymentGatewayProxy(paymentGateway, 1);
        }
    }
}
