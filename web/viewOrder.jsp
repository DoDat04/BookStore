<%-- 
    Document   : viewOrder
    Created on : Jun 25, 2024, 5:17:54 PM
    Author     : Do Dat
--%>

<%@page import="datdt.product.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order</title>
    </head>
    <body>
        <h1>Your Order Details</h1>
        <table border="1">
            <thead>
                <tr>
                    <th colspan="2">Order Information</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Order ID:</td>
                    <td>${sessionScope.ORDER_ID}</td>
                </tr> 
                <tr>
                    <td>Date:</td>
                    <td>${sessionScope.ORDER_DATE}</td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td>${sessionScope.CUST_NAME}</td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>${sessionScope.CUST_EMAIL}</td>
                </tr>
                <tr>
                    <td>Address:</td>
                    <td>${sessionScope.CUST_ADDRESS}</td>
                </tr>
            </tbody>
        </table>
        <br/>

        <table border="1">
            <thead>
                <tr>
                    <th colspan="4">Product Information</th>
                </tr>
                <tr>
                    <th>Sku</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${sessionScope.CART_ITEMS}">
                    <tr>
                        <td>
                            ${product.sku}
                        </td>
                        <td>
                            ${product.name}
                        </td>
                        <td>
                            ${product.quantity}
                        </td>
                        <td>
                            ${product.price * product.quantity}
                        </td>                       
                    </tr>                   
                </c:forEach>
                <tr>
                    <td colspan="2">Total:</td>
                    <td>${sessionScope.TOTAL_QUANTITY}</td>
                    <td>${sessionScope.TOTAL_PRICE}</td>
                </tr>
            </tbody>
        </table>
        <a href="ProductServlet">Return to Market</a>        
    </body>
</html>
