package systemTest;

import java.util.HashMap;
import java.util.Map;

import core.common.CoreStartUp;
import core.dbAccess.DbConnect;
import core.dbAccess.DbType;

/**
 * テストを実施する際に使用するクラス
	 * <p>
	 * Coreパッケージ内に修正が加わった場合や新規機能を実装する場合にコードの実行確認などを実施する
	 * </p>
 * @author tkwest3143
 *
 */
public class Test extends CoreStartUp{

	/**
	 *
	 * @see core.common.CoreStartUp#executeStart()
	 */
	@Override
	public void executeStart() {
		System.out.println("testStart!!");

		String sqlQuery="insert into userinfo(name,born,password) values ('west1234','19951201','12345');";
		String sqlSelect="select name from userinfo;";

		Map<String,DbType> map=new HashMap<String,DbType>();
		map.put("name", DbType.STRING);

		DbConnect firstConn=new DbConnect();
		firstConn.setConnect("LMDB");

		System.out.println(getOSName());

	}

	/**
	 * メインメソッド
	 * @param args コマンドライン引数(使用しない)
	 */
	public static void main(String[] args) {
		new Test().start("test");
	}
}
