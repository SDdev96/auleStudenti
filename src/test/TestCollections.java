package test;

import java.util.ArrayList;
import java.util.List;

import persone.*;
import report.Anagrafica;
import report.CognomeComparator;

public class TestCollections {
    public static void main(String arg[]) {
        Anagrafica a = new Anagrafica("OOP23", new CognomeComparator());

        a.aggiungi(new Studente("Mario", "Rossi", "MRS001", "1"));
        a.aggiungi(new Studente("Luca", "Rossi", "DRS001", "1"));
        a.aggiungi(new Studente("Salvatore", "D'Ambrosio", "ARS001", "2"));

        List<Persona> elenco = new ArrayList<>();

        elenco.add(new Studente("Salvatore", "D'Ambrosio", "ARS001", "2"));
        elenco.add(new Studente("Mario", "Rossi", "MRS001", "1"));
        elenco.add(new Studente("Luca", "Rossi", "DRS001", "1"));

        elenco.sort(new CognomeComparator());

        System.out.println(elenco);

    }
}
