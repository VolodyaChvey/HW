package com.chvey.DTO;

import com.chvey.domain.Product;
import com.chvey.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OrderDTO {
    private Long id;
    private User userDTO;
    private String totalPriceDTO;
    private List<Product> productsDTO;

    public OrderDTO() {
    }

    public Long getId() {
        return id;
    }

    public OrderDTO setId(Long id) {
        this.id = id;
        return null;
    }

    public User getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(User userDTO) {
        this.userDTO = userDTO;
    }

    public String getTotalPriceDTO() {
        return totalPriceDTO;
    }

    public void setTotalPriceDTO(Double totalPriceDTO) {
        this.totalPriceDTO = String.format("%.2f",totalPriceDTO);
    }

    public List<Product> getProductsDTO() {
        return productsDTO;
    }

    public void setProductsDTO(List<Product> productsDTO) {
        this.productsDTO = productsDTO;
    }
}
