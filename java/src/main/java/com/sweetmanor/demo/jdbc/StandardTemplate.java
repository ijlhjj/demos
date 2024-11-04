package com.sweetmanor.demo.jdbc;

import com.sweetmanor.common.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StandardTemplate {
    private static final Logger logger = LogManager.getLogger();

    private DataSource dataSource;

    public Person getPersonById(long id) {
        String sql = "select id, name, age, sex from persons where id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            Person person = null;
            if (rs.next()) {
                person = new Person();
                person.setName(rs.getString("name"));
                person.setAge(rs.getInt("age"));
                person.setSex(rs.getString("sex"));
            }

            return person;
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }

        return null;
    }

}
