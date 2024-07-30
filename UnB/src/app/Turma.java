package app;

public class Turma {
	String codigo, diaHora, semestre, qtdVagas;
	
	Professor professor;
	Disciplina disciplina;
	Aluno[] alunos;
	
	public Turma(String codigo, Professor professor, Disciplina disciplina, String diaHora, String semestre, String vagas,
			Aluno alunos) {
		this.codigo = codigo;
		this.diaHora = diaHora;
		this.semestre = semestre;
		this.qtdVagas = vagas;
	}

	public final String getCodigo() {
		return codigo;
	}

	public final String getDiaHora() {
		return diaHora;
	}

	public final String getSemestre() {
		return semestre;
	}

	public final String getQtdVagas() {
		return qtdVagas;
	}
	
	protected void finalize() throws Throwable {
		System.out.println("Destruindo objeto: " + this);
	}
	
	public int VagasLivre() {
		//qtdVagas - vagasOculpadas;
		return 0;
	}
	public String toString() {
		StringBuilder resposta = new StringBuilder();
		resposta.append(super.toString());
		resposta.append("Codigo: " + codigo + '\n');
		return resposta.toString();
	}
}
