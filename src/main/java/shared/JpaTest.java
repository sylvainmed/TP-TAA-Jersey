package shared;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import java.util.List;

public class JpaTest {

	private EntityManager manager;
	EntityTransaction tx;
	
	public JpaTest(EntityManager manager) {
		this.manager = manager;
		this.tx= manager.getTransaction();

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
		try {
		event.setDate(date);
		event.setHeure(heure);
		event.setPlace(place);
		tx.begin();
		manager.persist(event);
		} catch(NoResultException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}finally{
				tx.commit();
			}
		
	}
	
	public List<Event> getEvents(){
		return manager.createQuery("select e FROM Event e", Event.class).getResultList();
	}
	
	public Event getEvent(int id){
		Event event = (Event) manager.find(Event.class, 1);
		return(event);
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
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);


		try {
			test.addVoiture(3, 5);
			test.addVoiture(2, 6);
					
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		Enseignant es = (Enseignant) manager.createQuery(
				"select e1 from Enseignant as e1 where e1.nom='barais'")
				.getSingleResult();*/
		
	}

}
