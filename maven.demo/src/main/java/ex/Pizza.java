package ex;

public class Pizza {
	private int codigo;
	private String nome;
	private float valor;
	
	public Pizza() {
		this.codigo = -1;
		this.nome = "";
		this.valor = 0;
	}
	
	public Pizza(int codigo, String nome, float valor) {
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(char valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Pizza [codigo=" + codigo + ", nome=" + nome + ", valor=" + valor + "]";
	}	
	
}

