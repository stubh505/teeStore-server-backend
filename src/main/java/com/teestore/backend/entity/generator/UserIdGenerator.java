package com.teestore.backend.entity.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String prefix = "U";
        Connection connection = sharedSessionContractImplementor.connection();

        try {
            Statement statement = connection.createStatement();

            ResultSet set = statement.executeQuery("select count(user_id) as Id from Users");

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