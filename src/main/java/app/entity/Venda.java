package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idVenda;
	
	@NotBlank(message = "Erro campo vazio")
	private String endereco;
	
	@NotNull(message = "Erro campo vazio")
	private double valor;
	
	@NotBlank(message = "Erro campo vazio")
	private String status;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "venda_produto", 
			joinColumns = @JoinColumn(name = "IdVenda"),
			inverseJoinColumns = @JoinColumn(name = "IdProduto")
			)
	private List<Produto>produtos;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("vendas")
	private Cliente cliente;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("vendas")
	private Funcionario funcionario;
}
