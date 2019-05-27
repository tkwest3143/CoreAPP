package core.dbAccess;

import core.common.LogCreate;
import core.common.LogEnum;

/**
 * DBの操作を行うための汎用クラス
 * <p>
 * 
 * @author tkwest3143
 *
 */
public class DbOperation {

	LogCreate logger=new LogCreate(this.getClass().getName());
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
			logger.logMessage(LogEnum.INSERT_START);
			//TODO Insertの際の処理
			break;
		case UPDATE:
			logger.logMessage(LogEnum.UPDATE_START);
			//TODO Updateの際の処理
			break;
		case SELECT:
			logger.logMessage(LogEnum.SELECT_START);
			//TODO Selectの際の処理
			break;
		case DELETE:
			logger.logMessage(LogEnum.DELETE_START);
			//TODO Deleteの際の処理
			break;
		default:
			//想定外の例外処理
			logger.logMessage(LogEnum.DBOPERATION_ERROR);
		}
	}

}
