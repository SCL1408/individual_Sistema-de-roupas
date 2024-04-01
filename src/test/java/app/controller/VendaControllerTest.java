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
import app.entity.Funcionario;
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
		List<Venda> lista = new ArrayList<Venda>();
		for(int i=0; i<3;i++) {
			lista.add(new Venda((long) i, "endereco", 0, "OK", new ArrayList<Produto>(), 
					new Cliente(i, "cliente "+i, "123.456.789-10", 10*i+5, "("+i+i+")91234-1234", new ArrayList<Venda>()), 
					new Funcionario(i, "funcionario "+i, 10*i+5, i+i+i+i, new ArrayList<Venda>()))
			);			
			for(int j=0; j<3; j++) {
				lista.get(i).getProdutos().add(new Produto((long) j+i, "produto "+(j+i), 10*j+i));
			}
			for(int j=0; j<2; j++) {
				lista.get(i).getCliente().getVendas().add(lista.get(i));
				lista.get(i).getFuncionario().getVendas().add(lista.get(i));				
			}
		}
		
		when(this.vendaRepository.findAll()).thenReturn(lista);
		when(this.vendaRepository.findById((long) 1)).thenReturn(Optional.of(lista.get(1)));
		when(this.vendaRepository.save(lista.get(1))).thenReturn(lista.get(1));
	}
	
	@Test
	@DisplayName("Teste de findAll() OK em Venda")
	void findAllOk() {
		ResponseEntity<List<Venda>> response = this.vendaController.findAll();
		List<Venda> lista = response.getBody();
		assertEquals(3, lista.size());
	}
	
	@Test
	@DisplayName("Teste de findById() OK em Venda")
	void findByIdOk() {
		ResponseEntity<Venda> response = this.vendaController.findById(1);
		Venda venda = response.getBody();
		assertEquals(1, venda.getIdVenda());
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
}
