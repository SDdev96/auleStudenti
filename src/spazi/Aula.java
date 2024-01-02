package spazi;

import persone.Studente;

public class Aula {
    private Studente studenti[];
    private int sp;

    public Aula(int maxPosti) {
        studenti = new Studente[maxPosti];
        sp = 0;
    }

    public boolean aulaPiena() {
        return sp == studenti.length;
    }

    public boolean aulaVuota() {
        return sp == 0;
    }

    public void entra(Studente s) {
        studenti[sp] = s;
        sp++;
    }

    public Studente esce() {
        Studente ris = studenti[sp - 1];
        studenti[sp - 1] = null;
        sp--;
        return ris;
    }

    public String getInfoAula() {
        String elenco = "";
        for (int i = 0; i < sp; i++) {
            elenco += studenti[i].getInfoStudente();
        }
        return elenco;
    }
}
