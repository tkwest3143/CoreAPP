package core;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * CoreProperty
 * <p>
 *
 * @author nishi
 *
 */
public class CoreProperty {

	private String value;
	private Properties prop=new Properties();
	private String file="CoreAPP/config/dbconf.properties";

	/**
	 * コンストラクタ
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
			e.printStackTrace();
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
