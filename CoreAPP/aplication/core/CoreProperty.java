package core;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>
 *Propertiesファイルからキーに設定された値を読み込み、取得するためのクラス
 *</p>
 *コンストラクタは、ファイルのパス名を指定する。
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
	 * 値の取得と共に取得が正常に行われているかのチェックを行い、<br>
	 * 値が正常に取得できていない場合は、Exceptionを発生する。
	 * @param key
	 * 					キー
	 * @return value
	 * 					キーに設定されている値
	 */
	public String PropValue(String key) {
		this.value=prop.getProperty(key);
		if(!propValueCheck(this.value)) new CoreException("PROP_VALUE_NULL");
		return this.value;
	}

	/**
	 * プロパティファイルから読み込んだ値が存在するかを確認するメソッド
	 * <p>
	 * プロパティから取得した値を引数に設定し、nullではないかをチェックする。<br>
	 * もし引数に設定されている値がnullの場合は、falseを返し、そうでない場合はtrueを返す。
	 * @param checkValue チェック対象の値
	 * @return	チェックした結果
	 */
	public boolean propValueCheck(String checkValue)
	{
		//結果を取得できているかのチェック
		if(checkValue!=null) {
			return true;
		}
		return false;
	}

	/**
	 * プロパティのインスタンスを取得する。
	 * @return {@link java.util.Properties}
	 */
	public Properties getProperties() {
		return this.prop;
	}

}
