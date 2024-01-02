package persone;

@SuppressWarnings("all")

public class Persona implements Comparable<Persona>, java.io.Serializable {

    private String nome;
    private String cognome;
    private String codFiscale;

    public Persona(String nome, String cognome, String codFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.codFiscale = codFiscale;
    }

    public Persona(String nome, String cognome) {
        this(nome, cognome, null);
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCodFiscale() {
        return codFiscale;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setCodFiscale(String codFiscale) {
        this.codFiscale = codFiscale;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.codFiscale == null ? 0 : this.codFiscale.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Persona other = (Persona) obj;

        return this.codFiscale.equals(other.codFiscale);
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nCognome: " + cognome + "\nCodice Fiscale: "
                + (codFiscale == null ? "Non inserito" : codFiscale) + "\n";
    }

    @Override
    public int compareTo(Persona p) {
        return codFiscale.compareTo(p.codFiscale);
    }
}
