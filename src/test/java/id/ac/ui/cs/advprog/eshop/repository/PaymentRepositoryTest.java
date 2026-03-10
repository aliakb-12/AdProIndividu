package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        Order order = new Order("order-id", List.of(new Product()), 123L, "Ali");

        Payment payment1 = new Payment("p-1", order, "VOUCHER", Map.of("voucherCode", "ESHOP1234ABC5678"));
        payments.add(payment1);

        Payment payment2 = new Payment("p-2", order, "CASH_ON_DELIVERY", Map.of("address", "Depok", "deliveryFee", "10000"));
        payments.add(payment2);
    }

    @Test
    void testSave() {
        Payment payment = payments.get(0);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
    }

    @Test
    void testFindByIdNotFound() {
        assertNull(paymentRepository.findById("non-existent"));
    }

    @Test
    void testFindAll() {
        paymentRepository.save(payments.get(0));
        paymentRepository.save(payments.get(1));
        assertEquals(2, paymentRepository.findAll().size());
    }
}
