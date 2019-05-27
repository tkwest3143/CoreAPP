package core.common;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * メール送信クラス
 * <p>
 * メール送信のための基底クラスです。<br>
 *
 * @author nishi
 *
 */
public class CoreMail {
	LogCreate logger=new LogCreate(this.getClass().getName());
	String propPath="./config/mail.properties";
	CoreProperty prop =new CoreProperty(propPath);
	Session session;
	MimeMessage mm;

	/**
	 * コンストラクタ
	 * <p>
	 *configフォルダからメール送信のためのプロパティを設定し、MimeMessageを設定する。
	 */
	public CoreMail() {
		logger.logMessage(LogEnum.MAIL_START);
		this.session = Session.getDefaultInstance(this.prop.getProperties());
		this.mm=new MimeMessage(this.session);
	}

	/**
	 * コンストラクタ
	 * <p>
	 * 個別にプロパティを設定している場合はこのコンストラクタを使用<br>
	 * ※できるだけCorePropertyを使用する
	 * @param props セッションのインスタンス化のためのプロパティインスタンス
	 */
	public CoreMail(Properties props) {
		logger.logMessage(LogEnum.MAIL_START);
		this.session=Session.getDefaultInstance(props);
		this.mm=new MimeMessage(this.session);
	}
	/**
	 * 一つのアドレスにメールを送信する際のメソッド
	 * <p>
	 * メールを送信する相手が一つだけの場合に使用します。<br>
	 * 複数人に贈りたい場合は、{@link core.common.CoreMail#getAddress(String[])}を参照してください
	 * @param address メール送信先アドレス
	 * @return {@link javax.mail.internet.InternetAddress}
	 * @throws AddressException アドレス設定時の例外
	 */
	public InternetAddress getAddress(String address) throws AddressException {
		InternetAddress toAddress=new InternetAddress(address);
		return toAddress;
	}
	/**
	 * 複数のメールアドレスにメールを送りたい場合に使用するメソッドです。<br>
	 *
	 * 一つのメールアドレスのみにメールを送りたい場合は、以下のメソッドでも利用できます。<br>
	 * {@link core.common.CoreMail#getAddress(String)}
	 * @param address address メール送信先アドレス
	 * @return {@link javax.mail.internet.InternetAddress}
	 * @throws AddressException アドレス設定時の例外
	 */
	public InternetAddress[] getAddress(String[] address) throws AddressException {
		InternetAddress[] toAddress=new InternetAddress[address.length];
		for(int i=0; i<address.length;i++) {
			toAddress[i]=new InternetAddress(address[i]);
		}
		return toAddress;
	}

	/**
	 * 引数に指定されている項目からメールを送信するメソッド
	 * @param toAddress 送信先アドレス
	 * @param fromAddress 送信元アドレス
	 * @param subject 件名
	 * @param text 本文
	 */
	public void basicSendMail(String toAddress,String fromAddress,String subject,String text) {
		try {
			this.mm.setRecipient(MimeMessage.RecipientType.TO,getAddress(toAddress));
			mm.setFrom(getAddress(fromAddress));
			mm.setSubject(subject);
			mm.setText(text);
			mm.saveChanges();
			Transport.send(mm);
		}catch(MessagingException ae) {
			new CoreException(LogEnum.ADDRESS_EXCEPTION,ae);
		}
	}
}
