/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Do Dat
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String VIEW_YOUR_CART = "viewCart.jsp";
    
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String STARTUP_CONTROLLER = "StartUpServlet";
    private final String UPDATE_SERVLET = "UpdateServlet";
    private final String ADD_TO_CART = "AddToCartServlet";
    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "RemoveItemFromCartServlet";
    private final String CHECK_OUT_ORDER_SERVLET = "CheckOutOrderServlet";
    private final String ADD_ACCOUNT_CONTROLLER = "AddAccountServlet";
    private final String PRODUCT_CONTROLLER = "ProductServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
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
        //Which button did user click?
        String button = request.getParameter("btAction"); //phải cop thg param bỏ vô form của html kh làm ngược lại
        String url = LOGIN_PAGE;
        
        try {
            if (button == null) { //khi goi chua co parameter
                //noi check cookie
                //dieu phoi se chuyen qua servlet chuc nang
                url = STARTUP_CONTROLLER;
            } else if (button.equals("Login")) {//user click login link
                url = LOGIN_CONTROLLER;// mapping controller
            } else if (button.equals("Search")) {//user click search link
                url = SEARCH_LASTNAME_CONTROLLER;
            } else if (button.equals("Delete")) {//user click delete link
                url = DELETE_ACCOUNT_CONTROLLER;              
            } else if (button.equals("Update")) {//user click update
                url = UPDATE_SERVLET;
            } else if (button.equals("Add Book to Your Cart")) {//user click buy books
                url = ADD_TO_CART;
            } else if (button.equals("View Your Cart")) {
                url = VIEW_YOUR_CART;//la dynamic view vi khach hang mua do khac nhau
            } else if (button.equals("Remove Selected Item")) {
                url = REMOVE_ITEM_FROM_CART_CONTROLLER;
            } else if (button.equals("CheckOut")) {
                url = CHECK_OUT_ORDER_SERVLET;
            } else if (button.equals("Create Account")) {
                url = ADD_ACCOUNT_CONTROLLER;
            } else if (button.equals("ViewMarket")) {
                url = PRODUCT_CONTROLLER;
            } else if (button.equals("Logout")) {
                url = LOGOUT_CONTROLLER;
            }
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
