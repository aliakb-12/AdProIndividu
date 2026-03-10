package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Builder
public class Order {
    String id;
    String author;
    List<Product> products;
    Long orderTime;

    @Setter
    String status;

    public Order(String id, String author, List<Product> products, Long orderTime) {

    }

    public Order(String id, String author, List<Product> products, Long orderTime, String status) {

    }
}
