package cadastros;

import app.Professor;

public class CadastroProfessor {
	int numProfessores;
	private Professor[] professores;
	
	//contrutor iniciando uma array de professor
	public CadastroProfessor() {
		numProfessores = 0;
		professores = new Professor[0];
	}
	
	public int cadastrarProfessor(Professor p) {
		Professor[] temp = new Professor[numProfessores + 1];
		//Copia do array Professor para a referencia temp
		for (int i=0; i < professores.length; i++) {
			temp[i] = professores[i];
		}
		//acrescentar professor cadastrado no array temp
		temp[temp.length -1] = p;
		//passar o array temporario para o array professores
		professores = temp;
		numProfessores++; 
		return numProfessores;
	}
	public Professor pesquisarProfessor(String matriculoFUB) {
		Professor pesquisa = null;
		for(int i = 0; i < professores.length; i++) {
			Professor p = professores[i];
			String matFUB = p.getMatriculaFUB();
			if (matFUB.equalsIgnoreCase(matriculoFUB)) {
				return professores[i];
			}
		}
		
		
		return pesquisa;
	}
}
