package com.teestore.backend.dao;

import com.teestore.backend.entity.AddressEntity;
import com.teestore.backend.entity.CardEntity;
import com.teestore.backend.entity.UserEntity;
import com.teestore.backend.model.Address;
import com.teestore.backend.model.Card;
import com.teestore.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "userDAO")
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public User addUser(User user) throws Exception {

        Query query1 = entityManager.createQuery("select u from UserEntity u where u.contactNumber =:contactNumber");
        query1.setParameter("contactNumber", user.getContactNumber());

        Query query2 = entityManager.createQuery("select u from UserEntity u where u.emailId =:emailId");
        query2.setParameter("emailId", user.getEmailId());

        List<UserEntity> result1 = query1.getResultList();
        List<UserEntity> result2 = query2.getResultList();
        if ((result1 != null && !result1.isEmpty()) || (result2 != null && !result2.isEmpty()))
            return null;

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());
        userEntity.setContactNumber(user.getContactNumber());
        userEntity.setEmailId(user.getEmailId());
        userEntity.setDateOfBirth(user.getDateOfBirth());
        userEntity.setPassword(user.getPassword());

        List<Address> addressList = user.getAddresses();
        List<AddressEntity> addressEntityList = null;

        if (addressList != null && !addressList.isEmpty()) {

            addressEntityList = new ArrayList<>();
            for (Address address : addressList) {
                AddressEntity addressEntity = new AddressEntity();
                addressEntity.setStreet(address.getStreet());
                addressEntity.setCity(address.getStreet());
                addressEntity.setState(address.getState());
                addressEntity.setPinCode(address.getPinCode());

                entityManager.persist(addressEntity);
                address.setAddressId(addressEntity.getAddressId());
                addressEntityList.add(addressEntity);
            }

        }

        userEntity.setAddresses(addressEntityList);
        entityManager.persist(userEntity);
        user.setUserId(userEntity.getUserId());

        return user;
    }

    @Override
    public User getUserByContactNumber(String contactNumber) throws Exception {

        Query query = entityManager.createQuery("select u from UserEntity u where u.contactNumber =:contactNumber");
        query.setParameter("contactNumber", contactNumber);
        User user = null;
        List<UserEntity> userEntities = query.getResultList();
        if (userEntities != null && !userEntities.isEmpty()) {
            UserEntity userEntity = userEntities.get(0);
            user = new User();
            user.setUserId(userEntity.getUserId());
            user.setUserName(userEntity.getUserName());
            user.setEmailId(userEntity.getEmailId());
            user.setContactNumber(userEntity.getContactNumber());
            user.setPassword(userEntity.getPassword());
            user.setDateOfBirth(userEntity.getDateOfBirth());

            List<AddressEntity> addressEntityList = userEntity.getAddresses();
            List<Address> addressList = null;

            if (addressEntityList != null && !addressEntityList.isEmpty()) {
                addressList = new ArrayList<>();

                for (AddressEntity addressEntity : addressEntityList) {
                    Address address = new Address();
                    address.setAddressId(addressEntity.getAddressId());
                    address.setStreet(addressEntity.getStreet());
                    address.setCity(addressEntity.getCity());
                    address.setState(addressEntity.getState());
                    address.setPinCode(addressEntity.getPinCode());
                    addressList.add(address);
                }
            }
            user.setAddresses(addressList);
        }
        return user;
    }

    @Override
    public User getUserByEmailId(String emailId) throws Exception {
        Query query = entityManager.createQuery("select u from UserEntity u where u.emailId =:emailId");
        query.setParameter("emailId", emailId);
        User user = null;
        List<UserEntity> userEntities = query.getResultList();

        if (userEntities != null && !userEntities.isEmpty()) {
            UserEntity userEntity = userEntities.get(0);
            user = new User();
            user.setUserId(userEntity.getUserId());
            user.setUserName(userEntity.getUserName());
            user.setEmailId(userEntity.getEmailId());
            user.setContactNumber(userEntity.getContactNumber());
            user.setPassword(userEntity.getPassword());
            user.setDateOfBirth(userEntity.getDateOfBirth());

            List<AddressEntity> addressEntityList = userEntity.getAddresses();
            List<Address> addressList = null;

            if (addressEntityList != null && !addressEntityList.isEmpty()) {
                addressList = new ArrayList<>();

                for (AddressEntity addressEntity : addressEntityList) {
                    Address address = new Address();
                    address.setAddressId(addressEntity.getAddressId());
                    address.setStreet(addressEntity.getStreet());
                    address.setCity(addressEntity.getCity());
                    address.setState(addressEntity.getState());
                    address.setPinCode(addressEntity.getPinCode());
                    addressList.add(address);
                }
            }
            user.setAddresses(addressList);
        }
        return user;
    }

    @Override
    public User getUser(String userId) throws Exception {

        UserEntity userEntity = entityManager.find(UserEntity.class, userId);

        if (userEntity == null)
            return null;

        User user = new User();
        user.setUserId(userEntity.getUserId());
        user.setUserName(userEntity.getUserName());
        user.setEmailId(userEntity.getEmailId());
        user.setContactNumber(userEntity.getContactNumber());
        user.setPassword(userEntity.getPassword());
        user.setDateOfBirth(userEntity.getDateOfBirth());

        List<AddressEntity> addressEntityList = userEntity.getAddresses();
        List<Address> addressList = null;

        if (addressEntityList != null && !addressEntityList.isEmpty()) {
            addressList = new ArrayList<>();

            for (AddressEntity addressEntity : addressEntityList) {
                Address address = new Address();
                address.setAddressId(addressEntity.getAddressId());
                address.setStreet(addressEntity.getStreet());
                address.setCity(addressEntity.getCity());
                address.setState(addressEntity.getState());
                address.setPinCode(addressEntity.getPinCode());
                addressList.add(address);
            }
        }
        user.setAddresses(addressList);

        return user;
    }

    @Override
    public String editUser(String userId, User user) throws Exception {
        UserEntity userEntity = entityManager.find(UserEntity.class, userId);

        if (userEntity == null)
            return null;

        if (user.getUserName() != null)
            userEntity.setUserName(user.getUserName());

        if (user.getDateOfBirth() != null)
            userEntity.setDateOfBirth(user.getDateOfBirth());

        if (user.getPassword() != null)
            userEntity.setPassword(user.getPassword());

        entityManager.persist(userEntity);

        return userEntity.getUserId();
    }

    @Override
    public String addAddress(String userId, Address address) throws Exception {
        UserEntity userEntity = entityManager.find(UserEntity.class, userId);

        if (userEntity == null)
            return null;

        List<AddressEntity> addressEntityList = userEntity.getAddresses();

        if (addressEntityList == null)
            addressEntityList = new ArrayList<>();

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet(address.getStreet());
        addressEntity.setCity(address.getCity());
        addressEntity.setState(address.getState());
        addressEntity.setPinCode(address.getPinCode());

        addressEntityList.add(addressEntity);
        userEntity.setAddresses(addressEntityList);

        entityManager.persist(userEntity);

        return addressEntity.getAddressId();
    }

    @Override
    public String editAddress(String addressId, Address address) throws Exception {
        AddressEntity addressEntity = entityManager.find(AddressEntity.class, addressId);

        if (addressEntity == null)
            return null;

        addressEntity.setStreet(address.getStreet());
        addressEntity.setCity(address.getCity());
        addressEntity.setState(address.getState());
        addressEntity.setPinCode(address.getPinCode());

        entityManager.persist(addressEntity);

        return addressEntity.getAddressId();
    }

    @Override
    public String deleteAddress(String userId, String addressId) throws Exception {

        UserEntity userEntity = entityManager.find(UserEntity.class, userId);
        AddressEntity addressEntity = entityManager.find(AddressEntity.class, addressId);

        String id = null;
        if (userEntity != null) {

            List<AddressEntity> addressEntityList = userEntity.getAddresses();

            if (addressEntityList != null && !addressEntityList.isEmpty() && addressEntity != null) {

                for (AddressEntity aEntity : addressEntityList) {
                    if (aEntity.equals(addressEntity)) {
                        addressEntityList.remove(aEntity);
                        entityManager.persist(userEntity);

                        id = addressEntity.getAddressId();
                        break;
                    }
                }
            }
        }

        return id;
    }

    @Override
    public Card getCard(String cardNo) throws Exception {
        Card card = null;
        CardEntity entity = entityManager.find(CardEntity.class, cardNo);

        if (entity != null) {
            card = new Card();
            card.setCardHolderName(entity.getCardHolderName());
            card.setCardNumber(entity.getCardNumber());
            card.setExpiryMonthYear(entity.getExpiryMonthYear());

            User user = new User();
            user.setUserId(entity.getUser().getUserId());

            card.setUser(user);
        }
        return card;
    }

    @Override
    public List<Card> getAllUserCards(String userId) throws Exception {
        Query query = entityManager.createQuery("select c from CardEntity c where c.user.userId =:userId");
        query.setParameter("userId", userId);

        List<Card> cards = null;
        List<CardEntity> entities = query.getResultList();

        if (entities != null && !entities.isEmpty()) {
            cards = new ArrayList<>();

            for (CardEntity entity : entities) {
                if (entity != null) {
                    Card card = new Card();
                    card.setCardHolderName(entity.getCardHolderName());
                    card.setCardNumber(entity.getCardNumber());
                    card.setExpiryMonthYear(entity.getExpiryMonthYear());

                    User user = new User();
                    user.setUserId(entity.getUser().getUserId());

                    card.setUser(user);

                    cards.add(card);
                }
            }
        }
        return cards;
    }

    @Override
    public String deleteCard(String cardNo) throws Exception {
        String res = null;
        CardEntity entity = entityManager.find(CardEntity.class, cardNo);

        if (entity != null) {
            entity.setUser(null);
            entityManager.remove(entity);
            res = entity.getCardNumber();
        }
        return res;
    }

    @Override
    public String addCard(Card card, String userId) throws Exception {
        UserEntity userEntity = entityManager.find(UserEntity.class, userId);

        if (card != null && userEntity != null) {
            CardEntity entity = new CardEntity();
            entity.setCardHolderName(card.getCardHolderName());
            entity.setCardNumber(card.getCardNumber());
            entity.setCvv(card.getCvv());
            entity.setExpiryMonthYear(card.getExpiryMonthYear());
            entity.setUser(userEntity);

            entityManager.persist(entity);

            return entity.getCardNumber();
        }
        return null;
    }
}
