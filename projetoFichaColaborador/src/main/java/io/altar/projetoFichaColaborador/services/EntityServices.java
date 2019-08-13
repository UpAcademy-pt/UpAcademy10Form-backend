package io.altar.projetoFichaColaborador.services;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("entity")
public class EntityServices {
	
	@Context
	private UriInfo context;
}
