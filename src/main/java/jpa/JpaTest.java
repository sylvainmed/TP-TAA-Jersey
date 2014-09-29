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
	
	public Passager addPassager(String nom, Voiture voiture){
		Passager p = new Passager();
		p.setNom(nom);
		p.setVoiture(voiture);
		manager.persist(p);
		return p;
	}
	
	public Passager addPassager(String nom){
		Passager p = new Passager();
		p.setNom(nom);
		manager.persist(p);
		return p;
	}
	
	public List<Voiture> getVoitures(){
		return manager.createQuery("select e FROM Voiture e", Voiture.class).getResultList();
	}
	
	public List<Passager> getPassagers(){
		return manager.createQuery("select e FROM Passagers e", Passager.class).getResultList();
	}
	
	public List<Event> getEvents(){
		return manager.createQuery("select e FROM Event e", Event.class).getResultList();
	}
	
	public Voiture getVoiture(int id){
		Voiture voiture = (Voiture) manager.find(Voiture.class, 1);
		return(voiture);
	}
	
	public Passager getPassager(int id){
		Passager passager = (Passager) manager.find(Passager.class, 1);
		return(passager);
	}
	
	public Event getEvent(int id){
		Event event = (Event) manager.find(Event.class, 1);
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
			manager.persist(event);
					
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
