<%-- 
    Document   : createAccount
    Created on : Jun 27, 2024, 10:50:34 AM
    Author     : Do Dat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
        <form action="DispatchServlet" method="POST">
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6 -30 chars)<br/>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                    ${errors.usernameLengthErr}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                    ${errors.usernameIsExisted}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" />(6 -30 chars)<br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                    ${errors.passwordLengthErr}
                </font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMacthed}">
                <font color="red">
                    ${errors.confirmNotMacthed}
                </font><br/>
            </c:if>
            Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" />(2 -30 chars)<br/>
            <c:if test="${not empty errors.fullnameLengthErr}">
                <font color="red">
                    ${errors.fullnameLengthErr}
                </font><br/>
            </c:if>
            <input type="submit" value="Create Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
