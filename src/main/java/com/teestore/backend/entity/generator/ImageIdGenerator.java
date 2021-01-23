package com.teestore.backend.entity.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        Connection connection = sharedSessionContractImplementor.connection();

        try {
            PreparedStatement preparedStatement = null;
            ResultSet set = null;
            String randomId = null;
            do {
                randomId = "I" + (int) (Math.round(Double.parseDouble((Math.random() * 99999 + 10000) + "")));
                preparedStatement = connection.prepareStatement("select * from Images where image_id=?");
                preparedStatement.setString(1, randomId);
                set = preparedStatement.executeQuery();
            } while (set.next());

            return randomId;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
