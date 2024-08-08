package cadastros;

import java.util.ArrayList;
import java.util.List;

import app.Disciplina;

public class CadastroDisciplina {
	int numDisciplinas;
	private List<Disciplina> disciplinas;

	public CadastroDisciplina() {
		numDisciplinas = 0;
		disciplinas = new ArrayList<Disciplina>();
	}
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public boolean cadastrarDisciplina(Disciplina d) {
		boolean cadastrou = disciplinas.add(d);// adiciona um elementro na lista e retorna um booleano se add
		if (cadastrou) {
			numDisciplinas = disciplinas.size();// mostra o tamanho da lista
		}
		return cadastrou;
	}

	public Disciplina pesquisarDisciplina(String codigo) {
		for (Disciplina p : disciplinas) {
			if (p.getCodigo().equalsIgnoreCase(codigo)) {// dentro do loop pega as matricula dos disciplinas e compara
																// como a matricula digitada ignortando a caixa alta
				return p;// retorna o aluno que for igual
			}
		}
		return null;// retorna nulo ser não achar
	}

	public boolean removerDisciplina(Disciplina d) {
		boolean removeu = false;
		if (disciplinas.contains(d)) {// ve se contem o objeto na lista
			removeu = disciplinas.remove(d);// remove o aluno com aquela matricula
		}
		return removeu;

	}

	public boolean atualizarDisciplina(String codigo, Disciplina d) {
		boolean resposta = false;
		Disciplina remover = pesquisarDisciplina(codigo);
		if (remover != null) {
			disciplinas.remove(remover);// remove o elemento que quer se atualizar da lista
			resposta = disciplinas.add(d);// e adiciona o elemento atualizado na lista
		}
		return resposta;
	}
	
	@Override
	public String toString() {
		String listaDisciplinas = null;
		for(Disciplina a : disciplinas) {
			listaDisciplinas += a.toString() + "\n";
		}
		return listaDisciplinas;
	}

}
