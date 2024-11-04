package com.sweetmanor.demo.jdbc;

import com.sweetmanor.common.Const;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class CallableStatementDemo {

    public String driver;
    public String url;
    public String user;
    public String password;

    public void initParam(String filePath) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(filePath));
        driver = prop.getProperty("driver");
        url = prop.getProperty("url");
        user = prop.getProperty("user");
        password = prop.getProperty("password");
    }

    public void callProcedure() throws ClassNotFoundException {
        Class.forName(driver);
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            CallableStatement cs = conn.prepareCall("{call add_pro(?,?,?)}");
            cs.setInt(1, 4);
            cs.setInt(2, 5);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            System.out.println("执行结果是" + cs.getInt(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        CallableStatementDemo ct = new CallableStatementDemo();
        ct.initParam(Const.classPath + "oracle.ini");
        ct.callProcedure();
    }

}
