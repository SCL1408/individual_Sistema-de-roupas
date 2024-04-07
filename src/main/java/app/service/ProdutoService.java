package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Produto;
import app.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	public ProdutoRepository produtoRepository;

	public List<Produto> findAll() {
		return this.produtoRepository.findAll();
	}

	public Produto findById(long id) {
		// TODO Auto-generated method stub
		return this.produtoRepository.findById(id).get();
	}

	public void save(Produto produto) {
		this.produtoRepository.save(produto);
	}

	public void update(long id, Produto produto) {
		produto.setIdProduto(id);
		this.produtoRepository.save(produto);
	}

	public String delete(long id) {
		String nome = findById(id).getNome();
		this.produtoRepository.deleteById(id);
		return nome;
	}
	
	public List<Produto> findByNome(String nome) {
		return this.produtoRepository.findByNome(nome);
	}
	
	public List<Produto> findByValor(double valor) {
		return this.produtoRepository.findByValor(valor);
	}
	
	public List<Produto> findByPodutoValor(double valor){
		return this.produtoRepository.findByPodutoValor(valor);
	}
		
	
}
