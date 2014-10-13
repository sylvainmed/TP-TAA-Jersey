package server;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import shared.Event;
import shared.JpaTest;
import shared.Passager;

import java.util.List;

@Path("/events")
public class EventRessource {
	
	@POST
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void addEvent(String name, String location){
	System.out.println("Adds an event");
	}
	
	@POST
	@Path("/participer")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void participe(int idEvent, Passager passager){
	System.out.println("Inscrit un passager à un event");
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Event> getEvents(){
	System.out.println("Returns the events list");
	return JpaTest.getEvents();
	}

}
