package systemTest;

import core.CoreException;
import core.CoreStartUp;
import core.FileOperation;
import core.FileOperation.FileSelect;
import core.LogEnum;

/**
 * テストを実施する際に使用するクラス
	 * <p>
	 * Coreパッケージ内に修正が加わった場合や新規機能を実装する場合にコードの実行確認などを実施する
	 * </p>
 * @author tkwest3143
 *
 */
public class Test extends CoreStartUp{

	/**
	 *
	 * @see core.CoreStartUp#executeStart()
	 */
	@Override
	public void executeStart() {
		System.out.println("test");

		int[][] inputdata= {{1,2,3},{4,5,6}};
		FileOperation FO=new FileOperation("test");
		FO.writeFile(FileSelect.DAT, inputdata);
		try {
			throw new CoreException("SQLEXCEPTION");
		}catch(CoreException e) {
			new CoreException(LogEnum.IOEXCEPTION,e);
		}
	}

	/**
	 * メインメソッド
	 * @param args コマンドライン引数(使用しない)
	 */
	public static void main(String[] args) {
		new Test().start("test");
	}
}
