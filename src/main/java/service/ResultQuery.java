package service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Pedro Arthur and Gabriel Victor
 * 
 *         Serializable class that stores the result of the operation done in
 *         the database.
 *
 */
public class ResultQuery{

	private ArrayList<HashMap<String, String>> result;

	/**
	 * 
	 * Constructor that converts the result set from the query in the database to a
	 * list of hashmap with the data as String.
	 * 
	 * @param resultSet
	 * @throws SQLException
	 */
	public ResultQuery(ResultSet resultSet) throws SQLException {
		this.result = new ArrayList<>();

		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnsNumber = rsmd.getColumnCount();

		while (resultSet.next()) {
			HashMap<String, String> resultMap = new HashMap<>();
			for (int i = 1; i <= columnsNumber; i++) {
				String columnValue = resultSet.getString(i);
				resultMap.put(rsmd.getColumnName(i), columnValue);
			}
			this.result.add(resultMap);
		}
	}

	public ArrayList<HashMap<String, String>> getResult() {
		return result;
	}

	public void setResult(ArrayList<HashMap<String, String>> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return this.result.toString();
	}
}
