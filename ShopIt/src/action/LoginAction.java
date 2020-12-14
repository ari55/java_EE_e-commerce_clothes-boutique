package action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import manager.LoginManager;

public class LoginAction {
	public static void connexion(HttpServletRequest request, HttpServletResponse response) {
		//
		Boolean isvalid = true;
		HashMap<String, String> loginParams = new HashMap<String, String>();
		HashMap<String, String> messageDerreur = new HashMap<String, String>();
		loginParams.put("login", request.getParameter("login").toLowerCase());
		loginParams.put("password", request.getParameter("password"));

		if (isvalid) {
			User user = LoginManager.login(loginParams.get("login"), loginParams.get("password"));
			if (user != null) {
				messageDerreur.put("loginState", "ok");
				request.getSession().setAttribute("user", user);
			} else {
				messageDerreur.put("loginState", "mauvaisEmailOuMotDePasse");
			}
		} else
			messageDerreur.put("loginState", "incorrect");
		System.out.println(loginParams);
		System.out.println(messageDerreur);

		request.setAttribute("cleValeurLogin", loginParams);
		request.setAttribute("cleValeurErreur", messageDerreur);
	}

}
