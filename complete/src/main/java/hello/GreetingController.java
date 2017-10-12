
package hello;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.Notification;
import model.jsonEnvio;
import model.respuestaFCM;

@RestController
public class GreetingController extends HttpServlet{
	
	respuestaFCM respuesta;
	Notification notificacionAux;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @RequestMapping("/greeting")
    public Saludo greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	System.out.print("peticion: "+ counter.incrementAndGet());
        return new Saludo(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    
    
    @RequestMapping(value="/mensaje", method= RequestMethod.POST)
    public respuestaFCM mensaje (@RequestBody @Valid Notification notificacion,	BindingResult result) throws Exception {
    	RestTemplate restTemplate = new RestTemplate();
    	notificacionAux=notificacion;
    	this.run(restTemplate);
    	return respuesta;
    }
    
    
    
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
    	System.out.print("peticion: "+ counter.incrementAndGet());
    	HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "key=" + "AAAAkAFwJpI:APA91bHS9yOKQwmtbty2FS0uN_TmH5rXeBEOIXrLDOyBDIX2FK7U-JFw-3L9UIVinoCuMf50iebdazaQfKyGsLoMP2jpxiKv80_HBuMXFZvMmhCoFRtWcUPViaktuqT51mcqBH2OWxjP");
		headers.setContentType(MediaType.APPLICATION_JSON);
		//headers.add("Content-Type", "application/json");
		String instanceID="/topics/news";
		jsonEnvio jsEnv = new jsonEnvio(instanceID, new Notification (notificacionAux.getTitle(),notificacionAux.getBody()));
		String urlPost = "https://fcm.googleapis.com/fcm/send";
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpEntity<jsonEnvio> request = new HttpEntity<jsonEnvio>(jsEnv, headers);
		respuesta = restTemplate.postForObject(urlPost, request, respuestaFCM.class);
		log.info(respuesta.toString());
		return args -> {
			respuestaFCM quote = respuesta;
			log.info(quote.toString());
		};
    }
    
}