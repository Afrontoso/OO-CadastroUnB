package view;

import javax.swing.JOptionPane;

import app.Aluno;
import cadastros.CadastroAluno;
import model.exception.CampoEmBrancoException;

public class MenuAluno {

	public static Aluno dadosNovoAluno() throws CampoEmBrancoException {
		JOptionPane.showMessageDialog(null, "Todos os campos são obrigatorios!");
		String nome = lerNome();
		String cpf = lerCPF();
		String email = lerEmail();
		String matricula = lerMatricula();
		String curso = lerCurso();
		return new Aluno(nome, cpf, email, matricula, curso);
	}

	private static String lerNome() throws CampoEmBrancoException {
		String nome = JOptionPane.showInputDialog("Informe o nome do aluno: ");
		if (nome.isEmpty()) {
			throw new CampoEmBrancoException("NOME");
		}
		return nome;
	}

	private static String lerCPF() throws CampoEmBrancoException {
		String CPF = JOptionPane.showInputDialog("Informe o CPF do aluno: ");
		if (CPF.isEmpty()) {
			throw new CampoEmBrancoException("CPF");
		}
		return CPF;
	}

	private static String lerEmail() throws CampoEmBrancoException {
		String email = JOptionPane.showInputDialog("Informe o e-mail do aluno: ");
		if (email.isEmpty()) {
			throw new CampoEmBrancoException("E-MAIL");
		}
		return email;
	}

	private static String lerMatricula() throws CampoEmBrancoException {
		String matricula = JOptionPane.showInputDialog("Informe a matrícula do aluno: ");
		if (matricula.isEmpty()) {
			throw new CampoEmBrancoException("MATRÍCULA");
		}
		return matricula;
	}

	private static String lerCurso() throws CampoEmBrancoException {
		String curso = JOptionPane.showInputDialog("Informe o curso do aluno: ");
		if (curso.isEmpty()) {
			throw new CampoEmBrancoException("CURSO");
		}
		return curso;
	}

	public static void menuAluno(CadastroAluno cadAluno) throws CampoEmBrancoException {
		String txt = "Informe a opção desejada \n" + "1 - Cadastrar aluno\n" + "2 - Pesquisar aluno\n"
				+ "3 - Atualizar aluno\n" + "4 - Remover aluno\n" + "5 - Lista de alunos matrículados completa\n"
				+ "0 - Voltar para menu anterior";

		int opcao = -1;
		do {
			try {
				String strOpcao = JOptionPane.showInputDialog(txt);
				opcao = Integer.parseInt(strOpcao);

				switch (opcao) {
				case 1:
					Aluno novoAluno = dadosNovoAluno();
					boolean b = cadAluno.cadastrarAluno(novoAluno);
					if (b) {
						JOptionPane.showMessageDialog(null, "MATRÍCULA CONCLUIDA\nALUNO: " + novoAluno.getNome()
								+ "\nMATRÍCULA: " + novoAluno.getMatricula());
					}
					break;

				case 2:
					String matricula = lerMatricula();
					Aluno a = cadAluno.pesquisarAluno(matricula);
					if (a != null) {
						JOptionPane.showMessageDialog(null, a.toString());
					} else {
						JOptionPane.showMessageDialog(null, "Matrícula incorreta ou não existe.");
					}
					break;

				case 3:
					matricula = lerMatricula();
					Aluno novoCadastro = dadosNovoAluno();
					boolean atualizado = cadAluno.atualizarAluno(matricula, novoCadastro);
					if (atualizado) {
						JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
					}
					break;

				case 4:
					matricula = lerMatricula();
					Aluno remover = cadAluno.pesquisarAluno(matricula);
					boolean removido = cadAluno.removerAluno(remover);
					if (removido) {
						JOptionPane.showMessageDialog(null, "Aluno removido do cadastro");
						System.gc();
					}
					break;
				case 5:
					JOptionPane.showMessageDialog(null, "Lista de Alunos Matrículados:\n" + cadAluno.toString());// Somente
																													// para
																													// verificar
																													// a
																													// lista
					break;
				case 0:
					return;

				default:
					JOptionPane.showMessageDialog(null, "Nenhuma opção valida.\n" + "Tente novamente!");
					opcao = -1;
					break;
				}
			} catch (CampoEmBrancoException e) {
				JOptionPane.showMessageDialog(null,
						"Opção em branco:\nCampo " + e.getMessage() + " esta em branco, tente novamente novamente!");
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Opção inválida");
				opcao = -1;
			}

		} while (opcao != 0);
		return;
	}
}
