package view;

import java.util.List;

import javax.swing.JOptionPane;

import app.*;

import cadastros.CadastroTurma;
import cadastros.CadastroAluno;
import cadastros.CadastroDisciplina;
import cadastros.CadastroProfessor;

public class MenuTurma {
	int numTurmas; 
	private List<Turma> turma;
	
	private static CadastroProfessor cadastroProfessor;
	private static CadastroDisciplina cadastroDisciplina;
	private static CadastroAluno cadastroAluno;
	
	public static Turma dadosNovaTurma() {
		String codigo = lerCodigo();
		String diaHora = lerDiaHora();
		String semestre = lerSemestre();
		int qtdVagas = lerVagas();
		
		Disciplina disciplina = lerDiciplina();
		Professor professor = lerProfessor();
		
		Aluno[] alunos = lerAlunosTurmas();//acho que aqui vai ser o ArryList
		
		return new Turma(codigo, professor, disciplina, diaHora, semestre, qtdVagas, alunos);
	}
	
	private static String lerCodigo() {
		return JOptionPane.showInputDialog("Informe o codigo da turma: ");
	}

	private static String lerDiaHora() {
		return JOptionPane.showInputDialog("Informe o dia e a hora da turma: ");
	}

	private static String lerSemestre() {
		return JOptionPane.showInputDialog("Informe o semestre da turma: ");
	}
	
	private static int lerVagas() {
		return Integer.parseInt(JOptionPane.showInputDialog("Informe o numero de vagas da turma: "));
	}
	
	private static Disciplina lerDiciplina() {
		Disciplina disciplina = (Disciplina) JOptionPane.showInputDialog(null, "Selecione a disciplina:",
                "Cadastro de Turma", JOptionPane.QUESTION_MESSAGE, null, cadastroDisciplina.getDisciplinas().toArray(), null);
			
		return disciplina;
	}
	
	private static Professor lerProfessor() {
		Professor professor = (Professor) JOptionPane.showInputDialog(null, "Selecione o professor:",
                "Cadastro de Turma", JOptionPane.QUESTION_MESSAGE, null, cadastroProfessor.getProfessores().toArray(), null);
		
		
//		String matriculaFUB = JOptionPane.showInputDialog("Informe a codigoFUB do professor que administrara a turma: ");
//		CadastroProfessor prof;
//		Professor professor = prof.pesquisarProfessor(matriculaFUB);
//		
		return professor;
	}
	
	private static Aluno lerAlunosTurmas() {
		int opcao = JOptionPane.YES_OPTION;
        while (opcao == JOptionPane.YES_OPTION && turma.getAlunos().size() < qtdVagas) {
            opcao = JOptionPane.showConfirmDialog(null, "Deseja cadastrar um novo aluno?", "Cadastro de Alunos", JOptionPane.YES_NO_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                Aluno aluno = (Aluno) JOptionPane.showInputDialog(null, "Selecione o aluno:",
                        "Cadastro de Turma", JOptionPane.QUESTION_MESSAGE, null, cadastroAluno.getAlunos().toArray(), null);
                if (aluno != null) {
                    turma.adicionarAluno(aluno);
                }
            }
        }
        
        
//		int opção = -1;
//		do {
//		JOptionPane.showInputDialog("Informe a matriculas dos alunos da turma: ");
//		}while(opção != 0);
//		
//		retu
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
