/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbank.processor;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author akanniw
 */
public class GetConnection {

    /** Uses JNDI and Datasource (preferred style).   */
    static Logger logger = null;

//    public Connection getJNDIConnection() {
//        Connection result = null;
//        try {
//            result = DatabaseService.getConnection();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
//    }

    public DataSource getPrConn() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("jdbc/AcctServiceDatasource");
    }

//    public DataSource getPrFundsTDConn() throws NamingException {
//        Context c = new InitialContext();
//        return (DataSource) c.lookup("jdbc/FundsTDDatasource");
//    }

//    public static Connection getFCCConnection() {
//        Connection connection = null;
//        OracleDataSource oracleDataSource = null;
//        try {
//            javax.naming.InitialContext ctx = new javax.naming.InitialContext();
//            DataSource ds = (DataSource) ctx.lookup("jdbc/FundsTDDatasource");
//            oracleDataSource = ds.unwrap(OracleDataSource.class);
//            connection = oracleDataSource.getConnection();
//        } catch (SQLException ex) {
//            logger.fatal(ex.getMessage());
//        } catch (NamingException ex) {
//            logger.fatal(ex.getMessage());
//        }
//        return connection;
//    }
}
