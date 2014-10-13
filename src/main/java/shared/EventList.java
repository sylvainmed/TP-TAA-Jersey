package shared;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EventList {
	
	private static EventList INSTANCE = new EventList();
	
	public static EventList getInstance(){
		return INSTANCE;
	}
	public EntityManager manager;
	EntityTransaction tx;
	
	public EventList(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		this.manager = factory.createEntityManager();
		this.tx = manager.getTransaction();
	}
	
	public EventList(EntityManager manager, EntityTransaction tx){
		this.manager = manager;
		this.tx = tx;
		}
	
	public void addVoiture(int places, int idevent){
		tx.begin();
		Voiture car = new Voiture();
		Event event = getEvent(idevent);
		car.setPlaces(places);
		car.setEvent(event);
		manager.persist(car);
		tx.commit();
	}
	
	public Event getEvent(int id){
		Event event = (Event) manager.find(Event.class, 1);
		return(event);
	}
	
	public List<Event> getEvents(){
		return manager.createQuery("select e FROM Event e", Event.class).getResultList();
	}
	
	public void participe (Passager passager, int idevent){
		tx.begin();
		Event event = (Event) manager.createQuery("FROM Event WHERE id="+idevent).getSingleResult();
		passager.setEvent(event);
		event.getPassagers().add(passager);
		
		tx.commit();
	}
	
	public void ajouteDansVoiture(Passager passager, int idvoiture){
		tx.begin();
		Voiture voiture = (Voiture) manager.createQuery("FROM Car WHERE id="+idvoiture).getSingleResult();
		passager.setVoiture(voiture);
		voiture.getPassagers().add(passager);
		voiture.decPlaces();
		tx.commit();
	}
	
	public Passager addPassager(int idEvent, String name){
		tx.begin();
		Event event = getEvent(idEvent);
		Passager passager = new Passager();
		passager.setNom(name);
		passager.setEvent(event);
		manager.persist(passager);
		tx.commit();
		return passager;
		}
	
	public void addEvent(String place, String date, String heure){
		Event event = new Event();
		event.setDate(date);
		event.setHeure(heure);
		event.setPlace(place);
		tx.begin();
		manager.persist(event);
		tx.commit();
			
		
	}

}
