package ru.shemplo.shop.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.shemplo.shop.db.DBAccess;
import ru.shemplo.shop.db.DBLib;
import ru.shemplo.shop.servlet.html.HTMLBuilder;
import ru.shemplo.snowball.utils.db.DBType;

public class AddProductServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7511012126994740512L;
	
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		HTMLBuilder html = new HTMLBuilder ("Query page");
		resp.setContentType ("text/html");
		
		long price = Long.parseLong (req.getParameter ("price"));
		String name = req.getParameter ("name");
		
		try {
			DBAccess db = DBAccess.getInstanceOf (DBType.SQLite);
			String query = DBLib.insertProduct (name, price);
			db.update (query);
			
			html.addSmallHeader ("New product added successfully");
			html.addParagraph ("<b>Product:</b> " + name + " (" + price + " c.u.)");
		} catch (SQLException sqle) {
			html.addHeader ("Processing query failed");
			html.addParagraph ("<b>Error:</b> " + sqle);
			
			resp.setStatus (HttpServletResponse.SC_NOT_ACCEPTABLE);
			resp.getWriter ().println (html);
			
			return;
		}
		
		resp.setStatus (HttpServletResponse.SC_OK);
		resp.getWriter ().println (html);
	}

}
