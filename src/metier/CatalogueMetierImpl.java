package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CatalogueMetierImpl implements ICatalogueMetier{
	 Statement stmt = null;
	 PreparedStatement ps = null;
	 Connection conn = SingletonConnection.getConncetion();
	 
	
	@Override
	public void addProduit(Produit p) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO `cat_perod`.`produits`(`REF_PROD`,`DESIGNATION`,`PRIX`,`QUANTITE`)\r\n" + 
				"VALUES(?,?,?,?);";
		try {
			
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, p.getReference());
			preparedStmt.setString (2, p.getReference());
			preparedStmt.setDouble(3, p.getPrix());
			preparedStmt.setInt(4, p.getQuantite());
			preparedStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public List<Produit> listProduit() {
		List<Produit> produits = new ArrayList<Produit>();
		// TODO Auto-generated method stub
		System.out.print("entered produit");
		String query = "SELECT `produits`.`REF_PROD`,`produits`.`DESIGNATION`,`produits`.`PRIX`,`produits`.`QUANTITE`\r\n" + 
				"FROM `cat_perod`.`produits`;";

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
		         //Retrieve by column name
		         String ref  = rs.getString("REF_PROD");
		         String design  = rs.getString("DESIGNATION");
		         int prix = rs.getInt("PRIX");
		         int quantite = rs.getInt("QUANTITE");

		         //Display values
		         System.out.print("Refrence: " + ref);
		         System.out.print(", Desingnation: " + design);
		         System.out.print(", Prix: " + prix);
		         System.out.println(", Quantite: " + quantite);
		         
		         Produit p = new Produit(ref, design, prix, quantite);
		         produits.add(p);
		      }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produits;
	}

	@Override
	public List<Produit> produitParMC(String mc) {
		List<Produit> produits = new ArrayList<Produit>();
		// TODO Auto-generated method stub
		String query = "SELECT `produits`.`REF_PROD`,`produits`.`DESIGNATION`,`produits`.`PRIX`,`produits`.`QUANTITE`\r\n" + 
				"FROM `cat_perod`.`produits`;";
		System.out.print("entered produit mc");
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery(query);
			
			while(rs.next()){
		         //Retrieve by column name
		         String ref  = rs.getString("REF_PROD");
		         String design  = rs.getString("DESIGNATION");
		         int prix = rs.getInt("PRIX");
		         int quantite = rs.getInt("QUANTITE");
		         
		         if(ref.contains(mc)||design.contains(mc)||String.valueOf(prix).contains(mc)||String.valueOf(quantite).contains(mc)) {
		        	 Produit p = new Produit(ref, design, prix, quantite);
		        	 produits.add(p);
		         }
		         
		         //Display values
//		         System.out.print("Refrence: " + ref);
//		         System.out.print(", Desingnation: " + design);
//		         System.out.print(", Prix: " + prix);
//		         System.out.println(", Quantite: " + quantite);
		         
		        
		      }
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produits;
	}

	@Override
	public Produit getProduit(String ref) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT `produits`.`REF_PROD`,`produits`.`DESIGNATION`,`produits`.`PRIX`,`produits`.`QUANTITE`\r\n" + 
					"FROM `cat_perod`.`produits` WHERE `produits`.`REF_PROD`=?;");
			ps.setString(1, ref);

			ResultSet rs = ps.executeQuery();							
			if(!rs.next()) {
				System.out.println("Le produit don't la referance "+ref+" est introuvable");
			}
			else {
				rs.beforeFirst();
				while(rs.next()){
			         //Retrieve by column name
			         String refe  = rs.getString("REF_PROD");
			         String design  = rs.getString("DESIGNATION");
			         int prix = rs.getInt("PRIX");
			         int quantite = rs.getInt("QUANTITE");

			         //Display values
			         System.out.print("Refrence: " + refe);
			         System.out.print(", Desingnation: " + design);
			         System.out.print(", Prix: " + prix);
			         System.out.println(", Quantite: " + quantite);
			      }

			}
			//						System.out.println(rs.getRow());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public void updateProduit(Produit p) {
		 System.out.print("entere update quary");
		// TODO Auto-generated method stub
		String query = "UPDATE `cat_perod`.`produits`\r\n" + 
				"SET `DESIGNATION` = ?,`PRIX` = ?,`QUANTITE` = ?\r\n" + 
				"WHERE `REF_PROD` = ?;";
		try {
			
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, p.getDesignation());
			preparedStmt.setDouble (2, p.getPrix());
			preparedStmt.setInt(3, p.getQuantite());
			preparedStmt.setString(4, p.getReference());
			
			int rowsUpdated = preparedStmt.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing product was updated successfully!");
			}
			else {
				 System.out.print("Something went wrong on update");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public void deleteProduit(String ref) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM `cat_perod`.`produits` WHERE (`REF_PROD` = ?);";
		try {
			
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, ref);
			
			
			int rowsUpdated = preparedStmt.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing product was deleted successfully!");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
