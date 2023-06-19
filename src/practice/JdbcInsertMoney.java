/*Lesson3_Chapter13_JDBC③データ登録　確認問題
1.実行準備
　①カラムmoney(int型)をuserテーブルに追加する→insertする
	コマンドプロンプトで、ALTER TABLE user ADD COLUMN money int;と入れて作成
	②ユーザーAさん、ユーザーBさんを登録し、それぞれの残高を10,000にする。
*/
package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcInsertMoney {
	//MySOLと接続する
	/*jdbc:mysql://ホスト名:ポート番号/データベース名?user=ユーザー名&password=パスワード&useSSL=false&allowPublicKeyRetrieval=true  */
	private static final String URL = "jdbc:mysql://localhost:3306/database01?user=user01&"
			+ "password=password01&useSSL=false&allowPublicKeyRetrieval=true";
	
	public static void main(String[] args) throws ClassNotFoundException{
		
		//Connection（データベースとの接続を表す）、PreparedStatement（発行するSQLを表す）を、それぞれ生成
		//Connectionを生成してデータベースとの接続をする
		try( Connection connection = DriverManager.getConnection(URL);
				//PreparedStatementを生成してSQLを発行する
					PreparedStatement statement = connection.prepareStatement("insert into user(email,name,money) values(?,?,?)")){
				
				//プレースホルダーにパラメータをセットする
				//②ユーザーA、ユーザーBを登録する。残高は10000
				statement.setString(1, "");	
				statement.setString(2, "ユーザーA"); //user(name　⇐１番目の登録
				//statement.setInt(3, 10000); //user(…, monery)の方
				statement.setLong(3, 10000L);
				
				int count = statement.executeUpdate();
				
				statement.setString(1, "");
				statement.setString(2, "ユーザーB");
				//statement.setInt(3, 10000);
				statement.setLong(3, 10000L);
				
				//SQLの実行
				count += statement.executeUpdate();
				System.out.println("userテーブルに、新しく" + count + "件のデータが挿入されました");
				
		//JDBC接続で発生する例外は、java.sql.SQLExceptionなので、これをcatchする
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}
}
