package cadastros;

import java.util.ArrayList;
import java.util.List;

public class Cadastro<T> {
	private List<T> lista;

    public Cadastro() {
        this.lista = new ArrayList<>();
    }

    public void adicionar(T item) {
        lista.add(item);
    }

    public List<T> listar() {
        return lista;
    }

    public T buscar(int index) {
        if (index >= 0 && index < lista.size()) {
            return lista.get(index);
        } else {
            throw new IndexOutOfBoundsException("Índice fora dos limites");
        }
    }

}
