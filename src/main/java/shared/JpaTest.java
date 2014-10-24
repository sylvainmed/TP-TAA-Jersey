package shared;



public class JpaTest {
	
	EventList eventlist;
	
	
	public JpaTest(EventList eventlist) {
		this.eventlist = eventlist;

	}
	
	public EventList getEventlist() {
		return eventlist;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
//		EntityManager manager = factory.createEntityManager();
//		JpaTest test = new JpaTest(manager);
		EventList eventlist = new EventList();


		try {
			Event concert = new Event();
			concert.setDate("23/02/1998");
			concert.setHeure("12:25");
			concert.setPlace("paris");
			eventlist.addEvent(concert);
			
			Event festival = new Event();
			festival.setDate("10/10/10");
			festival.setHeure("10:10");
			festival.setPlace("tokyo");
			
			Passager toto = new Passager();
			Voiture bmw = new Voiture();
			toto.setNom("toto");
			toto.setVoiture(bmw);
			eventlist.inscription(toto);
			eventlist.addVoiture(bmw, 4, concert);
			
			Passager titi = new Passager();
			Voiture mercedes = new Voiture();
			titi.setNom("titi");
			titi.setVoiture(mercedes);
			titi.setEvent(concert);
			eventlist.inscription(titi);
			
			Passager lili = new Passager();
			lili.setNom("lili");
			Passager lolo = new Passager();
			lolo.setNom("lolo");
			Passager lulu = new Passager();
			lulu.setNom("lulu");
			Event event = new Event();
			eventlist.addPassager(event, lili);
			eventlist.addPassager(event, lolo);
			eventlist.addPassager(event, lulu);
					
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		Enseignant es = (Enseignant) manager.createQuery(
				"select e1 from Enseignant as e1 where e1.nom='barais'")
				.getSingleResult();*/
		
	}

}
