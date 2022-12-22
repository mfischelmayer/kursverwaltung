import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Institut {
    private final String name;
    private final List<Kurs> kurse;

    public Institut( String name ) {
        this.name = name;
        this.kurse = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Kurs> getKurse() {
        return kurse;
    }

    /**
     * Fuegt dem Institut einen neuen Kurs hinzu. Der Kursname darf nicht bereits vorhanden sein
     *
     * @param name
     * @param maxKursteilnehmer
     * @param trainer
     * @param von
     * @param bis
     */
    public void kursHinzufuegen( String name,
                                 int maxKursteilnehmer,
                                 Trainer trainer,
                                 LocalDate von,
                                 LocalDate bis ) {
        // 1
        int idx = 0;
        boolean found = false;
        while(idx < kurse.size() && !found) {
            Kurs kurs = kurse.get( idx );
            if(kurs.getName().equals( name )){
                found = true;
            }
            idx++;
        }

        if(found){
            System.out.println("Kurs mit dem Namen " + name + " existiert bereits. Kurs wird nicht angelegt");
        } else {
            kurse.add( new Kurs( name, maxKursteilnehmer, von, bis, trainer ) );
        }
    }

    /**
     * @param start
     * @param end
     * @return eine Liste von Kursen welche zwischen ubergebenen Start- und Enddatum stattfinden,
     * oder eine leere Liste, wenn keine Kurse gefunden wurden
     */
    public List<Kurs> sucheKurseZwischen( LocalDate start, LocalDate end ) {
        List<Kurs> gefundeneKurse = new ArrayList<>();
        for ( Kurs kurs : kurse ) {
            if ( ( kurs.getVon().isEqual( start ) || kurs.getVon().isAfter( start ) )
                    && ( kurs.getBis().isEqual( end ) || kurs.getBis().isBefore( end ) ) ) {
                gefundeneKurse.add( kurs );
            }
        }
        return gefundeneKurse;
    }

    /**
     *
     * @param kursname
     * @return den gefundenen Kurs, oder null, wenn kein Kurs mit entsprechenden Namen gefunden wurde
     */
    public Kurs sucheKurs(String kursname ) {
        for ( Kurs kurs : kurse ) {
            if ( kurs.getName().equals( kursname ) ){
                return kurs;
            }
        }
        return null;
    }

}

/*
    1) Wie fast immer gibt es mehrere Moeglichkeiten. Wir haben gelernt, dass fuer "unbestimmte" Schleifendurchgaenge
     eine while Schleife die bessere Wahl ist. Diese wurde hier auch angewandt.
     Wir koennte hier aber auch folgendes machen:

     for ( Kurs kurs : kurse ) {
            if(kurs.getName().equals( name )){
                System.out.println("Kurs mit dem Namen " + name + " existiert bereits. Kurs wird nicht angelegt");
                return;
            }
        }
    kurse.add( new Kurs( name, maxKursteilnehmer, vorgeschriebenerFrauenanteilInProzent, von, bis, trainer ) );

    In diesem Fall wuerden wir, wenn der Name existiert, die Metode mit "return" verlassen.
    Das waere hier auch eine gute Umsetzung. Die unbestimmte Schleife ist dann die bessere Wahl,
    wenn wir die Methode fortfuehren muessen.

    Spaeter lernen wir generell wie wir verhindern koennen das Eintraege mehrfach in einer Liste vorkommen.
    Dazu muessen wir vorher noch lernen wie wir ueberhaupt definieren wann etwas "gleich" ist
 */
