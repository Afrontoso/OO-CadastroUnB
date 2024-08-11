package cadastros;

import java.util.ArrayList;
import java.util.List;

import app.Turma;

public class CadastroTurma extends Cadastro<Turma> {
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
			numTurmas = turmas.size();// mostra o tamanho da lista
		}
		return cadastrou;
	}

	public Turma pesquisarTurma(String codigo) {
		for (Turma t : turmas) {
			if (t.getCodigo().equalsIgnoreCase(codigo)) {
				return t;
			}
		}
		return null;
	}

	public boolean removerTurma(Turma t) {
		boolean removeu = false;
		if (turmas.contains(t)) {
			removeu = turmas.remove(t);
		}
		return removeu;
	}

	public boolean atualizarTurma(String codigo, Turma t) {
		boolean resposta = false;
		Turma remover = pesquisarTurma(codigo);
		if (remover != null) {
			turmas.remove(remover);
			resposta = turmas.add(t);
		}
		return resposta;
	}

	@Override
	public String toString() {
		StringBuilder resposta = new StringBuilder();
		for (Turma t : turmas) {
			resposta.append(t.toString()).append("\n");
		}
		return resposta.toString();
	}
}
