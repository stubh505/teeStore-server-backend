package com.teestore.backend.entity.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CartIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String prefix = "C";
        Connection connection = sharedSessionContractImplementor.connection();

        try {
            Statement statement = connection.createStatement();

            ResultSet set = statement.executeQuery("select count(cart_id) as Id from Cart");

            if (set.next()) {
                int id = set.getInt(1) + 1001;
                return prefix + id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}