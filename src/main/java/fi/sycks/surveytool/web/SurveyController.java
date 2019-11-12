package fi.sycks.surveytool.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public String index(Model model) {
		model.addAttribute("surveys",(List<Kysely>)kyselyRepository.findAll());
		return "index";
	}
	
	@RequestMapping("/muokkaakysely/{kyselyid}")
	public String muokkaaKysely(@PathVariable("kyselyid") long kyselyid, Model model) {
		model.addAttribute("kysely", kyselyRepository.findById(kyselyid).get());
		model.addAttribute("kysymykset",(List<Kysymys>)kysymysRepository.findAll());
		return "muokkaakysely";	
	}
	@RequestMapping(value = "/tallennakysely", method = RequestMethod.POST)
	public String save(Kysely kysely) {
		kyselyRepository.save(kysely);
		
		return "redirect:index";
	}
	@RequestMapping("/api/kysely") 
	public @ResponseBody List<Kysely> getAllKyselyREST(){
		return (List<Kysely>) kyselyRepository.findAll();
	}

	@RequestMapping("/api/kysymys")
	public @ResponseBody List<Kysymys> getAllKysymyksetREST(){
		return (List<Kysymys>) kysymysRepository.findAll();
	}
	
	@RequestMapping("/api/kysymys/kyselyid/{kyselyid}")
	public @ResponseBody List<Kysymys> getAllKysymyksetByKyselyIdREST(@PathVariable("kyselyid") long kyselyid){
		Optional<Kysely> kysely = kyselyRepository.findById(kyselyid);
		if(kysely.get() == null) return new ArrayList<>();
		return (List<Kysymys>) kysymysRepository.findByKysely(kysely.get());
	}
	
	@RequestMapping("/api/vastaaja") 
	public @ResponseBody List<Vastaaja> getAllVastaajaREST(){
		return (List<Vastaaja>) vastaajaRepository.findAll();
	}

	@RequestMapping("/api/vastaus")
	public @ResponseBody List<Vastaus> getAllVastausREST(){
		return (List<Vastaus>) vastausRepository.findAll();
	}
	
	@RequestMapping("/vastaukset/kyselyid/{kyselyid}")
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
	
	@GetMapping(value ="/vastaus/{kyselyid}")
	public String getVastauksetToKysely(@PathVariable("kyselyid") long kyselyid, Model model) {
		List<Kysymys> kysymykset = 
				(List<Kysymys>) kysymysRepository.findByKysely(kyselyRepository.findById(kyselyid).get());
		
		Collections.sort(kysymykset, new Comparator<Kysymys>() {
			public int compare (Kysymys o1, Kysymys o2) {
				return o1.getKysymysteksti().compareTo(o2.getKysymysteksti());
			}
		});
		
		List<Vastaaja> vastaajat = new ArrayList<>();
		for(Kysymys kysymys : kysymykset) {
			List<Vastaus> vastaukset = kysymys.getVastaukset();
			for(Vastaus vastaus : vastaukset) {
				Vastaaja vastaaja = vastaus.getVastaaja();
				if(!vastaajat.contains(vastaaja)) {
					vastaajat.add(vastaaja);
				}
			}
		}
		
		System.out.println(vastaajat.size());
		model.addAttribute("vastaajat", vastaajat.size());
		model.addAttribute("kysymykset", kysymykset);
		return "vastaus";
	}
	
	@GetMapping("/vastaukset")
	public @ResponseBody List<Vastaus> all() {
		return (List<Vastaus>) vastausRepository.findAll();
	}
	
	
	
	@PostMapping("/vastaukset")
	public @ResponseBody void vastausKyselyyn(@RequestBody Vastaus[] vastaukset) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		Vastaaja vastaaja = new Vastaaja(dtf.format(now) + "");
		vastaajaRepository.save(vastaaja);
		for(Vastaus vastaus : vastaukset) {
			vastaus.setVastaaja(vastaaja);
			vastausRepository.save(vastaus);
		}
	}
	
	@GetMapping("/vastaajat")
	public @ResponseBody List<Vastaaja> all1() {
		return (List<Vastaaja>) vastaajaRepository.findAll();
	}
	

}
