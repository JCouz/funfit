<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Participants in Batch</title>
</head>
<body>
    <h1>Participants in Batch</h1>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone Number</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="participant" items="${participantsInBatch}">
                <tr>
                    <td>${participant.participantId}</td>
                    <td>${participant.name}</td>
                    <td>${participant.email}</td>
                    <td>${participant.phoneNumber}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>