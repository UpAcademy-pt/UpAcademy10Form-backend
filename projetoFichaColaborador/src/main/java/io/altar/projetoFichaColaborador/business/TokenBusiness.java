package io.altar.projetoFichaColaborador.business;

import java.io.IOException;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;

import javax.inject.Inject;
import io.altar.projetoFichaColaborador.models.Token;
import io.altar.projetoFichaColaborador.repositories.TokenRepository;

public class TokenBusiness {

@Inject
private TokenRepository tR;

@Inject
private EmailBusiness eB;



private static final SecureRandom secureRandom = new SecureRandom(); // threadsafe
private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); // threadsafe

public Token generateNewToken(String email) throws IOException {
    byte[] randomBytes = new byte[16];
    secureRandom.nextBytes(randomBytes);
    String value = base64Encoder.encodeToString(randomBytes);
    String employeeEmail = email;
    Long timeToLive = Instant.now().toEpochMilli()+86400000;//24h to milliseconds
    //Long timeToLive = Instant.now().toEpochMilli() + 10;
    Token Token = new Token(value, employeeEmail, timeToLive);
    Token tokenSaved = tR.create(Token);
    eB.sendEmail(employeeEmail,value);
    return tokenSaved;
}

public String generateNewToken() {
    byte[] randomBytes = new byte[16];
    secureRandom.nextBytes(randomBytes);
    return base64Encoder.encodeToString(randomBytes);
}

public boolean isValid(String tokenValue) {
    Token token = tR.getTokenByValue(tokenValue);
    System.out.println(token);
    if (token != null) {
        if (token.getTimeToLive() > Instant.now().toEpochMilli()) {
            return true;
        } else {
            return false;
        }
    } else {
        return false;
    }
}

public Token getTokenByEmail(String employeeEmail) {
    Token token = tR.getTokenByEmail(employeeEmail);
    return token;
}	

public void removeToken(String tokenValue) {
	Token token = tR.getTokenByValue(tokenValue);
	System.out.println(token.toString());
	long id = token.getId();
	System.out.println(id);
	tR.remove(id);
	return;
}

//public void sendEmail(String email,String token) throws IOException {
//    Email from = new Email("newcolaborador@gmail.com");
//    String subject = "Registo Ficha Colaborador - Projeto UpAcademy";
//    Email to = new Email(email);
//    Content content = new Content("text/plain", "Registe no link: exemplolink: url?"+ token);
//    Mail mail = new Mail(from, subject, to, content);
//
//    SendGrid sg = new SendGrid(System.getProperty("SGKey"));
//    Request request = new Request();
//    try {
//      request.setMethod(Method.POST);
//      request.setEndpoint("mail/send");
//      request.setBody(mail.build());
//      Response response = sg.api(request);
//      System.out.println(response.getStatusCode());
//      System.out.println(response.getBody());
//      System.out.println(response.getHeaders());
//    } catch (IOException ex) {
//      throw ex;
//    }
//  }



}
