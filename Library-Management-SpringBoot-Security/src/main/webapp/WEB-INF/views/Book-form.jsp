<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>Save Book</title>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="/LibraryManagement/books/list">Books Directory</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"> </span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item "><a class="nav-link"
					href="/LibraryManagement/books/showFormForAdd">New Book <span class="sr-only">(current)</span></a>
				</li>
				<!--<li class="nav-item"><a class="nav-link"
					href="@{/tickets/addTicket}">New Ticket</a></li>-->
			</ul>
		</div>
	</nav>
<br>
<br>
	<div class="container">

		<h3>Book Directory</h3>
		<hr>

		<p class="h4 mb-4">Enter Book Details</p>

		<form action="/LibraryManagement/books/save" method="POST">

			<!-- Add hidden form field to handle update -->
			<input type="hidden" name="id" value="${Book.id}" />

			<div class="form-inline">
				<input type="text" name="name" value="${Book.name}"
					class="form-control mb-4 col-4" placeholder="Name" required="required">



			</div>

			<div class="form-inline">

				<input type="text" name="category" value="${Book.category}"
					class="form-control mb-4 col-4" placeholder="Category" required="required">



			</div>

			<div class="form-inline">

				<input type="text" name="author" value="${Book.author}"
					class="form-control mb-4 col-4" placeholder="Author" required="required">



			</div>

			<button type="submit" class="btn btn-info col-2">Save</button>

		</form>
<br>
<br>
		<hr>
		<a href="/LibraryManagement/books/list">Back to Books List</a>

	</div>
</body>

</html>










