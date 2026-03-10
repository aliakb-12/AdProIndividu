package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String status = "REJECTED";
        if ("VOUCHER".equals(method)) {
            status = validateVoucher(paymentData.get("voucherCode"));
        } else if ("CASH_ON_DELIVERY".equals(method)) {
            status = validateCOD(paymentData);
        }
        Payment payment = new Payment(UUID.randomUUID().toString(), order, method, paymentData);
        return setStatus(payment, status);
    }

    private String validateVoucher(String code) {
        if (code == null || code.length() != 16 || !code.startsWith("ESHOP")) return "REJECTED";
        return (code.chars().filter(Character::isDigit).count() == 8) ? "SUCCESS" : "REJECTED";
    }

    private String validateCOD(Map<String, String> data) {
        String addr = data.get("address");
        String fee = data.get("deliveryFee");
        return (addr != null && !addr.isBlank() && fee != null && !fee.isBlank()) ? "SUCCESS" : "REJECTED";
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        if (status.equals("SUCCESS")) {
            payment.getOrder().setStatus("SUCCESS");
        } else if (status.equals("REJECTED")) {
            payment.getOrder().setStatus("FAILED");
        }
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}