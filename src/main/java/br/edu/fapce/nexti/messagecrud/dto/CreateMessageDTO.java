package br.edu.fapce.nexti.messagecrud.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.edu.fapce.nexti.messagecrud.model.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMessageDTO {
	@NotNull(message = "O atributo [number] da mensagem nao pode ser vazio")
	private Long number;
	@NotEmpty(message = "O atributo [text] da mensagem nao pode ser vazio")
	private String text;

	public Message toMensagem() {
		return Message.builder().number(getNumber()).text(getText()).build();
	}
}
