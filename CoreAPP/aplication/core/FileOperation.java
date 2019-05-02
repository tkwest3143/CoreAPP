package core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;

import core.LogCreate.LogLevel;

/**
 * FileOperation
 * <p>
 * ファイル操作を行う共通のクラス。
 * @author tkwest
 *
 */
public class FileOperation {
	private LogCreate logger=new LogCreate(this.getClass().getName());
	private CoreProperty prop=new CoreProperty("./config/file.properties");
	private String file;
	private InputStream is;
	private BufferedReader br;
	LogCreate log=new LogCreate(this.getClass().getName());//ログ出力のためのクラス
	/**
	 * FileSelect
	 * <p>
	 * ファイルを操作するためのキー
	 * @author tkwest3143
	 *
	 */
	public enum FileSelect{
		/**
		 * csvファイルを操作するためのキー
		 */
		CSV,
		/**
		 * datファイルを操作するためのキー
		 */
		DAT,
		/**
		 * txtファイルを操作するためのキー
		 */
		TXT
	}

	/**
	 * コンストラクタ
	 * <p>
	 * fileKeyに設定されたファイルのパスを取得する。
	 * </p>
	 * @param fileKey プロパティファイルに記載している扱うファイルのキー
	 */
	public FileOperation(String fileKey) {
 		this.file=prop.PropValue(fileKey);
	}
	/**
	 * <p>
	 * コンストラクタで指定されたファイルを読み込み、<br>
	 * BufferedReaderを返すメソッド
	 *
	 * @return BufferedReader
	 * 					ファイルから読み込まれたBufferedReader
	 */
	public BufferedReader readFile() {
		logger.logMessage(LogEnum.FILEREAD_START);
		try {
			this.log.logMessage(LogLevel.INFO, "ファイルの読み込みを行います");
			this.is=new FileInputStream(this.file);
			Reader reader=new InputStreamReader(this.is,"utf-8");
			this.br=new BufferedReader(reader);
		}catch(IOException e) {

		}finally {
			if(this.is!=null) {
				try {
					this.is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return this.br;
	}
	/**
	 * <p>
	 * BufferedReaderで読み込んだファイルから、
	 * 一行ずつ読み込みリストにして返すメソッド
	 * @param br 読み込んだBufferedReader
	 * @return ArrayList 一行ずつ読み込んだファイルの内容を格納したリスト
	 */
	public ArrayList<String> LineList(BufferedReader br){
		ArrayList<String> fileList=new ArrayList<String>();
		String line;
		try {
			while((line=br.readLine())!=null) {
				fileList.add(line);
			}
		} catch (IOException e) {
			new CoreException(LogEnum.IOEXCEPTION,e);
		}
		return fileList;
	}

	/**
	 * ファイル書き出しのためのメソッドcsv、dat、txtファイルでそれぞれの操作を行う。
	 * <p>
	 * 以下を参照<br>
	 * <p>
	 * [CSV]<br>
	 * カンマと改行でcsvファイルを記述。<br>
	 * {@link core.FileOperation#writeCsvFile csvファイルを書き込むメソッド詳細}
	 * </p><p>
	 * [DAT]<br>
	 * 2次元か３次元配列をinputDataに設定することでファイルに記述する。<br>
	 * {@link core.FileOperation#writeDatFile datファイルを書き込むメソッド詳細}
	 * </p><p>
	 * [TXT]<br>
	 * inputDataの内容をそのままtxtファイルに記述する。<br>
	 * {@link core.FileOperation#writeTxtFile txtファイルを書き込むメソッド詳細}
	 * </p>
	 * @param fileSelect ファイル操作のための区分
	 * @param inputData ファイルに書き込むデータ
	 */
	public void writeFile(FileSelect fileSelect,Object inputData) {
		try {
			switch(fileSelect) {
			case CSV:
				writeCsvFile(inputData);
				break;
			case DAT:
				writeDatFile(inputData);
				break;
			case TXT:
				writeTxtFile(inputData);
				break;
			default:
				break;
			}
		}catch(IOException e) {
			new CoreException(LogEnum.IOEXCEPTION,e);
		}
	}
	private  void writeTxtFile(Object inputdata) throws IOException{
		//TODO テキストファイルに書き出す処理を記述してください
	}

	private void writeDatFile(Object inputdata)throws IOException {
		 FileOutputStream fos=
				 new FileOutputStream(this.file,false);
		 OutputStreamWriter osw=
				 new OutputStreamWriter(fos);
		int twoDList[][];
		int threeDList[][][];
		 try {
			twoDList=(int[][])inputdata;
			 for(int[] j:twoDList) {
				 for(int k:j) {
					 String s=String.valueOf(k);
					 osw.write(s);
					 osw.write(" ");
				 }
				 osw.write(System.getProperty("line.separator"));
			 }
		 }catch(NumberFormatException e) {
			 try {
			 threeDList=(int[][][] )inputdata;
			 for(int[][] i:threeDList) {
				 for(int[] j:i) {
					 for(int k:j) {
						 osw.write(k);
						 osw.write(" ");
					 }
				 }
				 osw.write(System.getProperty("line.separator"));
			 }
			 }catch(NumberFormatException e2) {
				 //TODO キャスト時のエラーを書いてください
			 }
		 }finally {
			 osw.close();
		 }
	}

	/**
	 * CSVファイルを作成するためのメソッド
	 * <p>
	 * inputされたデータを型変換し、CSVファイルを作成します。<br>
	 * Listが引数として設定されているとひとつづつ読み込み、CSVファイルを作成します。<br>
	 * Stringが設定されているとそのままファイルに書き込み、最後にカンマを書き込む。<br>
	 * ListとString以外の型は予想していないのでエラーを出力するようにしている。
	 * @param inputdata
	 * @throws IOException
	 */
	private void writeCsvFile(Object inputdata) throws IOException{
		//TODO CSVファイルを作成するための処理を記述して下さい。
		FileOutputStream fos=
				 new FileOutputStream(this.file,false);
		 OutputStreamWriter osw=
				 new OutputStreamWriter(fos);

		 //引数のinputdataがListかどうかの判定
		 if(inputdata instanceof ArrayList) {
			 @SuppressWarnings("unchecked")
			ArrayList<String> list=(ArrayList<String>)inputdata;
			 for(String s:list) {
				 osw.write(s);
				 if(!s.equals(System.lineSeparator()))
				 osw.write(",");
			 }
		 }else if(inputdata==(Class<?>)String.class) {
			String str=(String)inputdata;
			osw.write(str);
			osw.write(",");
		 }else {
			 //TODO 引数がList、Stringではないとき
		 }
		 osw.close();
	}

}
