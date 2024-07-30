package cadastros;

import java.util.List;
import java.util.ArrayList;

import app.Aluno;

public class CadastroAluno {
	int numAlunos; 
	private List<Aluno> alunos;
	
	public CadastroAluno() {
		numAlunos = 0;
		alunos = new ArrayList<Aluno>();
		
	}
	
	public int cadastrarAluno(Aluno a) {
		boolean cadastrou = alunos.add(a);//adiciona um elementro na lista e retorna um booleano se add
		if (cadastrou) {
			numAlunos = alunos.size();//mostra o tamanho da lista
		}
		return numAlunos;
	}
	
	public Aluno pesquisarAluno(String matriculaAluno) {
		for (Aluno a: alunos) {
			if (a.getMatricula().equalsIgnoreCase(matriculaAluno)) {//dentro do loop pega as matricula dos alunos e compara como a matricula digitada ignortando a caixa alta
				return a;//retorna o aluno que for igual
			}
		}
		return null;//retorna nulo ser não achar
	}
	
	public boolean removerAluno(Aluno a) {
		boolean removeu = false; 
		if (alunos.contains(a)) {//ve se contem o objeto na lista
			removeu = alunos.remove(a);//remove o aluno com aquela matricula
		}
		return removeu;
	
	}
	
	public boolean atualizarAluno(String matricula, Aluno a) {
		boolean resposta = false;
		Aluno remover = pesquisarAluno(matricula);
		if(remover != null) {
			alunos.remove(remover);//remove o elemento que quer se atualizar da lista
			resposta = alunos.add(a);// e adiciona o elemento atualizado na lista
		}
		return resposta;
	}
}
