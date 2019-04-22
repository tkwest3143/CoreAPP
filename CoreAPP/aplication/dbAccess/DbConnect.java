package dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import core.CoreException;
import core.CoreProperty;

/**
 * @author tkwest3143
 *
 */
public class DbConnect {
	CoreProperty prop=new CoreProperty("CoreAPP/config/dbconf.properties");
	Connection conn=null;

	//DB接続のための基本設定(dbconf.propertiesファイルからの読み込み)
	String username=prop.PropValue("username");
	String dbname=prop.PropValue("dbname");
	String password=prop.PropValue("password");
	String port=prop.PropValue("port");
	String host=prop.PropValue("host");


	/**
	 * コネクションを設定する
	 * <p>
	 * @param dbname 接続するDB名
	 */
	public void setConnection(String dbname) {
		this.dbname=dbname;
		try {
			this.conn=DriverManager.getConnection(
					"jdbc:postgresql://"+this.host+this.port+"/"+this.dbname, this.username, this.password
					);
		}catch(SQLException e) {
			new CoreException("SQLEXCEPTION");
		}
	}
	/**
	 * コネクションを取得するためのメソッド
	 * @return conn
	 * 					SQLConnection
	 */
	public Connection getConnect() {
		return this.conn;
	}

	/**
	 * Connectionを閉じるためのメソッド
	 * @param conn
	 * 					コネクション
	 * @return closeに成功したか失敗したか
	 * @throws SQLException SQLコネクションを閉じるのに失敗した場合の例外
	 */
	public boolean closeConnection(Connection conn) throws SQLException {
		if(conn!=null) {
			conn.close();
			return true;
		}else {
		return false;
		}
	}

}
