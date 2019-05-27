package core.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * LogCreate
 * <p>
 * ログを作成するクラス。
 * @version 1.00
 * @author tkwest3143
 *
 */
public class LogCreate {
	private final Logger logger;//ロガー
	private String logMessage;//ログメッセージのキー
	private  LogLevel logLevel;//ログ出力レベル
	private final CoreProperty prop=new CoreProperty("./config/log.properties");

	/**
	 *
	 * <p>
	 * logに出力するレベルを定義
	 * </p>
	 * 以下の内容が実行されます。
	 * <p>
	 * <p>[INFO]</p>
	 * org.apache.logging.log4j.Logger.info(ログに出力されるメッセージのキー)
	 * <p>[ERROR]</p>
	 * org.apache.logging.log4j.Logger.error(ログに出力されるメッセージのキー)
	 * <p>[FATAL]</p>
	 * org.apache.logging.log4j.Logger.fatal(ログに出力されるメッセージのキー)
	 * </p>
	 * @author tkwest3143
	 *
	 */
	public enum LogLevel  {
		/**
		 * 起動中の状態や実行する処理などの情報を記述。
		 *
		 *
		 */
		INFO,
		/**
		 * Exceptionが発生する以外のエラーを記述。
		 *
		 */
		ERROR,

		/**
		 * Exception発生時に用いる。
		 */
		FATAL
	}

	/**
	 * @param classname クラス名
	 */
	public LogCreate(String classname) {
		this.logger=LogManager.getLogger(classname);
	}
	/**
	 * ログを出力するためのメソッド
	 * <p>
	 * 引数に設定されているログの出力レベルとメッセージを引数として設定し、ログを書いていく。<br>
	 * logMessageに設定されている値がそのままログに出力される。
	 * @param logLevel
	 * 					ログに出力するメッセージのレベル<br>
	 * 					使用できるレベルはinfoとerrorのみ
	 * @param logMessage
	 * 					ログに出力するメッセージ
	 */
	public void logMessage(LogLevel logLevel,String logMessage) {
		this.logLevel=logLevel;
		this.logMessage=logMessage;
		this.buildLogMessage();
	}

	/**
	 *
	 *ログ出力文言：{@link LogEnum}
	 * @param logMessage LogEnumクラスに定義されているメッセージのキー
	 */
	public void logMessage(LogEnum logMessage) {
		this.logLevel=logMessage.getLogLevel();
		this.logMessage=this.prop.PropValue(logMessage.name());
		this.buildLogMessage();
	}

	/**
	 *
	 * ログ出力文言：{@link LogEnum}
	 *
	 * @param logMessage Exception発生時に出力されるログメッセージ
	 * @param e 発生例外
	 */
	public void logMessage(LogEnum logMessage,Throwable e) {
		this.logLevel=logMessage.getLogLevel();
		this.logMessage=this.prop.PropValue(logMessage.name());
		this.logger.fatal(this.logMessage,e);
	}
	/**
	 * 例外発生時のログ出力メソッド
	 * <p>
	 * @param logMessage  ログに出力するメッセージのキー
	 *
	 * @param e 発生例外
	 */
	public void logMessage(String logMessage,Throwable e) {
		this.logMessage=this.prop.PropValue(logMessage);
		this.logger.fatal(this.logMessage,e);
	}

	/**
	 * ログを一行出力するための文字列を生成するメソッド
	 */
	private void buildLogMessage() {
		//ログ出力文字列の初期化処理
		switch(this.logLevel) {
		case INFO:
			this.logger.info(this.logMessage);
			break;
		case ERROR:
			this.logger.error(this.logMessage);
			break;
		default:
		}
	}
}
