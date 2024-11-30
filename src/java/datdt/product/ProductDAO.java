/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.product;

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
public class ProductDAO implements Serializable {

    public List<ProductDTO> getAllProducts() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> products = new ArrayList<>();

        try {
            con = DBHelper.getConnection(); // Assuming DBHelper provides a valid database connection
            if (con != null) {
                String sql = "SELECT sku, name, description, quantity, price, status "
                        + "FROM Product";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int sku = rs.getInt("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    boolean status = rs.getBoolean("status");

                    ProductDTO dto = new ProductDTO(sku, name, description, quantity, price, status);
                    products.add(dto);
                }
            }
        } finally {
            // Close all resources in reverse order of creation
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
        return products;
    }
}
