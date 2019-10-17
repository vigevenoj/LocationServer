package com.sharkbaitextraordinaire.location.resources.management;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.sharkbaitextraordinaire.location.db.ManagementDAO;
import com.sharkbaitextraordinaire.location.db.OwntracksUpdateDAO;

@Path ("/manage")
public class ManagementResource {
	
	private OwntracksUpdateDAO otdao;
	private ManagementDAO mgmtdao;

    public ManagementResource(OwntracksUpdateDAO otdao, ManagementDAO mgmtdao) {
        this.otdao = otdao;
        this.mgmtdao = mgmtdao;
    }

	@Path("/tracking")
	@PUT
	public boolean enableTracking() {
		return true;
	}
	
	@Path("/tracking")
	@DELETE
	public boolean disableTracking() {
		return true;
	}
	
	@Path("/tracking")
	@GET
	public boolean trackingStatus() {
		return true;
	}
	
	// need a method to clear all tracked points, not sure if this should be a DELETE to the /tracking path or not
	@Path("/clearTracking")
	@GET // TODO should be a delete, probably
	public void deleteTrackedPoints() {
		otdao.deleteAll();
	}
	
}
