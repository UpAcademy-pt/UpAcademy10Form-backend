package io.altar.projetoFichaColaborador.business;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;

import javax.inject.Inject;

import io.altar.projetoFichaColaborador.models.Token;
import io.altar.projetoFichaColaborador.repositories.TokenRepository;

public class TokenBusiness {
	
	@Inject
	private TokenRepository tR;
	
	private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

	public Token generateNewToken(String Email) {
		byte[] randomBytes = new byte[16];
	    secureRandom.nextBytes(randomBytes);
	    String value = base64Encoder.encodeToString(randomBytes);
	    String employeeEmail = Email;
	    //Long timeToLive = Instant.now().toEpochMilli()+86400000;//24h to milliseconds
	    Long timeToLive = Instant.now().toEpochMilli()+10;
	    Token Token = new Token(value,employeeEmail,timeToLive);
	    Token tokenSaved = tR.addEntity(Token);
		return tokenSaved;    
	}
	
	 public String generateNewToken() {
		    byte[] randomBytes = new byte[16];
		    secureRandom.nextBytes(randomBytes);
		    return base64Encoder.encodeToString(randomBytes);		    
	}
	 
	
	public boolean isValid(String tokenValue) {
		Token token = tR.getTokenByValue(tokenValue);
		//validar se existe
		if (token.getTimeToLive() > Instant.now().toEpochMilli()) {
			return true;
		} else {
			return false;
		}
	}
	
//	public String isEmailValid(String Email) {
//		if (!tR.getAll().contains(Email)){
//			
//		}
//		
//	}
		
}
