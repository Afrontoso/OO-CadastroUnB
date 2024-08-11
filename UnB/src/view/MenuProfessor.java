package view;

import javax.swing.JOptionPane;

import app.Professor;
import cadastros.CadastroProfessor;
import model.exception.CampoEmBrancoException;

public class MenuProfessor {

	public static Professor dadosNovoProfessor() throws CampoEmBrancoException {
		JOptionPane.showMessageDialog(null, "Todos os campos são obrigatorios!");
		String nome = lerNome();
		String cpf = lerCPF();
		String email = lerEmail();
		String matriculaFUB = lerMatriculaFUB();
		String areaFormacao = lerAreaFormacao();
		return new Professor(nome, cpf, email, matriculaFUB, areaFormacao);
	}

	private static String lerNome() throws CampoEmBrancoException {
		String nome = JOptionPane.showInputDialog("Informe o nome do(a) professor(a): ");
		if (nome.isEmpty()) {
			throw new CampoEmBrancoException("NOME");
		}
		return nome;
	}

	private static String lerCPF() throws CampoEmBrancoException {
		String CPF = JOptionPane.showInputDialog("Informe o CPF do(a) professor(a): ");
		if (CPF.isEmpty()) {
			throw new CampoEmBrancoException("CPF");
		}
		return CPF;
	}

	private static String lerEmail() throws CampoEmBrancoException {
		String email = JOptionPane.showInputDialog("Informe o e-mail do(a) professor(a): ");
		if (email.isEmpty()) {
			throw new CampoEmBrancoException("E-MAIL");
		}
		return email;
	}

	private static String lerMatriculaFUB() throws CampoEmBrancoException {
		String matricula = JOptionPane.showInputDialog("Informe a matrículaFUB(a) do professor(a): ");
		if (matricula.isEmpty()) {
			throw new CampoEmBrancoException("MATRICULAFUB");
		}
		return matricula;
	}

	private static String lerAreaFormacao() throws CampoEmBrancoException {
		String curso = JOptionPane.showInputDialog("Informe a área de formação do(a) professor(a): ");
		if (curso.isEmpty()) {
			throw new CampoEmBrancoException("ÁREA DE FORMAÇãO");
		}
		return curso;
	}

	public static void menuProfessor(CadastroProfessor cadProfessor) throws CampoEmBrancoException {
		String txt = "Informe a opção desejada \n" + "1 - Cadastrar professor(a)\n" + "2 - Pesquisar professor(a)\n"
				+ "3 - Atualizar professor(a)\n" + "4 - Remover professor(a)\n"
				+ "5 - Lista completa de professores matrículados\n" + "0 - Voltar para menu anterior";

		int opcao = -1;
		do {
			try {
				String strOpcao = JOptionPane.showInputDialog(txt);
				opcao = Integer.parseInt(strOpcao);

				switch (opcao) {
				case 1:
					Professor novoProfessor = dadosNovoProfessor();

					boolean b = cadProfessor.cadastrarProfessor(novoProfessor);
					if (b) {
						JOptionPane.showMessageDialog(null, "MATRICULA CONCLUIDA\nPROFESSOR(A): "
								+ novoProfessor.getNome() + "\nMATRíCULAFUB: " + novoProfessor.getMatriculaFUB());
					}
					break;

				case 2:
					String matriculaFUB = lerMatriculaFUB();
					Professor p = cadProfessor.pesquisarProfessor(matriculaFUB);
					if (p != null) {
						JOptionPane.showMessageDialog(null, p.toString());
						break;
					} else {
						JOptionPane.showMessageDialog(null, "MatrículaFUB incorreta ou não existe.");
						break;
					}

				case 3:
					matriculaFUB = lerMatriculaFUB();
					Professor novoCadastro = dadosNovoProfessor();
					boolean atualizado = cadProfessor.atualizarProfessor(matriculaFUB, novoCadastro);
					if (atualizado) {
						JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
					}
					break;

				case 4:
					matriculaFUB = lerMatriculaFUB();
					Professor remover = cadProfessor.pesquisarProfessor(matriculaFUB);
					boolean removido = cadProfessor.removerProfessor(remover);
					if (removido) {
						JOptionPane.showMessageDialog(null, "Professor(a) removido(a) do cadastro");
						System.gc();
					}
				case 5:
					JOptionPane.showMessageDialog(null,
							"Lista de Professores Matriculados\n" + cadProfessor.toString());// Somente para verificar a
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
						"Opção em branco:\nCampo " + e.getMessage() + " esta em branco, tente novamente novamente");
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Opção invalida");
				opcao = -1;
			}
		} while (opcao != 0);
		return;
	}
}
