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



<%
	@SuppressWarnings("unchecked")
	ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
%>
<%
	Panier cart = (Panier) session.getAttribute("cart");
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
		<main class="formcontainer">
			<form action="inscription" method="post"
				style="border: 1px solid #ccc">
				<div class="container">
					<h1>Contactez nous</h1>
					<p>Veuillez remplir le formulaire.</p>
					<hr>
					<label for="firstname"><b>Prenom</b></label> <input type="text"
						placeholder="Firstname" name="firstname" required> <label
						for="lastname"><b>Nom</b></label> <input type="text"
						placeholder="Lastname" name="lastname" required> <label
						for="email"><b>Email</b></label> <input type="text"
						placeholder="Enter Email" name="email" required>
					<textarea name="text" rows="5" cols="20"></textarea>
					<div class="clearfix">
						<button type="submit" class="signupbtn">Envoyer</button>
					</div>
				</div>
			</form>
			<div class="container-map">
				<div>
					<img src="images/icone1.jpg" alt=""> <img
						src="images/icone2.jpg" alt="">
				</div>
				<div class="maps">
					<embed
						src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d2710.207049454749!2d-1.5729033!3d47.2125309!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4805ec0fcda6c4cb%3A0xd620ca38dafa1e9a!2s2+Rue+La+Motte+Picquet%2C+44100+Nantes!5e0!3m2!1sfr!2sfr!4v1423244007186" />
				</div>
			</div>
		</main>
	</div>

</body>
</html>