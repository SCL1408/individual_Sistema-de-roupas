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

import app.entity.Produto;
import app.repository.ProdutoRepository;

@SpringBootTest
public class ProdutoControllerTest {
	@Autowired
	ProdutoController produtoController;
	
	@MockBean
	ProdutoRepository produtoRepository;
	
	@BeforeEach
	void setup() {
		when(this.produtoRepository.findAll()).thenReturn(new ArrayList<Produto>());
		when(this.produtoRepository.findById((long) 1)).thenReturn(Optional.of(new Produto()));
		when(this.produtoRepository.save(new Produto())).thenReturn(new Produto());
	}
	
	@Test
	@DisplayName("findAll ok")
	void findAllOk() {
		ResponseEntity<List<Produto>> response = this.produtoController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	@DisplayName("findById Ok")
	void findByIdOk() {
		ResponseEntity<Produto> response = this.produtoController.findById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(302, httpStatus);
	}
	
	@Test
	@DisplayName("delete ok")
	void deleteOk() {
		ResponseEntity<String> response = this.produtoController.delete(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	@DisplayName("save ok")
	void saveOk() {
		ResponseEntity<String> response = this.produtoController.save(new Produto());
		int httpStatus = response.getStatusCode().value();
		assertEquals(201, httpStatus);
	}
	
	@Test
	@DisplayName("update ok")
	void updateOk() {
		ResponseEntity<String> response = this.produtoController.update(1, new Produto());
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
}
