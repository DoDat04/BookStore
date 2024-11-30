/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.controller;

import datdt.cart.CartBean;
import datdt.product.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "RemoveItemFromCartServlet", urlPatterns = {"/RemoveItemFromCartServlet"})
public class RemoveItemFromCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //1. Cust goes to his/her carts place
            HttpSession session = request.getSession(false); //do chi la ao giac o client
            if (session != null) {
                //2. Cust takes his/her cart
                CartBean cart = (CartBean)session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets items
                    Map<Integer, ProductDTO> items = cart.getItems();
                    if (items != null) {
                        //4. Cust chooses remove some item
                        //tao thanh mang kieu string
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if (selectedItems != null) {
                            for(String item : selectedItems) {
                                cart.removeItemFromCart(item);
                            }//each item is process
                            session.setAttribute("CART", cart);//gio hang trong tay minh nen can setAtt
                        }//user choose at least 1 item to remove     
                    }//items have existed
                }//cart has existed                               
            }//check co cart place thi moi xoa
            
        } finally {
            //5. refresh by calling previous function again using url rewriting
            String urlRewriting = "DispatchServlet"
                    + "?btAction=View Your Cart";
            response.sendRedirect(urlRewriting);//neu dung req dispatcher se tao ra array trung btAction
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
