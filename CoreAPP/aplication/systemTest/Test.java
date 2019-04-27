package systemTest;

import core.CoreStartUp;
import core.LogCreate;
import core.LogCreate.LogLevel;

/**
 * @author tkwest3143
 *
 */
public class Test extends CoreStartUp{


	/*
	 * @see core.CoreStartUp#executeStart()
	 */
	@Override
	public void executeStart() {
		System.out.println("test");
		LogCreate log=new LogCreate();
		log.logMessage(LogLevel.INFO, "IOEXCEPTION");
	}

	/**
	 * メインメソッド
	 * @param args
	 * 					コマンドライン引数(使用しない)
	 */
	public static void main(String[] args) {
		new Test().start("test");
	}
}
