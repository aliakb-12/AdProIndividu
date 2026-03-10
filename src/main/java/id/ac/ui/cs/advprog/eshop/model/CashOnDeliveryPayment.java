package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class CashOnDeliveryPayment extends Payment {

    public CashOnDeliveryPayment(String id, Order order, Map<String, String> paymentData) {
        super(id, order, "CASH_ON_DELIVERY", paymentData);
        validateCOD();
    }

    private void validateCOD() {
        String addr = this.paymentData.get("address");
        String fee = this.paymentData.get("deliveryFee");
        if (addr != null && !addr.isBlank() && fee != null && !fee.isBlank()) {
            this.setStatus("SUCCESS");
        } else {
            this.setStatus("REJECTED");
        }
    }
}