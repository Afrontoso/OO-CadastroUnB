package view;

import javax.swing.JOptionPane;

import app.Turma;
import cadastros.CadastroTurma;

public class MenuTurma {

	public static Turma dadosNovaTurma() {
		String codigo = lerCodigo();
		String professor = lerProfessor();
		String disciplina = lerDiciplina();
		String diaHora = lerDiaHora();
		String semestre = lerSemestre();
		String vagas = lerVagas();
		String alunos = lerTurmas();//acho que aqui vai ser o ArryList
		return new Turma(codigo, professor, disciplina, diaHora, semestre, vagas, alunos);
	}
	
	private static String lerCodigo() {
		return JOptionPane.showInputDialog("Informe o codigo da turma: ");
	}

	private static String lerProfessor() {
		return JOptionPane.showInputDialog("Informe a codigoFUB do professor que administrara a turma: ");
	}

	private static String lerDiciplina() {
		return JOptionPane.showInputDialog("Informe qual é a materia da turma: ");
	}

	private static String lerDiaHora() {
		return JOptionPane.showInputDialog("Informe o dia e a hora da turma: ");
	}

	private static String lerSemestre() {
		return JOptionPane.showInputDialog("Informe o semestre da turma: ");
	}
	private static String lerVagas() {
		return JOptionPane.showInputDialog("Informe o numero de vagas da turma: ");
	}
	private static String lerTurmas() {
		return JOptionPane.showInputDialog("Informe os alunos codigodos na turma: ");
	}

	public static void menuTurma(CadastroTurma cadTurma) {
		String txt = "Informe a opção desejada \n" + "1 - Cadastrar turma\n" + "2 - Pesquisar turma\n"
				+ "3 - Atualizar turma\n" + "4 - Remover turma\n" + "0 - Voltar para menu anterior";

		int opcao = -1;
		do {
			String strOpcao = JOptionPane.showInputDialog(txt);
			opcao = Integer.parseInt(strOpcao);

			switch (opcao) {
			case 1:
				Turma novaTurma = dadosNovaTurma();
				cadTurma.cadastrarTurma(novaTurma);
				break;

			case 2:
				String codigo = lerCodigo();
				Turma a = cadTurma.pesquisarTurma(codigo);
				if (a != null) {
					JOptionPane.showMessageDialog(null, a.toString());
				}else {
					JOptionPane.showMessageDialog(null, "Cogigo incorreta ou não existe.");
				}
				break;

			case 3:
				codigo = lerCodigo();
				Turma novoCadastro = dadosNovaTurma();
				boolean atualizado = cadTurma.atualizarTurma(codigo, novoCadastro);
				if (atualizado) {
					JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
				}
				break;

			case 4:
				codigo = lerCodigo();
				Turma remover = cadTurma.pesquisarTurma(codigo);
				boolean removido = cadTurma.removerTurma(remover);
				if (removido) {
					JOptionPane.showMessageDialog(null, "Turma removida do cadastro");
					System.gc();
				}

			default:
				JOptionPane.showMessageDialog(null, "Nenhuma opcao valida.\n"
						+ "Tente novamente!");
				opcao = -1;

				break;
			}
		} while (opcao != 0);
		return;
	}

}
