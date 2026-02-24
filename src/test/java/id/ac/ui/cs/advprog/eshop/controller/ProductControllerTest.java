package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService service;

    /* =======================
       GET /product/create
       ======================= */
    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("isEdit", false));
    }

    /* =======================
       POST /product/create
       ======================= */
    @Test
    void testCreateProductPost() throws Exception {
        mockMvc.perform(post("/product/create")
                        .param("name", "Laptop")
                        .param("quantity", "10")
                        .param("price", "10000"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        Mockito.verify(service).create(Mockito.any(Product.class));
    }

    /* =======================
       GET /product/edit/{id}
       ======================= */
    @Test
    void testEditProductPage() throws Exception {
        UUID id = UUID.randomUUID();
        Product product = new Product();
        product.setProductID(id);

        Mockito.when(service.findByID(id)).thenReturn(product);

        mockMvc.perform(get("/product/edit/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attribute("product", product))
                .andExpect(model().attribute("isEdit", true));
    }

    /* =======================
       POST /product/edit
       ======================= */
    @Test
    void testEditProductPost() throws Exception {
        mockMvc.perform(post("/product/edit")
                        .param("id", UUID.randomUUID().toString())
                        .param("name", "Phone")
                        .param("quantity", "5")
                        .param("price", "5000"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        Mockito.verify(service).edit(Mockito.any(Product.class));
    }

    /* =======================
       POST /product/delete/{id}
       ======================= */
    @Test
    void testDeleteProductPage() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(post("/product/delete/{id}", id)
                        .param("id", id.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        Mockito.verify(service).delete(id);
    }

    /* =======================
       GET /product/list
       ======================= */
    @Test
    void testProductListPage() throws Exception {
        Mockito.when(service.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attributeExists("products"));
    }
}