package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Produto;
import app.service.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
	@Autowired
	public ProdutoService produtoService;

	@GetMapping("/findAll")
	public ResponseEntity<List<Produto>> findAll() {
		try {
			return new ResponseEntity<List<Produto>>(this.produtoService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Produto> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<Produto>(this.produtoService.findById(id), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Produto produto) {
		try {
			this.produtoService.save(produto);
			return new ResponseEntity<String>("O produto " + produto.getNome() + " foi cadastrado com sucesso", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Houve o erro: " + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/updade/{id}")
	public ResponseEntity<String> update(@PathVariable long id, @RequestBody Produto produto){
		try {
			this.produtoService.update(id, produto);
			return new ResponseEntity<String>("O produto " + produto.getNome() + " foi atualizado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Houve o erro: " + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id){
		try {
			return new ResponseEntity<String>("O produto " + this.produtoService.delete(id) + " foi deletado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Houve o erro: " + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByNome")
	public ResponseEntity<List<Produto>> findByIdade (@RequestParam String nome){
		
		try {
			
			List<Produto> lista = this.produtoService.findByNome(nome);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		}catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByValor")
	public ResponseEntity<List<Produto>> findByValor (@RequestParam double valor){
		
		try {
			
			List<Produto> lista = this.produtoService.findByValor(valor);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		}catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/findByProdutoValor")
	public ResponseEntity<List<Produto>> findByPodutoValor (@RequestParam double valor){
		
		try {
			
			List<Produto> lista = this.produtoService.findByPodutoValor(valor);
			return new ResponseEntity<>(lista, HttpStatus.OK);
		}catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
