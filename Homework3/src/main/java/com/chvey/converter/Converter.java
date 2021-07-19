package com.chvey.converter;

import com.chvey.DTO.OrderDTO;
import com.chvey.DTO.ProductsDTO;
import com.chvey.domain.Order;
import com.chvey.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Converter {
    @Autowired
    private OrderDTO orderDTO;
    @Autowired
    private ProductsDTO productsDTO;
    @Autowired
    private ProductsRepository productsRepository;

    public OrderDTO convertOrderToOrederDTO(Order order) {
        orderDTO.setId(order.getId());
        orderDTO.setUserDTO(order.getUser());
        orderDTO.setTotalPriceDTO(order.getTotalPrice());
        orderDTO.setProductsDTO(order.getProducts());
        return orderDTO;
    }

    public ProductsDTO convertProductsDTO(String userName) {
        productsDTO.setUserName(userName);
        productsDTO.setPriceList(productsRepository.getAll());
        return productsDTO;
    }
}
