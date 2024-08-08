package app;

import java.util.ArrayList;
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
		
		this.alunos = new ArrayList<Aluno>();
	}
	
	public boolean adicionarAluno(Aluno aluno) {
        if (alunos.size() < qtdVagas) {
            alunos.add(aluno);
            return true;
        }
        return false;
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

	public final int getQtdVagas() {
		return qtdVagas;
	}
	
	public final List<Aluno> getAlunos() {
		return alunos;
	}
	
	protected void finalize() throws Throwable {
		System.out.println("Destruindo objeto: " + this);
	}
	
	
	public String toString() {
		StringBuilder resposta = new StringBuilder();
		resposta.append("DISCIPLINA: ");
		resposta.append(disciplina.getNome() + " CODIGO: " + getCodigo());
		resposta.append("\nPROFESSOR(A): ");
		resposta.append(professor.getNome() + "\n");
		resposta.append('(' + semestre  + " - " + diaHora + ")\n");
		
		for(Aluno a : alunos) {
			resposta.append(a);
			resposta.append("NOME: " + a.getNome() + '\n');
			resposta.append("MATRICULA: " + a.getMatricula() + '\n');
			System.out.println("To no loop");
		}
		
		return resposta.toString();
	}
	
//	public String toString(boolean listaPresenca) {
//		StringBuilder resposta = new StringBuilder();
//		for(Aluno a : alunos) {
//			resposta.append(a);
//			resposta.append("NOME: " + a.getNome() + '\n');
//			resposta.append("MATRICULA: " + a.getMatricula() + '\n');
//			
//		}
//		return resposta.toString();
//	}
	
}
