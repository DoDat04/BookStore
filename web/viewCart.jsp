<%-- 
    Document   : viewCart
    Created on : Jun 17, 2024, 11:06:51 AM
    Author     : Do Dat
--%>

<%@page import="java.util.Map"%>
<%@page import="datdt.cart.CartBean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Market</title>
    </head>
    <body>
        <h1>Your cart include</h1>
        <c:if test="${not empty sessionScope.CART}">
            <c:set var="cart" value="${sessionScope.CART}" />
            <c:set var="items" value="${cart.items}" />
            <c:if test="${not empty items}">
                <form action="DispatchServlet">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Sku</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="entry" items="${items}">
                                <tr>
                                    <td>                                       
                                        ${entry.value.sku}
                                    </td>
                                    <td>
                                        ${entry.value.name}
                                    </td>
                                    <td>
                                        ${entry.value.description}
                                    </td>
                                    <td>
                                        ${entry.value.quantity}
                                    </td>
                                    <td>
                                        ${entry.value.price}
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${entry.key}" />                                             
                                    </td>
                                </tr>
                            </c:forEach>

                            <tr>
                                <td colspan="5">
                                    <a href="DispatchServlet?btAction=ViewMarket">Add more Books to cart</a>
                                </td>
                                <td>
                                    <input type="submit" name="btAction" value="Remove Selected Item"/>
                                </td>                                   
                            </tr>
                        </tbody>
                    </table>
                </form>
                <form action="DispatchServlet" method="POST">
                    Name <input type="text" name="txtCusName" value="" required /> <br/>
                    Email <input type="email" name="txtEmail" value="" required /> <br/>
                    Address <textarea rows="1" name="txtAddress" required ></textarea> <br/>
                    <input type="submit" name="btAction" value="CheckOut" />
                </form>
            </c:if>
        </c:if>   
        <c:if test="${empty sessionScope.CART.items}">
            <h2>
                <font color="red">
                No cart is existed!!!
                </font>
            </h2>
        </c:if>
        <%--<%
            //1. Cust goes to his/her cart place
            if (session != null) {
                //co items thi moi xem duoc
                //2. Cust takes his/her cart
                CartBean cart = (CartBean) session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
        %>
        <form action="DispatchServlet">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (String key : items.keySet()) {
                    %>
                    <tr>
                        <td> 
                            <%= ++count%> 
                        </td>
                        <td>
                            <%= key%>
                        </td>
                        <td>
                            <%= items.get(key)%>
                        </td>
                        <td>
                            <input type="checkbox" name="chkItem" value="<%= key%>">                                             
                        </td>
                    </tr>
                    <%
                        }//get all item from keyset

                    %>
                    <tr>
                        <td colspan="3">
                            <a href="market.html">Add more Books to cart</a>
                        </td>
                        <td>
                            <input type="submit" name="btAction" value="Remove Selected Item"/>
                        </td>                                   
                    </tr>
                </tbody>
            </table>
        </form>
        <form action="DispatchServlet" method="POST">
            Name <input type="text" name="txtCusName" value="" required /> <br/>
            Email <input type="email" name="txtEmail" value="" required /> <br/>
            Address <textarea rows="1" name="txtAddress" required ></textarea> <br/>
            <input type="submit" name="btAction" value="CheckOut" />
        </form>
        <%                        //4. Cust show all item
                        return;
                    }//items have existed
                }//cart has existed
            }//cart place has existed


        %>
        <h2>
            <font color="red">
            No cart is existed!!!
            </font>
        </h2>
        --%>
    </body>
</html>
