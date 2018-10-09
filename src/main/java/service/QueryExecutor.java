package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/bd")
public class QueryExecutor implements Access {
	private Connection connection;

	@GET
	@Path("/selectDB")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response querySelect(@QueryParam("query") String query) throws SQLException {
		ResultQuery queryResult = null;
		try (Statement stmt = this.connection.createStatement()) {

			ResultSet result = stmt.executeQuery(query);
			queryResult = new ResultQuery(result);
			return Response.ok(queryResult).build();
		} catch (SQLException e) {
			return Response.status(400).build();
//			throw new SQLException("Erro ao executar query.");

		}
	}

	@POST
	@Path("/editDB")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response queryExecute(@QueryParam("query") String query) throws SQLException {
		try (Statement stmt = this.connection.createStatement()) {

			stmt.executeUpdate(query);

			return Response.ok("Operação concluida.").build();
		} catch (SQLException e) {
			return Response.status(400).build();
//			throw new SQLException("Erro ao executar query.");

		}

	}

	@POST
	@Path("/connect")
	@Override
	public Response connectDB(@QueryParam("url") String url, @QueryParam("user") String user,
			@QueryParam("password") String password) throws SQLException, ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, user, password);

			return Response.ok("Banco conectado.").build();
		} catch (SQLException e) {
			return Response.status(400).build();
//			throw new SQLException("Erro ao tentar conectar com o Banco de dados.");
		} catch (ClassNotFoundException e) {
			return Response.serverError().build();
//			throw new ClassNotFoundException("Erro ao Conectar com drive JDBC.");
		}
	}
}
