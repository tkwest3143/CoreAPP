package core.dbAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import core.CoreException;
import core.FileOperation;
import core.FileOperation.FileSelect;
import core.LogCreate;
import core.LogEnum;

/**
 * insert処理を記述している抽象クラス
 * <p>
 *
 * @author nishi
 *
 */
public abstract class BaseQuery {
	private String firstOrder;
	private LogCreate looger=new LogCreate(this.getClass().getName());


	/**
	 * 実際にSQLに実行する文を作成するクラス
	 * @return 実行するQuery文
	 */
	public abstract  StringBuffer createSQL() ;

	/**
	 * 引数に設定されているコネクションとSQL文から実行し、ResultSetを返すメソッド
	 * <p>
	 * 実行に成功した場合は、ResultSetを返し、失敗した場合にはExceptionを発生させる。
	 * @param conn Connection
	 * @param sqlQuery 実行するSQL文
	 * @return 実行した結果を返す
	 */
	public ResultSet startQuery(Connection conn,String sqlQuery) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sqlQuery);
			return rs;
		} catch (SQLException e) {
			new CoreException(LogEnum.SQLCONNECTION_ERROR,e);
			return null;
		}
	}

	/**
	 * SQLを実行したResultSetからListに格納して返すメソッド
	 * <p>
	 * 返すリストの型は、Stringに変換して、返す。
	 * @param rs SQL実行結果のResultSet
	 * @param map カラムの名前と扱う型を格納したMap
	 * @return ResultSetから取得した値をリストに格納
	 */
	public List<String> getResultList(ResultSet rs,Map<String,DbType> map){
		List<String> resultList = new ArrayList<String>();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		String addList;
		try {
			while(rs.next()) {
				//一行ずつMapを見ていき、switch文でString型へ変換しリストに格納
				for(Map.Entry<String, DbType> type: map.entrySet()) {
					switch(type.getValue()){
					case DATE:
						addList=sdf.format(rs.getDate(type.getKey()));
						resultList.add(addList);
						break;
					case DOUBLE:
						addList=String.valueOf(rs.getDouble(type.getKey()));
						resultList.add(addList);
						break;
					case INTEGER:
						addList=String.valueOf(rs.getInt(type.getKey()));
						resultList.add(addList);
						break;
					case STRING:
						resultList.add(rs.getString(type.getKey()));
						break;
					default:
						break;
					}
				}
				resultList.add(System.lineSeparator());
			}
		}catch(SQLException e) {
			//TODO
		}
		return resultList;
	}

	/**
	 * ResultSetからCSVファイルを作成するメソッド
	 * <p>
	 * カラム名と型を引数として設定することによって、カンマで区切ってファイルを作成する。<br>
	 * 一行の読み込みが終了したら次の行は、改行してファイルに書き込む。
	 * ※Selectのみ使用
	 * @param rs 読み込みたいデータを格納したResultSet
	 * @param map  取得したいカラム名と型を格納しているMap
	 * @param fileName 作成したいファイル名を設定しているキー
	 *
	 */
	public void createResultCsv(ResultSet rs,Map<String,DbType> map,String fileName) {
		this.looger.logMessage(LogEnum.IOEXCEPTION);
		FileOperation FO=new FileOperation(fileName);
		List<String> resultList=this.getResultList(rs, map);
		FO.writeFile(FileSelect.CSV, resultList);
	}
}
