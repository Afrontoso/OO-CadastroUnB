package principal;

import javax.swing.JOptionPane;

import cadastros.*;
import model.exception.CampoEmBrancoException;
import model.exception.DisciplinaNaoAtribuidaException;
import model.exception.ProfessorNaoAtribuidoException;
import view.*;

public class Principal {

	static CadastroAluno cadAluno;
	static CadastroProfessor cadProf;
	static CadastroDisciplina cadDisciplina;
	static CadastroTurma cadTurma;

	public static void main(String[] args)
			throws CampoEmBrancoException, DisciplinaNaoAtribuidaException, ProfessorNaoAtribuidoException {

		cadAluno = new CadastroAluno();
		cadProf = new CadastroProfessor();
		cadDisciplina = new CadastroDisciplina();
		cadTurma = new CadastroTurma();

		CadastroProfessor cadastroProfessor = cadProf;
		CadastroDisciplina cadastroDisciplina = cadDisciplina;
		CadastroAluno cadastroAluno = cadAluno;
		MenuTurma.setCadastros(cadastroProfessor, cadastroDisciplina, cadastroAluno);

		int opcao = 0;

		do {
			try {

				opcao = MenuPrincipal.menuOpcoes();
				switch (opcao) {
				case 1:
					MenuAluno.menuAluno(cadAluno);
					break;
				case 2:
					MenuProfessor.menuProfessor(cadProf);
					break;
				case 3:
					MenuDisciplina.menuDisciplina(cadDisciplina);
					break;
				case 4:
					MenuTurma.menuTurma(cadTurma);
					break;
				case 0:
					JOptionPane.showMessageDialog(null, "Fechando...");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opção invalida");
					opcao = -1;
					break;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Opção invalida");
				opcao = -1;
			}

		} while (opcao != 0);
		return;

	}

}
