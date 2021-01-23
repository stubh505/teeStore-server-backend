package com.teestore.backend.service;

import com.teestore.backend.dao.UserDAO;
import com.teestore.backend.model.Address;
import com.teestore.backend.model.Card;
import com.teestore.backend.model.User;
import com.teestore.backend.utility.HashingUtility;
import com.teestore.backend.validator.CardValidator;
import com.teestore.backend.validator.RegistrationValidator;
import com.teestore.backend.validator.UserValidatorLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User addUser(User user) throws Exception {

        if (user == null)
            throw new Exception("UserService.INVALID_USER");

        RegistrationValidator.validateRegistration(user);
        user.setPassword(HashingUtility.getHashValue(user.getPassword()));
        User u = userDAO.addUser(user);
        if (u == null)
            throw new Exception("UserService.INVALID_REGISTRATION");
        return u;
    }

    @Override
    public User loginUser(User user) throws Exception {

        if (user == null)
            throw new Exception("UserService.INVALID_USER");

        UserValidatorLogin.validateUserForLogin(user);
        User userFromDB = null;

        if (user.getContactNumber() != null)
            userFromDB = userDAO.getUserByContactNumber(user.getContactNumber());
        else if (user.getEmailId() != null)
            userFromDB = userDAO.getUserByEmailId(user.getEmailId());

        if (userFromDB == null)
            throw new Exception("UserService.INVALID_CREDENTIALS");

        String passwordFromDB = userFromDB.getPassword();

        if (passwordFromDB != null) {
            String hashedPassword = HashingUtility.getHashValue(user.getPassword());

            if (hashedPassword.equals(passwordFromDB)) {
                userFromDB.setPassword(null);
                return userFromDB;
            } else {
                throw new Exception("UserService.INVALID_CREDENTIALS");
            }
        } else {
            throw new Exception("UserService.INVALID_CREDENTIALS");
        }

    }

    @Override
    public User getUser(String userId) throws Exception {

        if (userId == null || userId.equals("")) {
            throw new Exception("UserService.Invalid_User");
        }

        User userFromDB = userDAO.getUser(userId);

        if (userFromDB == null)
            throw new Exception("UserService.USER_NOT_FOUND");

        return userFromDB;
    }

    @Override
    public String editUser(String userId, User user) throws Exception {

        if (userId == null || user == null || userId.equals(""))
            throw new Exception("UserService.INVALID_USER");

        if (user.getPassword() != null)
            user.setPassword(HashingUtility.getHashValue(user.getPassword()));

        String uId = userDAO.editUser(userId, user);

        if (uId == null)
            throw new Exception("UserService.UNABLE_TO_EDIT_USER");

        return uId;
    }

    @Override
    public String addAddress(String userId, Address address) throws Exception {

        if (userId == null || userId.equals(""))
            throw new Exception("UserService.INVALID_USER_ID");

        if (address == null)
            throw new Exception("UserService.INVALID_ADDRESS");

        String aId = userDAO.addAddress(userId, address);

        if (aId == null)
            throw new Exception("UserService.UNABLE_TO_ADD_ADDRESS");

        return aId;
    }

    @Override
    public String editAddress(String addressId, Address address) throws Exception {
        if (addressId == null || addressId.equals(""))
            throw new Exception("UserService.INVALID_USER_ADDRESS");

        if (address == null)
            throw new Exception("UserService.INVALID_ADDRESS");

        String aId = userDAO.editAddress(addressId, address);

        if (aId == null)
            throw new Exception("UserService.ADDRESS_NOT_PERSISTED");

        return aId;
    }

    @Override
    public String deleteAddress(String userId, String addressId) throws Exception {

        if (userId == null || userId.equals(""))
            throw new Exception("UserService.INVALID_USER_ID");

        if (addressId == null || addressId.equals(""))
            throw new Exception("UserService.INVALID_ADDRESS_ID");

        String aId = userDAO.deleteAddress(userId, addressId);

        if (aId == null)
            throw new Exception("UserService.UNABLE_TO_DELETE_ADDRESS");

        return aId;
    }

    @Override
    public Card getCard(String cardId) throws Exception {
        if (cardId == null || cardId.equals(""))
            throw new Exception("UserService.INVALID_CARD_ID");

        Card card = userDAO.getCard(cardId);

        if (card == null)
            throw new Exception("UserService.UNABLE_TO_RETRIEVE_CARD");

        return card;
    }

    @Override
    public List<Card> getAllUserCards(String userId) throws Exception {
        if (userId == null || userId.equals(""))
            throw new Exception("UserService.INVALID_USER_ID");

        List<Card> cards = userDAO.getAllUserCards(userId);

        if (cards == null || cards.isEmpty())
            throw new Exception("UserService.UNABLE_TO_DELETE_CARD");

        return cards;
    }

    @Override
    public String deleteCard(String cardId) throws Exception {
        if (cardId == null || cardId.equals(""))
            throw new Exception("UserService.INVALID_CARD_ID");

        String card = userDAO.deleteCard(cardId);

        if (card == null)
            throw new Exception("UserService.UNABLE_TO_DELETE_CARD");

        return card;
    }

    @Override
    public String addCard(Card card, String userId) throws Exception {
        if (userId == null || userId.equals(""))
            throw new Exception("UserService.INVALID_USER_ID");

        if (card == null)
            throw new Exception("UserService.INVALID_CARD_DATA");

        CardValidator.validateCard(card);
        String cardId = userDAO.addCard(card, userId);

        if (cardId == null)
            throw new Exception("UserService.UNABLE_TO_ADD_CARD");

        return cardId;
    }
}
