package core;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * FileOperation
 * <p>
 * ファイル操作を行う共通のクラス。
 * @author nishi
 *
 */
public class FileOperation {
	private InputStream is;
	private BufferedOutputStream bos;
	private FileOutputStream fos;
	private FileWriter fw;

	/**
	 * @param file
	 * @return is
	 * 					データファイルを読み込む
	 */
	public InputStream readDataFile(String file) {
		try {
			this.is=new FileInputStream(file);
		}catch(IOException e) {

		}
		return this.is;

	}
	/**
	 *
	 */
	public void writeTextFile() {

	}
	/**
	 *
	 */
	public void writeDataFile() {


	}
}
