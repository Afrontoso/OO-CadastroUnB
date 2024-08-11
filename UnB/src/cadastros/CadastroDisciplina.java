package cadastros;

import java.util.ArrayList;
import java.util.List;

import app.Disciplina;

public class CadastroDisciplina extends Cadastro<Disciplina> {
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
		boolean cadastrou = disciplinas.add(d);
		if (cadastrou) {
			numDisciplinas = disciplinas.size();
		}
		return cadastrou;
	}

	public Disciplina pesquisarDisciplina(String codigo) {
		for (Disciplina p : disciplinas) {
			if (p.getCodigo().equalsIgnoreCase(codigo)) {
				return p;
			}
		}
		return null;
	}

	public boolean removerDisciplina(Disciplina d) {
		boolean removeu = false;
		if (disciplinas.contains(d)) {
			removeu = disciplinas.remove(d);
		}
		return removeu;
	}

	public boolean atualizarDisciplina(String codigo, Disciplina d) {
		boolean resposta = false;
		Disciplina remover = pesquisarDisciplina(codigo);
		if (remover != null) {
			disciplinas.remove(remover);
			resposta = disciplinas.add(d);
		}
		return resposta;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Disciplina d : disciplinas) {
			sb.append(d.toString() + "\n");
		}
		return sb.toString();
	}
}
