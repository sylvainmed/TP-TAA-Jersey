package jpa;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Class Voiture
 * @author Sylvain MEDARD
 * @author Rudi Tuvache
 *
 */
@Entity
@Table(name = "Voiture")
public class Voiture {
	

	private int id;
	private int places; // free places
	private List<Passager> passagers;
	private Event event;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}
	
	@OneToMany(mappedBy="voiture")
	public List<Passager> getPassagers() {
		return passagers;
	}

	public void setPassagers(List<Passager> passagers) {
		this.passagers = passagers;
	}
	
	public synchronized Boolean addPassager(Passager passager){
		if(this.getPlaces()>0){
			this.getPassagers().add(passager);
			this.setPlaces(getPlaces()-1);;
			return true;
		}
		else{
			return false;
		}
	}
	
	public synchronized Boolean delPassager(Passager passager){
		if(this.getPassagers().contains(passager)){
			this.getPassagers().remove(passager);
			return true;
		}
		else{
			return false;
		}
	}
	
	@ManyToOne
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	
	
}
