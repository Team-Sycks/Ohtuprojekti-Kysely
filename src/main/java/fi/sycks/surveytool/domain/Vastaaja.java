package fi.sycks.surveytool.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Vastaaja {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long vastaajaid;
	private String vastausaika;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "vastaaja")
	private List<Vastaus> vastaukset;
	
	public Vastaaja() {}

	public Vastaaja(String vastausaika) {
		super();
		this.vastausaika = vastausaika;
	}

	public Vastaaja(Long vastaajaid, String vastausaika) {
		super();
		this.vastaajaid = vastaajaid;
		this.vastausaika = vastausaika;
	}

	public void setVastaajaid(Long vastaajaid) {
		this.vastaajaid = vastaajaid;
	}

	public void setVastausaika(String vastausaika) {
		this.vastausaika = vastausaika;
	}

	public void setVastaukset(List<Vastaus> vastaukset) {
		this.vastaukset = vastaukset;
	}

	public Long getVastaajaid() {
		return vastaajaid;
	}

	public String getVastausaika() {
		return vastausaika;
	}

	public List<Vastaus> getVastaukset() {
		return vastaukset;
	}

	@Override
	public String toString() {
		return "Vastaaja [vastaajaid=" + vastaajaid + ", vastausaika=" + vastausaika + "]";
	}
	
	

}
