package com.example.shoeshop.model;

import java.util.ArrayList;
import java.util.List;

public class ProductToCart {
    private int productId;
    private int quantity;

    public ProductToCart(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static List<ProductToCart> convertListCartItemDTOToThis(ArrayList<CartItemDTO> lists){
        List<ProductToCart> products = new ArrayList<>();
        for ( CartItemDTO tmp: lists) {
            products.add(convertCartItemDtoToThis(tmp));
        }
        return  products;
    }

    public static ProductToCart convertCartItemDtoToThis (CartItemDTO dto){
        return  new ProductToCart(dto.getId(), dto.getQuantity());
    }
}
