package com.sharkbaitextraordinaire.location.resources;

import com.sharkbaitextraordinaire.location.core.OwntracksUpdate;
import com.sharkbaitextraordinaire.location.db.OwntracksUpdateDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path(value = "/rawupdates")
public class OwntracksResource {

    private OwntracksUpdateDAO otdao;

    public OwntracksResource(OwntracksUpdateDAO otdao) {
        this.otdao = otdao;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<OwntracksUpdate> getAllUpdates() {
        return otdao.findAll();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @GET @Path("/since/{tst}") 
    public List<OwntracksUpdate> getUpdatesSince(@PathParam("tst") Long tst ) {
    	return otdao.findSinceTimestamp(tst);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET @Path("/latest")
	public OwntracksUpdate getLatestUpdate(){
		return otdao.findLatest();
	}
	
}
