/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Do Dat
 */
public class DBHelper {
    public static Connection getConnection() throws NamingException , SQLException { //quen adddriver hoac viet tam bay neu kh go dc  
        
        //1. get current context
        Context context = new InitialContext();
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        DataSource ds = (DataSource)tomcatContext.lookup("DS007");
        Connection con = ds.getConnection();
        return con;
        //2. lookup
        
        //1.Phai load driver (driver la tinh)
        //go com .... voi SQLServerDriver xong xuoi moi them " "
        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        //2.Phai co dia chi cua DBMS (tao connection string)
        //String url = "jdbc:sqlserver://" //enter xuong hang
                    //+ "localhost:1433;"
                    //+ "databaseName=se1824";
        //go dc protocol jdbc:sqlserver://ip:port;databaseName=<dbName>
        //phai get connection tu driver manager
        //Connection con = DriverManager.getConnection(url, "sa", "12345");
        
        //3.Tra lai connection
        //return con;
    }
}
