<%@page import="java.util.ArrayList"%>
<%@page import="entities.Item"%>
<%@ page import="util.Const"%>
<%@page import="entities.Category"%>
<%@page import="java.util.HashMap"%>
<%@page import="action.InscriptionAction"%>
<%@ page import="entities.User"%>


<%
User user = (User)session.getAttribute("user");
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

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/accueil.css" type="text/css" />
<link rel="stylesheet" href="css/login.css" type="text/css" />

</head>
<body>
	<div id="page">
		<header id="entete">
			<div id="blocUn">
<% 
			if (user != null) {
				%>
				<li id="loginState" >
					<a href="#" id="user"><%=user.getFirstName()%></a>
				
				</li>
				<% 
			}
			%>
				<a href="home?"><img src="images/logooo.png" alt=""></a>
				<div id="navig">
					<nav>
						<ul>
							<li><a href="contact?">CONTACT</a></li>
<li><a href="login?"> <%
 	if (user != null) {
 %> DECONNEXION <%
 	} else
 %> CONNEXION
							</a></li>						</ul>
					</nav>
					<div id="loupePanier">
						<div id="loupe">
							<form>
								<input type="text" placeholder="Recherche"> <img
									src="images/recherche.png" alt="">
							</form>

						</div>
						<div id="panier">
							<div>
								<input type="text" placeholder="0" readonly="readonly">
							</div>
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
		
		<%
		if (request.getParameter("fromCart") != null) {
	%>
	<div class="progress-cart">
		<span class="label label-success">Panier</span> <span
			class="label label-success">Sommaire</span> <span
			class="label label-warning">Compte</span> <span
			class="label label-warning">Facture</span>
	</div>
	<%
		}
	%>
		<%
		@SuppressWarnings("unchecked")
		HashMap<String, String> cleValeurLogin = (HashMap<String, String>) request.getAttribute("cleValeurLogin");
		@SuppressWarnings("unchecked")
		HashMap<String, String> cleValeurErreur = (HashMap<String, String>) request.getAttribute("cleValeurErreur");

		if (cleValeurLogin == null)
			cleValeurLogin = new HashMap<String, String>();

		if (request.getAttribute("logout") != null) {
	%>
	<div class="alert alert-success" role="alert">Vous êtes
		maintenant déconnecté.</div>
	<%
		}
		
		
		if (cleValeurErreur != null && !cleValeurErreur.get("loginState").equals("ok")) {
			%>
			<div class="">
				<div class="">
					<h3 class="">Login Invalide</h3>
				</div>
				<ul class="">
					<%
						if (cleValeurErreur != null && cleValeurErreur.get("loginState").equals("mauvaisEmailOuMotDePasse")) {
					%>
					<li style="margin-bottom: 0px; white-space: pre-line;">Email ou Mot de passe incorrect</li>
					<%
						} else if (cleValeurErreur != null && cleValeurErreur.get("loginState").equals("incorrect")) {
								if (cleValeurErreur != null && cleValeurErreur.containsKey("login")) {
					%>
					<li style="margin-bottom: 0px; white-space: pre-line;"><%=cleValeurErreur.get("login")%></li>
					<%
						}

								if (cleValeurErreur != null && cleValeurErreur.containsKey("password")) {
					%>
					<li style="margin-bottom: 0px; white-space: pre-line;"><%=cleValeurErreur.get("password")%></li>
					<%
						}
							}
					%>
				</ul>
			</div>
			<%
				}
			%>

			<div class="form">
				<form class="login-form" action="login" method="post">
					<input type="email" name="login" id="login" placeholder="email"
						value="<%=InscriptionAction.getOrDefault(cleValeurLogin, "login", "")%>" /> <input
						type="password" id="password" class="" name="password" required />
					<button
						type="subm
					<%if (request.getParameter("fromCart") != null) {%>
			<input type="hidden" name="fromCart" value="true">
<%}%>it"
						class="btn btn-default">Connexion</button>

					<p class="message">
						Not registered? <a href="inscription?">Create an account</a>
					</p>
				</form>
			</div>

		</main>
	</div>

</body>
</html>