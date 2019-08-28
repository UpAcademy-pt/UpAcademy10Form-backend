package io.altar.projetoFichaColaborador.business;

import java.io.IOException;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.altar.projetoFichaColaborador.models.Token;
import io.altar.projetoFichaColaborador.repositories.TokenRepository;

public class TokenBusiness {

	@Inject
	private TokenRepository tR;

	@Inject
	private EmailBusiness eB;

	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

	@Transactional
	public Token generateNewToken(String email) throws IOException {
		byte[] randomBytes = new byte[32];
		secureRandom.nextBytes(randomBytes);
		String value = base64Encoder.encodeToString(randomBytes);
		String employeeEmail = email;
		Long timeToLive = Instant.now().toEpochMilli() + 86400000;
		Token token = new Token(value, employeeEmail, timeToLive);
		eB.sendEmail(employeeEmail, value);
		return tR.create(token);
	}

	public String generateNewToken() {
		secureRandom.nextBytes(new byte[16]);
		return base64Encoder.encodeToString(new byte[16]);
	}

	public boolean isValid(String tokenValue) {
		Token token = tR.getTokenByValue(tokenValue);
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
		return tR.getTokenByEmail(employeeEmail);
	}

	@Transactional
	public void removeToken(String tokenValue) {
		tR.remove(tR.getTokenByValue(tokenValue).getId());
	}
}
