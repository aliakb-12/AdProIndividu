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

        Product result = productService.create(product);

        assertNotNull(result.getProductID()); // UUID is generated
        verify(productRepository).create(product);
    }

    /* =======================
       delete
       ======================= */
    @Test
    void testDeleteProduct() {
        UUID id = UUID.randomUUID();

        productService.delete(id);

        verify(productRepository).delete(id);
    }

    /* =======================
       edit
       ======================= */
    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductID(UUID.randomUUID());

        when(productRepository.edit(product)).thenReturn(product);

        Product result = productService.edit(product);

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

        List<Product> products = productService.findAll();

        assertEquals(2, products.size());
        assertTrue(products.contains(p1));
        assertTrue(products.contains(p2));
    }

    /* =======================
       findByID
       ======================= */
    @Test
    void testFindByID() {
        UUID id = UUID.randomUUID();
        Product product = new Product();
        product.setProductID(id);

        when(productRepository.findByID(id)).thenReturn(product);

        Product result = productService.findByID(id);

        assertEquals(product, result);
        verify(productRepository).findByID(id);
    }
}