package action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Adresse;
import entities.User;
import manager.InscriptionManager;
import manager.SessionManager;

public class InscriptionAction {

	public static boolean addUser(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> cleEtValeurDesParametresDuFormulaire = new HashMap<String, String>();
		boolean isFill = true;
		// je cree une variable qui prend les cle a mettre dans le hsamap
		String[] cles = { "lastName", "firstName", "email", "confirmEmail", "password", "confirmPassword", "addr_no",
				"addr_street", "addr_zip", "addr_city", "addr_state", "addr_country" };
		String[] clesAdesse = { "addr_appt" };
		// valeurs pur le hashmap
		String[] valeurs = { request.getParameter(cles[0]), request.getParameter(cles[1]),
				request.getParameter(cles[2]).toLowerCase(), request.getParameter(cles[3]).toLowerCase(),
				request.getParameter(cles[4]), request.getParameter(cles[5]), request.getParameter(cles[6]),
				request.getParameter(cles[7]), request.getParameter(cles[8]), request.getParameter(cles[9]),
				request.getParameter(cles[10]), request.getParameter(cles[11]) };
		String[] valeurAdresse = { request.getParameter(clesAdesse[0]) };
		// enregitrement des valeur cles et valeurs dans le hashmap
		for (int i = 0; i < valeurs.length; i++) {
			cleEtValeurDesParametresDuFormulaire.put(cles[i], valeurs[i]);
		}
		// (String)hash_map.put(20, "All")

		/*
		 * for (int i = 0; i < valeurAdresse.length; i++) {
		 * cleEtValeurDesParametresDuFormulaire.put(clesAdesse[i], valeurAdresse[i]); }
		 */

		// if (isFill) {
		// Créer l'addresse en premier
		// Creer l'utilisateur et relie l'adresse
		User user = new User();
		Adresse address = new Adresse();
		// Utilisez Misc.getOrDefault pour les champs qui sont optionnel, car il se
		// pourrait que sa retourne null
		// pas testé plus haut
		address.setId(-1);
		address.setNo(cleEtValeurDesParametresDuFormulaire.get("addr_no"));
		address.setAppartement(InscriptionAction.getOrDefault(cleEtValeurDesParametresDuFormulaire, "addr_appt", ""));
		address.setRue(cleEtValeurDesParametresDuFormulaire.get("addr_street"));
		address.setZip(cleEtValeurDesParametresDuFormulaire.get("addr_zip"));
		address.setVille(cleEtValeurDesParametresDuFormulaire.get("addr_city"));
		address.setEtat(cleEtValeurDesParametresDuFormulaire.get("addr_state"));
		address.setPays(cleEtValeurDesParametresDuFormulaire.get("addr_country"));

		user.setId(-1);
		user.setLastName(cleEtValeurDesParametresDuFormulaire.get("lastName"));
		user.setFirstName(cleEtValeurDesParametresDuFormulaire.get("firstName"));
		user.setEmail(cleEtValeurDesParametresDuFormulaire.get("email"));
		user.setPassword(cleEtValeurDesParametresDuFormulaire.get("password"));
		user.setShipAddress(address);
		int rep = InscriptionManager.signUp(user);

		// Si une erreur est arrivé
		if (rep < 1) {
			isFill = false;
			if (rep == 0)
				request.setAttribute("error", "accountExisting");
			else if (rep == -1)
				request.setAttribute("error", "DBProblem");
		}
		// }
		// HashMap pour conserver les valeurs entré par l'utilisateur (on ne le forcera
		// pas à tous réécrire)
		if (!isFill)
			request.setAttribute("cleEtValeurDesParametresDuFormulaire", cleEtValeurDesParametresDuFormulaire);
		return isFill;
	}

	public static String getOrDefault(HashMap<String, String> hm, String key, String defaultValue) {
		String returnValue = (hm != null ? hm.getOrDefault(key, defaultValue) : defaultValue);
		return (returnValue != null ? returnValue : defaultValue);
	}

	public static void ajouterUser(HttpServletRequest request, User user) {
		InscriptionManager.signUp(user);
		// request.setAttribute("user", " utilisateur ajouter avec sucess ");
		SessionManager.add(request, "user", user);
	}

}
