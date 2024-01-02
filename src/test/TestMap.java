package test;

import java.io.IOException;
import java.time.LocalDate;

import persone.Studente;
import report.AnagraficaStudenti;
import report.RegistroPresenze;

public class TestMap {
    public static void main(String[] args) throws IOException {

        AnagraficaStudenti a = new AnagraficaStudenti("OOP23_1");
        RegistroPresenze rp = new RegistroPresenze("OOP23_1", a);

        // a.aggiungi(new Studente( "Mario", "Rossi", "MRS001", "061270001"));
        // a.aggiungi(new Studente( "Davide", "Rossi", "DRS001", "061270002"));
        // a.aggiungi(new Studente( "Davide", "Allegri", "ZRS001", "061270003"));
        // se ci fossero due con la stessa matricola, il secondo non verrebbe aggiunto
        // siccome abbiamo usato la funzione putIfAbsent

        // System.out.println(a);
        // System.out.println(a.cerca("061270001"); stamap il valore con la matricola
        // inserita

        rp.rileva(new Studente("Mario", "Rossi", "MRS001", "061270001"), LocalDate.now());
        rp.rileva(new Studente("Davide", "Rossi", "DRS001", "061270002"), LocalDate.now());
        rp.rileva(new Studente("Davide", "Allegri", "ZRS001", "061270003"), LocalDate.now());

        rp.rileva(new Studente("Mario", "Rossi", "MRS001", "061270001"), LocalDate.of(2023, 11, 3));
        rp.rileva(new Studente("Davide", "Rossi", "DRS001", "061270002"), LocalDate.of(2023, 11, 3));

        // rp.stampaPresenza("061270001");
        // rp.stampaPresenza("061270002");
        // rp.stampaPresenza("061270003");

        // rp.stampaPresenze(LocalDate.now());
        System.out.println(rp);

        // lezione I/O
        a.scriviDOS();
        System.out.println(AnagraficaStudenti.leggiDIS("OOP23"));
        rp.scriviDOS();

        RegistroPresenze rp1 = RegistroPresenze.leggiDIS("OOP23_1");
        System.out.println(rp1);
        System.out.println(rp1.getAnagrafica());

        // rp.scriviOBJ();

        // RegistroPresenze rpp = RegistroPresenze.leggiOBJ("OOP23_1");
        // System.out.print(rpp);
        // System.out.println(rpp.getAnagrafica());

    }

}
