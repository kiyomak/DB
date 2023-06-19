/*Lesson3_Chapter13_JDBC③データ登録　確認問題
 2.トランザクション成功の確認
トランザクション処理を使って、ユーザーAさんからユーザーBさんに、1,000円渡す処理を実装する。
ユーザーAさんのお金が9,000円、ユーザーBさんのお金が11,000円になったことを確認する。
*/
package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUpdateMoney {
	//MySOLと接続する
	/*jdbc:mysql://ホスト名:ポート番号/データベース名?user=ユーザー名&password=パスワード&useSSL=false&allowPublicKeyRetrieval=true  */
	private static final String URL = "jdbc:mysql://localhost:3306/database01?user=user01&"
			+ "password=password01&useSSL=false&allowPublicKeyRetrieval=true";
	
	//mainメソッド
	public static void main(String[] args)throws ClassNotFoundException, SQLException  {
		//コネクション変数を初期化
		Connection connection = null;
		
		//トランザクション処理のため、try-catch構文を作る
		// try(){}ではなく、引数を使わないtry{}を使用する
		try {
			//コネクションの取得
			connection = DriverManager.getConnection(URL);
			PreparedStatement user = connection.prepareStatement("update user set money=? Where id=?");
			
				//①自動コミットさせない設定にする
				connection.setAutoCommit(false);
				
				//ユーザーAのお金を1,000円減らすSQLを発行する→PreparedStatement
				//1番目＝金額、2番目＝id番号指定
					user.setLong(1, 9000L);
					user.setLong(2,11L);
					
					int count = user.executeUpdate();
				
				//ユーザーBのお金を1,000円増やすSQLを発行する→PreparedStatement
					user.setLong(1, 11000L);
					user.setLong(2,12L);
					
					count +=user.executeUpdate();
			
				//②全てのSQLが成功したので、コミット処理
				connection.commit();
				
				//コンソールに更新数だす。
				System.out.println("userテーブルの" + count + "件のデータが更新されました。");
				
		}catch(Exception e) {
			//③例外が発生したのでロールバック(取り消し)処理をする
			//1つのトランザクション内で発行したすべてのSQLの実行を取り消す
			connection.rollback();
			e.printStackTrace();
			
		}
		
		
	}
	
}
