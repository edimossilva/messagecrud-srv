package br.edu.fapce.nexti.messagecrud.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import br.edu.fapce.nexti.messagecrud.dto.CreateMessageDTO;
import br.edu.fapce.nexti.messagecrud.dto.EditMessageDTO;
import br.edu.fapce.nexti.messagecrud.model.Message;
import br.edu.fapce.nexti.messagecrud.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepository;

	public List<String> validate(BindingResult bindingResult) {
		List<String> errors = new ArrayList<String>();
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(e -> errors.add(e.getDefaultMessage()));
		}
		return errors;
	}

	public Message save(Message mensagem) {
		return messageRepository.save(mensagem);
	}

	public Message save(@Valid CreateMessageDTO dto) {
		Message mensagem = dto.toMensagem();
		return messageRepository.save(mensagem);
	}

	public void deleteById(Long id) {
		messageRepository.deleteById(id);
	}

	public List<Message> list() {
		return messageRepository.findAll();
	}

	public Message findById(Long id) {
		return messageRepository.findById(id).get();
	}

	public Message update(@Valid EditMessageDTO dto) {
		return messageRepository.save(dto.toMensagem());
	}

}
