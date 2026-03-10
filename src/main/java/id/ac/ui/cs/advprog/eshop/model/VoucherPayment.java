package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class VoucherPayment extends Payment {

    public VoucherPayment(String id, Order order, Map<String, String> paymentData) {
        super(id, order, "VOUCHER", paymentData);
        validateVoucher();
    }

    private void validateVoucher() {
        String code = this.paymentData.get("voucherCode");
        if (code != null && code.length() == 16 && code.startsWith("ESHOP")) {
            if (code.chars().filter(Character::isDigit).count() == 8) {
                this.setStatus("SUCCESS");
                return;
            }
        }
        this.setStatus("REJECTED");
    }
}