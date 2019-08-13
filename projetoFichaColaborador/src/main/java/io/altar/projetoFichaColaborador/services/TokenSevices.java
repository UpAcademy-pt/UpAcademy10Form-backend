package io.altar.projetoFichaColaborador.services;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import io.altar.projetoFichaColaborador.business.TokenBusiness;
import io.altar.projetoFichaColaborador.models.Token;

@Path("token")
public class TokenSevices {

@Context
private UriInfo context;

@Inject
private TokenBusiness tb;

@GET
@Path("generateToken/{email}")
@Produces(MediaType.APPLICATION_JSON)
public Token generateToken(@PathParam("email")String email) throws IOException {
    return tb.generateNewToken(email);
}
// @POST
// @Produces(MediaType.TEXT_PLAIN)
// public Response validateToken(String token) {
// System.out.println("Token Value:" + token);
// if(tg.isValid(token)) {
// return Response.status(Status.OK).entity("Token Valido").build();
// }
// else {
// return Response.status(Status.UNAUTHORIZED).build();
// }
//
// }

// @GET
// @Path("getTokenByEmail/{employeeEmail}")
// @Produces(MediaType.APPLICATION_JSON)
// public Token getTokenByEmail(@PathParam("employeeEmail") String employeeEmail) {
// return tg.getTokenByEmail(employeeEmail);
// }

@GET
@Path("")
@Produces(MediaType.TEXT_PLAIN)
public Response getTokenByValue(@QueryParam("val") String value) {
    if(tb.isValid(value)) {
        return Response.status(Status.OK).entity("Token Valido").build();    
    }else {
        return Response.status(Status.UNAUTHORIZED).entity("Token Inválido").build();
    }
}

@DELETE
@Path("{val}")
@Produces(MediaType.TEXT_PLAIN)
public Response deleteTokenByValue(@PathParam("val") String value) {
	tb.removeToken(value);
	return Response.status(Status.OK).entity("Token Apagado").build(); 

}



   
}



