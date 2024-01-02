package spazi;

import persone.Persona;
import report.eccezioni.*;

public abstract class Spazio {

    private String nome;
    public final int numPosti;

    public Spazio(String nome, int numPosti) {
        this.nome = nome;

        if (numPosti < 1)
            throw new IllegalArgumentException("numPosti deve essere un valore intero positivo.");

        this.numPosti = numPosti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract void entra(Persona p) throws SpazioException;

    public abstract Persona esce() throws SpazioException;

    @Override
    public String toString() {
        return "Spazio" + "\nNome:" + nome + "\nCapienza=" + numPosti + '\n';
    }

}
