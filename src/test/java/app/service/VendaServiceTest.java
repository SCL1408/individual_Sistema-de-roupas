package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.entity.Produto;

@SpringBootTest
public class VendaServiceTest {
	@Autowired
	VendaService vendaService;
	
	@Test
	void calcularValorTotalOk() {
		List<Produto> produtos = new ArrayList<Produto>();
		for(int i=0; i<3; i++) {
			Produto produto = new Produto();
			produto.setValor(10);
			produtos.add(produto);
		}
		this.vendaService.calcularValorTotal(produtos);
		
		assertEquals(30, produtos);
	}
	
	@Test
	void calcularValorTotalListaNulo() {
		List<Produto> produtos = null;
		
		assertThrows(Exception.class, ()->{
			this.vendaService.calcularValorTotal(produtos);
		});
	}
}
