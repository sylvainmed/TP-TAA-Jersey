package jpa;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;


/**
 * Class Event
 * @author Sylvain MEDARD
 * @author Rudi Tuvache
 *
 */

@Entity
@Table(name = "Event")
public class Event {

	private int id;
	private String place;
	private String date;
	private String heure;
	private List<Voiture> voitures;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	@OneToMany(mappedBy="event")
	public List<Voiture> getVoitures() {
		return voitures;
	}
	public void setVoitures(List<Voiture> voitures) {
		this.voitures = voitures;
	}
	public Voiture addVoiture(Voiture voiture){
		this.voitures.add(voiture);
		return voiture;
	}
	
	
	
}
