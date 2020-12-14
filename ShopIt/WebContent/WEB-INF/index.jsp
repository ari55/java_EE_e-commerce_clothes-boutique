<%@page import="java.util.ArrayList"%>
<%@page import="entities.Item"%>
<%@ page import="util.Const"%>
<%@page import="entities.Category"%>
<%@page import="entities.Panier"%>
<%@page import="action.PanierAction"%>
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
	Panier cart = (Panier) session.getAttribute("cart");
%>
<%
	String placeHolderSearch = (String) request.getAttribute("placeHolder");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/accueil.css" type="text/css" />
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
				<li id="loginState"><a href="#" id="user"><%=user.getFirstName()%></a>

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
							</a></li>
						</ul>
					</nav>
					<div id="loupePanier">
						<div id="loupe">
							<form role="itemSearch" action="recherche" method="post">
								<input type="text" placeholder="Rechercher" name="itemSearch"
									value=<%=placeHolderSearch != null && placeHolderSearch != "888888" ? placeHolderSearch : " "%>>
								<button type="submit">
									<a href=""> <img src="images/recherche.png" alt=""></a>
								</button>
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
			<div id="messagePromo">
				<div>PROMOS DES REDUCTIONS A</div>
				<div>JUSQU'A -50%</div>
			</div>

			<div id="contenu">
				<%
					if (items != null && items.size() > 0) {
						for (Item item : items) {
				%>
				<div class="itemDescription">
					<div>

						<a href="produit?item=<%=item.getId()%>"> <img
							src="<%=Const.IMAGE_PRODUCT + item.getImage()%>" alt=""></a>
					</div>
					<p><%=item.getName()%></p>
					<p><%=item.getDescription()%></p>
					<p><%=item.getPrice()%></p>
					<form method="post" id="form-add-item">
						<input type="hidden" name="itemId" value="<%=item.getId()%>">
						<button type="submit" class="">
							<span class=""></span> Ajouter <input type="number" min="1"
								max="999" name="qty" class="">
						</button>


					</form>
				</div>
				<%
					}
					} else {
				%>
				<div class="">Aucun produit en Promo pour le moment.</div>
				<%
					}
				%>
			</div>

		</main>
	</div>

</body>
</html>