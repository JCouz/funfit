<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of Participants</title>
</head>
<body>
	<h1>List of Participants</h1>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Phone Number</th>
				<th>Batch ID</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="participant" items="${listParticipant}">
				<tr>
					<td>${participant.participantId}</td>
					<td>${participant.name}</td>
					<td>${participant.email}</td>
					<td>${participant.phoneNumber}</td>
					<td>${participant.batchId}</td>
					<td><a
						href="participant?action=edit&id=${participant.participantId}">Edit</a>
						<a
						href="participant?action=delete&id=${participant.participantId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a href="add-participant.html">Add New Participant</a>
</body>
</html>