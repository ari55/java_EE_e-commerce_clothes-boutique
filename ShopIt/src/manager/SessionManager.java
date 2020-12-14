package manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {

	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}

	public static void destroySession(HttpServletRequest request) {
		HttpSession session = getSession(request);
		session.invalidate();
	}
	 public static void add(HttpServletRequest request, String key, Object value) {
	        HttpSession session = request.getSession();
	        session.setAttribute(key, value);
	    }
}
