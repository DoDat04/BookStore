/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.registration;

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
public class RegistrationDAO implements Serializable {

//    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
    public RegistrationDTO checkLogin(String username, String password) throws SQLException, NamingException {
        //Khoi tao gan = null va dong lai bang moi cach
        Connection con = null;
        PreparedStatement stm = null;//khai bao theo chieu thuan, dong theo chieu nguoc
        ResultSet rs = null;
        RegistrationDTO result = null;

        try {//11
            con = DBHelper.getConnection();
            if (con != null) {//11
                String sql = "Select lastname, isAdmin " //menh de phai viet tren 1 dong va enter xuong dong, buoc 12
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ? ";

                stm = con.prepareStatement(sql); //truyen 2 tham so tu trai sang phai tinh la 1, da nap roi
                //Viet sql phai coi co tham so hay khong
                stm.setString(1, username);
                stm.setString(2, password); //xay ra loi khi set sai tham so, du tham so, kieu du lieu

                rs = stm.executeQuery(); //khong truyen tham so, buoc 12
                //toi da la 1 dong toi thieu la 0 dong do username la primary key
                if (rs.next()) { //ktra username va password co ton tai khong, buoc 13
                    //Map
                    //Get
                    String fullName= rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //Set
                    result = new RegistrationDTO(username,"", fullName, role); //khong truyen password
                }//buoc 13
            }

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
        //1.connect database
        //1.1
        //phai co cau lenh sql
        //tao ra statement object
        //execute query de co kq xu li
        //process result

        return result;
    }

    //du lieu kieu List<RegistrationDTO>
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;//khai bao theo chieu thuan, dong theo chieu nguoc
        ResultSet rs = null;

        try {//11
            con = DBHelper.getConnection();
            if (con != null) {//11
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";

                stm = con.prepareStatement(sql); //truyen 2 tham so tu trai sang phai tinh la 1, da nap roi
                stm.setString(1, "%" + searchValue + "%");

                rs = stm.executeQuery(); //khong truyen tham so, buoc 12
                while (rs.next()) {
                    //map data
                    //get du lieu tu resultset
                    //set data to DTO properties
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role);
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }//neu list ko ton tai tao list moi
                    this.accounts.add(dto);
                }
            }

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
    }

    public boolean deleteAccount(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;//khai bao theo chieu thuan, dong theo chieu nguoc
        boolean result = false;

        try {//11
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Delete From Registration "
                        + "Where username = ?"; // prepareStm
                //3. create Statement Object
                stm = con.prepareStatement(sql);
                // 2 ? => 2 tham so 
                stm.setString(1, username);
                // loi -> truyen it thamm so, hoac nhieu tham so honw
                //4. Execute Query
                int affectedRows = stm.executeUpdate();
                //5. Process result
                if (affectedRows > 0) {
                    result = true;
                }
            } // end connection is available

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

    public boolean updateAccount(String username, String password, boolean isRole) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "UPDATE Registration "
                        + "SET password = ?, isAdmin = ? "
                        + "WHERE username = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isRole);
                stm.setString(3, username);
                //4. Execute Query
                int affectedRows = stm.executeUpdate();
                //5. Process result
                if (affectedRows > 0) {
                    result = true;
                }
            }
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
    
    public boolean createAccount(RegistrationDTO dto) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;//khai bao theo chieu thuan, dong theo chieu nguoc
        boolean result = false;

        try {//11
            con = DBHelper.getConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "INSERT Into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") VALUES("
                        + "?, ?, ?, ?"
                        + ")"; // prepareStm
                //3. create Statement Object
                stm = con.prepareStatement(sql);
                // 2 ? => 2 tham so 
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                //4. Execute Query
                int affectedRows = stm.executeUpdate();
                //5. Process result
                if (affectedRows > 0) {
                    result = true;
                }
            } // end connection is available

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
