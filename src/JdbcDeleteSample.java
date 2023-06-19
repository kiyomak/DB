
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcDeleteSample {

	private static final String URL = "jdbc:mysql://localhost:3306/database01?user=user01&"
			+ "password=password01&useSSL=false&allowPublicKeyRetrieval=true";

	public static void main(String[] args) throws ClassNotFoundException {

		// Connection（データベースとの接続を表す）、PreparedStatement（発行するSQLを表す）を、それぞれ生成します。
		try (Connection connection = DriverManager.getConnection(URL);
				PreparedStatement statement = connection.prepareStatement("delete from user where id = ?")) {

			// プレースホルダにパラメータをセット
			//パラメータ（順番（今回はid指定）, id対象？）
			statement.setLong(1, 10L);

			// SQLの実行
			int count = statement.executeUpdate();
			System.out.println("userテーブルの" + count + "件のデータを削除しました");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}