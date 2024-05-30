//Java program to set up connection and get all data from table
import java.sql.*;

public class GFG {
	public static void main(String arg[])
	{
		Connection connection = null;
		try {
			// below two lines are used for connectivity.
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/grouppy",
				"homura", "homura");

			// mydb is database
			// mydbuser is name of database
			// mydbuser is password of database

			Statement statement;
			statement = connection.createStatement();
			ResultSet resultSet;
			resultSet = statement.executeQuery(
				"select * from users");
			int code;
			String title;
			while (resultSet.next()) {
				code = resultSet.getInt("id");
				title = resultSet.getString("name").trim();
				System.out.println("Code : " + code
								+ " Title : " + title);
			}
			resultSet.close();
			statement.close();
			connection.close();
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
	} // function ends
} // class ends
