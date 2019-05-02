/**
 * ©2019 takanori nishi
 */

package core;

import core.LogCreate.LogLevel;

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
	LogCreate log=new LogCreate(this.getClass().getName());
	LogLevel LL=LogLevel.ERROR;
	/**
	 * コンストラクタ
	 * <p>
	 * 引数に設定されたメッセージのキーをもとにエラーを出力する。<br>
	 *
	 *<p>
	 * ログのためのクラス：{@link LogCreate}<br>
	 * ログの出力のメソッド：{@link LogCreate#logMessage(LogLevel,String)}
	 * </p>
	 * @param ErrMsg
	 * 					例外発生時のエラーメッセージのキー
	 */
	public CoreException (String ErrMsg) {
		super();
		this.log.logMessage(LL,ErrMsg);
		return;
	}

	/**
	 * コンストラクタ
	 * <p>
	 * 引数にThrowableを使うことにより、
	 * catch文の際にこのコンストラクタを用いることで例外発生の概要と
	 * 発生個所がコンソールとログの両方に出力されるようになる。
	 * </p><p>
	 * ログのための共通クラス：{@link LogCreate}<br>
	 * ログの出力のメソッド：{@link LogCreate#logMessage(Throwable)}
	 * </p>
	 * @param Message ログに出力するメッセージのキー
	 * @param e 例外処理に扱うThrowable
	 */
	public CoreException(String Message,Throwable e) {
		super(e);
		log.logMessage(Message,e);
		return;
	}

	/**
	 *  コンストラクタ
	 * <p>
	 * 引数にThrowableを使うことにより、
	 * catch文の際にこのコンストラクタを用いることで例外発生の概要と
	 * 発生個所がコンソールとログの両方に出力されるようになる。
	 * </p><p>
	 * ログのためのクラス：{@link LogCreate}<br>
	 * ログの出力のメソッド：{@link LogCreate#logMessage(Throwable)}
	 * </p>
	 * @param logMessage LogEnumクラスに記述されているログ情報
	 * @param e 例外処理に扱うThrowable
	 */
	public CoreException(LogEnum logMessage,Throwable e) {
		log.logMessage(logMessage,e);
		return;
	}


}
