package br.edu.fapce.nexti.messagecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fapce.nexti.messagecrud.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
