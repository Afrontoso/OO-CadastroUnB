package cadastros;

import java.util.ArrayList;
import java.util.List;

import app.Aluno;

public class Cadastro<T> {
	private List<T> lista;

    public Cadastro() {
        this.lista = new ArrayList<>();
    }
    
    public List<T> getLista() {
    	return lista;
    }

    public boolean cadastrar(T item) {
    	boolean cadastrou = lista.add(item);
		return cadastrou;
    }

    public Aluno pesquisarAluno(String matricula) {
		for (T t: lista) {
			if (t. getMatricula().equalsIgnoreCase(matricula)) {
				return t;
			}
		}
		return null;
	}
   
    public boolean remover(T item) {
		boolean removeu = false; 
		if (lista.contains(item)) {
			removeu = lista.remove(item);
		}
		return removeu;
	
	}

    public T buscar(int index) {
        if (index >= 0 && index < lista.size()) {
            return lista.get(index);
        } else {
            throw new IndexOutOfBoundsException("Índice fora dos limites");
        }
    }

}
