package persone;

public class Docente extends Persona {

    private String insegnamento;

    public Docente(String nome, String cognome, String codFiscale, String insegnamento) {
        super(nome, cognome, codFiscale);
        this.insegnamento = insegnamento;
    }

    public String getInsegnamento() {
        return insegnamento;
    }

    public void setInsegnamento(String insegnamento) {
        this.insegnamento = insegnamento;
    }

    @Override
    public String toString() {
        return super.toString() + "Insegnamento:" + insegnamento + '\n';
    }

}
