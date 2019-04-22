package core;

/**
 * クラス名：CoreException
 * <p>
 * クラス概要：共通のExceptionクラス。
 * <p>
 * [詳細]<br>
 * エラーメッセージからエラー個所を読み込み任意に設定されているエラーメッセージをログに出力する。
 * @author tkwest3143
 *
 */
public class CoreException extends Exception {
	LogCreate log=new LogCreate();
	/**
	 * コンストラクタ
	 * <p>
	 * 引数に設定されたメッセージをもとにエラーを出力する。
	 *
	 * @param ErrMsg
	 * 					例外発生時のエラーメッセージ
	 */
	public CoreException (String ErrMsg) {
		this.log.logMessage("E", ErrMsg);
	}

}
