package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Funcionario;
import app.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	public FuncionarioRepository funcionarioRepository;

	public List<Funcionario> findAll() {
		return this.funcionarioRepository.findAll();
	}

	public Funcionario findById(long id) {
		// TODO Auto-generated method stub
		return this.funcionarioRepository.findById(id).get();
	}

	public void save(Funcionario funcionario) {
		this.funcionarioRepository.save(funcionario);
	}

	public void update(long id, Funcionario funcionario) {
		funcionario.setIdFuncionario(id);
		this.funcionarioRepository.save(funcionario);
	}

	public String delete(long id) {
		String nome = findById(id).getNome();
		this.funcionarioRepository.deleteById(id);
		return nome;
	}

	public List<Funcionario> findByNome(String nome) {
		return this.funcionarioRepository.findByNome(nome);
	}
	
	public List<Funcionario> findByIdade(int idade) {
		return this.funcionarioRepository.findByIdade(idade);
	}
	
	public List<Funcionario> findByFuncionarioIdade(int idade){
		return this.funcionarioRepository.findByFuncionarioIdade(idade);
	}
	
	public List<Funcionario> findByMatricula(int matricula) {
		return this.funcionarioRepository.findByMatricula(matricula);
	}
	
}
