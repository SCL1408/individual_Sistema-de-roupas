package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import app.entity.Funcionario;
import app.repository.FuncionarioRepository;

@SpringBootTest
public class FuncionarioControllerTest {
	@Autowired
	FuncionarioController funcionarioController;
	
	@MockBean
	FuncionarioRepository funcionarioRepository;
	
	@BeforeEach
	void setup() {
		when(this.funcionarioRepository.findAll()).thenReturn(new ArrayList<Funcionario>());
		when(this.funcionarioRepository.findById((long) 1)).thenReturn(Optional.of(new Funcionario()));
		when(this.funcionarioRepository.save(new Funcionario())).thenReturn(new Funcionario());
	}
	
	@Test
	@DisplayName("findAll ok")
	void findAllOk() {
		ResponseEntity<List<Funcionario>> response = this.funcionarioController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	@DisplayName("findById Ok")
	void findByIdOk() {
		ResponseEntity<Funcionario> response = this.funcionarioController.findById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(302, httpStatus);
	}
	
	@Test
	@DisplayName("delete ok")
	void deleteOk() {
		ResponseEntity<String> response = this.funcionarioController.delete(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	@DisplayName("save ok")
	void saveOk() {
		ResponseEntity<String> response = this.funcionarioController.save(new Funcionario());
		int httpStatus = response.getStatusCode().value();
		assertEquals(201, httpStatus);
	}
	
	@Test
	@DisplayName("update ok")
	void updateOk() {
		ResponseEntity<String> response = this.funcionarioController.update(1, new Funcionario());
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
}
