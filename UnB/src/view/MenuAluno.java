package view;

import javax.swing.JOptionPane;

import app.Aluno;
import cadastros.CadastroAluno;
import model.exception.DmException;

public class MenuAluno {

	public static Aluno dadosNovoAluno() throws DmException {
		JOptionPane.showMessageDialog(null, "Todos os campos são obrigatorios!");
		String nome = lerNome();
//		if (nome.isEmpty()) {
//			throw new DmException("Campo nome em branco, digite novamente");
//		}
		String cpf = lerCPF();
		String email = lerEmail();
		String matricula = lerMatricula();
		String curso = lerCurso();
		if (nome.isEmpty() || cpf.isEmpty()|| email.isEmpty() || matricula.isEmpty() || curso.isEmpty())
			throw new DmException("A um campo em branco");
		return new Aluno(nome, cpf, email, matricula, curso);
	}

	private static String lerCurso() {
		return JOptionPane.showInputDialog("Informe o curso do aluno: ");
	}

	private static String lerEmail() {
		return JOptionPane.showInputDialog("Informe o email do aluno: ");
	}

	private static String lerCPF() {
		return JOptionPane.showInputDialog("Informe o CPF do aluno: ");
	}

	private static String lerNome() throws DmException {
		String nome = JOptionPane.showInputDialog("Informe o nome do aluno: ");
		if (nome.isEmpty()) {
			throw new DmException("Campo nome em branco, digite novamente");
		}
		return nome;
	}

	private static String lerMatricula() {
		return JOptionPane.showInputDialog("Informe a matricula do aluno: ");
	}

	public static void menuAluno(CadastroAluno cadAluno) throws DmException {
		String txt = "Informe a opção desejada \n" + "1 - Cadastrar aluno\n" + "2 - Pesquisar aluno\n"
				+ "3 - Atualizar aluno\n" + "4 - Remover aluno\n" + "0 - Voltar para menu anterior";

		int opcao = -1;
		do {
			try {
				String strOpcao = JOptionPane.showInputDialog(txt,JOptionPane.QUESTION_MESSAGE);
				opcao = Integer.parseInt(strOpcao);

				switch (opcao) {
				case 1:
					Aluno novoAluno = dadosNovoAluno();
					cadAluno.cadastrarAluno(novoAluno);
					break;

				case 2:
					String matricula = lerMatricula();
					Aluno a = cadAluno.pesquisarAluno(matricula);
					if (a != null) {
						JOptionPane.showMessageDialog(null, a.toString());
						System.out.println(a.toString());
					} else {
						JOptionPane.showMessageDialog(null, "Matricula incorreta ou não existe.");
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

				default:
					JOptionPane.showMessageDialog(null, "Nenhuma opcao valida.\n" + "Tente novamente!");
					opcao = -1;

					break;
				}
			} catch (DmException e) {
				JOptionPane.showMessageDialog(null, "Opcao em branco!");
				opcao = -1;
			}

		} while (opcao != 0);
		return;
	}
}
