package core.dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import core.common.CoreException;
import core.common.CoreProperty;
import core.common.LogCreate;
import core.common.LogEnum;

/**
 * データベースとのConnectionを確立するためのクラス
 * @author tkwest3143
 *
 */
public class DbConnect {
	private LogCreate logger=new LogCreate(this.getClass().getName());
	CoreProperty prop=new CoreProperty("./config/dbconf.properties");
	Connection conn=null;

	//DB接続のための基本設定(dbconf.propertiesファイルからの読み込み)
	private final String username=prop.PropValue("username");
	private String dbname=prop.PropValue("dbname");
	private final String password=prop.PropValue("password");
	private final String port=prop.PropValue("port");
	private final String host=prop.PropValue("host");

	/**
	 * Connectionを設定する
	 * <p>
	 * 接続するデータベースの情報は、dbconf.propertiesから参照し、情報を取得する。<br>
	 * データベース名を引数に設定することにより、そのデータベースとの接続を試みる。
	 * @param dbname 接続するDB名
	 */
	public void setConnect(String dbname) {
		this.dbname=dbname;
		try {
			logger.logMessage(LogEnum.SQLCONNECTION_START);
			this.conn=DriverManager.getConnection(
					"jdbc:postgresql://"+this.host+this.port+"/"+this.dbname, this.username, this.password
					);
		}catch(SQLException e) {
			new CoreException(LogEnum.SQLCONNECTION_ERROR,e);
		}
	}
	/**
	 * Connectionを取得するためのメソッド
	 * @return conn
	 * 					SQLConnection
	 */
	public Connection getConnect() {
		return this.conn;
	}

	/**
	 * Connectionを閉じるためのメソッド
	 * <p>
	 * 正しく閉じられた場合、trueを返し、nullの場合は、エラーメッセージを出力する。<br>
	 * 閉じる際に例外が発生した場合は、CoreExceptionをcatchし、ログに出力する。
	 * @param conn
	 * 					コネクション
	 * @return closeに成功したか失敗したか
	 *
	 */
	public boolean closeConnect(Connection conn){
		try {
			if(conn!=null) {
				conn.close();
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			new CoreException(LogEnum.SQLEXCEPTION,e);
		}
		return false;
	}

}
