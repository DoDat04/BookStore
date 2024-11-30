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
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {
    private final String MARKET_PAGE = "ProductServlet";
    
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
        String url = MARKET_PAGE;
        int sku = Integer.parseInt(request.getParameter("sku"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        float price = Float.parseFloat(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        try {            
            //1. Cust goes to cart place
            HttpSession session = request.getSession(); // bat buoc check       
            //2. Cust takes his/her cart (ko ton tai --> new cart)
            CartBean cart = (CartBean)session.getAttribute("CART");
            if(cart == null) {
                cart = new CartBean();
            }
            //3. Cust drops an item to cart
            //Du lieu tu client ve server la req param
            ProductDTO item = new ProductDTO(sku, name, description, quantity, price, true);
            cart.addItemTocart(item);
            session.setAttribute("CART", cart);//neu chi get thi tren scope no chua duoc update, phai update cho no  
            request.setAttribute("message", "Add product with sku " + sku + " to cart successfully! New quantity: " + item.getQuantity());
        } finally {
            url = "DispatchServlet?btAction=ViewMarket";
            //4. Cust continues going shopping
            //di shopping tiep la qlai trang 
            request.getRequestDispatcher(url).forward(request, response);//tai vi du lieu van con nam o session cho du da tra response
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
