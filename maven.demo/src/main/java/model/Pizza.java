package model;

public class Pizza {

	private int codigo;
	private String nome;
	private double valor;
	
	public Pizza() {
		this.codigo = 0;
		this.nome = "";
		this.valor = 0;
	}
	
	public Pizza(int codigo, String nome, double valor) {
		setCodigo(codigo);
		setNome(nome);
		setValor(valor);
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Pizza [codigo=" + codigo + ", nome=" + nome + ", valor=" + valor + "]";
	}	
	
	@Override
	public boolean equals(Object obj) {
	    // Verifica se o objeto é o mesmo (referência)
	    if (this == obj) return true;

	    // Verifica se o objeto é nulo ou se as classes são diferentes
	    if (obj == null || getClass() != obj.getClass()) return false;

	    // Caso seja um objeto do tipo Pizza, realiza a comparação
	    Pizza pizza = (Pizza) obj;
	    return this.codigo == pizza.codigo;
	}

	
	public String toJson() {
	    return "{" +
	           "\"codigo\": " + this.getCodigo() + "," +
	           "\"nome\": \"" + this.getNome() + "\"," +
	           "\"valor\": " + this.getValor() +
	           "}";
	}
	

}
