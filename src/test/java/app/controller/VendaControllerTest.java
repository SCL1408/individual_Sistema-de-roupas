package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
			/*for(int j=0; j<2; j++) {
				lista.get(i).getCliente().getVendas().add(lista.get(i));
				lista.get(i).getFuncionario().getVendas().add(lista.get(i));				
			}*/
		}
		
		when(this.vendaRepository.findAll()).thenReturn(lista);
		//when(this.vendaRepository.findById((long) 1)).thenReturn(lista.get(0));
	}
	
	@Test
	@DisplayName("Teste de findAll() em Venda")
	void findAllOk() {
		ResponseEntity<List<Venda>> response = this.vendaController.findAll();
		List<Venda> lista = response.getBody();
		assertEquals(3, lista.size());
	}
}
