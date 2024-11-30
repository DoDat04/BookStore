/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.orderdetail;

import datdt.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Do Dat
 */
public class OrderDetailDAO implements Serializable{    
    public boolean addOrderDetail(OrderDetailDTO orderdetail)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. get connection
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create SQL string
                String sql = "Insert Into OrderDetail (productId, unitPrice, quantity, orderId, total) "
                        + "Values (?, ?, ?, ?, ?)";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderdetail.getProductId());
                stm.setFloat(2, orderdetail.getUnitPrice());
                stm.setInt(3, orderdetail.getQuantity());
                stm.setString(4, orderdetail.getOrderId());
                stm.setFloat(5, orderdetail.getTotal());
                //4. Execute query
                int affectedRows = stm.executeUpdate();
                //5. Process result
                if (affectedRows > 0) {
                    result = true;
                } // Insert successfully
            }// Connect successfully

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }  
}
