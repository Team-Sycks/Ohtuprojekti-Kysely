package fi.sycks.surveytool;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.sycks.surveytool.web.SurveyController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SurveytoolApplicationTests {
	
	@Autowired
	private SurveyController controller;
	

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}



