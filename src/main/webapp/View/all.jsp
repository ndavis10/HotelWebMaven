<%-- 
    Document   : all
    Created on : Feb 9, 2015, 8:31:31 PM
    Author     : viewt_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/materialize.min.css" rel="stylesheet" type="text/css"/>
        <title>Hotel List</title>
    </head>
    <body>
        <div class="container">
            <h1>All Hotels</h1>
            <div class="row">
                <a href="Create">Create New</a>
            </div>
            <table class="striped responsive-table">
                <tr>
                    <th>Name</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Postal Code</th>
                    <th>Notes</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="hotel" items="${list}">
                    <tr>
                        <td>${hotel.hotelName}</td>
                        <td>${hotel.streetAddress}</td>
                        <td>${hotel.city}</td>
                        <td>${hotel.state}</td>
                        <td>${hotel.postalCode}</td>
                        <td>${hotel.notes}</td>
                        <td><a href="Edit?id=${hotel.hotelID}">Edit</a> | <a href="Delete?id=${hotel.hotelID}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
        <script src="js/materialize.min.js" type="text/javascript"></script>
    </body>
</html>
