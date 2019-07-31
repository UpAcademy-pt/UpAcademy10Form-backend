package io.altar.projetoFichaColaborador.business;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class EmailBusiness {
	
	// for example, smtp.mailgun.org
    private static final String SMTP_SERVER = "smtp.mailgun.org";
    private static final String USERNAME = "postmaster@sandbox429ef89083f042179b66d633cac87500.mailgun.org";
    private static final String PASSWORD = "d83cd3bcb57987ac07a6b4d6c14b258b-f877bd7a-4a9f6ffa";

    private static final String EMAIL_FROM = "newcolaborador@gmail.com";
    private static final String EMAIL_TO = "cepobu@tmailcloud.net";
    //fazer query para inserir o email a eviar
    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT = "Ficha de Colaborador Aubay";
    private static final String EMAIL_TEXT = "<h1>Muito Boa Tarde,</h1><br><h1>Em anexo segue o link para o preenchimento da ficha de colaborador</h1><br><h1>***Link para preenchimento da ficha de colaborador***</h1>";

    public void sendEmail() {

        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        prop.put("mail.smtp.port", "465"); // default port 25
        prop.put("mail.smtp.auth", "true");
        
        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {

            msg.setFrom(new InternetAddress(EMAIL_FROM));

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));

            msg.setSubject(EMAIL_SUBJECT);
			
			// TEXT email
            //msg.setText(EMAIL_TEXT);

			// HTML email
            msg.setDataHandler(new DataHandler(new HTMLDataSource(EMAIL_TEXT)));

            
			SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
			
			// connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
			
			// send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    static class HTMLDataSource implements DataSource {

        private String html;

        public HTMLDataSource(String htmlString) {
            html = htmlString;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            if (html == null) throw new IOException("html message is null!");
            return new ByteArrayInputStream(html.getBytes());
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("This DataHandler cannot write HTML");
        }

        @Override
        public String getContentType() {
            return "text/html";
        }

        @Override
        public String getName() {
            return "HTMLDataSource";
        }
    }
	

}
