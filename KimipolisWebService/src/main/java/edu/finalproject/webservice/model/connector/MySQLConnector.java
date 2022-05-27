package edu.finalproject.webservice.model.connector;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class that represents a MySQL Connector.
 */
public class MySQLConnector {

    @Setter
    @Getter
    Properties prop = new Properties();

    public MySQLConnector() {
        try {
            //Loads all the properties of file "config.properties".
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the connection object for a MySQL DDBB
     * @return a {@link Connection}
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        try {

            //Indicates which driver is going to be used.
            Class.forName(prop.getProperty(MySQLConstant.DRIVER));

            //Creates the connection based on the obtained URL.
            return  DriverManager.getConnection(getURL());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Obtains the URL to connect to a MySQL DDBB.
     * @return an URL
     */
    private String getURL() {
        //jdbc:mysql://localhost:3306/world?user=sa&password=adminPassword&useSSL=false;
        return new StringBuilder().append(prop.getProperty(MySQLConstant.URL_PREFIX))
                .append(prop.getProperty(MySQLConstant.URL_HOST)).append(":")
                .append(prop.getProperty(MySQLConstant.URL_PORT)).append("/")
                .append(prop.getProperty(MySQLConstant.URL_SCHEMA)).append("?user=")
                .append(prop.getProperty(MySQLConstant.USER)).append("&password=")
                .append(prop.getProperty(MySQLConstant.PASSWD)).append("&useSSL=")
                .append(prop.getProperty(MySQLConstant.URL_SSL)).append(("&allowPublicKeyRetrieval="))
                .append(prop.getProperty(MySQLConstant.ALLOW_PUBLIC_KEY_RETRIEVAL)).append(("&useJDBCCompliantTimezoneShift="))
                .append(prop.getProperty(MySQLConstant.USE_JDBC_COMPLIANT_TIMEZONE_SHIFT)).append(("&useLegacyDatetimeCode="))
                .append(prop.getProperty(MySQLConstant.USE_LEGACY_DATE_TIME_CODE)).append(("&serverTimezone="))
                .append(prop.getProperty(MySQLConstant.SERVER_TIMEZONE)).toString();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySQLConnector connector = new MySQLConnector();
        Connection connection = connector.getMySQLConnection();
        System.out.println(connection.getCatalog());
    }
}
