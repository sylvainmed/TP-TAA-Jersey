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
			eventlist.addVoiture(3, 5);
			eventlist.addVoiture(2, 6);
			eventlist.addEvent("5", "23/02/1998", "12:25");
					
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		Enseignant es = (Enseignant) manager.createQuery(
				"select e1 from Enseignant as e1 where e1.nom='barais'")
				.getSingleResult();*/
		
	}

}
