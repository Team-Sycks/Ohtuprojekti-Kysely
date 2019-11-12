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
public class Monivalinta {
	@Id
	@JsonIgnore
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long valintaid;
	
	private String valintanimi;
	
	private long järjestysNumero;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="kysymysid")
	private Kysymys kysymys;

	public Monivalinta() {}
	
	public Monivalinta(String valintanimi, long järjestysNumero, Kysymys kysymys) {
		super();
		this.valintanimi = valintanimi;
		this.järjestysNumero = järjestysNumero;
		this.kysymys = kysymys;
	}

	public Long getValintaid() {
		return valintaid;
	}

	public void setValintaid(Long valintaid) {
		this.valintaid = valintaid;
	}

	public String getValintanimi() {
		return valintanimi;
	}

	public void setValintanimi(String valintanimi) {
		this.valintanimi = valintanimi;
	}

	public long getJärjestysNumero() {
		return järjestysNumero;
	}

	public void setJärjestysNumero(long järjestysNumero) {
		this.järjestysNumero = järjestysNumero;
	}

	public Kysymys getKysymys() {
		return kysymys;
	}

	public void setKysymys(Kysymys kysymys) {
		this.kysymys = kysymys;
	}
}
