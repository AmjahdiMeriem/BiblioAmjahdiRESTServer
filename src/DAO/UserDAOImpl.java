package DAO;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Model.RoleUser;
import Model.User;
@Path("/UserDAOImpl")
public class UserDAOImpl {

	@POST
	@Path("/saveClient")
	@Produces({MediaType.APPLICATION_XML})
	public boolean saveClient(User user) throws UnsupportedEncodingException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/AchatLivres ?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
					"root", "");
			PreparedStatement pr = cn.prepareStatement("INSERT INTO user VALUES(NULL,?,?,?,?)");
			pr.setString(1, user.getNameUser());
			pr.setString(2, user.getEmailUser());
			pr.setString(3, user.getPasswordUser());
			pr.setLong(4, user.getIdRoleUser());
			pr.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//--------------------------------------------------------------------------------------------------------------------//
}
