package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Model.Livre;

@Path("/LivreDAOImpl")
public class LivreDAOImpl {
//-------------------------------------------------------------------------------------------------------------------------------//
	@POST
	@Path("/addLivre")
	@Produces({ MediaType.APPLICATION_XML })
	public boolean add(Livre livre) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/achatlivres ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
					"root", "");
			PreparedStatement pr = cn.prepareStatement("INSERT INTO livre VALUES(?,?,?,?,?,?,?,?,?,?)");
			pr.setLong(1, livre.getISBN());
			pr.setString(2, livre.getTitre());
			pr.setString(3, livre.getAuteur());
			pr.setString(4, livre.getEditeur());
			pr.setInt(5, livre.getAnnee());
			pr.setLong(6, livre.getIdGenreLivre());
			pr.setLong(7, livre.getIdLangueLivre());
			pr.setInt(8, livre.getPrix());
			pr.setString(9, livre.getDescription());
			pr.setString(10, livre.getUrlImage());
			pr.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// -----------------------------------------------------------------------------
	// --------------------------------------------------//

	@GET

	@Path("/findBookAll")

	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Livre> findBookAll() {
		Livre livre = new Livre();
		List<Livre> livres = new ArrayList<Livre>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/AchatLivres ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
					"root", "");
			PreparedStatement pr = cn.prepareStatement("SELECT * FROM livre");
			ResultSet r = pr.executeQuery();
			while (r.next()) {
				livre.setISBN(r.getLong(1));
				livre.setTitre(r.getString(2));
				livre.setAuteur(r.getString(3));
				livre.setEditeur(r.getString(4));
				livre.setAnnee(r.getInt(5));
				livre.setIdGenreLivre(r.getLong(6));
				livre.setIdLangueLivre(r.getLong(7));
				livre.setPrix(r.getInt(8));
				livre.setDescription(r.getString(9));
				livre.setUrlImage(r.getString(10));
				livres.add(livre);
				livre = new Livre();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return livres;
	}

	// -----------------------------------------------------------------------------
	// --------------------------------------------------//

	/*
	 * @GET
	 * 
	 * @Path("/findBookByLangKind")
	 * 
	 * @Produces({ MediaType.APPLICATION_XML }) public List<Livre>
	 * findBookByLangKind(@PathParam("idGenre") Long idGenre, @PathParam("idLangue")
	 * Long idLangue) { Livre livre = new Livre(); List<Livre> livres = new
	 * ArrayList<Livre>(); try { Class.forName("com.mysql.cj.jdbc.Driver");
	 * Connection cn = DriverManager.getConnection(
	 * "jdbc:mysql://localhost:3308/AchatLivres ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC"
	 * , "root", ""); PreparedStatement pr = cn.
	 * prepareStatement("SELECT * FROM livre WHERE IdGenreLivre=? and IdLangueLivre=?"
	 * ); pr.setLong(1, idGenre); pr.setLong(2, idLangue); ResultSet r =
	 * pr.executeQuery(); while (r.next()) { livre.setISBN(r.getLong(1));
	 * livre.setTitre(r.getString(2)); livre.setAuteur(r.getString(3));
	 * livre.setEditeur(r.getString(4)); livre.setAnnee(r.getInt(5));
	 * livre.setIdGenreLivre(r.getLong(6)); livre.setIdLangueLivre(r.getLong(7));
	 * livre.setPrix(r.getInt(8)); livre.setDescription(r.getString(9));
	 * livre.setUrlImage(r.getString(10)); livres.add(livre); livre = new Livre(); }
	 * } catch (Exception e) { e.printStackTrace(); } return livres; }
	 */

	// -------------------------------------------------------------------------------------------------------------------------------//

	@GET

	@Path("/findBookByISBN/{isbn}")

	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Livre findBookByISBN(Long isbn) {
		Livre livre = new Livre();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/AchatLivres ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
					"root", "");
			PreparedStatement pr = cn.prepareStatement("SELECT * FROM livre WHERE ISBN=?");
			pr.setLong(1, isbn);
			ResultSet r = pr.executeQuery();
			while (r.next()) {
				livre.setISBN(r.getLong(1));
				livre.setTitre(r.getString(2));
				livre.setAuteur(r.getString(3));
				livre.setEditeur(r.getString(4));
				livre.setAnnee(r.getInt(5));
				livre.setIdGenreLivre(r.getLong(6));
				livre.setIdLangueLivre(r.getLong(7));
				livre.setPrix(r.getInt(8));
				livre.setDescription(r.getString(9));
				livre.setUrlImage(r.getString(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return livre;
	}

	// -----------------------------------------------------------------------------------------------------//
	@DELETE
	@Path("/removeLivre/{ISBN}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void removeLivre(@PathParam("ISBN") Long ISBN) {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/AchatLivres ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
					"root", "");
			PreparedStatement pr = cn.prepareStatement("DELETE FROM livre WHERE ISBN=?");

			pr.setLong(1, ISBN);
			pr.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	// --------------------------------------------------------------------------------------------------------//
}
