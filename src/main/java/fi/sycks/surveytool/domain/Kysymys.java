package fi.sycks.surveytool.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Kysymys {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long kysymysid;
	private String kysymysteksti;
	private String tyyppi;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="kysymys")
	private List<Vastaus> vastaukset;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="kyselyid")
	private Kysely kysely;
	
	@Transient
	public static final String TYPE_SHORT_TEXT = "short_text";
	
	@Transient
	public static final String TYPE_MULTICHOICE = "multichoice";
	
	@Transient
	public static final String TYPE_NUMBER = "number";
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy ="kysymys")
	private List<Monivalinta> monivalinnat;

	public Kysymys() {
	}

	public Kysymys(Long kysymysid, String teksti, String tyyppi, Kysely kysely) {
		super();
		this.kysymysid = kysymysid;
		this.kysymysteksti = teksti;
		this.tyyppi = tyyppi;
		this.kysely = kysely;
	}

	public Kysymys(String teksti, String tyyppi, Kysely kysely) {
		super();
		this.kysymysteksti = teksti;
		this.tyyppi = tyyppi;
		this.kysely = kysely;
	}

	public Long getKysymysid() {
		return kysymysid;
	}


	public String getTyyppi() {
		return tyyppi;
	}

	public Kysely getKysely() {
		return kysely;
	}

	public void setKysymysid(Long kysymysid) {
		this.kysymysid = kysymysid;
	}

	public void setTyyppi(String tyyppi) {
		this.tyyppi = tyyppi;
	}

	public void setKysely(Kysely kysely) {
		this.kysely = kysely;
	}
	public String getKysymysteksti() {
		return kysymysteksti;
	}

	public void setKysymysteksti(String kysymysteksti) {
		this.kysymysteksti = kysymysteksti;
	}

	public List<Vastaus> getVastaukset() {
		return vastaukset;
	}

	public void setVastaukset(List<Vastaus> vastaukset) {
		this.vastaukset = vastaukset;
	}

	@Override
	public String toString() {
		
		if(this.kysely !=null)
		return "Kysymys [kysymysid=" + kysymysid + ", kysymysteksti=" + kysymysteksti + ", tyyppi=" + tyyppi + this.getKysely() + "]";
		else
			return "Kysymys [kysymysid=" + kysymysid + ", kysymysteksti=" + kysymysteksti + ", tyyppi=" + tyyppi + "]";
	}

	public List<Monivalinta> getMonivalinnat() {
		return monivalinnat;
	}

	public void setMonivalinnat(List<Monivalinta> monivalinnat) {
		this.monivalinnat = monivalinnat;
	}

}
