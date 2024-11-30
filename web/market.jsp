<%-- 
    Document   : market
    Created on : Jul 1, 2024, 8:53:29 PM
    Author     : Do Dat
--%>

<%@page import="datdt.product.ProductDTO"%>
<%@page import="datdt.product.ProductDAO"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Market</title>
        <style>
            #notification {
                display: none;
                position: fixed;
                top: 20px;
                left: 50%;
                transform: translateX(-50%);
                background-color: #4CAF50;
                color: white;
                padding: 15px;
                border-radius: 5px;
                z-index: 1000;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
            }
        </style>
    </head>
    <body>
        <div id="notification"></div>
        
        <h1>Book Market</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>SKU</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${requestScope.products}">
                    <tr>
                        <td>${product.sku}</td>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>${product.quantity}</td>
                        <td>${product.price}</td>
                        <td>${product.status ? 'available' : 'unavailable'}</td>
                        <td>
                            <form action="AddToCartServlet" method="post">
                                <input type="hidden" name="sku" value="${product.sku}" />
                                <input type="hidden" name="name" value="${product.name}" />
                                <input type="hidden" name="price" value="${product.price}" />
                                <input type="hidden" name="description" value="${product.description}" />
                                <input type="hidden" name="quantity" value="1" />
                                <input type="submit" value="Add Book to Your Cart" name="btAction" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br/>
        <form action="DispatchServlet">
            <input type="submit" value="View Your Cart" name="btAction" />
        </form>
        <a href="login.html">Click here back to Login Page</a>

        <script>
            window.onload = function() {
                var message = "<%= request.getAttribute("message") %>";
                if (message) {
                    var notification = document.getElementById('notification');
                    notification.textContent = message;
                    notification.style.display = 'block';
                    setTimeout(function() {
                        notification.style.display = 'none';
                    }, 3000);
                }
            };
        </script>
    </body>
</html>
