package core;

import core.LogCreate.LogLevel;

/**
 * ログ出力のエラーメッセージを格納する列挙型クラス
 * <p>
 * プロパティファイルに記載されているキーを列挙型で記述し、あとからString型へと変更することでログ出力を行う。<br>
 * キーのJavaDocはプロパティファイルに記載されているメッセージを記述している。<br>
 *
 * @author nishi
 *
 */
public enum LogEnum {
	//-----情報-----
	/**
	 * アプリケーションを開始します。
	 */
	APPLICATION_START(LogLevel.INFO),
	/**
	 * アプリケーションを終了します。
	 */
	APPLICATION_END(LogLevel.INFO),
	/**
	 * プロパティファイルに設定したキーがありません。プロパティファイルを確認してください.
	 */
	PROP_VALUE_NULL(LogLevel.ERROR),

	//-----データベース操作-----
	/**
	 * データベースとの連携時に何らかのエラーが発生しました。
	 */
	SQLEXCEPTION(LogLevel.ERROR),

	//-----ファイル操作-----
	/**
	 * ファイルを操作時にエラーが発生しました。
	 */
	IOEXCEPTION(LogLevel.ERROR),
	/**
	 * ファイル読み込み時にエラーが発生しました。
	 */
	FILEREAD_EXCEPTION(LogLevel.ERROR),

	/**
	 * ファイル書き込み時にエラーが発生しました。
	 */
	FILEWRITE_EXCEPTION(LogLevel.ERROR),
	/**
	 * ファイル読み込みを開始します。
	 */
	FILEREAD_START(LogLevel.INFO),
	;

	private final LogLevel logLevel;

	private LogEnum(final LogLevel logLevel) {
		this.logLevel=logLevel;
	}

	/**
	 * ログに出力されるレベルを返すメソッド
	 * @return ログ出力レベル
	 */
	public LogLevel getLogLevel() {
		return this.logLevel;
	}
}
