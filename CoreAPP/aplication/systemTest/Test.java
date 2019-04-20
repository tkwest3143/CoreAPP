package systemTest;

import core.CoreStartUp;

/**
 * @author nishi
 *
 */
public class Test extends CoreStartUp{

	@Override
	public void executeStart() {
		System.out.println("test");

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
