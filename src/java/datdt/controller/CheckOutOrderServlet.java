/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.controller;

import datdt.cart.CartBean;
import datdt.order.OrderDAO;
import datdt.orderdetail.OrderDetailDAO;
import datdt.orderdetail.OrderDetailDTO;
import datdt.product.ProductDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "CheckOutOrderServlet", urlPatterns = {"/CheckOutOrderServlet"})
public class CheckOutOrderServlet extends HttpServlet {

    private final String VIEW_ORDER_PAGE = "viewOrder.jsp";

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
        String url = VIEW_ORDER_PAGE;
        String custName = request.getParameter("txtCusName");
        String custEmail = request.getParameter("txtEmail");
        String custAddress = request.getParameter("txtAddress");

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                CartBean cart = (CartBean) session.getAttribute("CART");
                if (cart != null) {
                    Map<Integer, ProductDTO> items = cart.getItems();
                    if (items != null) {                       
                        float totalPrice = cart.getTotalPrice();
                        int totalQuantity = cart.getTotalQuantity();
                        OrderDAO dao = new OrderDAO();
                        String orderId = dao.createOrder(custName, custEmail, custAddress, totalQuantity);

                        // Add each item in the cart to the OrderDetail table
                        OrderDetailDAO detailDao = new OrderDetailDAO();
                        for (ProductDTO product : items.values()) {
                            OrderDetailDTO orderDetail = new OrderDetailDTO(
                                product.getSku(),
                                product.getPrice(),
                                product.getQuantity(),
                                orderId,
                                product.getPrice() * product.getQuantity()
                            );
                            detailDao.addOrderDetail(orderDetail);
                        }

                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        String orderDate = sdf.format(new Date()); // Current date and time formatted
                        // Store order date in session
                        session.setAttribute("ORDER_DATE", orderDate);

                        // Store cart items in session for display in viewOrder.jsp
                        session.setAttribute("CART_ITEMS", items.values());
                        // Set customer information in session attributes
                        session.setAttribute("CUST_NAME", custName);
                        session.setAttribute("CUST_EMAIL", custEmail);
                        session.setAttribute("CUST_ADDRESS", custAddress);
                        // Set order ID as request attribute to display in viewOrder.jsp
                        session.setAttribute("ORDER_ID", orderId);
                        // Set total quantity and price as request attribute to display in viewOrder.jsp
                        session.setAttribute("TOTAL_QUANTITY", totalQuantity);
                        session.setAttribute("TOTAL_PRICE", totalPrice);
                        // Remove cart from session
                        session.removeAttribute("CART");
                    }
                }
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {          
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
     * @param response servlet response)
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
        return "CheckOutOrderServlet handles the checkout process";
    }// </editor-fold>
}
