<%@page import="java.util.ArrayList"%>
<%@page import="entities.Item"%>
<%@ page import="util.Const"%>
<%@page import="entities.Category"%>
<%@page import="entities.Panier"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="entities.ItemPanier"%>
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
	DecimalFormat df = new DecimalFormat("####0.00");
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
							<form role="itemSearch" action="recherche" method="post">
								<input type="text" placeholder="Rechercher" name="itemSearch"
									value=<%=placeHolderSearch != null && placeHolderSearch != "888888" ? placeHolderSearch : " "%>>
								<button type="submit">
									<img src="images/recherche.png" alt="">
								</button>
							</form>
						</div>
						<div id="panier">
						<div>
								<input type="text" placeholder="" value="<%=cart.getSize()%>"
									readonly="readonly">
							</div>
							<img src="images/pannier.png" alt="">
						</div>
					</div>
				</div>
			</div>

		</header>
		<main id="containerP">
			<%
				if (cart != null && !cart.isEmpty()) {
			%>
			<div>
				<div>
					<form name="cart" action="panier" method="post">
						<table>
							<thead>
								<tr>
									<th>Produit</th>
									<th>Quantite</th>
									<th>Prix</th>
									<th>Total</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<%
									for (ItemPanier itemPanier : cart.getCart().values()) {
								%>

								<tr>
									<td>
										<div>
											<a href="#"> <img src="<%=itemPanier.getImgAndPath()%>"
												style="width: 72px; height: 72px;">
											</a>
											<div>
												<h4>
													<a href=""=<%=itemPanier.getId()%>"><%=itemPanier.getName()%></a>
												</h4>

												<h5>
													Stock: <span
														id="stock-qty-<%=itemPanier.getSerialNumber()%>"><%=itemPanier.getStock()%></span>
												</h5>
												<span>Status: </span><span
													id="stock-status-<%=itemPanier.getSerialNumber()%>"
													class=" <%=((itemPanier.inStock()) ? "success" : "attention")%>">
													<strong><%=((itemPanier.inStock()) ? "En stock" : "le est stock Insufisant")%></strong>
												</span>
											</div>
										</div>
									</td>
									<td style="text-align: center"><input type="number"
										min="1" max="999"
										name="<%="qty-" + itemPanier.getSerialNumber()%>"
										value="<%=itemPanier.getQuantity()%>"
										id="<%="pq-" + itemPanier.getSerialNumber()%>"></td>
									<td><strong id="<%="pp-" + itemPanier.getSerialNumber()%>"><%=df.format(itemPanier.getPrice())%>$</strong></td>
									<td class=""><strong
										id="<%="pt-" + itemPanier.getSerialNumber()%>"><%=df.format(itemPanier.getPrice() * itemPanier.getQuantity())%>$</strong></td>
									<td>
										<button type="submit"
											name="<%="p-del-" + itemPanier.getSerialNumber()%>">
											Supprimer <span></span>
										</button>
									</td>
								</tr>
								<%
									}
								%>

								<tr>

									<td><h3>Total</h3></td>
									<td class=""><h3>
											<strong id="cart-total"><%=df.format(cart.generateTotal())%>$</strong>
										</h3></td>
								</tr>
								<tr>

									<td>
										<button type="submit" name="back">
											<span class=""></span> Accueil
										</button>
									</td>
									<td>
										<button type="submit" name="refresh">Actualiser</button>
									</td>
									<td>
										<button type="submit" name="checkout">
											Commander <span class=""></span>
										</button>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			<%
				} else {
			%>
			<div class="">
				<div class="">Le panier est vide</div>
			</div>
			<%
				}
			%>
		</main>
	</div>

</body>
</html>