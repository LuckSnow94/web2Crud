package com.ufpr.tads.web2.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.facade.AtendimentoFacade;

@Path("produtos")
public class ProdutoResource extends Application {
    @Context
    private UriInfo context;
       
    /**
     * @see Application#Application()
     */
    public ProdutoResource() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of ProdutoResource
     * @return an instance of String
     */
    @GET
    @Produces("application/json")
    public List<Produto> getJson() {
        //Retorna uma lista com todos os produtos
    	return AtendimentoFacade.searchAllProdutos();
    }

    @GET
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Produto getJson(@PathParam("id") int id) {
    	//Retorna o produto de código (id)
    	return AtendimentoFacade.buscarProduto(id);
    }
    
    @POST
    @Consumes("application/json")
    public void postJson(String nomeProduto) {
    	//Insere um novo produto
    	AtendimentoFacade.insertProduto(nomeProduto);
    }
    
    /**
     * PUT method for updating or creating an instance of ProdutoResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public void putJson(@PathParam("id") int idProduto, String nomeProduto) {
    	//Atualiza o produto de código (idProduto)
    	AtendimentoFacade.updateProduto(new Produto(idProduto,nomeProduto));
    }
    
    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public void deleteJson(@PathParam("id") int id) {
    	//Remove o produto de código (id)
    	AtendimentoFacade.deleteProduto(id);
    }

}