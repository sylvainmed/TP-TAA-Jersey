package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	
	public Voiture addVoiture(int places, Event event){
		Voiture car = new Voiture();
		car.setPlaces(places);
		car.setEvent(event);
		manager.persist(car);
		return car;
	}
	
	public Event addEvent(String place, String date, String heure){
		Event event = new Event();
		event.setDate(date);
		event.setHeure(heure);
		event.setPlace(place);
		manager.persist(event);
		return event;
		
	}
	
	public List<Event> getEvents(){
		return manager.createQuery("FROM Event").getResultList();
	}
	
	public Event getEvent(int id){
		Event event = (Event) manager.createQuery("FROM Event WHERE id=1").getSingleResult();
		return(event);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		try {
			Event event = new Event();
			test.addVoiture(3, event);
			//manager.persist(et);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		/*
		Enseignant es = (Enseignant) manager.createQuery(
				"select e1 from Enseignant as e1 where e1.nom='barais'")
				.getSingleResult();*/
		
	}

}
