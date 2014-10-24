package server;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import shared.Event;
import shared.EventList;
import shared.Passager;

@Path("/event")
public class EventRessource {
	
	public EventRessource() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	@Path("/getevent")
	@Produces({MediaType.APPLICATION_JSON })
	public List<Event> getEvents(){
		return EventList.getInstance().getEvents();
	}
	
	@POST
	@Path("/new")
	@Consumes({MediaType.APPLICATION_JSON})
	public void participer (int idEvent, Passager passager){
		EventList.getInstance().participe(passager, idEvent);
		System.out.println("Passager ajouté à un event");
	}

}
