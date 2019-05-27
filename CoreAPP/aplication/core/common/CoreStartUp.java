package core.common;

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
	private LogCreate log=new LogCreate(this.getClass().getName());
	private String OS_NAME;

	/**
	 * コンストラクタ
	 * 起動しているOSを調べる
	 */
	public CoreStartUp() {
		this.OS_NAME=System.getProperty("os.name").toLowerCase();
	}
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

	/**
	 * @return 起動しているOSの種類を返却する。
	 */
	public String getOSName() {
		return this.OS_NAME;
	}

	/**
	 * 簡略化したOSの名前を返却する。
	 * <p>
	 * WINDOWSの場合は、そのままWINDOWSを返却し、MAC、LINUXはUNIXの文字列を返却する。
	 * @return 簡略化したOSの名前
	 */
	public String getBasicOSName() {
		String basicOS_NAME=null;
		if(this.OS_NAME.contains("WINDOWS")){
			basicOS_NAME="WINDOWS";
		}else if(this.OS_NAME.contains("MAC")||this.OS_NAME.contains("LINUX")) {
			basicOS_NAME="UNIX";
		}
		return basicOS_NAME;
	}
}
