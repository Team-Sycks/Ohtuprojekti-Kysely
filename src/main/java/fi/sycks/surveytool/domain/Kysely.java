package fi.sycks.surveytool.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Kysely {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long kyselyid;
	private String kyselynimi;
	private String deployattu;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy ="kysely")
	private List<Kysymys> kysymykset;
	
	@JsonIgnore
	@Transient
	public static final String STATUS_DEPLOYED = "Ylhäällä";
	
	@JsonIgnore
	@Transient
	public static final String STATUS_NOT_DEPLOYED = "Alhaalla";
	
	public Kysely() {}

	public Kysely(String kyselynimi, String deployattu) {
		super();
		this.kyselynimi = kyselynimi;
		this.deployattu = deployattu;
	}
	

	public Kysely(Long kyselyid, String kyselynimi, String deployattu) {
		super();
		this.kyselyid = kyselyid;
		this.kyselynimi = kyselynimi;
		this.deployattu = deployattu;
	}

	public void setKyselyid(Long kyselyid) {
		this.kyselyid = kyselyid;
	}

	public void setKyselynimi(String kyselynimi) {
		this.kyselynimi = kyselynimi;
	}

	public void setDeployattu(String deployattu) {
		this.deployattu = deployattu;
	}

	public void setKysymykset(List<Kysymys> kysymykset) {
		this.kysymykset = kysymykset;
	}

	public Long getKyselyid() {
		return kyselyid;
	}

	public String getKyselynimi() {
		return kyselynimi;
	}

	public String getDeployattu() {
		return deployattu;
	}

	public List<Kysymys> getKysymykset() {
		return kysymykset;
	}

	@Override
	public String toString() {
		return "Kysely [kyselyid=" + kyselyid + ", kyselynimi=" + kyselynimi + ", deployattu=" + deployattu + "]";
	}
	
	
	
	
}
