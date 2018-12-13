package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.CatalogueMetierImpl;
import metier.ICatalogueMetier;
import metier.Produit;

public class ControleurServlet extends HttpServlet {
	private ICatalogueMetier metier;
//	String ref;
boolean add = true;
	public void init() throws ServletException {
		metier = new CatalogueMetierImpl();
//		System.out.println("iiiinniii");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.getRequestDispatcher("VueProduits.jsp").forward(request, response);
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("INSIDE THE POSTT");

		ProduitModel model = new ProduitModel();

		request.setAttribute("model", model);
		String action = request.getParameter("action");
//filling table
		if (action != null) {
			System.out.println("first if");
			if (action.equals("chercher")) {
				System.out.println("secod if");
				model.setMotCle(request.getParameter("motCle"));
				List<Produit> produits = metier.produitParMC(model.getMotCle());
				model.setProduits(produits);
				request.getRequestDispatcher("VueProduits.jsp").forward(request, response);
			} else if (action.equals("delete")) {// delete from table
				System.out.println("enter DELETE");
				String ref = request.getParameter("ref");
				metier.deleteProduit(ref);
				List<Produit> produits = metier.listProduit();
				model.setProduits(produits);
				
				request.getRequestDispatcher("/VueProduits.jsp").forward(request, response);
			}
			else if (action.equals("edit")) {
				add=false;
				System.out.println("enter edite");
				String ref = request.getParameter("ref");
				metier.getProduit(ref);
				List<Produit> produits = metier.listProduit();
				model.setProduits(produits);
				request.setAttribute("ref", produits.get(0).getReference());
				request.setAttribute("des", produits.get(0).getDesignation());
				request.setAttribute("prix", produits.get(0).getPrix());
				request.setAttribute("quan", produits.get(0).getQuantite());
				
				
				request.getRequestDispatcher("/VueProduits.jsp").forward(request, response);
				
			}else if (action.equals("save")&&!add) {
				System.out.println("enter save");
				Produit p = new Produit(request.getParameter("ref"), request.getParameter("reg"),
						Double.valueOf(request.getParameter("prix")), Integer.valueOf(request.getParameter("quantite")));
				
				List<Produit> produits = metier.listProduit();
				model.setProduits(produits);
				
				request.setAttribute("ref", produits.get(0).getReference());
				request.setAttribute("des", produits.get(0).getDesignation());
				request.setAttribute("prix", produits.get(0).getPrix());
				request.setAttribute("quan", produits.get(0).getQuantite());
				
				metier.updateProduit(p);
				metier.getProduit((request.getParameter("ref")));
				produits.clear();
				produits = metier.listProduit();
				model.setProduits(produits);
				
				request.setAttribute("ref", null);
				request.setAttribute("des", null);
				request.setAttribute("prix", null);
				request.setAttribute("quan", null);
				add=true;
				request.getRequestDispatcher("/VueProduits.jsp").forward(request, response);
				
			}else if (action.equals("save")&&add) {
				System.out.println("entered adding to database");
				Produit p = new Produit(request.getParameter("ref"), request.getParameter("reg"),
						Double.valueOf(request.getParameter("prix")), Integer.valueOf(request.getParameter("quantite")));
				metier.addProduit(p);
				List<Produit> produits = metier.listProduit();
				model.setProduits(produits);

				request.getRequestDispatcher("/VueProduits.jsp").forward(request, response);
			}
			
		} else {
			System.out.println("nuuuuuuuuuuul");
			response.sendRedirect("VueProduits.jsp");
		}

//		if (request.getParameter("action2") != null) {
//			System.out.println("entered action2");
//			Produit p = new Produit(request.getParameter("ref"), request.getParameter("reg"),
//					Double.valueOf(request.getParameter("prix")), Integer.valueOf(request.getParameter("quantite")));
//			metier.addProduit(p);
//			List<Produit> produits = metier.listProduit();
//			model.setProduits(produits);
//
//			request.getRequestDispatcher("/VueProduits.jsp").forward(request, response);
//		}
	}

}
