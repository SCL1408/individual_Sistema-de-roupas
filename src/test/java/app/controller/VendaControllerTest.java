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
import app.entity.Venda;
import app.repository.VendaRepository;

@SpringBootTest
public class VendaControllerTest {
	@Autowired
	VendaController	vendaController;
	
	@MockBean
	VendaRepository vendaRepository;
	
	@BeforeEach
	void setup() {
		when(this.vendaRepository.findAll()).thenReturn(new ArrayList<Venda>());
 		when(this.vendaRepository.findById((long) 1)).thenReturn(Optional.of(new Venda()));
		when(this.vendaRepository.save(new Venda())).thenReturn(new Venda());
		when(this.vendaRepository.findByClienteNome("cliente 1")).thenReturn(new ArrayList<Venda>());
		when(this.vendaRepository.findByFuncionarioNome("funcionario 1")).thenReturn(new ArrayList<Venda>());
		when(this.vendaRepository.findByValorMaior(1)).thenReturn(new ArrayList<Venda>());
		when(this.vendaRepository.findByProdutos(new Produto())).thenReturn(new ArrayList<Venda>());
	}
	
	@Test
	@DisplayName("Teste de findAll() OK em Venda")
	void findAllOk() {
		ResponseEntity<List<Venda>> response = this.vendaController.findAll();
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	@DisplayName("Teste de findById() OK em Venda")
	void findByIdOk() {
		ResponseEntity<Venda> response = this.vendaController.findById(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(302, httpStatus);
	}
	
	@Test
	@DisplayName("Teste de save() OK em Venda")
	void saveOk() {
		Venda venda = new Venda(0, "endereco", 0, "OK", new ArrayList<Produto>(), null, null);
		ResponseEntity<String> response = this.vendaController.save(venda);
		int httpStatus = response.getStatusCode().value();
		assertEquals(201, httpStatus);
	}
	
	@Test
	@DisplayName("Teste de update() OK em Venda")
	void updateOk() {
		Venda venda = new Venda(1000, "endereco 1000", 1000, "OK", new ArrayList<Produto>(), null, null);
		ResponseEntity<String> response = this.vendaController.update(1, venda);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	@DisplayName("Teste de delete() OK em Venda")
	void deleteOk() {
		ResponseEntity<String> response = this.vendaController.delete(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(200, httpStatus);
	}
	
	@Test
	@DisplayName("Teste de findByClienteNome() OK em Venda")
	void findByClienteNomeOk() {
		ResponseEntity<List<Venda>> response = this.vendaController.findByClienteNome("cliente 1");
		int httpStatus = response.getStatusCode().value();
		assertEquals(302, httpStatus);
	}
	
	@Test
	@DisplayName("Teste de findByFuncionarioNome() OK em Venda")
	void findByFuncionarioNomeOk() {
		ResponseEntity<List<Venda>> response = this.vendaController.findByFuncionarioNome("funcionario 1");
		int httpStatus = response.getStatusCode().value();
		assertEquals(302, httpStatus);
	}
	
	@Test
	@DisplayName("Teste de findByValorMaior() OK em Venda")
	void findByValorMaiorOk() {
		ResponseEntity<List<Venda>> response = this.vendaController.findByValorMaior(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(302, httpStatus);
	}
	
	@Test
	@DisplayName("Teste de findByProduto() OK em Venda")
	void findByProdutoOk() {
		ResponseEntity<List<Venda>> response = this.vendaController.findByProduto(1);
		int httpStatus = response.getStatusCode().value();
		assertEquals(302, httpStatus);
	}
}