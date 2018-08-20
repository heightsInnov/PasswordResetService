package com.unionbank.processor;

import java.util.Properties;

public class DatabaseService {

    private static Properties connProps = new Properties();
    private static Properties connPropsPOS = new Properties();
    private static Properties connPropsAlert = new Properties();
    private static String dbURL;
    private static String dbURLPOS;
    private static String dbURLAlert;

    private DatabaseService() {
    }

//    public static void init() throws Exception {
//        Properties props = PropertiesFileReader.getProperties("posting.properties");
//        String driver = props.getProperty("JDBC_DRIVER_CLASS");
//        String username = props.getProperty("JDBC_USERNAME");
//        String pwd = props.getProperty("JDBC_PASSWORD");
//        dbURL = props.getProperty("JDBC_DATABASE_URL");
//        System.out.println("JDBC URL is " + dbURL);
//        connProps.put("user", username);
//        connProps.put("password", pwd);
//        try {
//            Class.forName(driver);
//        } catch (java.lang.Exception ex) {
//            System.err.print(ex.getMessage());
//            ex.printStackTrace();
//        }
//    }
//
//    public static Connection getConnection() throws SQLException {
//        if ((dbURL == null) || (connProps == null)) {
//            try {
//                init();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return DriverManager.getConnection(dbURL, connProps);
//    }
//
//    public static void releaseConnection(Connection conn) {
//        try {
//            conn.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}
