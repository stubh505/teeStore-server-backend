package com.teestore.backend.dao;

import com.teestore.backend.model.Cart;
import com.teestore.backend.model.Order;

import java.util.List;

public interface OrdersDAO {

    /**
     * Service function to purchase items in cart
     *
     * @param cart    Cart body to be purchased
     * @param aId     Delivery Address ID
     * @param payment Payment method
     * @return Order ID
     * @throws Exception Unable to persist Order
     */
    String buyNow(Cart cart, String aId, String payment) throws Exception;

    /**
     * Service function to retrieve order by order id
     *
     * @param orderId Order id to be pulled
     * @return Order body
     * @throws Exception Unable to retrieve order
     */
    Order getOrder(String orderId) throws Exception;

    /**
     * Service function to get Orders for User
     *
     * @param userId user id whose order is being retrieved
     * @return list of orders
     * @throws Exception Orders not found
     */
    List<Order> getOrdersByUserId(String userId) throws Exception;
}
