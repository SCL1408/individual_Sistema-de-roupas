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

import app.entity.Cliente;
import app.repository.ClienteRepository;

@SpringBootTest
public class ClienteControllerTest {
	@Autowired
	ClienteController clienteController;
	
	@MockBean
	ClienteRepository clienteRepository;
	
	@BeforeEach
	void setup() {
		when(this.clienteRepository.findAll()).thenReturn(new ArrayList<Cliente>());
		when(this.clienteRepository.findById((long) 1)).thenReturn(Optional.of(new Cliente()));
		when(this.clienteRepository.save(new Cliente())).thenReturn(new Cliente());
	}
	
	@Test
	@DisplayName("findAll ok")
	void findAllOk() {
		ResponseEntity<List<Cliente>> response = this.clienteController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	@DisplayName("findById Ok")
	void findByIdOk() {
		ResponseEntity<Cliente> response = this.clienteController.findById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(302, httpStatus);
	}
	
	@Test
	@DisplayName("delete ok")
	void deleteOk() {
		ResponseEntity<String> response = this.clienteController.delete(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	@DisplayName("save ok")
	void saveOk() {
		ResponseEntity<String> response = this.clienteController.save(new Cliente());
		int httpStatus = response.getStatusCode().value();
		assertEquals(201, httpStatus);
	}
	
	@Test
	@DisplayName("update ok")
	void updateOk() {
		ResponseEntity<String> response = this.clienteController.update(1, new Cliente());
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
}