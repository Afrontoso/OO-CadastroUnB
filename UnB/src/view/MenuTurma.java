package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import app.*;
import cadastros.*;
import model.exception.CampoEmBrancoException;
import model.exception.DisciplinaNaoAtribuidaException;
import model.exception.ProfessorNaoAtribuidoException;

public class MenuTurma {

	// int numTurmas;
//	private List<Turma> turma;

	private static CadastroProfessor cadastroProfessor;
	private static CadastroDisciplina cadastroDisciplina;
	private static CadastroAluno cadastroAluno;

//	public MenuTurma(CadastroProfessor cadastroProfessor, CadastroDisciplina cadastroDisciplina, CadastroAluno cadastroAluno) {
//        this.cadastroProfessor = cadastroProfessor;
//        this.cadastroDisciplina = cadastroDisciplina;
//        this.cadastroAluno = cadastroAluno;
//    }

	public static void setCadastros(CadastroProfessor cadProf, CadastroDisciplina cadDisc, CadastroAluno cadAluno) {
		cadastroProfessor = cadProf;
		cadastroDisciplina = cadDisc;
		cadastroAluno = cadAluno;
	}

	public static Turma dadosNovaTurma() throws CampoEmBrancoException, ProfessorNaoAtribuidoException, DisciplinaNaoAtribuidaException {
		JOptionPane.showMessageDialog(null, "Todos os campos são obrigatorios!");
		String codigo = lerCodigo();
		String diaHora = lerDiaHora();
		String semestre = lerSemestre();
		int qtdVagas = lerVagas();

		Professor professor = lerProfessor();
		Disciplina disciplina = lerDisciplina();
//		if (professor == null) {
//			throw new ProfessorNaoAtribuidoException("Nenhum Professor associado a turma");
//		}
//		if (disciplina == null) {
//			throw new DisciplinaNaoAtribuidaException("Nenhuma Disciplina associado a turma");
//		}

		List<Aluno> alunos = lerAlunos(qtdVagas);

		return new Turma(codigo, professor, disciplina, diaHora, semestre, qtdVagas, alunos);
	}

	private static String lerCodigo() throws CampoEmBrancoException {
		String codigo = JOptionPane.showInputDialog("Informe o codigo da turma: ");
		if (codigo.isEmpty()) {
			throw new CampoEmBrancoException("CODIGO");
		}
		return codigo;
	}

	private static String lerDiaHora() throws CampoEmBrancoException {
		String diaHora = JOptionPane.showInputDialog("Informe o dia e a hora da turma: ");
		if (diaHora.isEmpty()) {
			throw new CampoEmBrancoException("DIA E HORA");
		}
		return diaHora;
	}

	private static String lerSemestre() throws CampoEmBrancoException {
		String semestre = JOptionPane.showInputDialog("Informe o semestra da turma: ");
		if (semestre.isEmpty()) {
			throw new CampoEmBrancoException("SEMESTRE");
		}
		return semestre;
	}

	private static int lerVagas() throws CampoEmBrancoException {
		String strVagas = JOptionPane.showInputDialog("Informe a quantidade de vagas da turma: ");
		if (strVagas.isEmpty()) {
			throw new CampoEmBrancoException("VAGAS");
		}
		return Integer.parseInt(strVagas);
	}

	private static Disciplina lerDisciplina() throws DisciplinaNaoAtribuidaException{
		String codigoDisciplina = JOptionPane
				.showInputDialog("Informe o código da disciplina:\n " + cadastroDisciplina.getDisciplinas());
		Disciplina disciplina = cadastroDisciplina.pesquisarDisciplina(codigoDisciplina);
		if (disciplina == null) {
			throw new DisciplinaNaoAtribuidaException("Disciplina não cadastrada.");
		}
		return disciplina;
	}

	private static Professor lerProfessor() throws ProfessorNaoAtribuidoException {
		String matriculaFUB = JOptionPane
				.showInputDialog("Informe a matrícula do professor:\n " + cadastroProfessor.getProfessores());
		Professor professor = cadastroProfessor.pesquisarProfessor(matriculaFUB);
		if (professor == null) {
			throw new ProfessorNaoAtribuidoException("Professor não cadastrado.");
		}
		return professor;
	}

	private static List<Aluno> lerAlunos(int qtdVagas) {
		List<Aluno> alunos = new ArrayList<>();
		for (int i = 0; i < qtdVagas; i++) {
			String matriculaAluno = JOptionPane.showInputDialog(
					"Informe a matrícula do aluno (ou deixe em branco para finalizar): " + cadastroAluno.getAlunos());
			if (matriculaAluno.isEmpty()) {
				break;
			}
			Aluno aluno = cadastroAluno.pesquisarAluno(matriculaAluno);
			if (aluno == null) {
				JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
				i--; // Repetir a iteração para substituir a entrada inválida.
			} else {
				alunos.add(aluno);
			}
		}
		System.out.println(alunos);
		return alunos;
	}

	public static void menuTurma(CadastroTurma cadTurma) throws CampoEmBrancoException, DisciplinaNaoAtribuidaException, ProfessorNaoAtribuidoException {
		String txt = "Informe a opção desejada \n" + "1 - Cadastrar turma\n" + "2 - Pesquisar turma\n"
				+ "3 - Atualizar turma\n" + "4 - Remover turma\n" + "0 - Voltar para menu anterior";

		int opcao = -1;
		do {
			try {

				String strOpcao = JOptionPane.showInputDialog(txt);
				opcao = Integer.parseInt(strOpcao);

				switch (opcao) {
				case 1:
					Turma novaTurma = dadosNovaTurma();
					cadTurma.cadastrarTurma(novaTurma);
					break;

				case 2:
					String codigo = lerCodigo();
					Turma t = cadTurma.pesquisarTurma(codigo);
					if (t != null) {
						JOptionPane.showMessageDialog(null, t.toString());
					} else {
						JOptionPane.showMessageDialog(null, "Codigo incorreta ou não existe.");
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
				case 0:
					return;
				default:
					JOptionPane.showMessageDialog(null, "Nenhuma opcao valida.\n" + "Tente novamente!");
					opcao = -1;

					break;
				}
			}catch (ProfessorNaoAtribuidoException e) {
				JOptionPane.showMessageDialog(null, "Nenhum professor associado a turma");
			}catch (DisciplinaNaoAtribuidaException e) {
				JOptionPane.showMessageDialog(null, "Nenhuma disciplina associado a turma");
			} catch (CampoEmBrancoException e) {
				JOptionPane.showMessageDialog(null, "Opção em branco:\nCampo " + e.getMessage() + " esta em branco, tente novamente novamente");
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Opção invalida");
				opcao = -1;
			}
		} while (opcao != 0);
		return;
	}

}
