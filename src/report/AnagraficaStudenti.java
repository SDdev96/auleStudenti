package report;

import java.io.*;
import java.util.*;

import persone.Studente;

@SuppressWarnings("all")

public class AnagraficaStudenti implements java.io.Serializable {
    private String nome;
    private Map<String, Studente> anagrafica;

    public AnagraficaStudenti(String nome) {
        this.nome = nome;
        this.anagrafica = new HashMap<>();
    }

    public void aggiungi(Studente s) {
        anagrafica.putIfAbsent(s.getMatricola(), s);
    }

    public void aggiorna(Studente s) {
        anagrafica.put(s.getMatricola(), s);
    }

    public Studente rimuovi(String matricola) {
        return anagrafica.remove(matricola);
    }

    public Studente cerca(String matricola) {
        return anagrafica.get(matricola);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("AnagraficaStudenti" + nome + "\n");

        for (Studente s : anagrafica.values()) {
            sb.append(s.getNome() + " " + s.getCognome() + " " + s.getMatricola() + "\n");
        }

        return sb.toString();
    }

    /**
     * Scrivi su file tramite stream di byte
     * 
     * @throws IOException
     * 
     * @see OutputStream
     * @see FileOutputStream
     * @see BufferedOutputStream
     * @see DataOutputStream#writeUTF(String)
     */
    public void scriviDOS() throws IOException {
        try (final DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(nome + "_anagrafica.bin")))) {
            for (Studente s : anagrafica.values()) {
                dos.writeUTF(s.getNome());
                dos.writeUTF(s.getCognome());
                dos.writeUTF(s.getCodFiscale());
                dos.writeUTF(s.getMatricola());
            }
        }
    }

    /**
     * Leggi da file tramite stream di byte
     * 
     * @param nome
     * @return L'elemento costruito a partire dai dati del file
     * 
     * @throws IOException
     * 
     * @see InputStream
     * @see FileInputStream
     * @see BufferedInputStream
     * @see DataInputStream#readUTF()
     * 
     * @see EOFException
     * @see FileNotFoundException
     */
    public static AnagraficaStudenti leggiDIS(String nome) throws IOException {
        AnagraficaStudenti a = new AnagraficaStudenti(nome);
        try (final DataInputStream dis = new DataInputStream(
                new BufferedInputStream(new FileInputStream(nome + ".bin")))) {
            while (true) {
                String name = dis.readUTF();
                String cognome = dis.readUTF();
                String codFis = dis.readUTF();
                String matricola = dis.readUTF();

                a.aggiungi(new Studente(nome, cognome, codFis, matricola));
            }
        } catch (EOFException e) {
            e.printStackTrace();
            System.out.println("Caricamento terminato");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File non trovato");
        }
        return a;
    }

    /**
     * Scrivi su file tramite stream di caratteri con ';' come separatore di valori
     * CSV (Comma Separated Values)
     * 
     * @throws IOException
     * 
     * @see {@link Writer#append(CharSequence)}
     * @see FileWriter
     * @see BufferedWriter
     * 
     * @see Studente
     * 
     */
    public void scriviCSV() throws IOException {
        try (final BufferedWriter bfw = new BufferedWriter(new FileWriter(nome + "_anagrafica.csv"))) {
            bfw.append("NOME;COGNOME;CODICEFISCALE;MATRICOLA\n");
            for (Studente s : anagrafica.values()) {
                bfw.append(s.getNome() + ";");
                bfw.append(s.getCognome() + ";");
                bfw.append(s.getCodFiscale() + ";");
                bfw.append(s.getMatricola() + "\n");
            }
        }
    }

    /**
     * Leggi da file tramite stream di caratteri con CSV
     * 
     * @param nome
     * @return L'elemento costruito a partire dal file
     * 
     * @throws IOException
     * 
     * @see Reader
     * @see FileReader
     * @see BufferedReader#readLine()
     * @see String#split(String)
     * 
     * @see Studente
     */
    public static AnagraficaStudenti leggiCSV(String nome) throws IOException {
        AnagraficaStudenti a = new AnagraficaStudenti(nome);

        try (BufferedReader bfr = new BufferedReader(new FileReader(nome + "_anagrafica.csv"))) {
            if (bfr.readLine() == null)
                return null;

            String line;
            while ((line = bfr.readLine()) != null) {
                String fields[] = line.split(";");

                Studente s = new Studente(fields[0], fields[1], fields[2], fields[3]);
                a.aggiungi(s);
            }
        }
        return a;
    }

    /**
     * Leggi da file tramite stream di carattere con Scanner
     * 
     * @param nome
     * @return l'elemento costruito a partire dal file
     * 
     * @throws IOException
     * 
     * @see Reader
     * @see FileReader
     * @see BufferedReader
     * @see Scanner#useDelimiter(String)
     * @see Scanner#hasNextLine()
     * @see Scanner#nextLine()
     * @see Scanner#hasNext()
     * @see Scanner#next()
     * 
     * @see Studente
     */
    public static AnagraficaStudenti leggiCSVS(String nome) throws IOException {
        AnagraficaStudenti a = new AnagraficaStudenti(nome);

        try (Scanner s = new Scanner(new BufferedReader(new FileReader(nome + "_anagrafica.csv")))) {
            s.useDelimiter("[;\n]");
            if (s.hasNextLine())
                s.nextLine();

            while (s.hasNext()) {
                String name = s.next();
                String cognome = s.next();
                String codFiscale = s.next();
                String matricola = s.next();

                Studente st = new Studente(name, cognome, codFiscale, matricola);
                a.aggiorna(st);
            }
        }
        return a;
    }
}
