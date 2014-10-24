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
	
	EntityManager manager;
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
	
	public void addVoiture(Voiture car, int places, Event event){
		tx.begin();
		car.setPlaces(places);
		car.setEvent(event);
		manager.persist(car);
		tx.commit();
	}
	
	public void inscription(Passager passager){
		tx.begin();
		manager.persist(passager);
		tx.commit();
	}
	
	public Event getEvent(int id){
		Event event = (Event) manager.find(Event.class, 1);
		return(event);
	}
	
	public List<Event> getEvents(){
		return manager.createQuery("select e FROM Event as e", Event.class).getResultList();
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
	
	public void addPassager(Event event, Passager passager){
		tx.begin();
		passager.setEvent(event);
		manager.persist(passager);
		tx.commit();
		}
	
//	public void addEvent(String place, String date, String heure){
//		Event event = new Event();
//		event.setDate(date);
//		event.setHeure(heure);
//		event.setPlace(place);
//		tx.begin();
//		manager.persist(event);
//		tx.commit();
//	}
	
	public void addEvent(Event event){
		tx.begin();
		manager.persist(event);
		tx.commit();
	}

}
