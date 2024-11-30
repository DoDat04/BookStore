/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.controller;

import datdt.registration.RegistrationDAO;
import datdt.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Do Dat
 */
public class LoginServlet extends HttpServlet {

    private final String SEARCH_PAGE = "search.jsp";
    //private final String INVALID_PAGE = "invalid.html";

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
        PrintWriter out = response.getWriter();

        String button = request.getParameter("btAction");
        String url = SEARCH_PAGE; //set default khi sai username pass
        
        
        try {
            //out.print("Username: " + username + " - " + password + " - " + button);
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            //In du lieu ra console de debug
            //System.out.println("Username: " + username + " - " + password + " - " + button);
            //get parameter tuong ung
           
            //call method of model
            //1. Goi new obj
            //2. Goi dc phuong thuc cua obj
            RegistrationDAO dao = new RegistrationDAO();
            RegistrationDTO result = dao.checkLogin(username, password);
            //3. xu li ket qua
            if (result != null) {
                url = SEARCH_PAGE;   
                //use session
                HttpSession session = request.getSession(); //tai vi lan dau dang nhap cua co session
                session.setAttribute("USER", result);
                session.setAttribute("USERNAME", username);
                
                //cho ghi nhan login thanh cong
                //sending cookies
                Cookie cookie = new Cookie(username, password);
                cookie.setMaxAge(60 * 30);
                response.addCookie(cookie);
            } else {
                request.setAttribute("ERROR", "Incorrect UserID or Password");
            }
            //
            //Yeu cau user click button
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
