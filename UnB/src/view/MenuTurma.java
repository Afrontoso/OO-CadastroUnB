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
	private static List<Turma> listaTurmas = new ArrayList<>();

	private static CadastroProfessor cadastroProfessor;
	private static CadastroDisciplina cadastroDisciplina;
	private static CadastroAluno cadastroAluno;
	

	public static void setCadastros(CadastroProfessor cadProf, CadastroDisciplina cadDisc, CadastroAluno cadAluno) {
		cadastroProfessor = cadProf;
		cadastroDisciplina = cadDisc;
		cadastroAluno = cadAluno;
	}

	public static Turma dadosNovaTurma()
			throws CampoEmBrancoException, ProfessorNaoAtribuidoException, DisciplinaNaoAtribuidaException {
		JOptionPane.showMessageDialog(null, "Todos os campos são obrigatorios!");
		String codigo = lerCodigo();
		String diaHora = lerDiaHora();
		String semestre = lerSemestre();
		int qtdVagas = lerVagas();

		Professor professor = lerProfessor();
		Disciplina disciplina = lerDisciplina();

		List<Aluno> alunos = lerAlunos(qtdVagas);
		return new Turma(codigo, professor, disciplina, diaHora, semestre, qtdVagas, alunos);
	}

	private static String lerCodigo() throws CampoEmBrancoException {
		String codigo = JOptionPane.showInputDialog("Informe o código da turma: ");
		if (codigo.isEmpty()) {
			throw new CampoEmBrancoException("CÓDIGO");
		}
		return codigo;
	}

	private static String lerDiaHora() throws CampoEmBrancoException {
		String diaHora = JOptionPane.showInputDialog("Informe o dia é a hora da turma: ");
		if (diaHora.isEmpty()) {
			throw new CampoEmBrancoException("DIA E HORA");
		}
		return diaHora;
	}

	private static String lerSemestre() throws CampoEmBrancoException {
		String semestre = JOptionPane.showInputDialog("Informe o semestre da turma: ");
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

	private static Disciplina lerDisciplina() throws DisciplinaNaoAtribuidaException {
		String codigoDisciplina = JOptionPane
				.showInputDialog("Informe o código da disciplina:\n " + cadastroDisciplina.toString());
		Disciplina disciplina = cadastroDisciplina.pesquisarDisciplina(codigoDisciplina);
		if (disciplina == null) {
			throw new DisciplinaNaoAtribuidaException("Disciplina não cadastrada.");
		}
		return disciplina;
	}

	private static Professor lerProfessor() throws ProfessorNaoAtribuidoException {
		String matriculaFUB = JOptionPane
				.showInputDialog("Informe a matrícula do professor(a):\n " + cadastroProfessor.toString());
		Professor professor = cadastroProfessor.pesquisarProfessor(matriculaFUB);
		if (professor == null) {
			throw new ProfessorNaoAtribuidoException("Professor não cadastrado.");
		}
		return professor;
	}

	private static List<Aluno> lerAlunos(int qtdVagas) {
		List<Aluno> alunos = new ArrayList<>();
		int vagasDisponiveis = qtdVagas;

		for (int i = 0; i < qtdVagas; i++) {
			String matriculaAluno = JOptionPane
					.showInputDialog("Informe a matrícula do aluno (ou deixe em branco para finalizar): à "
							+ vagasDisponiveis + " vagas!\n" + cadastroAluno.toString());

			if (matriculaAluno.isEmpty()) {
				break;
			}
			Aluno aluno = cadastroAluno.pesquisarAluno(matriculaAluno);
			if (aluno == null) {
				JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
				i--; // Repetir a iteração para substituir a entrada inválida.
			} else {
				alunos.add(aluno);
				vagasDisponiveis--;
			}
		}
		return alunos;
	}
	
	public static void imprimirListaPresenca() {
		String codigoTurma = JOptionPane.showInputDialog("Digite o código da turma:");
        Turma turmaEncontrada = null;
        
        // Procurando a turma na lista
        for (Turma turma : listaTurmas) {
            if (turma.getCodigo().equals(codigoTurma)) {
                turmaEncontrada = turma;
                break;
            }
        }

        if (turmaEncontrada != null) {
            StringBuilder listaPresenca = new StringBuilder();
            listaPresenca.append("Disciplina: ").append(turmaEncontrada.getDisciplina().getNome()).append("\n");
            listaPresenca.append("Professor: ").append(turmaEncontrada.getProfessor().getNome()).append("\n");
            listaPresenca.append("Código da Turma: ").append(turmaEncontrada.getCodigo()).append("\n");
            listaPresenca.append("Alunos:\n");

            for (Aluno aluno : turmaEncontrada.getAlunos()) {
                listaPresenca.append("Matrícula: ").append(aluno.getMatricula()).append(", Nome: ").append(aluno.getNome()).append("\n");
            }

            JOptionPane.showMessageDialog(null, listaPresenca.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Turma não encontrada!");
        }
	}

	
	
	

	public static void menuTurma(CadastroTurma cadTurma)
			throws CampoEmBrancoException, DisciplinaNaoAtribuidaException, ProfessorNaoAtribuidoException {
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
					listaTurmas.add(novaTurma);
					boolean b = cadTurma.cadastrarTurma(novaTurma);
					if (b) {
						JOptionPane.showMessageDialog(null, "MATRICULA CONCLUIDA\nTURMA: " + novaTurma.getCodigo()
								+ "\nSEMESTRE: " + novaTurma.getSemestre());
					}
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
				case 5:
					
					//JOptionPane.showMessageDialog(null, "Lista de Turmas cadastradas\n" + cadTurma.toString());
					imprimirListaPresenca();
					break;
					
				case 0:
					return;
					
				default:
					JOptionPane.showMessageDialog(null, "Nenhuma opcao valida.\n" + "Tente novamente!");
					opcao = -1;
					break;
					
				}
			} catch (ProfessorNaoAtribuidoException e) {
				JOptionPane.showMessageDialog(null, "Nenhum professor associado a turma");
			} catch (DisciplinaNaoAtribuidaException e) {
				JOptionPane.showMessageDialog(null, "Nenhuma disciplina associado a turma");
			} catch (CampoEmBrancoException e) {
				JOptionPane.showMessageDialog(null,
						"Opção em branco:\nCampo " + e.getMessage() + " esta em branco, tente novamente novamente");
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Opção invalida");
				opcao = -1;
			}
		} while (opcao != 0);
		return;
	}

}
