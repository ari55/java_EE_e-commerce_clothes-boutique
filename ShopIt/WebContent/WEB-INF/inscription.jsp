<%@page import="java.util.ArrayList"%>
<%@page import="entities.Item"%>
<%@ page import="util.Const"%>
<%@page import="entities.Category"%>
<%@page import="java.util.HashMap"%>
<%@page import="action.InscriptionAction"%>
<%@page import="entities.Panier"%>
<%@page import="action.PanierAction"%>
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
<%
	Panier cart = (Panier) session.getAttribute("cart");
%>
<%
	@SuppressWarnings("unchecked")
	HashMap<String, String> cleEtValeurDesParametresDuFormulaire = (HashMap<String, String>) request
			.getAttribute("cleEtValeurDesParametresDuFormulaire");
	String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/accueil.css" type="text/css" />
<link rel="stylesheet" href="css/inscription.css" type="text/css" />

</head>
<body>
	<%
		PanierAction.init(request, response);
	%>
	<%
		if (error != null) {
			if (error.equals("accountExisting")) {
	%>
	<div class="alert alert-info">Un compte existe déjà pour cette
		adresse email.</div>
	<%
		} else if (error.equals("DBProblem")) {
	%>
	<div class="alert alert-danger">Une erreur de connexion c'est
		produite. Veuillez attendre quelques temps avant de faire une nouvelle
		tentative. Si vous voyez ce message pour la deuxième fois, veuillez
		contactez l'administrateur du site pour lui informer du problème.</div>
	<%
		}
		}
	%>
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
								<input type="text" placeholder="" value="<%=cart.getSize()%>"
									readonly="readonly">
							</div>
							<a href="panier?"><img src="images/pannier.png" alt=""></a>
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
			<form action="inscription" method="post"
				style="border: 1px solid #ccc">
				<div class="container">
					<h1>Inscription</h1>
					<p>Veuillez remplir le formulaire.</p>
					<hr>
					<label for="firstName"><b>Prenom</b></label> <input type="text"
						placeholder="Firstname" name="firstName"
						value="<%=InscriptionAction.getOrDefault(cleEtValeurDesParametresDuFormulaire, "firstname", "")%>"
						required>

					<labelfor="lastname"> <b>Nom</b> </label> <input type="text"
						placeholder="Lastname" name="lastName"
						value="<%=InscriptionAction.getOrDefault(cleEtValeurDesParametresDuFormulaire, "lastName", "")%>"
						required> <label for="email"><b>Email</b></label> <input
						type="text" placeholder="Enter Email" name="email"
						value="<%=InscriptionAction.getOrDefault(cleEtValeurDesParametresDuFormulaire, "email", "")%>"
						required> <label for="confirmEmail"><b>
							Confirm Email</b></label> <input type="text" placeholder="Confirm Email"
						name="confirmEmail"
						value="<%=InscriptionAction.getOrDefault(cleEtValeurDesParametresDuFormulaire, "confirmEmail", "")%>"
						required> <label for="psw"><b>Password</b></label> <input
						type="password" placeholder="Enter Password" name="password"
						value="<%=InscriptionAction.getOrDefault(cleEtValeurDesParametresDuFormulaire, "password", "")%>"
						required> <label for="psw-repeat"><b>Confirmation
							Password</b></label> <input type="password" placeholder=" Password"
						name="confirmPassword"
						value="<%=InscriptionAction.getOrDefault(cleEtValeurDesParametresDuFormulaire, "confirmPassword", "")%>"
						required> <label for="numeroCivique"><b>Numero
							civique</b></label> <input type="text" placeholder="Numero civique"
						name="addr_no"
						value="<%=InscriptionAction.getOrDefault(cleEtValeurDesParametresDuFormulaire, "addr_no", "")%>"
						required> <label for="appartement"><b>Appartement</b></label>
					<input type="text" placeholder="Appartement" name="addr_appt"
						value="<%=InscriptionAction.getOrDefault(cleEtValeurDesParametresDuFormulaire, "addr_appt", "")%>"
						required> <label for="rue"><b>Rue</b></label> <input
						type="text" placeholder="Rue" name="addr_street"
						value="<%=InscriptionAction.getOrDefault(cleEtValeurDesParametresDuFormulaire, "addr_street", "")%>"
						required> <label for="codePostal"><b>Code
							Postal</b></label> <input type="text" placeholder="Code Postal"
						name="addr_zip"
						value="<%=InscriptionAction.getOrDefault(cleEtValeurDesParametresDuFormulaire, "addr_zip", "")%>"
						required> <label for="ville"><b>Ville</b></label> <input
						type="text" placeholder="Ville" name="addr_city"
						value="<%=InscriptionAction.getOrDefault(cleEtValeurDesParametresDuFormulaire, "addr_city", "")%>"
						required> <label for="etat"><b>Etat</b></label> <input
						type="text" placeholder="Etat" name="addr_state"
						value="<%=InscriptionAction.getOrDefault(cleEtValeurDesParametresDuFormulaire, "addr_state", "")%>"
						required> <label for="pays"><b>Pays</b></label> <input
						type="text" placeholder="Pays" name="addr_country"
						value="<%=InscriptionAction.getOrDefault(cleEtValeurDesParametresDuFormulaire, "addr_country", "")%>"
						required> <label> <input type="checkbox"
						checked="checked" name="remember" style="margin-bottom: 15px">
						Remember me
					</label> <%
 	if (request.getParameter("fromCart") != null) {
 %> <input type="hidden" name="fromCart" value="true"> <%
 	}
 %>
					<div>
						<button type="submit" class="">S'inscrire</button>
					</div>
				</div>
			</form>

		</main>
	</div>

</body>
</html>