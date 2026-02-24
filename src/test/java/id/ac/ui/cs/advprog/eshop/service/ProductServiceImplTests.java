package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTests{

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    /* =======================
       create
       ======================= */
    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductName("Laptop");
        product.setProductQuantity(10);

        final Product result = productService.create(product);

        assertNotNull(result.getProductID()); // UUID is generated
        verify(productRepository).create(product);
    }

    /* =======================
       delete
       ======================= */
    @Test
    void testDeleteProduct() {
        final UUID productId = UUID.randomUUID();

        productService.delete(productId);

        verify(productRepository).delete(productId);
    }

    /* =======================
       edit
       ======================= */
    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductID(UUID.randomUUID());

        when(productRepository.edit(product)).thenReturn(product);

        final Product result = productService.edit(product);

        assertEquals(product, result);
        verify(productRepository).edit(product);
    }

    /* =======================
       findAll
       ======================= */
    @Test
    void testFindAllProducts() {
        Product p1 = new Product();
        Product p2 = new Product();

        Iterator<Product> iterator = List.of(p1, p2).iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        final List<Product> products = productService.findAll();

        assertEquals(2, products.size());
        assertTrue(products.contains(p1));
        assertTrue(products.contains(p2));
    }

    /* =======================
       findByID
       ======================= */
    @Test
    void testFindByID() {
        final UUID productId = UUID.randomUUID();
        Product product = new Product();
        product.setProductID(productId);

        when(productRepository.findByID(productId)).thenReturn(product);

        final Product result = productService.findByID(productId);

        assertEquals(product, result);
        verify(productRepository).findByID(productId);
    }
}