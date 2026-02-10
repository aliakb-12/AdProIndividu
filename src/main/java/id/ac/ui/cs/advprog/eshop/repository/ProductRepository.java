package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product>productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        return product;
    }

    public Product edit(Product product){
        for(Product productExist : productData){
            if(productExist.getProductID().equals(product.getProductID())){
                productExist.setProductName(product.getProductName());
                productExist.setProductQuantity(product.getProductQuantity());
                return productExist;
            }
        }
        return null;
    }

    public void delete(UUID productId){
        // Mencari UUID yang sama
        productData.removeIf(product -> productId.equals(product.getProductID()));
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findByID(UUID id){
        for(Product product : productData){
            if(product.getProductID().equals(id)){
                return  product;
            }
        }
        return null;
    }


}
