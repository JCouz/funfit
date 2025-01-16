<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of Batches</title>
</head>
<body>
    <h1>List of Batches</h1>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Day of Week</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="batch" items="${listBatch}">
                <tr>
                    <td>${batch.batchId}</td>
                    <td>${batch.batchName}</td>
                    <td>${batch.startTime}</td>
                    <td>${batch.endTime}</td>
                    <td>${batch.dayOfWeek}</td>
                    <td>
                        <a href="batch?action=edit&id=${batch.batchId}">Edit</a>
                        <a href="batch?action=delete&id=${batch.batchId}">Delete</a>
                        <a href="participant?action=listByBatch&batchId=${batch.batchId}">View Participants</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href="add-batch.html">Add New Batch</a>
</body>
</html>