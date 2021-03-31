package cn.com.yanyuchen.mymusic_web.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件发送工具
 */
public class EmailUtil {

    private static final String sendBoxString = "mail.com";

    /**
     * 发送验证码
     *
     * @param to 要发送的邮箱
     * @return 生成的验证码
     */
    public static String send(String to, String text) throws MessagingException {

        // 配置参数
        Properties prop = new Properties();
        // 发件人的邮箱的SMTP 服务器地址（不同的邮箱，服务器地址不同，如139和qq的邮箱服务器地址不同）
        String hostString = "smtp.163.com";
        prop.setProperty("mail.host", hostString);
        // 使用的协议（JavaMail规范要求）
        prop.setProperty("mail.transport.protocol", "smtp");
        // 需要请求认证
        prop.setProperty("mail.smtp.auth", "true");

        // 使用JavaMail发送邮件的5个步骤
        // 1、创建session
        Session session = Session.getInstance(prop);
        // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        Transport ts = null;
        // 2、通过session得到transport对象
        ts = session.getTransport();
        // 3、使用邮箱的用户名和密码连接邮件服务器（不同类型的邮箱不一样，网易邮箱输入的是用户名和密码，这里我用的qq邮箱，输入的是邮箱用户名和smtp授权码，smtp授权码可登陆邮箱，进入设置启动smtp服务后获取）
        // 发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        String passwordString = "passwordString";
        ts.connect(hostString, sendBoxString, passwordString);
        // 4、创建邮件
        Message message = createSimpleMail(session, to, text);
        // 5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
        return text;
    }

    /**
     * 创建一封邮件
     *
     * @param session       邮箱session
     * @param to            要发送的邮箱
     * @param contentString 内容字符串
     * @return 返回可发送的邮件
     * @throws MessagingException 邮件异常
     */
    public static MimeMessage createSimpleMail(Session session, String to, String contentString)
            throws MessagingException {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明发件人
        message.setFrom(new InternetAddress(sendBoxString));
        // 指明收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        // 邮件的标题
        message.setSubject("[请勿回复]iMusic-热爱你的音乐 验证码");
        // 邮件的文本内容

        message.setContent(contentString, "text/html;charset=UTF-8");
        return message;
    }

}
