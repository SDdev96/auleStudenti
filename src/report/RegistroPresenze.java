package report;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import persone.Studente;

@SuppressWarnings("all")
public class RegistroPresenze implements Serializable {
    private String nome;
    private Map<String, Set<LocalDate>> presenze;
    private AnagraficaStudenti anagrafica;

    public RegistroPresenze(String nome, AnagraficaStudenti anagrafica) {
        this.nome = nome;
        this.anagrafica = anagrafica;
        this.presenze = new HashMap<>();
        // this.presenze = new TreeMap<>(); //key-based order
    }

    public AnagraficaStudenti getAnagrafica() {
        return anagrafica;
    }

    /**
     * Rileva la presenza di uno studente, aggiunge lo studente nella data passata
     * 
     * @param s Lo studente
     * @param d La data nella quale aggiungerlo
     */
    public void rileva(Studente s, LocalDate d) {
        if (presenze.containsKey(s.getMatricola())) {
            Set<LocalDate> date = presenze.get(s.getMatricola());
            date.add(d);
        } else {
            Set<LocalDate> date = new HashSet();
            date.add(d);
            presenze.put(s.getMatricola(), date);
        }

        anagrafica.aggiungi(s);
    }

    /**
     * Stampo le presenza rispetto alla matricola
     * 
     * @param matricola
     */
    public void stampaPresenze(String matricola) {
        System.out.println(anagrafica.cerca(matricola));
        System.out.println(presenze.get(matricola));
        System.out.println();
    }

    /**
     * Stampa rispetto alla data, devo scorrere tutte le coppie key-value
     * 
     * @param d La data utilizzata per rilevare le presenze
     */
    public void stampaPresenza(LocalDate d) {
        System.out.println("Report studenti che hanno rilevato in data: " + d + '\n');

        for (Map.Entry<String, Set<LocalDate>> me : presenze.entrySet())
            if (me.getValue().contains(d))
                System.out.println(anagrafica.cerca(me.getKey()));
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("RegistroPresenze " + nome + "\n");

        for (Map.Entry<String, Set<LocalDate>> me : presenze.entrySet()) {
            Studente s = anagrafica.cerca(me.getKey());
            sb.append(s.getNome() + ' ' + s.getCognome() + ' ' + s.getMatricola() + ' ' + me.getValue() + '\n');
        }

        return sb.toString();
    }

