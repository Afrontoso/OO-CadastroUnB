package app;

import java.util.List;

public class Turma {
	String codigo, diaHora, semestre;
	int qtdVagas;
	
	Professor professor;
	Disciplina disciplina;
	private List<Aluno> alunos;
	
	public Turma(String codigo, Professor professor, Disciplina disciplina, String diaHora, String semestre, int qtdVagas, List<Aluno> alunos) {
		this.codigo = codigo;
		this.diaHora = diaHora;
		this.semestre = semestre;
		this.qtdVagas = qtdVagas;
		this.professor = professor;
		this.disciplina = disciplina;
		
		this.alunos = alunos;
	}
	
//	public Turma(String codigo, Professor professor, Disciplina disciplina, String diaHora, String semestre, int qtdVagas) {
//		this.codigo = codigo;
//		this.diaHora = diaHora;
//		this.semestre = semestre;
//		this.qtdVagas = qtdVagas;
//		this.professor = professor;
//		this.disciplina = disciplina;
//	}
	
//	public boolean adicionarAluno(Aluno aluno) {
//        if (alunos.size() < qtdVagas) {
//            alunos.add(aluno);
//            return true;
//        }
//        return false;
//    }
	

	public final String getCodigo() {
		return codigo;
	}

	public final String getDiaHora() {
		return diaHora;
	}

	public final String getSemestre() {
		return semestre;
	}

	public final int getQtdVagas() {
		return qtdVagas;
	}
	
	public final List<Aluno> getAlunos() {
		return alunos;
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
		resposta.append(super.toString() + "\n");
		resposta.append(disciplina.getDepartamento() + " - ");
		resposta.append(disciplina.getNome() + " - ");
		resposta.append(codigo);
		resposta.append('(' + semestre  + " - " + diaHora + ")\n");
		resposta.append(getAlunos());
	
		return resposta.toString();
	}
	
	public String toString(Aluno alunos) {
		StringBuilder resposta = new StringBuilder();
		resposta.append(super.toString() + "\n");
		resposta.append(disciplina.getDepartamento() + " - ");
		resposta.append(disciplina.getNome() + " - ");
		resposta.append(codigo);
		resposta.append('(' + semestre  + " - " + diaHora + ")\n");
		resposta.append(alunos.toStringAlt());
			
		
		return resposta.toString();
	}
}
