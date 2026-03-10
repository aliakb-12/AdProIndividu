package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    Order order;

    @BeforeEach
    void setUp() {
        order = new Order("order-id", List.of(new Product()), 123L, "Ali");
    }

    @Test
    void testAddPaymentVoucherSuccess() {
        Map<String, String> data = Map.of("voucherCode", "ESHOP1234ABC5678");
        // Mock the repo save to return whatever is passed to it
        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArguments()[0]);

        Payment result = paymentService.addPayment(order, "VOUCHER", data);

        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", order.getStatus()); // Check order sync
    }

    @Test
    void testAddPaymentVoucherRejectedInvalidLength() {
        Map<String, String> data = Map.of("voucherCode", "ESHOP123"); // Too short
        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArguments()[0]);

        Payment result = paymentService.addPayment(order, "VOUCHER", data);
        assertEquals("REJECTED", result.getStatus());
    }

    @Test
    void testAddPaymentCODSuccess() {
        Map<String, String> data = Map.of("address", "UI Depok", "deliveryFee", "5000");
        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArguments()[0]);

        Payment result = paymentService.addPayment(order, "CASH_ON_DELIVERY", data);
        assertEquals("SUCCESS", result.getStatus());
    }

    @Test
    void testAddPaymentCODRejectedEmptyAddress() {
        Map<String, String> data = Map.of("address", "", "deliveryFee", "5000");
        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArguments()[0]);

        Payment result = paymentService.addPayment(order, "CASH_ON_DELIVERY", data);
        assertEquals("REJECTED", result.getStatus());
    }

    @Test
    void testSetStatusRejectedUpdatesOrderToFailed() {
        Payment payment = new Payment("p-1", order, "VOUCHER", new HashMap<>());
        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArguments()[0]);

        paymentService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus()); // Per instruction: Rejected Payment = Failed Order
    }
}