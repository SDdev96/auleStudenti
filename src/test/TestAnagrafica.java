package test;

import java.io.IOException;
import java.time.LocalDate;

import persone.Studente;
import report.AnagraficaStudenti;
import report.RegistroPresenze;

public class TestAnagrafica {
    public static void main(String arg[]) throws IOException {
        AnagraficaStudenti a = new AnagraficaStudenti("OOP23");

        RegistroPresenze rp = new RegistroPresenze("OOP23", a);

        a.aggiungi(new Studente("nome1", "cognome1", "codFiscale1", "matricola1"));
        a.aggiungi(new Studente("nome2", "cognome2", "codFiscale2", "matricola2"));
        a.aggiungi(new Studente("nome3", "cognome3", "codFiscale3", "matricola3"));
        // a.aggiungi(new Studente("nome4", "cognome4", "codFiscale4", "matricola4"));
        // a.aggiungi(new Studente("nome5", "cognome5", "codFiscale5", "matricola5"));

        // System.out.println(a);
        // System.out.println(a.cerca("matricola1"));

        rp.rileva(new Studente("nome1", "cognome1", "codFiscale1", "001"), LocalDate.now());
        rp.rileva(new Studente("nome2", "cognome2", "codFiscale2", "002"), LocalDate.now());
        rp.rileva(new Studente("nome3", "cognome3", "codFiscale3", "003"), LocalDate.now());

        rp.rileva(new Studente("nome2", "cognome2", "codFiscale2", "002"), LocalDate.of(2023, 11, 3));
        rp.rileva(new Studente("nome3", "cognome3", "codFiscale3", "003"), LocalDate.of(2023, 11, 3));

        // rp.stampaPresenze("matricola1");
        // rp.stampaPresenze("matricola2");
        // rp.stampaPresenze("matricola3");

        // rp.stampaPresenza(LocalDate.now());
        // rp.stampaPresenza(LocalDate.of(2023, 11, 3));

        // System.out.println(rp);

        // a.scriviCSV();
        // System.out.println(AnagraficaStudenti.leggiCSV("OOP23"));

        rp.scriviCSV();

        RegistroPresenze rpp = RegistroPresenze.leggiCSV("OOP231");
        System.out.println(RegistroPresenze.leggiCSV("OOP231"));
        System.out.println(rpp.getAnagrafica());
    }
}
