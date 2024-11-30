/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.order;

import datdt.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

/**
 *
 * @author Do Dat
 */
public class OrderDAO implements Serializable{       
    public String createOrder(String custName, String custEmail, String custAddress, int orderQuantity) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String orderId = null;
        
        try {
            con = DBHelper.getConnection();

            // Generate new order ID
            String sqlGetOrderId = "SELECT MAX(id) AS lastOrderId FROM [Order]";
            stm = con.prepareStatement(sqlGetOrderId);
            rs = stm.executeQuery();
            if (rs.next()) {
                String lastOrderId = rs.getString("lastOrderId");
                if (lastOrderId != null) {
                    int newId = Integer.parseInt(lastOrderId.substring(2)) + 1;
                    orderId = String.format("Od%03d", newId);
                } else {
                    orderId = "Od001";
                }
            }

            // Insert order details into database
            String sqlInsertOrder = "INSERT INTO [Order](id, date, customer, address, email, total) VALUES(?, GETDATE(), ?, ?, ?, ?)";
            stm = con.prepareStatement(sqlInsertOrder);
            stm.setString(1, orderId);
            stm.setString(2, custName);
            stm.setString(3, custAddress);
            stm.setString(4, custEmail);
            stm.setInt(5, orderQuantity); // Assign orderQuantity to total
            stm.executeUpdate();
            
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }        
        return orderId;
    }          
}
