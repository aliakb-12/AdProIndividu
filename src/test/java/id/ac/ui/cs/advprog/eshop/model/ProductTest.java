package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
public class ProductTest {
    Product product;
    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductID(UUID.randomUUID());
        this.product.setProductQuantity(100);
    }

    @Test
    void testGetProductId() {
        assertNotNull(this.product.getProductID());
        assertInstanceOf(UUID.class, this.product.getProductID());
    }

    @Test
    void testSetProductId() {
        UUID newId = UUID.randomUUID();
        this.product.setProductID(newId);
        assertEquals(newId, this.product.getProductID());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang",
                this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100,
                this.product.getProductQuantity());
    }
}
