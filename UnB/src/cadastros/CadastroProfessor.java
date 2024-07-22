package cadastros;

import app.Professor;

public class CadastroProfessor {
	int numProfessores;
	private Professor[] professores;

	// contrutor iniciando uma array de professor
	public CadastroProfessor() {
		numProfessores = 0;
		professores = new Professor[0];
	}

	public int cadastrarProfessor(Professor p) {
		Professor[] temp = new Professor[numProfessores + 1];
		// Copia do array Professor para a referencia temp
		for (int i = 0; i < professores.length; i++) {
			temp[i] = professores[i];
		}
		// acrescentar professor cadastrado no array temp
		temp[temp.length - 1] = p;
		// passar o array temporario para o array professores
		professores = temp;
		numProfessores++;
		return numProfessores;
	}

	public Professor pesquisarProfessor(String matriculaFUB) {
		Professor pesquisa = null;
		for (int i = 0; i < professores.length; i++) {
			Professor p = professores[i];
			String matFUB = p.getMatriculaFUB();
			if (matFUB.equalsIgnoreCase(matriculaFUB)) {
				return professores[i];
			}
		}
		return pesquisa;
	}

	public boolean removerProfessor(Professor p) {
		Professor remover = null;
		Professor[] temp = new Professor[numProfessores - 1];

		if (p != null) {
			remover = pesquisarProfessor(p.getMatriculaFUB());

			int j = 0;
			for (int i = 0; i < numProfessores; i++) {
				if (professores[i].getMatriculaFUB() != remover.getMatriculaFUB()) {
					temp[j] = professores[i];
					j++;
				} else {
					professores[i] = null;
				}
			}
			professores = temp;
			numProfessores--;
			return true;
		}

		return false;
	}
	
	public boolean atualizarProfessor(String matriculaFUB, Professor p) {
		int i;
		for (i = 0; i< professores.length; i++) {
			if (professores[i].getMatriculaFUB().equalsIgnoreCase(matriculaFUB)) {
				break;
			}
		}
		if (i > numProfessores) {
			return false;
		} else {
			professores[i] = p;
		}
		return true;	
		
	}
}
