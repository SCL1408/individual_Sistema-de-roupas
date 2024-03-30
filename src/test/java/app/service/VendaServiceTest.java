package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.entity.Produto;
import app.entity.Venda;

@SpringBootTest
public class VendaServiceTest {
	@Autowired
	VendaService vendaService;

	@Test
	@DisplayName("Testando calcularValorTotal para tudo certo")
	void calcularValorTotalOk() {
		List<Produto> produtos = new ArrayList<Produto>();
		for (int i = 0; i < 3; i++) {
			Produto produto = new Produto();
			produto.setValor(10);
			produtos.add(produto);
		}
		double valor = this.vendaService.calcularValorTotal(produtos);

		assertEquals(30, valor);
	}

	@Test
	@DisplayName("Testando calcularValorTotal para o caso da lista de entrada ser nula")
	void calcularValorTotalListaNulo() {
		List<Produto> produtos = null;

		assertThrows(Exception.class, () -> {
			this.vendaService.calcularValorTotal(produtos);
		});
	}

	@Test
	@DisplayName("Testando verificarStatus() com status que ativa a função")
	void verificarStatusOk1() {
		Venda venda = new Venda();
		List<Produto> produtos = new ArrayList<Produto>();
		for (int i = 0; i < 3; i++) {
			Produto produto = new Produto();
			produto.setValor(10);
			produtos.add(produto);
		}
		venda.setProdutos(produtos);
		venda.setValor(this.vendaService.calcularValorTotal(produtos));
		
		venda.setStatus("CANCELADO");

		venda = this.vendaService.verificarStatus(venda);

		assertNull(venda.getProdutos());
		assertEquals(0, venda.getValor());
	}

	@Test
	@DisplayName("Testando verificarStatus() com status que ativa função, mas coma caixa baixa")
	void verificarStatusOk2() {
		Venda venda = new Venda();
		List<Produto> produtos = new ArrayList<Produto>();
		for (int i = 0; i < 3; i++) {
			Produto produto = new Produto();
			produto.setValor(10);
			produtos.add(produto);
		}
		venda.setProdutos(produtos);
		venda.setValor(this.vendaService.calcularValorTotal(produtos));
		
		venda.setStatus("cancelado");

		venda = this.vendaService.verificarStatus(venda);

		assertNull(venda.getProdutos());
		assertEquals(0, venda.getValor());
	}

	@Test
	@DisplayName("Testando verificarStatus() com status que inativa função")
	void verificarStatusOk3() {
		Venda venda = new Venda();
		List<Produto> produtos = new ArrayList<Produto>();
		for (int i = 0; i < 3; i++) {
			Produto produto = new Produto();
			produto.setValor(10);
			produtos.add(produto);
		}
		venda.setProdutos(produtos);
		venda.setValor(this.vendaService.calcularValorTotal(produtos));
		venda.setStatus("VALIDO");

		venda = this.vendaService.verificarStatus(venda);

		assertNotNull(venda.getProdutos());
		assertNotEquals(0, venda.getValor());
	}

	@Test
	@DisplayName("Testando verificarStatus() com parâmetro nulo")
	void verificarStatusNull() {
		Venda venda = null;
		
		assertThrows(Exception.class, ()->{
			this.vendaService.verificarStatus(venda);
		});
	}
}
