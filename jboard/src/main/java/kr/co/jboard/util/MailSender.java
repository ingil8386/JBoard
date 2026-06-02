package kr.co.jboard.util;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailSender {

    // 인증번호를 보내는 관리자 Gmail 계정
    private static final String FROM_EMAIL = "ingil8386@gmail.com";

    // 메일에 표시될 이름
    private static final String FROM_NAME = "Board Project";

    // 구글 앱 비밀번호 16자리
    // 구글 계정 로그인 비밀번호 아님
    private static final String APP_PASSWORD = "tksczerqclknnljy";

    public static void sendAuthCode(String toEmail, String authCode) throws Exception {

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        props.put("mail.smtp.connectiontimeout", "5000");
        props.put("mail.smtp.timeout", "5000");
        props.put("mail.smtp.writetimeout", "5000");

        Session session = Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        });

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(FROM_EMAIL, FROM_NAME, "UTF-8"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

        message.setSubject("[Board Project] 비밀번호 찾기 인증번호", "UTF-8");

        String content = ""
                + "<div style='font-family:Arial, sans-serif;'>"
                + "<h2>비밀번호 찾기 인증번호</h2>"
                + "<p>아래 인증번호를 입력해주세요.</p>"
                + "<h1 style='color:#2f6fed;'>" + authCode + "</h1>"
                + "<p>본인이 요청하지 않았다면 이 메일은 무시하셔도 됩니다.</p>"
                + "</div>";

        message.setContent(content, "text/html; charset=UTF-8");

        Transport.send(message);
    }
}