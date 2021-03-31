package cn.com.yanyuchen.mymusic_web.service;

import cn.com.yanyuchen.mymusic_web.entity.User;
import cn.com.yanyuchen.mymusic_web.util.EmailUtil;

import javax.mail.MessagingException;

/**
 * 发送邮件的service
 */
public class MailService {

    /**
     * 发送注册验证码
     * @param verCode 验证码
     * @param email 要发的邮箱
     * @throws MessagingException 邮件异常
     */
    public void sendRegister(String verCode, String email) throws MessagingException {
        String text = "<!doctype html>\n" +
                "<html lang=\"en-US\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table width=\"700\" border=\"0\" align=\"center\" cellspacing=\"0\" style=\"width:700px;\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <div style=\"width:700px;margin:0 auto;border-bottom:1px solid #ccc;margin-bottom:30px;\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"700\" height=\"39\" style=\"font:12px Tahoma, Arial, 宋体;\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td width=\"210\">\n" +
                "                           \n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </div>\n" +
                "            <div style=\"width:680px;padding:0 10px;margin:0 auto;\">\n" +
                "                <div style=\"line-height:1.5;font-size:14px;margin-bottom:25px;color:#4d4d4d;\">\n" +
                "                    <strong style=\"display:block;margin-bottom:15px;\">\n" +
                "                        亲爱的iMusic用户：\n" +
                "                        <span style=\"color:#f60;font-size: 16px;\"></span>您好！\n" +
                "                    </strong>\n" +
                "\n" +
                "                    <strong style=\"display:block;margin-bottom:15px;\">\n" +
                "                        您正在注册账号，请在验证码输入框中输入：\n" +
                "                        <span style=\"color:#f60;font-size: 24px\">"+verCode+"</span>，以完成操作。\n" +
                "                    </strong>\n" +
                "                </div>\n" +
                "\n" +
                "                <div style=\"margin-bottom:30px;\">\n" +
                "                    <small style=\"display:block;margin-bottom:20px;font-size:12px;\">\n" +
                "                        <p style=\"color:#747474;\">\n" +
                "                            注意：工作人员不会向你索取此验证码，请勿泄漏！\n" +
                "                        </p>\n" +
                "                    </small>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div style=\"width:700px;margin:0 auto;\">\n" +
                "                <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
                "                    <p>此为系统邮件，请勿回复<br>\n" +
                "                        请保管好您的邮箱，避免账号被他人盗用\n" +
                "                    </p>\n" +
                "                    <p>iMusic 热爱你的音乐</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>";
        EmailUtil.send(email, text);
    }

    /**
     * 发送重设密码的验证码
     * @param verCode 要发送的验证码
     * @param user 对应的user实例
     * @throws MessagingException 发送异常
     */
    public void sendReset(String verCode, User user) throws MessagingException {
        String text = "<!doctype html>\n" +
                "<html lang=\"en-US\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title></title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table width=\"700\" border=\"0\" align=\"center\" cellspacing=\"0\" style=\"width:700px;\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <div style=\"width:700px;margin:0 auto;border-bottom:1px solid #ccc;margin-bottom:30px;\">\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"700\" height=\"39\" style=\"font:12px Tahoma, Arial, 宋体;\">\n" +
                "                    <tbody>\n" +
                "                    <tr>\n" +
                "                        <td width=\"210\">\n" +
                "                           \n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </div>\n" +
                "            <div style=\"width:680px;padding:0 10px;margin:0 auto;\">\n" +
                "                <div style=\"line-height:1.5;font-size:14px;margin-bottom:25px;color:#4d4d4d;\">\n" +
                "                    <strong style=\"display:block;margin-bottom:15px;\">\n" +
                "                        亲爱的 "+user.getUserName()+" ：\n" +
                "                        <span style=\"color:#f60;font-size: 16px;\"></span>您好！\n" +
                "                    </strong>\n" +
                "\n" +
                "                    <strong style=\"display:block;margin-bottom:15px;\">\n" +
                "                        您正在修改密码，请在验证码输入框中输入：\n" +
                "                        <span style=\"color:#f60;font-size: 24px\">"+verCode+"</span>，以完成操作。\n" +
                "                    </strong>\n" +
                "                </div>\n" +
                "\n" +
                "                <div style=\"margin-bottom:30px;\">\n" +
                "                    <small style=\"display:block;margin-bottom:20px;font-size:12px;\">\n" +
                "                        <p style=\"color:#747474;\">\n" +
                "                            注意：此操作可能会修改您的密码。如非本人操作，请及时登录并修改密码以保证帐户安全\n" +
                "                            <br>（工作人员不会向你索取此验证码，请勿泄漏！)\n" +
                "                        </p>\n" +
                "                    </small>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div style=\"width:700px;margin:0 auto;\">\n" +
                "                <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
                "                    <p>此为系统邮件，请勿回复<br>\n" +
                "                        请保管好您的邮箱，避免账号被他人盗用\n" +
                "                    </p>\n" +
                "                    <p>iMusic 热爱你的音乐</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>";
        EmailUtil.send(user.getEmail(), text);
    }

}
