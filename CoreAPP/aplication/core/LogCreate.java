package core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * LogCreate
 * <p>
 * ログを作成するクラス。
 * @author tkwest3143
 *
 */
public class LogCreate {
	private StringBuilder sb=new StringBuilder();
	private String logMessage;
	private String logLevel;
	private final String file="C://config/log.properties";
	private final String logDir="START_UP_LOGDIR";
	private final String logfile="START_UP_LOGNAME";

	/**
	 * @param logLevel
	 * @param logMessage
	 * 					ログに出力するメッセージ
	 */
	public void logMessage(String logLevel,String logMessage) {
		this.logLevel=logLevel;
		this.logMessage=logMessage;
		try {
			this.buildLogMessage();
			this.logWrite();
		}catch(IOException e) {
			System.out.println("IOException");
		}

	}
	private void logWrite() throws IOException{

		CoreProperty prop=new CoreProperty(file);
		try {
			//プロパティファイルからFileWriterを作成
		FileWriter fw=new FileWriter(prop.PropValue(this.logDir)+prop.PropValue(this.logfile));
		PrintWriter pw=new PrintWriter(new BufferedWriter(fw));
		//ログを書いていく
		pw.println(this.sb);

		closePrintWriter(pw);
		}catch(IOException e) {
			throw new IOException();
		}
	}
	private void buildLogMessage() {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String nowtime=sdf.format(date);
		this.sb.append("[");
		this.sb.append(nowtime);
		this.sb.append("]");
		this.sb.append("-");
		this.sb.append(this.logLevel);
		this.sb.append("-");
		this.sb.append(this.logMessage);
	}
	private void closePrintWriter(PrintWriter pw) throws IOException{
		if(pw!=null) {
			pw.close();
		}else {
			this.logMessage("E","FILECLOSE_NULL");

		}
	}
}
