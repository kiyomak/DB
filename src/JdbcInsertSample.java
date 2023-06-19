
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcInsertSample {
	/*jdbc:mysql://ホスト名:ポート番号/データベース名?
	         user=ユーザー名&password=パスワード&useSSL=false&allowPublicKeyRetrieval=true  */
	private static final String URL = "jdbc:mysql://localhost:3306/database01?user=user01&"
			+ "password=password01&useSSL=false&allowPublicKeyRetrieval=true";
	
	public static void main(String[] args) throws ClassNotFoundException{
		
		// Connection（データベースとの接続を表す）、PreparedStatement（発行するSQLを表す）を、それぞれ生成します。
		try( Connection connection = DriverManager.getConnection(URL);
				PreparedStatement statement = connection
						.prepareStatement("insert into user (email, name) values (?, ?)")){
			// プレースホルダにパラメータをセット
						statement.setString(1, "bbb@bbb.bbb");
						statement.setString(2, "山田三郎");
						// SQLの実行
						int count = statement.executeUpdate();
						System.out.println("userテーブルに、新しく" + count + "件のデータが挿入されました");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
