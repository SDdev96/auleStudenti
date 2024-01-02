package test;

import java.util.logging.Level;
import java.util.logging.Logger;

import persone.*;
import report.eccezioni.*;
import spazi.Laboratorio;

@SuppressWarnings("unused")

public class TestLaboratorio {
  public static void main(String[] args) {
    Laboratorio l = new Laboratorio("T25", 3);

    Persona p1 = new Studente("Mario", "Rossi", "MRS001", "061270001");

    try {
      l.entra(new Docente("Ernesto", "Bianchi", "ERS009", "TSW"));

      l.entra(p1);

      l.entra(new Studente("Luigi", "Rossi", "MRS002", "061270002"));

      l.entra(new Studente("Stefano", "Rossi", "MRS003", "061270003"));

    } catch (LaboratorioPienoException ex) {
      // Logger.getLogger(TestLaboratorio.class.getName()).log(Level.SEVERE, null,
      // ex);
      System.out.println(ex);
    }

    Studente p;
    try {
      p = (Studente) l.esce();
      l.entra(new Studente("Stefano", "Rossi", "MRS003", "061270003"));
      System.out.println(p);

    } catch (LaboratorioPienoException | LaboratorioVuotoException ex) {
      // Logger.getLogger(TestLaboratorio.class.getName()).log(Level.SEVERE, null,
      // ex);

    }

    catch (Exception e) {

    }

    finally {

      System.out.println("finally");

    }

    // l.entra(new Studente("Ernesto", "Rossi", "MRS004", "061270004"));

    // System.out.println(p);

  }

}
