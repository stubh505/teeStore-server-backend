package com.teestore.backend.dao;

import com.teestore.backend.entity.ContactEntity;
import com.teestore.backend.entity.UserEntity;
import com.teestore.backend.model.Contact;
import com.teestore.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "contactDAO")
public class ContactDAOImpl implements ContactDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Contact getMessage(String contactId) throws Exception {
        ContactEntity entity = entityManager.find(ContactEntity.class, contactId);
        Contact contact = null;

        if (entity != null) {
            contact = new Contact();

            contact.setContactEmail(entity.getContactEmail());
            contact.setContactId(entity.getContactId());
            contact.setMessage(entity.getMessage());
            contact.setPhoneNo(entity.getPhoneNo());
            contact.setSubject(entity.getSubject());

            User user = null;

            if (entity.getUser() != null) {
                user = new User();
                user.setEmailId(entity.getUser().getEmailId());
                user.setUserName(entity.getUser().getUserName());
                user.setUserId(entity.getUser().getUserId());
            }

            contact.setUser(user);
        }

        return contact;
    }

    @Override
    public String addMessage(Contact contact) throws Exception {
        ContactEntity entity = new ContactEntity();

        entity.setContactEmail(contact.getContactEmail());
        entity.setMessage(contact.getMessage());
        entity.setPhoneNo(contact.getPhoneNo());
        entity.setSubject(contact.getSubject());

        if (contact.getUser() != null) {
            UserEntity u = entityManager.find(UserEntity.class, contact.getUser().getUserId());
            entity.setUser(u);
        }

        entityManager.persist(entity);

        return entity.getContactId();
    }

    @Override
    public List<Contact> getMessagesOfUser(String userId) throws Exception {
        UserEntity user = entityManager.find(UserEntity.class, userId);
        Query query = entityManager.createQuery("select u from ContactEntity u where u.user =:user");
        query.setParameter("user", user);

        List<ContactEntity> entities = query.getResultList();
        List<Contact> contacts = null;

        if (entities != null && !entities.isEmpty()) {
            contacts = new ArrayList<>();

            Contact contact;
            for (ContactEntity entity : entities) {
                contact = new Contact();

                contact.setContactEmail(entity.getContactEmail());
                contact.setContactId(entity.getContactId());
                contact.setMessage(entity.getMessage());
                contact.setPhoneNo(entity.getPhoneNo());
                contact.setSubject(entity.getSubject());
                contact.setUser(new User());
                contact.getUser().setUserId(entity.getUser().getUserId());

                contacts.add(contact);
            }
        }
        return contacts;
    }
}
