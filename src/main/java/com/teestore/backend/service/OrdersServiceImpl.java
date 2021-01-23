package com.teestore.backend.service;

import com.teestore.backend.dao.OrdersDAO;
import com.teestore.backend.model.Cart;
import com.teestore.backend.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(value = "ordersService")
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    OrdersDAO ordersDAO;

    @Override
    public String buyNow(Cart cart, String aId, String payment) throws Exception {
        if (cart == null)
            throw new Exception("OrdersService.INVALID_CART");
        if (aId == null || aId.equals(""))
            throw new Exception("OrdersService.INVALID_DELIVERY_ADDRESS");
        if (payment == null || payment.equals(""))
            throw new Exception("OrdersService.INVALID_PAYMENT_METHOD");
        String res = ordersDAO.buyNow(cart, aId, payment);
        if (res == null)
            throw new Exception("OrdersService.UNABLE_TO_PLACE_ORDER");
        return res;
    }

    @Override
    public Order getOrder(String orderId) throws Exception {
        if (orderId == null)
            throw new Exception("OrdersService.INVALID_ORDER_ID");
        Order res = ordersDAO.getOrder(orderId);
        if (res == null)
            throw new Exception("OrdersService.UNABLE_TO_RETRIEVE_ORDER");
        return res;
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) throws Exception {
        if (userId == null)
            throw new Exception("OrdersService.INVALID_USER_ID");
        List<Order> res = ordersDAO.getOrdersByUserId(userId);
        if (res == null || res.isEmpty())
            throw new Exception("OrdersService.UNABLE_TO_RETRIEVE_ORDERS");
        return res;
    }
}
