package fi.sycks.surveytool.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class SurveyController {
		
	@RequestMapping("*")
	public String hello() {
		
		return "Hello";
	}

}
