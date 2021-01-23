package com.teestore.backend.dao;

import com.teestore.backend.model.Address;
import com.teestore.backend.model.Card;
import com.teestore.backend.model.User;

import java.util.List;

public interface UserDAO {

    /**
     * DAO method to persist new user after registration
     * @param user user details
     * @return persisted user id
     * @throws Exception Unable to persist
     */
    User addUser(User user) throws Exception;

    /**
     * DAO method to retrieve user during login
     * @param contactNumber provided contact number
     * @return User retrieved
     * @throws Exception Unable to retrieve
     */
    User getUserByContactNumber(String contactNumber) throws Exception;

    /**
     * DAO method to retrieve user during login
     * @param emailId provided email id
     * @return User retrieved
     * @throws Exception Unable to retrieve
     */
    User getUserByEmailId(String emailId) throws Exception;

    /**
     * DAO method to retrieve user by user id
     * @param userId user id to be retrieved
     * @return user
     * @throws Exception Unable to retrieve
     */
    User getUser(String userId) throws Exception;

    /**
     * DAO method to retrieve user and update user by user id
     * @param userId user id to e edited
     * @return user id
     * @throws Exception Unable to persist
     */
    String editUser(String userId, User user) throws Exception;

    /**
     * DAO method to add user address by user id
     * @param userId user id to br retrieved
     * @param address address details
     * @return address id
     * @throws Exception Unable to persist
     */
    String addAddress(String userId, Address address) throws Exception;

    /**
     * DAO method to edit user address by user id
     * @param userId user id to br retrieved
     * @param address address details
     * @return address id
     * @throws Exception Unable to persist
     */
    String editAddress(String userId, Address address) throws Exception;

    /**
     * DAO method to delete existing user address
     * @param userId user id to br retrieved
     * @param addressId address id
     * @return address id
     * @throws Exception Unable to remove
     */
    String deleteAddress(String userId, String addressId) throws Exception;

    /**
     * DAO method to get card by id
     * @param cardId card id
     * @return card
     * @throws Exception Unable to retrieve
     */
    Card getCard(String cardId) throws Exception;

    /**
     * DAO method to get all cards by user id
     * @param userId card id
     * @return card
     * @throws Exception Unable to remove
     */
    List<Card> getAllUserCards(String userId) throws Exception;

    /**
     * DAO method to delete card by id
     * @param cardId card id
     * @return card id
     * @throws Exception Unable to retrieve
     */
    String deleteCard(String cardId) throws Exception;

    /**
     * DAO method to add card for user
     * @param userId user id
     * @param card card details
     * @return card
     * @throws Exception Unable to persist
     */
    String addCard(Card card, String userId) throws Exception;
}
