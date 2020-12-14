package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.CategoryManager;

public class CategoryAction {
	
	public static void getAllCategories(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("categories", CategoryManager.getCategories());
	}
	public static void getCategoryById(String id, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("category", CategoryManager.getCategoryById(id));
	}
	
	public static int getSelectedCategory(HttpServletRequest request, HttpServletResponse response) {
		String paramCategory = request.getParameter("category");
		int categorySelected;
		if(paramCategory != null) {
			try {
				categorySelected = Integer.valueOf(paramCategory);
				if(CategoryManager.isExist(categorySelected) != 0)
					categorySelected = 1;
			}
			catch(NumberFormatException e) {
				categorySelected = 1;
			}
		}
		else
			categorySelected = 0;
		
		return categorySelected;
	}
}
