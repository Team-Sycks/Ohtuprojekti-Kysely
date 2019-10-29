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
	private String vastaus_teksti;
	
	@ManyToOne
	@JoinColumn(name="vastaajaid")
	private Vastaaja vastaaja;
	
	@ManyToOne
	@JoinColumn(name="kysymysid")
	private Kysymys kysymys;
	
	public Vastaus() {}

	public Vastaus(Long vastausid, String vastaus_teksti, Vastaaja vastaaja, Kysymys kysymys) {
		super();
		this.vastausid = vastausid;
		this.vastaus_teksti = vastaus_teksti;
		this.vastaaja = vastaaja;
		this.kysymys = kysymys;
	}

	public Vastaus(String vastaus_teksti, Vastaaja vastaaja, Kysymys kysymys) {
		super();
		this.vastaus_teksti = vastaus_teksti;
		this.vastaaja = vastaaja;
		this.kysymys = kysymys;
	}

	public void setVastausid(Long vastausid) {
		this.vastausid = vastausid;
	}

	public void setVastaus_teksti(String vastaus_teksti) {
		this.vastaus_teksti = vastaus_teksti;
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

	public String getVastaus_teksti() {
		return vastaus_teksti;
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
			return "Vastaus [vastausid=" + vastausid + ", vastaus_teksti=" + vastaus_teksti + this.getVastaaja() + this.getKysymys() +"]";
		else
			return "Vastaus [vastausid=" + vastausid + ", vastaus_teksti=" + vastaus_teksti + "]";
	}
	
	

}
