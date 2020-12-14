package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.UserManager;


public class UserAction {
	public static void getUsers(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("clients", UserManager.getAllUsers());
	}

}
