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
import fi.sycks.surveytool.domain.Monivalinta;
import fi.sycks.surveytool.domain.Vastaaja;
import fi.sycks.surveytool.domain.Vastaus;
import fi.sycks.surveytool.interfaces.KyselyRepository;
import fi.sycks.surveytool.interfaces.KysymysRepository;
import fi.sycks.surveytool.interfaces.MonivalintaRepository;
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
	
	@Autowired
	private MonivalintaRepository monivalintaRepository;
	
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
	public String muokkaaKysely(@PathVariable("kyselyid") Long kyselyid, Model model) {
		Kysely kysely = kyselyRepository.findById(kyselyid).get();
		model.addAttribute("kysely", kysely);
		model.addAttribute("kysymykset",(List<Kysymys>)kysymysRepository.findByKysely(kysely));
		List<String> status = new ArrayList<>();
		status.add(Kysely.STATUS_DEPLOYED);
		status.add(Kysely.STATUS_NOT_DEPLOYED);
		model.addAttribute("status", status);
		return "muokkaakysely";	
	}
	@RequestMapping(value = "/tallennakysely", method = RequestMethod.POST)
	public String save(Kysely kysely) {
		kyselyRepository.save(kysely);
		
		return "redirect:index";
	}
	@RequestMapping(value = "/poistakysely/{kyselyid}")
	public String poistaKysely(@PathVariable("kyselyid") Long kyselyid, Model model) {
		kyselyRepository.deleteById(kyselyid);
		return "redirect:../index";
	}
	@RequestMapping("/muokkaakysymys/{kysymysid}")
	public String muokkaaKysymys(@PathVariable("kysymysid") Long kysymysid, Model model) {
		model.addAttribute("kysymys", kysymysRepository.findById(kysymysid).get());
		List<String> tyypit = new ArrayList<>();
		tyypit.add(Kysymys.TYPE_MULTICHOICE);
		tyypit.add(Kysymys.TYPE_NUMBER);
		tyypit.add(Kysymys.TYPE_SHORT_TEXT);
		model.addAttribute("tyypit", tyypit);
		model.addAttribute("monivalinta", new Monivalinta());
		List<Monivalinta> monivalinnat =(List<Monivalinta>) monivalintaRepository.findAll();
		model.addAttribute("monivalinnat", monivalinnat);
		return "muokkaakysymys";	
	}
	
	@RequestMapping(value = "/tallennakysymys", method = RequestMethod.POST)
	public String save(Kysymys kysymys, Monivalinta monivalinta) {
		kysymys = kysymysRepository.save(kysymys);
		monivalinta = monivalintaRepository.save(monivalinta);
		return "redirect:muokkaakysely/" + kysymys.getKysely().getKyselyid();
	}
	@RequestMapping(value = "/poistakysymys/{kysymysid}")
	public String poistaKysymys(@PathVariable("kysymysid") Long kysymysid, Model model) {
		Optional<Kysymys> kysymys = kysymysRepository.findById(kysymysid);
		Kysely kysely = kyselyRepository.findById(kysymys.get().getKysely().getKyselyid()).get();
		kysymysRepository.deleteById(kysymysid);
		return "redirect:/muokkaakysely/"+kysely.getKyselyid();
	}
	@RequestMapping(value ="/poistavalinta/{valintaid}")
	public String poistaValinta(@PathVariable("valintaid") Long valintaid, Model model) {

		monivalintaRepository.deleteById(valintaid);

		return "redirect:../muokkaakysely";
	}
	
	@RequestMapping("/api/kysely") 
	public @ResponseBody List<Kysely> getAllKyselyREST(){
		return (List<Kysely>) kyselyRepository.findAll();
	}

	@RequestMapping("/api/kysymys/kyselyid/{kyselyid}")
	public @ResponseBody List<Kysymys> getAllKysymyksetByKyselyIdREST(@PathVariable("kyselyid") long kyselyid){
		Optional<Kysely> kysely = kyselyRepository.findById(kyselyid);
		if(kysely.get() == null) return new ArrayList<>();
		return (List<Kysymys>) kysymysRepository.findByKysely(kysely.get());
	}
	
	@RequestMapping("/api/monivalinta/{kysymysid}")
	public @ResponseBody List<Monivalinta> getMonivalinnatByKysymysIdREST(@PathVariable("kysymysid") long kysymysid){
		Optional<Kysymys> kysymys = kysymysRepository.findById(kysymysid);
		if(kysymys.get() == null) return new ArrayList<>();
		return (List<Monivalinta>) monivalintaRepository.findByKysymys(kysymys.get());
	}
	
	@RequestMapping("/api/vastaukset/kyselyid/{kyselyid}")
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
	@RequestMapping("/kaikkivastaukset/kyselyid/{kyselyid}")
	public String kaikkiVastauksetKyselyyn(@PathVariable("kyselyid") long kyselyid, Model model) {
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
		
		model.addAttribute("kaikkivastaukset", kyselyVastaukset);
		return "kaikkivastaukset";
	}
	@RequestMapping("/katsovastaukset/{kysymysid}")
	public String katsoVastaukset(@PathVariable("kysymysid") Long kysymysid, Model model) {
		model.addAttribute("kysymys", kysymysRepository.findById(kysymysid).get());
		model.addAttribute("vastaukset",(List<Vastaus>)vastausRepository.findAll());
		return "vastaus";	
	}
	
	@PostMapping("/api/vastaukset")
	public @ResponseBody void vastausKyselyyn(@RequestBody Vastaus[] vastaukset) throws Exception {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		
		if(vastaukset.length > 0) {
			Vastaus v = vastaukset[0];
			long kysymysId= v.getKysymys().getKysymysid();
			Optional<Kysymys> kysymys = kysymysRepository.findById(kysymysId);
			long id = kysymys.get().getKysely().getKyselyid();
			
			Optional<Kysely> kysely = kyselyRepository.findById(id);
			String status = kysely.get().getDeployattu();
			
			if(status.equals(Kysely.STATUS_NOT_DEPLOYED)) {
				
				throw new Exception("not deployed");
			}
		}
		
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
	@PostMapping("/lisaa")
	public String luoKysely() {
		Kysely kysely = kyselyRepository.save(new Kysely("",  Kysely.STATUS_NOT_DEPLOYED));
		return "redirect:/muokkaakysely/" + kysely.getKyselyid();
	}
	
	@PostMapping("/muokkaakysely/{kyselyid}")
	public String lisaaKysymys(@PathVariable("kyselyid") Long kyselyid, Model model) {
		Kysely kysely = kyselyRepository.findById(kyselyid).get();
	    model.addAttribute("kysely", kysely);
	    Kysymys kysymys = new Kysymys("", Kysymys.TYPE_SHORT_TEXT, kysely);
	    kysymys = kysymysRepository.save(kysymys);
	        
	    return "redirect:/muokkaakysymys/"+ kysymys.getKysymysid();
	}
	@GetMapping("/help")
	public String restApiDoc() {
		return "help";
	}

}
