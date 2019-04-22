package core;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * CoreProperty
 * <p>
 *Propertiesファイルからキーに設定された値を読み込み、取得するためのクラス
 * @author tkwest
 *
 */
public class CoreProperty {

	//取得する値
	private String value;
	//プロパティのインスタンス化
	private Properties prop=new Properties();
	//プロパティファイルのパス
	private String file="CoreAPP/config/dbconf.properties";

	/**
	 * コンストラクタ
	 * <p>
	 * 引数に設定されているファイルのパスを指定し、InputStreamでプロパティファイルを読み込む。
	 * @param file
	 * 						propertyファイルのパスを設定
	 *
	 */
	public CoreProperty(String file) {
		this.file=file;
		try {
			InputStream iS=new FileInputStream(this.file);
			prop.load(iS);
			iS.close();
		}catch(Exception e) {
			new CoreException("IOEXCEPTION");
		}
	}
	/**
	 * キーを設定し、設定されている値を返す。
	 * <p>
	 * @param key
	 * 					キー
	 * @return value
	 * 					キーに設定されている値
	 */
	public String PropValue(String key) {
		this.value=prop.getProperty(key);
		return this.value;
	}



}
