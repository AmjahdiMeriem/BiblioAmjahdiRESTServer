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

import Model.LangueLivre;

@Path("/LangueLivreDAOImpl")
public class LangueLivreDAOImpl {
//--------------------------------------------------------------------------------------------------------------------//

	@GET

	@Path("/findLangueAll")

	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<LangueLivre> findLangueAll() {
		LangueLivre langue = new LangueLivre();
		List<LangueLivre> langues = new ArrayList<LangueLivre>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/AchatLivres ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
					"root", "");
			PreparedStatement pr = cn.prepareStatement("SELECT * FROM languelivre");
			ResultSet r = pr.executeQuery();
			while (r.next()) {
				langue.setIdLangueLivre(r.getLong(1));
				langue.setNomLangueLivre(r.getString(2));

				langues.add(langue);
				langue = new LangueLivre();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return langues;
	}

//--------------------------------------------------------------------------------------------------------------------//
	@POST
	@Path("/addLangueLivre")
	@Produces({ MediaType.APPLICATION_XML })
	public void addLangueLivre(LangueLivre langue) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/AchatLivres ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
					"root", "");
			PreparedStatement pr = cn.prepareStatement("INSERT INTO langueLivre VALUES(NULL,?)");
			pr.setString(1, langue.getNomLangueLivre());
			pr.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//--------------------------------------------------------------------------------------------------------------------//
	@DELETE
	@Path("/removeLangueLivre/{idLangue}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public void removeLangueLivre(@PathParam("idLangue") Long idLangue) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/AchatLivres ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
					"root", "");

			PreparedStatement pr = cn.prepareStatement("DELETE FROM languelivre WHERE idLangueLivre=?");

			pr.setLong(1, idLangue);
			pr.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//--------------------------------------------------------------------------------------------------------------------//
}
