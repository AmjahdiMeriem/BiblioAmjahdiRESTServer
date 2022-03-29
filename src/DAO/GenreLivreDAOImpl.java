package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Model.GenreLivre;

@Path("/GenreLivreDAOImpl")
public class GenreLivreDAOImpl {
//------------------------------------------------------------------------------------------------------------------//

	@GET

	@Path("/findGenreLivre")

	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<GenreLivre> findGenreLivre() {
		GenreLivre genre = new GenreLivre();
		List<GenreLivre> genres = new ArrayList<GenreLivre>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/AchatLivres ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
					"root", "");
			PreparedStatement pr = cn.prepareStatement("SELECT * FROM genrelivre");
			ResultSet r = pr.executeQuery();
			while (r.next()) {
				genre.setIdGenreLivre(r.getLong(1));
				genre.setNomGenreLivre(r.getString(2));
				genres.add(genre);
				genre = new GenreLivre();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return genres;
	}

//------------------------------------------------------------------------------------------------------------------//
	@POST
	@Path("/addGenreLivre")
	@Produces({ MediaType.APPLICATION_XML })
	public void addGenreLivre(GenreLivre genre) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/AchatLivres ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
					"root", "");
			PreparedStatement pr = cn.prepareStatement("INSERT INTO genreLivre VALUES(NULL,?)");
			pr.setString(1, genre.getNomGenreLivre());
			pr.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//------------------------------------------------------------------------------------------------------------------//
	@DELETE
	@Path("/removeGenreLivre/{idGenreLivre}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public void removeGenreLivre(@PathParam("idGenreLivre") Long idGenreLivre) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/AchatLivres ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
					"root", "");

			PreparedStatement pr = cn.prepareStatement("DELETE FROM genrelivre WHERE idGenreLivre=?");

			pr.setLong(1, idGenreLivre);
			pr.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
