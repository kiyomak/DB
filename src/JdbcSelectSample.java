
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcSelectSample {

	private static final String DRIVER = "com.mysql.jdbc.Driver";

	private static final String URL = "jdbc:mysql://localhost:3306/database01?user=user01&"
	+ "password=password01&useSSL=false&allowPublicKeyRetrieval=true";

	public static void main(String[] args) throws ClassNotFoundException {

		/* JDBC4.0以降は不要
		try {
			// com.mysql.cj.jdbc.Driverクラスをロードして、初期化処理を行う
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// ドライバのクラスがクラスパスに見つからなかった場合にClassNotFoundExceptionが発生します。
			e.printStackTrace();
			// ドライバが見つからない場合は、後続の処理を続行できませんので、例外をthrowして処理を中断します。
			throw e;
		}
		*/

		// Connection（データベースとの接続を表す）、PreparedStatement（発行するSQLを表す）を、それぞれ生成します。
		//try-with-resources構文をつかうと、	自動的にclose()が呼ばれるので、closeを呼ぶ必要がない
		try (Connection connection = DriverManager.getConnection(URL);
				PreparedStatement statement = connection.prepareStatement("select * from user")) {

			// StatementにSQLを文字列で与えて実行します。
			// 戻り値は、SQLの実行結果を表す、ResultSet（結果セット）です。
			try (ResultSet resultSet = statement.executeQuery()) {

				// SQLの実行結果を、行数ぶんだけループしながら表示しています。
				while (resultSet.next()) {
					System.out.println("----------------------");
					System.out.println(resultSet.getLong("id"));
					System.out.println(resultSet.getString("email"));
					System.out.println(resultSet.getString("name"));
					System.out.println(resultSet.getDate("created_at"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}