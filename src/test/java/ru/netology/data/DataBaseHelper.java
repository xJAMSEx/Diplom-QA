package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseHelper {

    private static final QueryRunner runner = new QueryRunner();

    private DataBaseHelper() {
    }

    @SneakyThrows
    public static Connection getConn() {
        String dbUrl = System.getProperty("db.url");
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        return DriverManager.getConnection (dbUrl, login, password);
    }

    @SneakyThrows
    public static DataHelper.StatusPayment getStatusPayment() {
        String statusSQL = "SELECT status FROM payment_entity";
        Connection conn = getConn();
        return runner.query(conn, statusSQL, new BeanHandler<>(DataHelper.StatusPayment.class));
    }

    @SneakyThrows
    public static DataHelper.StatusCredit getStatusCredit() {
        String statusSQL = "SELECT status FROM credit_request_entity";
        Connection conn = getConn();
        return runner.query(conn, statusSQL, new BeanHandler<>(DataHelper.StatusCredit.class));
    }

}
