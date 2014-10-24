package shared;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;

/**
 * Class Passager
 * @author Sylvain MEDARD
 * @author Rudi Tuvache
 *
 */

@Entity
@Table(name = "Passager")
public class Passager {

	private int id;
	private String nom;
	private Voiture voiture;
	private Event event;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JsonIgnore
	public Voiture getVoiture() {
		return voiture;
	}
	
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JsonIgnore
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
		event.getPassagers().add(this);
	}
	
	
	
}
