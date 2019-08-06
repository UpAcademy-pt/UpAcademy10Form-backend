package io.altar.projetoFichaColaborador.business;

import java.io.IOException;


import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;


public class EmailBusiness {
	  
	public void sendEmail(String email,String token) throws IOException {
		    Email from = new Email("newcolaborador@gmail.com");
		    String subject = "Registo Ficha Colaborador - Projeto UpAcademy";
		    Email to = new Email(email);
		    Content content = new Content("text/plain", "Registe no link: exemplolink: url?"+ token);
		    Mail mail = new Mail(from, subject, to, content);

		    SendGrid sg = new SendGrid(System.getProperty("SGKey"));
		    Request request = new Request();
		    try {
		      request.setMethod(Method.POST);
		      request.setEndpoint("mail/send");
		      request.setBody(mail.build());
		      Response response = sg.api(request);
		      System.out.println(response.getStatusCode());
		      System.out.println(response.getBody());
		      System.out.println(response.getHeaders());
		    } catch (IOException ex) {
		      throw ex;
		    }
		  }
	
	

}






