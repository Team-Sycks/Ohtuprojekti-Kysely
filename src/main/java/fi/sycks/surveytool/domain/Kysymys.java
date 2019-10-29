package fi.sycks.surveytool.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Kysymys {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long kysymysid;
	private String teksti;
	private String tyyppi;
	
	@ManyToOne
	
	@JoinColumn(name="kyselyid")
	private Kysely kysely;
	
	@Transient
	public static final String TYPE_SHORT_TEXT = "short_text";
	
	@Transient
	public static final String TYPE_NUMBER = "number";

	public Kysymys() {
		super();
		this.kysymysid = null;
		this.teksti = null;
		this.tyyppi = null;
		this.kysely = kysely;
	}

	public Kysymys(Long kysymysid, String teksti, String tyyppi, Kysely kysely) {
		super();
		this.kysymysid = kysymysid;
		this.teksti = teksti;
		this.tyyppi = tyyppi;
		this.kysely = kysely;
	}

	public Kysymys(String teksti, String tyyppi, Kysely kysely) {
		super();
		this.teksti = teksti;
		this.tyyppi = tyyppi;
		this.kysely = kysely;
	}

	public Long getKysymysid() {
		return kysymysid;
	}

	public String getTeksti() {
		return teksti;
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

	public void setTeksti(String teksti) {
		this.teksti = teksti;
	}

	public void setTyyppi(String tyyppi) {
		this.tyyppi = tyyppi;
	}

	public void setKysely(Kysely kysely) {
		this.kysely = kysely;
	}

	@Override
	public String toString() {
		
		if(this.kysely !=null)
		return "Kysymys [kysymysid=" + kysymysid + ", teksti=" + teksti + ", tyyppi=" + tyyppi + this.getKysely() + "]";
		else
			return "Kysymys [kysymysid=" + kysymysid + ", teksti=" + teksti + ", tyyppi=" + tyyppi + "]";
	}
	
	
	

}
