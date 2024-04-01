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

import app.entity.Venda;
import app.service.VendaService;

@RestController
@RequestMapping("/api/venda")
public class VendaController {
	@Autowired
	public VendaService vendaService;

	@GetMapping("/findAll")
	public ResponseEntity<List<Venda>> findAll() {
		try {
			return new ResponseEntity<List<Venda>>(this.vendaService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Venda> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<Venda>(this.vendaService.findById(id), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Venda venda) {
		try {
			this.vendaService.save(venda);
			return new ResponseEntity<String>("A venda " + Integer.toString((int) venda.getIdVenda()) + " foi cadastrado com sucesso", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Houve o erro: " + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/updade/{id}")
	public ResponseEntity<String> update(@PathVariable long id, @RequestBody Venda venda){
		try {
			this.vendaService.update(id, venda);
			return new ResponseEntity<String>("O venda " + Integer.toString((int) venda.getIdVenda()) + " foi atualizado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Houve o erro: " + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id){
		try {
			return new ResponseEntity<String>("O venda " + this.vendaService.delete(id) + " foi deletado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Houve o erro: " + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByClienteNome")
	public ResponseEntity<List<Venda>> findByClienteNome(@RequestParam String clienteNome){
		try {
			return new ResponseEntity<List<Venda>>(this.vendaService.findByClienteNome(clienteNome), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByFuncionarioNome")
	public ResponseEntity<List<Venda>> findByFuncionarioNome(@RequestParam String funcionarioNome){
		try {
			return new ResponseEntity<List<Venda>>(this.vendaService.findByFuncionarioNome(funcionarioNome), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByValorMaior")
	public ResponseEntity<List<Venda>> findByValorMaior(@RequestParam double valor){
		try {
			return new ResponseEntity<List<Venda>>(this.vendaService.findByValorMaior(valor), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByProduto/{id}")
	public ResponseEntity<List<Venda>> findByProduto (@PathVariable long id){
		try {
			return new ResponseEntity<List<Venda>>(this.vendaService.findByProdutos(id), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
