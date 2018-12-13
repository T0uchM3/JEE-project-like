<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Insert title here</title>
<Link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	<div>
		<form action="controleur.php" method="post">

			<table>
				<tr>
					<td>Mot Cle</td>
					<td><input type="text" name="motCle" value="${model.motCle}" /></td>
					<td><input type="submit" name="action" value="chercher" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div>
		<form action="ControleurServlet" method="POST">
		<table>
			<tr>
				<td>REF:</td>
				<td>var</td>
				</tr>
				<tr>
				<td>Designation:</td>
				<td><input type="text" name="reg" vlaue"" size=20 " /></td>
				</tr>
				<tr>
				<td>Prix:</td>
				<td><input type="text" name="prix" vlaue"" size=20 /></td>
				</tr>
				<tr>
				<td>Quantite:</td>
				<td><input type="text" name="quantite" vlaue""/></td>
			</tr>
		</table>
		<input type="submit" name="action2" value="save" /><br>
		</form>
	</div>

	<div>
		<table class="table1">
			<tr>
				<th>REF</th>
				<th>DES</th>
				<th>PRIX</th>
				<th>QUANTITE</th>
			</tr>
			<c:forEach items="${model.produits }" var="p">
				<tr>
					<td>${p.reference}</td>
					<td>${p.designation}</td>
					<td>${p.prix}</td>
					<td>${p.quantite}</td>
					<td><a href="ControleurServlet.jsp?ref=${p.reference}">Supprimer</a></td>
				</tr>

			</c:forEach>

		</table>
	</div>



</body>
</html>