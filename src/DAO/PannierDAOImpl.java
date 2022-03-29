package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/PannierDAOImpl")
public class PannierDAOImpl {

	@GET
	@Path("/addLivrePannier/{idUser}/{ISBN}")
	@Produces({ MediaType.APPLICATION_XML })
	public void addLivrePannier(@PathParam("idUser") Long idUser,@PathParam("ISBN") Long ISBN) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/AchatLivres ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
					"root", "");
			PreparedStatement pr = cn.prepareStatement("INSERT INTO pannier VALUES(NULL,?,?)");
			pr.setLong(1, idUser);
			pr.setLong(2, ISBN);
			pr.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//--------------------------------------------------------------------------------------------------------------//
	
	/*
	 * public List<Livre> findLivrePannierByIdUsr(Long idUser) { List<Livre> livres
	 * = new ArrayList<Livre>(); Livre livre = new Livre(); try {
	 * Class.forName("com.mysql.cj.jdbc.Driver"); Connection cn =
	 * DriverManager.getConnection(
	 * "jdbc:mysql://localhost:3308/AchatLivres ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC"
	 * , "root", ""); PreparedStatement pr = cn.prepareStatement(
	 * "SELECT livre.* FROM pannier inner join livre on pannier.IdUser=? AND livre.ISBN=pannier.ISBN"
	 * ); pr.setLong(1, idUser); ResultSet r = pr.executeQuery();
	 * 
	 * while (r.next()) { livre.setISBN(r.getLong(1));
	 * livre.setTitre(r.getString(2)); livre.setAuteur(r.getString(3));
	 * livre.setEditeur(r.getString(4)); livre.setAnnee(r.getInt(5));
	 * livre.setIdGenreLivre(r.getLong(6)); livre.setIdLangueLivre(r.getLong(7));
	 * livre.setPrix(r.getInt(8)); livre.setDescription(r.getString(9));
	 * livre.setUrlImage(r.getString(10)); livres.add(livre); livre = new Livre(); }
	 * } catch (Exception e) { e.printStackTrace(); } return livres; }
	 */
}
