package com.teestore.backend.service;

import com.teestore.backend.dao.UserDAO;
import com.teestore.backend.exceptions.UserServiceException;
import com.teestore.backend.model.Address;
import com.teestore.backend.model.Card;
import com.teestore.backend.model.User;
import com.teestore.backend.utility.HashingUtility;
import com.teestore.backend.validator.CardValidator;
import com.teestore.backend.validator.RegistrationValidator;
import com.teestore.backend.validator.UserValidatorLogin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public User addUser(User user) throws Exception {

        logger.debug("Adding User...");
        if (user == null)
            throw new Exception("USER_INVALID_USER");

        RegistrationValidator.validateRegistration(user);
        user.setPassword(HashingUtility.getHashValue(user.getPassword()));
        User u = userDAO.addUser(user);
        if (u == null)
            throw new UserServiceException("USER_INVALID_REGISTRATION");

        logger.debug("User Added with id " + u.getUserId());
        return u;
    }

    @Override
    public User loginUser(User user) throws Exception {

        logger.debug("Validating account...");
        if (user == null)
            throw new UserServiceException("USER_INVALID_USER");

        UserValidatorLogin.validateUserForLogin(user);
        User userFromDB = null;

        if (user.getContactNumber() != null)
            userFromDB = userDAO.getUserByContactNumber(user.getContactNumber());
        else if (user.getEmailId() != null)
            userFromDB = userDAO.getUserByEmailId(user.getEmailId());

        if (userFromDB == null)
            throw new UserServiceException("USER_INVALID_CREDENTIALS");

        logger.debug("Account validated...");
        String passwordFromDB = userFromDB.getPassword();

        if (passwordFromDB != null) {
            String hashedPassword = HashingUtility.getHashValue(user.getPassword());

            if (hashedPassword.equals(passwordFromDB)) {
                userFromDB.setPassword(null);
                logger.debug("User "+userFromDB.getUserName()+" logged in...");
                return userFromDB;
            } else {
                throw new UserServiceException("USER_INVALID_CREDENTIALS");
            }
        } else {
            throw new UserServiceException("USER_INVALID_CREDENTIALS");
        }

    }

    @Override
    public User getUser(String userId) throws Exception {

        logger.debug("Getting user details for id "+ userId);
        if (userId == null || userId.equals("")) {
            throw new Exception("USER_INVALID_USER");
        }

        User userFromDB = userDAO.getUser(userId);

        if (userFromDB == null)
            throw new UserServiceException("USER_USER_NOT_FOUND");

        logger.debug("User details received...");
        return userFromDB;
    }

    @Override
    public String editUser(String userId, User user) throws Exception {

        logger.debug("Updating user details for id "+userId);
        if (userId == null || user == null || userId.equals(""))
            throw new UserServiceException("USER_INVALID_USER");

        if (user.getPassword() != null)
            user.setPassword(HashingUtility.getHashValue(user.getPassword()));

        String uId = userDAO.editUser(userId, user);

        if (uId == null)
            throw new UserServiceException("USER_UNABLE_TO_EDIT_USER");

        logger.debug("User details updated...");
        return uId;
    }

    @Override
    public String addAddress(String userId, Address address) throws Exception {

        logger.debug("Adding address for user id "+userId);
        if (userId == null || userId.equals(""))
            throw new UserServiceException("USER_INVALID_USER");

        if (address == null)
            throw new UserServiceException("USER_INVALID_ADDRESS");

        String aId = userDAO.addAddress(userId, address);

        if (aId == null)
            throw new UserServiceException("USER_ADDRESS_NOT_PERSISTED");

        logger.debug("Address added for user id "+userId);
        return aId;
    }

    @Override
    public String editAddress(String addressId, Address address) throws Exception {

        logger.debug("Updating address id "+addressId);
        if (addressId == null || addressId.equals(""))
            throw new UserServiceException("USER_INVALID_USER_ADDRESS");

        if (address == null)
            throw new UserServiceException("USER_INVALID_ADDRESS");

        String aId = userDAO.editAddress(addressId, address);

        if (aId == null)
            throw new UserServiceException("USER_ADDRESS_NOT_PERSISTED");

        logger.debug("Address updated for id "+addressId);
        return aId;
    }

    @Override
    public String deleteAddress(String userId, String addressId) throws Exception {

        logger.info("Deleting Address id "+addressId);
        if (userId == null || userId.equals(""))
            throw new Exception("USER_INVALID_USER");

        if (addressId == null || addressId.equals(""))
            throw new Exception("USER_INVALID_ADDRESS");

        String aId = userDAO.deleteAddress(userId, addressId);

        if (aId == null)
            throw new Exception("USER_UNABLE_TO_DELETE_ADDRESS");

        logger.info("Address deleted for id "+addressId);
        return aId;
    }

    @Override
    public Card getCard(String cardId) throws Exception {

        logger.debug("Getting card id "+cardId);
        if (cardId == null || cardId.equals(""))
            throw new Exception("USER_INVALID_CARD");

        Card card = userDAO.getCard(cardId);

        if (card == null)
            throw new Exception("USER_UNABLE_TO_RETRIEVE_CARD");

        logger.debug("Received card details for id "+cardId);
        return card;
    }

    @Override
    public List<Card> getAllUserCards(String userId) throws Exception {

        logger.debug("Getting cards for user id "+userId);
        if (userId == null || userId.equals(""))
            throw new Exception("USER_INVALID_USER");

        List<Card> cards = userDAO.getAllUserCards(userId);

        if (cards == null || cards.isEmpty())
            throw new Exception("USER_UNABLE_TO_RETRIEVE_CARD");

        logger.debug("Cards found for user id "+userId);
        return cards;
    }

    @Override
    public String deleteCard(String cardId) throws Exception {

        logger.debug("Deleting card id "+cardId);
        if (cardId == null || cardId.equals(""))
            throw new Exception("USER_INVALID_CARD");

        String card = userDAO.deleteCard(cardId);

        if (card == null)
            throw new Exception("USER_UNABLE_TO_DELETE_CARD");

        logger.debug("Deleted card id "+cardId);
        return card;
    }

    @Override
    public String addCard(Card card, String userId) throws Exception {

        logger.debug("Adding card for user id "+userId);
        if (userId == null || userId.equals(""))
            throw new Exception("USER_INVALID_USER");

        if (card == null)
            throw new Exception("USER_INVALID_CARD");

        CardValidator.validateCard(card);
        String cardId = userDAO.addCard(card, userId);

        if (cardId == null)
            throw new Exception("USER_UNABLE_TO_ADD_CARD");

        logger.debug("Card added with id "+cardId);
        return cardId;
    }
}
