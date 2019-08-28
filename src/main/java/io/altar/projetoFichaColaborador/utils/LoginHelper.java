package io.altar.projetoFichaColaborador.utils;

public class LoginHelper {
	public static String hashPassword(String TextPassword) {
		Integer hash = TextPassword.hashCode();
		String hashedPass = hash.toString();
		return hashedPass;
	}
}
