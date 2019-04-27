package core;

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
	private final Logger logger = LogManager.getLogger();//ロガー
	private String logMessage;//ログメッセージのキー
	private  LogLevel logLevel;//ログ出力レベル

	/**
	 * LogLevel
	 * <p>
	 * logに出力するレベルを定義
	 * </p>
	 * 今回はinfoとerrorの２種類のみを採用
	 * @author tkwest3143
	 *
	 */
	public enum LogLevel {
		/**
		 * 起動中の状態などを記述
		 */
		INFO,
		/**
		 * 発生したExceptionなどのエラーはすべて記述
		 */
		ERROR,
	}
	/**
	 * ログを出力するためのメソッド
	 * <p>
	 * 引数に設定されているログの出力レベルとメッセージのキーを引数として設定し、ログを書いていく。
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
