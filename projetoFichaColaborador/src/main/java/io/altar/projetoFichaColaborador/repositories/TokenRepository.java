package io.altar.projetoFichaColaborador.repositories;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import io.altar.projetoFichaColaborador.models.Token;

@RequestScoped
public class TokenRepository extends EntityRepository<Token>{

public Token getEntity(String employeeEmail) {
    return em.find(getEntityClass(),employeeEmail);
}

public void removeEntity(String employeeEmail) {
    em.remove(employeeEmail);

}

public Token getTokenByValue(String value) {
	TypedQuery<Token> query = em.createQuery("SELECT t FROM Token t WHERE t.value = :value", getEntityClass());
    query.setParameter("value", value);
    List<Token> foundTokens = (List<Token>) query.getResultList();
    if (foundTokens == null || foundTokens.isEmpty()) {
        return null;
    }
    Token foundToken = foundTokens.get(0);
    System.out.println(foundToken.toString());
    return foundToken;
}

public Token getTokenByValueForDelete(String value) {
    TypedQuery<Token> query = em.createQuery("SELECT t FROM Token t WHERE t.value = :value", getEntityClass());
    query.setParameter("value", value);
    List<Token> foundTokens = (List<Token>) query.getResultList();
    Token foundToken = foundTokens.get(0);
    System.out.println(foundToken.toString());
    return foundToken;
}



public Token getTokenByEmail(String employeeEmail) {
    TypedQuery<Token> query = em.createNamedQuery(Token.GET_TOKEN_BY_EMAIL, getEntityClass());
    query.setParameter("employeeEmail", employeeEmail);
    return query.getSingleResult();
}

@Override
protected Class<Token> getEntityClass() {
    return Token.class;
}

//

@Override
protected String getAllQuery() {
    return null;
}
@Override
protected String getByIdQuery() {
	return null;
}
@Override
protected String checkEntityExistsByIdQuery() {
	return null;
}
}

