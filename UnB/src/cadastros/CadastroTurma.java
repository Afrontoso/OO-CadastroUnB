package cadastros;

import java.util.ArrayList;
import java.util.List;

import app.Turma;


public class CadastroTurma extends Cadastro{
	int numTurmas;
	private List<Turma> turmas;
	
	public CadastroTurma() {
		this.numTurmas = 0;
		this.turmas = new ArrayList<Turma>();
		
	}
	
	public List<Turma> getTurmas() {
		return turmas;
	}
	
	public boolean cadastrarTurma(Turma t) {
		boolean cadastrou = turmas.add(t);
		if (cadastrou) {
			numTurmas = turmas.size();//mostra o tamanho da lista
		}
		return cadastrou;
	}

	public Turma pesquisarTurma(String codigo) {
		for (Turma t: turmas) {
			if (t.getCodigo().equalsIgnoreCase(codigo)) {//dentro do loop pega as matricula dos alunos e compara como a matricula digitada ignortando a caixa alta
				return t;//retorna o aluno que for igual
			}
		}
		return null;
	}
	
	public boolean removerTurma(Turma t) {
		boolean removeu = false; 
		if (turmas.contains(t)) {//ve se contem o objeto na lista
			removeu = turmas.remove(t);//remove o aluno com aquela matricula
		}
		return removeu;
	}

	public boolean atualizarTurma(String codigo, Turma t) {
		boolean resposta = false;
		Turma remover = pesquisarTurma(codigo);
		if(remover != null) {
			turmas.remove(remover);//remove o elemento que quer se atualizar da lista
			resposta = turmas.add(t);// e adiciona o elemento atualizado na lista
		}
		return resposta;
	}
	@Override
	public String toString() {
		String listaTurmas = null;
		for(Turma t : turmas) {
			listaTurmas += t.toString() + "\n";
		}
		return listaTurmas;
	}
	

}
