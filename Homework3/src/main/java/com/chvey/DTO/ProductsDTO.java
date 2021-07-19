package com.chvey.DTO;

import com.chvey.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProductsDTO {
    private String userName;
    private List<Product> priceList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Product> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Product> priceList) {
        this.priceList = priceList;
    }

    public ProductsDTO() {
    }
}
