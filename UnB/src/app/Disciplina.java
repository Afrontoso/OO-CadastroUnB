package app;

public class Disciplina{
	public String nome;
	public String codigo;
	public String departamento;
	
	public Disciplina(String nome, String codigo, String departamento) {
		this.nome = nome;
		this.codigo = codigo;
		this.departamento = departamento;
	}

	public final String getNome() {
		return nome;
	}

	public final String getCodigo() {
		return codigo;
	}

	public final String getDepartamento() {
		return departamento;
	}
	protected void finalize() throws Throwable {
		System.out.println("Destruindo objeto: " + this);
	}
	
	public String toString() {
		String resposta = super.toString() + '\n';
		resposta += "NOME: " + nome + '\n';
		resposta += "DEPARTAMENTO: " + departamento + '\n'; 
		resposta += "CODIGO: " + codigo + '\n';
		return resposta;
	}

}
