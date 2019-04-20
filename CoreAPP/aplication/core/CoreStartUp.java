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
	LogCreate log=new LogCreate();

	/**
	 *実行
	 */
	private void execute() {
		log.logMessage("I", "STARTUP");
		//実処理実行
		this.executeStart();
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
	 */
	public void start(String startUpId) {
		this.execute();
	}

}
