package io.altar.projetoFichaColaborador.business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.inject.Inject;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;

import io.altar.projetoFichaColaborador.utils.property.Property;
import com.sendgrid.helpers.mail.objects.Personalization;

public class EmailBusiness {

	@Inject
	@Property("email.send.address")
	private String emailFrom;

	@Inject
	@Property("email.subject")
	private String subject;

	@Inject
	@Property("email.body.type")
	private String contentType;

	@Inject
	@Property("email.sender.name")
	private String name;

	@Inject
	@Property("email.endpoint")
	private String endPoint;

	public String injectHtmlInEmail() {
		StringBuilder contentBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader("emailBodyContent.txt"));
			String str;
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		} catch (IOException e) {
			// TODO add exception
		}
		return contentBuilder.toString();
	}

	public void sendEmail(String email, String token) throws IOException {
		Mail mail = new Mail();
		SendGrid sg = new SendGrid(System.getProperty("SGKey"));
		mail.setTemplateId("d-9755b656b5824b6d805af377448f31ab");
		Personalization personalization = new Personalization();
		Email senderMail = new Email();
		Email to = new Email(email);
		senderMail.setName(name);
		senderMail.setEmail(emailFrom);
		personalization.setSubject(subject);
		mail.setFrom(senderMail);
		personalization.addTo(to);
		personalization.addDynamicTemplateData("token", token);
		mail.addPersonalization(personalization);
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint(endPoint);
			request.setBody(mail.build());
			sg.api(request);
		} catch (IOException e) {
			throw e;
		}
	}
}
