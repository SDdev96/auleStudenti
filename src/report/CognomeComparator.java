package report;

import java.util.Comparator;

import persone.Persona;

public class CognomeComparator implements Comparator<Persona> {

    @Override
    public int compare(Persona o1, Persona o2) {
        if (o1.getCognome().equals(o2.getCognome()))
            return o1.compareTo(o2);
        return o1.getCognome().compareTo(o2.getCognome());
    }

}
