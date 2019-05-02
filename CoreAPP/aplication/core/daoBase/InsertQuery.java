package core.daoBase;


import java.util.Map;

import core.dbAccess.BaseQuery;

/**
 * @author tkwest3143
 *
 */
public class InsertQuery extends BaseQuery {
	private StringBuffer sqlquery=new StringBuffer();
	private Map<String,Object> sqlColumn;
	private String tableName;

	/**
	 * コンストラクタ
	 * <p>
	 * データベースに挿入するカラム名と値を格納したMapを設定する
	 * @param map データベースに格納するための値<br>
	 * 								・キー：カラム名<br>
	 * 								・値：値
	 * @param tableName テーブル名
	 */
	public InsertQuery(Map<String,Object> map,String tableName) {
		this.sqlColumn=map;
		this.tableName=tableName;
	}
	/**
	 * @see core.dbAccess.BaseQuery#createSQL()
	 */
	@Override
	public StringBuffer createSQL() {
		this.sqlquery.append("INSERT INTO");
		this.sqlquery.append(this.tableName);
		this.sqlquery.append("(");
		int columnCnt=0;
		for(Map.Entry<String, Object> map:this.sqlColumn.entrySet()) {
			columnCnt++;
			
		}
		return this.sqlquery;
	}

}
