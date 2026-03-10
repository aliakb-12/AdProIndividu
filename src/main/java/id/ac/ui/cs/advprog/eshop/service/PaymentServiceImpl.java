package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.CashOnDeliveryPayment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.VoucherPayment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment;
        String paymentId = UUID.randomUUID().toString();

        if ("VOUCHER".equals(method)) {
            payment = new VoucherPayment(paymentId, order, paymentData);
        } else if ("CASH_ON_DELIVERY".equals(method)) {
            payment = new CashOnDeliveryPayment(paymentId, order, paymentData);
        } else {
            throw new IllegalArgumentException("Unsupported payment method");
        }

        // The subclass constructor automatically set the status upon validation, so we just sync it to the Order
        return setStatus(payment, payment.getStatus());
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