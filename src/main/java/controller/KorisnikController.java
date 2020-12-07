package controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.KorisnikCreateDto;
import dto.KorisnikDto;
import dto.KorisnikUpdateDto;
import service.KorisnikService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {
	private KorisnikService korisnikService;
	private JmsTemplate jmsTemplate;
	private String orderDestination;
	
	public KorisnikController(KorisnikService korisnikService, JmsTemplate jmsTemplate, @Value("${destination.createOrder}")String orderDestination) {
		super();
		this.korisnikService = korisnikService;
		this.jmsTemplate = jmsTemplate;
		this.orderDestination = orderDestination;
	}
	
	@GetMapping
	public ResponseEntity<Page<KorisnikDto>> findAll(@ApiIgnore Pageable pageable){
		return new ResponseEntity<>(korisnikService.findAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<KorisnikDto> findById(@PathVariable("id") String id){
		return new ResponseEntity<>(korisnikService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<KorisnikDto> add(@RequestBody @Valid KorisnikCreateDto productCreateDto){
		return new ResponseEntity<>(korisnikService.add(productCreateDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<KorisnikDto> update(@PathVariable("id") String id, @RequestBody @Valid KorisnikUpdateDto productUpdateDto) {
        return new ResponseEntity<>(korisnikService.update(id, productUpdateDto), HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        korisnikService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
