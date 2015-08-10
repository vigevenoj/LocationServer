package com.sharkbaitextraordinaire.location.resources;

import com.sharkbaitextraordinaire.location.core.OwntracksUpdate;
import com.sharkbaitextraordinaire.location.db.OwntracksUpdateDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path(value = "/rawupdates")
public class OwntracksResource {

    private OwntracksUpdateDAO otdao;

    public OwntracksResource(OwntracksUpdateDAO otdao) {
        this.otdao = otdao;
    }

    @GET
    public List<OwntracksUpdate> getAllUpdates() {
        return otdao.findAll();
    }

    @GET @Path("/latest")
	public OwntracksUpdate getLatestUpdate(){
		return otdao.findLatest();
	}
	
}