    /**
     * Scrive su file tramite stream di bytes
     * 
     * @throws IOException
     * 
     * @see {@link OutputStream}
     * @see {@link FileOutputStream}
     * @see {@link BufferedOutputStream}
     * @see {@link DataOutputStream#writeUTF(String)}
     * @see {@link DataOutputStream#writeInt(String)}
     * 
     * @see {@link Map#entrySet()}
     * @see {@link Map.Entry}
     * 
     * 
     */
    public void scriviDOS() throws IOException {
        try (final DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(nome + "_registro.bin")))) {
            for (Map.Entry<String, Set<LocalDate>> me : presenze.entrySet()) {
                dos.writeUTF(me.getKey());
                Set<LocalDate> date = me.getValue();
                dos.writeInt(date.size());

                for (LocalDate d : date)
                    dos.writeUTF(d.toString());
            }
        }
    }

    /**
     * Leggi su file tramite stream di bytes
     * 
     * @param nome
     * @return
     * 
     * @throws IOException
     * 
     * @see {@link InputStream}
     * @see {@link FileInputStream}
     * @see {@link BufferedInputStream}
     * @see {@link DataInputStream#readUTF()}
     * @see {@link DataInputStream#readInt()}
     * @see LocalDate#parse(CharSequence)
     * 
     * @see AnagraficaStudenti
     * @see Studente
     */
    public static RegistroPresenze leggiDIS(String nome) throws IOException {
        AnagraficaStudenti a = AnagraficaStudenti.leggiDIS(nome);
        RegistroPresenze rp = new RegistroPresenze(nome, a);

        try (final DataInputStream dis = new DataInputStream(
                new BufferedInputStream(new FileInputStream(nome + "_registro.bin")))) {
            while (true) {
                String id = dis.readUTF();
                int size = dis.readInt();
                Studente s = a.cerca(id);

                for (int i = 0; i < size; i++) {
                    rp.rileva(s, LocalDate.parse(dis.readUTF()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Caricamento terminato");
        }
        return rp;
    }

    /**
     * Scrivi su file tramite stream di caratteri con ';' come separatore di valori
     * CSV (Comma Separated Values)
     * 
     * @throws IOException
     * 
     * @see {@link Writer}
     * @see {@link FileWriter}
     * @see {@link BufferedWriter}
     * @see {@link PrintWriter#print(String)}
     * @see {@link PrintWriter#println(String)}
     * @see {@link Map#entrySet()}
     * @see {@link Map.Entry}
     * @see {@link LocalDate#parse(CharSequence)}
     * 
     * @see AnagraficaStudente
     * @see Studente
     * 
     * 
     */
    public void scriviCSV() throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(nome + "_registro.csv")))) {
            pw.println("NOME;COGNOME;CODICEFISCALE;MATRICOLA;DATE;;");

            for (Map.Entry<String, Set<LocalDate>> me : presenze.entrySet()) {
                Studente s = anagrafica.cerca(me.getKey());

                pw.print(s.getNome() + ";" + s.getCognome() + ";" + s.getCodFiscale() + ";" + s.getMatricola());

                for (LocalDate d : me.getValue()) {
                    pw.print(';' + d.toString());
                }
                pw.println();
            }
        }
    }

    /**
     * Leggi da file tramite stream di caratteri con CSV
     * 
     * @param nome
     * @return
     * 
     * @throws IOException
     * 
     * @see {@link Reader}
     * @see {@link FileReader}
     * @see {@link BufferedReader#readLine()}
     * @see {@link String#split(String)}
     * @see {@link LocalDate#parse(CharSequence)}
     * 
     * @see AnagraficaStudenti
     * @see Studente
     */
    public static RegistroPresenze leggiCSV(String nome) throws IOException {
        AnagraficaStudenti a = new AnagraficaStudenti(nome);
        RegistroPresenze rp = new RegistroPresenze(nome, a);

        try (BufferedReader bfr = new BufferedReader(new FileReader(nome + "_registro_anagrafica.csv"))) {
            if (bfr.readLine() == null)
                return null;

            String line;
            while ((line = bfr.readLine()) != null) {
                String fields[] = line.split(";");

                Studente s = new Studente(fields[0], fields[1], fields[2], fields[3]);

                for (int i = 4; i < fields.length; i++)
                    rp.rileva(s, LocalDate.parse(fields[i]));
            }
        }
        return rp;
    }

    /**
     * Scrivi su file tramite Object
     * 
     * @throws IOException
     * 
     * @see {@link OutputStream}
     * @see {@link FileOutputStream}
     * @see {@link BufferedOutputStream}
     * @ee {@link ObjectOutputStream#writeObject(Object)}
     */
    public void scriviOBJ() throws IOException {
        try (final ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(nome + "_registro_anagrafica.bin")))) {
            oos.writeObject(this);
        }
    }

    /**
     * Leggi da file tramite Object
     * 
     * @param nome
     * @return
     * 
     * @throws IOException
     * 
     * 
     * @see {@link InputStream}
     * @see {@link FileInputStream}
     * @see {@link BufferedInputStream}
     * @see {@link ObjectInputStream#readObject()}
     * @see {@link ClassNotFoundException} - Checked
     * 
     * 
     */
    public static RegistroPresenze leggiOBJ(String nome) throws IOException {
        try (final ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(nome + "_registro_anagrafica.bin")))) {
            try {
                RegistroPresenze rp = (RegistroPresenze) ois.readObject();
                return rp;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
