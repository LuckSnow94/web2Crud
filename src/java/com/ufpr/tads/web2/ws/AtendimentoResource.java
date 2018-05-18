package com.ufpr.tads.web2.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.facade.AtendimentoFacade;

@Path("atendimentos")
public class AtendimentoResource {
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public AtendimentoResource() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of AtendimentoResource
     * @return an instance of String
     */
    @GET
    @Produces("application/json")
    public Response getJson() {
    	//Retorna uma lista com todos os atendimentos
     	//return AtendimentoFacade.searchAllAtendimentos();
     	return Response.ok().build();
         }
    
    @GET
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Atendimento getJson2(@PathParam("id") int idAtendimento) {
    	//Retorna o atendimento de código (id)
    	return AtendimentoFacade.buscarAtendimento(idAtendimento);
    }

    @POST
    @Consumes("application/json")
    public void postJson(Atendimento atendimento) {
    	//Insere um novo atendimento
    	AtendimentoFacade.insertAtendimento(atendimento);
    }
    
    /**
     * PUT method for updating or creating an instance of AtendimentoResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Path("/resolve/{id}")
    @Consumes("application/json")
    public void putJson(@PathParam("id") int idAtendimento) {
    	//Resolve o atendimento de código (idAtendimento)
    	AtendimentoFacade.solveAtendimento(idAtendimento);
    }

}