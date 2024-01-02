package spazi;

import persone.Persona;
import report.eccezioni.*;


public class Laboratorio extends Spazio {

    private Persona persone[];
    private int testa;
    private int coda;
    private int riemp;

    public Laboratorio(String nome, int numPosti) {
        super(nome, numPosti);

        testa = 0;
        coda = 0;
        riemp = 0;

        persone = new Persona[numPosti];

    }

    public boolean laboratorioPieno() {

        return riemp == numPosti;

    }

    public boolean laboratorioVuoto() {

        return riemp == 0;

    }

    @Override
    public void entra(Persona p) throws LaboratorioPienoException {

        if (laboratorioPieno()) {

            throw new LaboratorioPienoException("Laboratorio Pieno.");

        }

        persone[coda] = p;

        coda = (coda + 1) % numPosti;

        riemp++;

    }

    @Override
    public Persona esce() throws LaboratorioVuotoException {

        if (laboratorioVuoto()) {

            throw new LaboratorioVuotoException("Laboratorio vuoto.");

        }

        Persona p = persone[testa];

        persone[testa] = null;

        testa = (testa + 1) % numPosti;

        riemp--;

        return p;

    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer(super.toString());

        for (int i = 0; i < riemp; i++) {

            sb.append(persone[(i + testa) % numPosti]);

        }

        return sb.toString();
    }

}
