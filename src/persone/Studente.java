package persone;

public class Studente extends Persona {
    private String matricola;

    public Studente(String nome, String cognome, String codFiscale, String matricola) {
        super(nome, cognome, codFiscale);
        this.matricola = matricola;
    }

    public Studente() {
        this(null, null, null, null);
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getInfoStudente() {
        return super.toString() + "Matricola:" + matricola + '\n';
    }
}
