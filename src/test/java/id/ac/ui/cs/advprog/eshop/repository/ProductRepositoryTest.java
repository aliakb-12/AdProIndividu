package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductID(UUID.randomUUID());
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductID(), savedProduct.getProductID());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductID(UUID.randomUUID());
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductID(UUID.randomUUID());
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductID(), savedProduct.getProductID());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductID(), savedProduct.getProductID());

        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        UUID productId = UUID.randomUUID();
        product.setProductID(productId);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductID(productId);
        updatedProduct.setProductName("Sampo Cap Usep");
        updatedProduct.setProductQuantity(200);

        Product result = productRepository.edit(updatedProduct);

        assertNotNull(result);
        assertEquals(productId, result.getProductID());
        assertEquals("Sampo Cap Usep", result.getProductName());
        assertEquals(200, result.getProductQuantity());
    }

    @Test
    void testEditProductNotFound() {
        UUID nonExistentId = UUID.randomUUID();
        Product updatedProduct = new Product();
        updatedProduct.setProductID(nonExistentId);
        updatedProduct.setProductName("Sampo Cap Usep");
        updatedProduct.setProductQuantity(200);

        Product result = productRepository.edit(updatedProduct);

        assertNull(result);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        UUID productId = UUID.randomUUID();
        product.setProductID(productId);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete(productId);

        Product result = productRepository.findByID(productId);
        assertNull(result);
    }

    @Test
    void testDeleteProductNotFound() {
        UUID nonExistentId = UUID.randomUUID();

        // Should not throw exception even if product doesn't exist
        assertDoesNotThrow(() -> productRepository.delete(nonExistentId));
    }
}

