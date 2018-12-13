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

public class ControleurServlet extends HttpServlet{
	private ICatalogueMetier metier;
	String ref;
	
	public void init() throws ServletException{
		metier = new CatalogueMetierImpl();
//		System.out.println("iiiinniii");
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		request.getRequestDispatcher("VueProduits.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
		
		List<Produit> produits = new ArrayList<>();
		if (request.getParameter("motCle") != null) {
		ProduitModel model = new ProduitModel();
		model.setMotCle(request.getParameter("motCle"));
		ref= request.getParameter("motCle");
//		System.out.println("before going to the other class");
		produits = metier.produitParMC(model.getMotCle());
//		List<Produit> produits = metier.listProduit();
		model.setProduits(produits);
//		System.out.println("mot cle"+model.getMotCle());
		request.setAttribute("model",model);
		request.getRequestDispatcher("/VueProduits.jsp").forward(request, response);
		
		}
		if (request.getParameter("action2") != null) {
			System.out.println("entered action2");
			ProduitModel model = new ProduitModel();
			Produit p = new Produit(ref, 
					request.getParameter("reg"),
					Double.valueOf(request.getParameter("prix")), Integer.valueOf(request.getParameter("quantite")));
			metier.updateProduit(p);
			produits = metier.listProduit();
			model.setProduits(produits);
			request.setAttribute("model",model);
			
			request.getRequestDispatcher("/VueProduits.jsp").forward(request, response);
		}
	}
		

}
