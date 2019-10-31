package fi.sycks.surveytool.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.sycks.surveytool.domain.Kysely;
import fi.sycks.surveytool.domain.Kysymys;
import fi.sycks.surveytool.domain.Vastaaja;
import fi.sycks.surveytool.domain.Vastaus;
import fi.sycks.surveytool.interfaces.KyselyRepository;
import fi.sycks.surveytool.interfaces.KysymysRepository;
import fi.sycks.surveytool.interfaces.UserRepository;
import fi.sycks.surveytool.interfaces.VastaajaRepository;
import fi.sycks.surveytool.interfaces.VastausRepository;

@Controller
// @ResponseBody
public class SurveyController {
	
	@Autowired
	private KyselyRepository kyselyRepository;
	
	@Autowired
	private KysymysRepository kysymysRepository;
	
	@Autowired
	private VastaajaRepository vastaajaRepository;
	
	@Autowired
	private VastausRepository vastausRepository;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("*")
	public String index() {
		return "index";
	}
	
	@GetMapping("/api/kysely") 
	public @ResponseBody List<Kysely> getAllKyselyREST(){
		return (List<Kysely>) kyselyRepository.findAll();
	}

	@GetMapping("/api/kysymys")
	public @ResponseBody List<Kysymys> getAllKysymyksetREST(){
		return (List<Kysymys>) kysymysRepository.findAll();
	}
	
	@GetMapping("/api/vastaaja") 
	public @ResponseBody List<Vastaaja> getAllVastaajaREST(){
		return (List<Vastaaja>) vastaajaRepository.findAll();
	}

	@GetMapping("/api/vastaus")
	public @ResponseBody List<Vastaus> getAllVastausREST(){
		return (List<Vastaus>) vastausRepository.findAll();
	}
	
	@GetMapping("/api/vastaus/kyselyid/{kyselyid}")
	public @ResponseBody List<Vastaus> getAllVastausByKyselyREST(@PathVariable("kyselyid") long kyselyid) {
		List<Vastaus> vastaukset =  (List<Vastaus>) vastausRepository.findAll();
		Optional<Kysely> kysely = kyselyRepository.findById(kyselyid);
		
		List<Vastaus> kyselyVastaukset = new ArrayList<>();
		if(kysely.get() != null) {
			for(Vastaus vastaus : vastaukset) {
				Kysymys kysymys = vastaus.getKysymys();
				if(kysymys.getKysely().getKyselyid() == kysely.get().getKyselyid()) {
					kyselyVastaukset.add(vastaus);
				}
			}		
		}
		
		return kyselyVastaukset;
	}
	@GetMapping("/vastaukset")
	public @ResponseBody List<Vastaus> all() {
		return (List<Vastaus>) vastausRepository.findAll();
	}
	@PostMapping("/vastaukset")
	public @ResponseBody Vastaus newVastaus(@RequestBody Vastaus newVastaus) {
		return vastausRepository.save(newVastaus);
	}
}
