package com.teestore.backend.entity.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddressIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        String prefix = "A";
        Connection connection = sharedSessionContractImplementor.connection();

        try {
            Statement statement = connection.createStatement();

            ResultSet set = statement.executeQuery("select max(address_id) as Id from Address");

            if (set.next()) {
                int id = Integer.parseInt(set.getString(1).substring(1)) + 1;
                return prefix + id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
