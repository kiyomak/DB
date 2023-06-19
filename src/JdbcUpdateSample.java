
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUpdateSample {

	private static final String URL = "jdbc:mysql://localhost:3306/database01?user=user01&"
			+ "password=password01&useSSL=false&allowPublicKeyRetrieval=true";

	public static void main(String[] args) throws ClassNotFoundException {

		// Connection（データベースとの接続を表す）、PreparedStatement（発行するSQLを表す）を、それぞれ生成します。
		try (Connection connection = DriverManager.getConnection(URL);
				PreparedStatement statement = connection
						.prepareStatement("update user set email = ?, name = ? where id = ?")) {

			// プレースホルダにパラメータをセット
			statement.setString(1, "ccc@ccc.ccc");
			statement.setString(2, "木下順子");
			statement.setLong(3, 1L);

			// SQLの実行
			int count = statement.executeUpdate();
			System.out.println("userテーブルの" + count + "件のデータが更新されました");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}