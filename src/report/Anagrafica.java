package report;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import persone.Persona;

@SuppressWarnings("all")

public class Anagrafica {
    private String nome;
    private Set<Persona> persone;

    public Anagrafica(String nome) {
        this.nome = nome;
        persone = new HashSet<Persona>();
    }

    public Anagrafica(String nome, Comparator<Persona> c) {
        this.nome = nome;
        persone = new TreeSet<Persona>(c);
    }

    public String getNome() {
        return nome;
    }

    public void aggiungi(Persona p) {
        persone.add(p);
    }

    @Override
    public String toString() {
        return "nome: " + nome + "\npersone:\n" + persone;
    }
}