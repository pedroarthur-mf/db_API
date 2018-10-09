package service;

import java.sql.SQLException;

import javax.ws.rs.core.Response;

public interface Access {
	public Response querySelect(String query) throws SQLException;
	
	public Response queryExecute(String query) throws SQLException;

	public Response connectDB(String url, String user, String password) throws SQLException, ClassNotFoundException ;
}
