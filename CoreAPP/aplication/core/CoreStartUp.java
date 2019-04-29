package core;

/**
 * CoreStartUp
 * <p>
 * CoreAPP内で実際に実行するクラスの規定となるクラス
 * <p>
 * 処理内容はstart->excecute->excecuteStartという順で執行される
 * @author tkwest
 *
 */
public abstract class CoreStartUp {
	/**
	 *
	 */
	private LogCreate log=new LogCreate(this.getClass().getName());

	/**
	 *実行
	 */
	private void execute() {
		//実処理実行
		this.executeStart();
		//終了処理
		log.logMessage(LogEnum.APPLICATION_END);
	}

	/**
	 * 実処理メソッド
	 * <p>
	 * 実際に実行する処理
	 */
	abstract public void executeStart() ;

	/**
	 * start
	 * <p>
	 * 実行メソッド
	 * @param startUpId
	 *			実行するアプリケーションのID
	 */
	public void start(String startUpId) {
		log.logMessage(LogEnum.APPLICATION_START);
		this.execute();
	}

}
