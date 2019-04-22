package dbAccess;

import core.CoreException;

/**
 * DbOperation
 * <p>
 * DBの操作を行うためのクラス
 * @author tkwest3143
 *
 */
public class DbOperation {

	/**
	 * DBの操作を行う上でSwitch文によって操作を判定するときに用いる
	 * @author tkwest3143
	 *
	 */
	public enum DbOrder{
		/**
		 * DbのInsert文を判定する
		 */
		INSERT,
		/**
		 * DbのUpdate文を判定する
		 */
		UPDATE,
		/**
		 * DbのSelect文を判定する
		 */
		SELECT,
		/**
		 * DbのDelete文を判定する
		 */
		DELETE,
	}
	/**
	 * コンストラクタ
	 * <p>
	 * 引数に設定されている命令文によってさまざまな変数を変更する。
	 * @param order
	 * 					SQLを実行する際の基本構文4種
	 */
	public DbOperation(DbOrder order) {
		switch(order){
		case INSERT:
			//Insertの際の処理@Todo
			break;
		case UPDATE:
			//Updateの際の処理
			break;
		case SELECT:
			//Selectの際の処理
			break;
		case DELETE:
			//Deleteの際の処理
			break;
		default:
			//想定外の例外処理
			new CoreException("EXCEPTION");
		}

	}
}
