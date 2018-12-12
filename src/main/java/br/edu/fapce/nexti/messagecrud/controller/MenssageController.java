package br.edu.fapce.nexti.messagecrud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fapce.nexti.messagecrud.dto.CreateMessageDTO;
import br.edu.fapce.nexti.messagecrud.dto.EditMessageDTO;
import br.edu.fapce.nexti.messagecrud.model.Message;
import br.edu.fapce.nexti.messagecrud.service.MessageService;

@RequestMapping("/messages")
@RestController
public class MenssageController {

	@Autowired
	private MessageService messageService;

	@GetMapping
	public ResponseEntity<?> listar() {
		return new ResponseEntity<List<Message>>(messageService.list(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> find(@PathVariable Long id) {
		return new ResponseEntity<Message>(messageService.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody CreateMessageDTO dto, BindingResult result) {
		List<String> errors = messageService.validate(result);

		if (!errors.isEmpty()) {
			return new ResponseEntity<List<String>>(errors, HttpStatus.BAD_REQUEST);
		}

		Message message = messageService.save(dto);
		return new ResponseEntity<Message>(message, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> update(@Valid @RequestBody EditMessageDTO dto, BindingResult result) {
		List<String> errors = messageService.validate(result);

		if (!errors.isEmpty()) {
			return new ResponseEntity<List<String>>(errors, HttpStatus.BAD_REQUEST);
		}

		Message message = messageService.update(dto);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		messageService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
