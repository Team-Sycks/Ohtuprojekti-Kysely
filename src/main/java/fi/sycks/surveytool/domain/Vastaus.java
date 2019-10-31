package fi.sycks.surveytool.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vastaus {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long vastausid;
	private String vastausteksti;
	
	@ManyToOne
	@JoinColumn(name="vastaajaid")
	private Vastaaja vastaaja;
	
	@ManyToOne
	@JoinColumn(name="kysymysid")
	private Kysymys kysymys;
	
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
	
	

}
