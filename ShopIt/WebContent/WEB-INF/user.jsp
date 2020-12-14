<%@page import="java.util.ArrayList"%>
<%@page import="entities.Item"%>
<%@ page import="util.Const"%>
<%@page import="entities.Category"%>
<%@page import="entities.Adresse"%>
<%@page import="entities.User"%>
<%@ page import="entities.User"%>

<%
	User user = (User) session.getAttribute("user");
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Item> items = (ArrayList<Item>) request.getAttribute("promoProduct");
%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
%>
<%
	// Recuperer l'utilisateur
	Adresse address = user.getShipAddress();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/accueil.css" type="text/css" />
</head>
<body>
	<div id="page">
		<header id="entete">
			<div id="blocUn">
				<%
					if (user != null) {
				%>
				<li id="loginState"><a href="#" id="user"><%=user.getFirstName()%></a>

				</li>
				<%
					}
				%>
				<a href="home?"><img src="images/logooo.png" alt=""></a>
				<div id="navig">
					<nav>
						<ul>
							<li><a href="categorie/categorie.html">CONTACT</a></li>
							<li><a href="login?"> <%
 	if (user != null) {
 %> DECONNEXION <%
 	} else
 %> CONNEXION
							</a></li>
						</ul>
					</nav>
					<div id="loupePanier">
						<div id="loupe">
							<form>
								<input type="text" placeholder="Recherche"> <img
									src="images/recherche.png" alt="">
							</form>

						</div>
						<div id="panier">
							<img src="images/pannier.png" alt="">

						</div>
					</div>
				</div>
			</div>

			<div id="blocDeux">
				<nav>

					<ul>
						<%
							if (categories != null && categories.size() > 0) {
								for (Category category : categories) {
						%>

						<li><a href="categoryByItem?category=<%=category.getId()%>"><%=category.getName()%></a></li>
						<%
							}
							}
						%>
					</ul>

				</nav>

			</div>

		</header>
		<main id="container">
			<div class="container">
				<div class="">
					<span class="">Panier</span> <span class="">Sommaire</span>
					<spanclass"">Compte</span> <span class="">Facture</span>
				</div>
				<div">
					<div class="">
						<h3 class="">Compte</h3>
					</div>
					<div class="">
						<fieldset>
							<div class="">
								<legend>Information Utilisateur</legend>
								<p>
									Nom:
									<%=user.getLastName()%></p>
								<p>
									Prenom:
									<%=user.getFirstName()%></p>
								<p>
									Email:
									<%=user.getEmail()%></p>
							</div>
						</fieldset>
						<fieldset class="col-sm-6 col-lg-6 col-md-6">
							<legend>Adresse de livraison</legend>
							<p>
								Numéro civique:
								<%=address.getNo()%></p>
							<%=((address.getAppartement() == null) ? "<p>Appartement: " + address.getAppartement() + "</p>" : "")%>
							<p>
								Rue:
								<%=address.getRue()%></p>
							<p>
								Code Postal:
								<%=address.getZip()%></p>
							<p>
								Ville:
								<%=address.getVille()%></p>
							<p>
								Province:
								<%=address.getEtat()%></p>
							<p>
								Pays:
								<%=address.getPays()%></p>
						</fieldset>
					</div>
				</div>
				<form action="cartuser" method="post"
					style="float: left; margin-right: 10px;">
					<button type="submit" name="toInvoice">
						Confirmer <span></span>
					</button>
				</form>
				<button type="submit" name="modify">Modifier</button>
			</div>
		</main>
	</div>

</body>
</html>