package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.text.html.HTMLEditorKit.InsertHTMLTextAction;

public class TestMetier {
	static Statement stmt = null;
	static Connection conn = null;
	static PreparedStatement ps = null;

	public static void main(String[] args) {
		try {
			
		conn = SingletonConnection.getConncetion();
//		insertMeth();
//		fetchtMeth();
		fetchtMethKey("Ord HP 760");
//		fetchtMethRef("PR03");
//		updateMeth("PR03", "HP macbook", 1330, 50);
//		deleteMeth("PR02");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	//insert into database
	public static void insertMeth() {
		String query = "INSERT INTO `cat_perod`.`produits`(`REF_PROD`,`DESIGNATION`,`PRIX`,`QUANTITE`)\r\n" + 
				"VALUES(?,?,?,?);";
		try {
			
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, "PR04");
			preparedStmt.setString (2, "Ord HP 333");
			preparedStmt.setInt(3, 111);
			preparedStmt.setInt(4, 2222);
			preparedStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	//read from database
		public static void fetchtMeth() {
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
			      }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		//read from database with key or wtv (who cares)
				public static void fetchtMethKey(String mc) {
					String query = "SELECT `produits`.`REF_PROD`,`produits`.`DESIGNATION`,`produits`.`PRIX`,`produits`.`QUANTITE`\r\n" + 
							"FROM `cat_perod`.`produits` WHERE `produits`.`DESIGNATION`=?;";
					try {
						System.out.println("mc="+mc);
						ps = conn.prepareStatement(query);
//						PreparedStatement ps = conn.prepareStatement("SELECT `produits`.`REF_PROD`,`produits`.`DESIGNATION`,`produits`.`PRIX`,`produits`.`QUANTITE`\r\n" + 
//								"FROM `cat_perod`.`produits` WHERE `produits`.`DESIGNATION`=?;");
						ps.setString(1, mc);

						ResultSet rs = ps.executeQuery();	
						
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
					         
//					         Produit p = new Produit(ref, design, prix, quantite);
//					         produits.add(p);
					        
					      }
						
			
//					try {
//						PreparedStatement ps = conn.prepareStatement("SELECT `produits`.`REF_PROD`,`produits`.`DESIGNATION`,`produits`.`PRIX`,`produits`.`QUANTITE`\r\n" + 
//								"FROM `cat_perod`.`produits` WHERE `produits`.`DESIGNATION`=?;");
//						ps.setString(1, x);
//
//						ResultSet rs = ps.executeQuery();	
//						
//						while(rs.next()){
//					         //Retrieve by column name
//					         String ref  = rs.getString("REF_PROD");
//					         String design  = rs.getString("DESIGNATION");
//					         int prix = rs.getInt("PRIX");
//					         int quantite = rs.getInt("QUANTITE");
//
//					         //Display values
//					         System.out.print("Refrence: " + ref);
//					         System.out.print(", Desingnation: " + design);
//					         System.out.print(", Prix: " + prix);
//					         System.out.println(", Quantite: " + quantite);
//					      }
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				//read from database with reference
				public static void fetchtMethRef(String x) {
					
			
					try {
						PreparedStatement ps = conn.prepareStatement("SELECT `produits`.`REF_PROD`,`produits`.`DESIGNATION`,`produits`.`PRIX`,`produits`.`QUANTITE`\r\n" + 
								"FROM `cat_perod`.`produits` WHERE `produits`.`REF_PROD`=?;");
						ps.setString(1, x);

						ResultSet rs = ps.executeQuery();							
						if(!rs.next()) {
							System.out.println("Le produit don't la referance "+x+" est introuvable");
						}
						else {
							rs.beforeFirst();
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
						      }

						}
						//						System.out.println(rs.getRow());
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				
				//update the database
				public static void updateMeth(String ref, String des, int prix, int quantite) {
					String query = "UPDATE `cat_perod`.`produits`\r\n" + 
							"SET `DESIGNATION` = ?,`PRIX` = ?,`QUANTITE` = ?\r\n" + 
							"WHERE `REF_PROD` = ?;";
					try {
						
						PreparedStatement preparedStmt = conn.prepareStatement(query);
						preparedStmt.setString (1, des);
						preparedStmt.setInt (2, prix);
						preparedStmt.setInt(3, quantite);
						preparedStmt.setString(4, ref);
						
						int rowsUpdated = preparedStmt.executeUpdate();
						if (rowsUpdated > 0) {
						    System.out.println("An existing product was updated successfully!");
						}
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				
				//delete from database
				public static void deleteMeth(String ref) {
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
