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
public class Vastaus {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long vastausid;
	private String vastausteksti;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="vastaajaid")
	private Vastaaja vastaaja;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="kysymysid")
	private Kysymys kysymys;
	
	//For JSON
	@Transient
	private long kysymysid;
	
	public Vastaus() {}

	public Vastaus(Long vastausid, String vastausteksti, Vastaaja vastaaja, Kysymys kysymys) {
		super();
		this.vastausid = vastausid;
		this.vastausteksti = vastausteksti;
		this.vastaaja = vastaaja;
		this.kysymys = kysymys;
	}

	public Vastaus(String vastausteksti, Vastaaja vastaaja, Kysymys kysymys) {
		super();
		this.vastausteksti = vastausteksti;
		this.vastaaja = vastaaja;
		this.kysymys = kysymys;
	}

	public void setVastausid(Long vastausid) {
		this.vastausid = vastausid;
	}

	public void setVastausteksti(String vastausteksti) {
		this.vastausteksti = vastausteksti;
	}

	public void setVastaaja(Vastaaja vastaaja) {
		this.vastaaja = vastaaja;
	}

	public void setKysymys(Kysymys kysymys) {
		this.kysymys = kysymys;
	}

	public Long getVastausid() {
		return vastausid;
	}

	public String getVastausteksti() {
		return vastausteksti;
	}

	public Vastaaja getVastaaja() {
		return vastaaja;
	}

	public Kysymys getKysymys() {
		return kysymys;
	}
	
	
	@Override
	public String toString() {
		if(this.vastaaja !=null && this.kysymys !=null)
			return "Vastaus [vastausid=" + vastausid + ", vastausteksti=" + vastausteksti + this.getVastaaja() + this.getKysymys() +"]";
		else
			return "Vastaus [vastausid=" + vastausid + ", vastausteksti=" + vastausteksti + "]";
	}

	public long getKysymysid() {
		return kysymysid;
	}

	public void setKysymysid(long kysymysid) {
		this.kysymysid = kysymysid;
	}

}
