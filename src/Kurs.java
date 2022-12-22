import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kurs {

    private final String name;
    private final int maxTeilnehmerAnzahl;
    private LocalDate von;
    private LocalDate bis;
    private List<Teilnehmer> teilnehmer;
    private Trainer trainer;

    public Kurs( String name,
                 int maxTeilnehmerAnzahl,
                 LocalDate von,
                 LocalDate bis,
                 Trainer trainer ) {

        if ( name == null || name.isEmpty() ) {
            System.out.println( "kein Name angegeben. es wird ein Standardname vergeben" );
            this.name = "KURS";
        } else {
            this.name = name;
        }

        if ( maxTeilnehmerAnzahl < 1 ) {
            System.out.println( "ungültiger Wert bei der maximalen Teilnehmeranzahl: Es wird 10 als maximale Teilnehmeranzahl gesetzt" );
            this.maxTeilnehmerAnzahl = 10;
        } else {
            this.maxTeilnehmerAnzahl = maxTeilnehmerAnzahl;
        }

        // gerne auch die restlichen paramter pruefen ;-)
        this.von = von;
        this.bis = bis;
        this.trainer = trainer;
        this.teilnehmer = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getMaxTeilnehmerAnzahl() {
        return maxTeilnehmerAnzahl;
    }

    public LocalDate getVon() {
        return von;
    }

    public void setVon( LocalDate von ) {
        this.von = von;
    }

    public LocalDate getBis() {
        return bis;
    }

    public void setBis( LocalDate bis ) {
        this.bis = bis;
    }

    public List<Teilnehmer> getTeilnehmer() {
        return teilnehmer;
    }

    public void setTeilnehmer( List<Teilnehmer> teilnehmer ) {
        this.teilnehmer = teilnehmer;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer( Trainer trainer ) {
        this.trainer = trainer;
    }

    /**
     * Fuege den uebergebenen Teilnehmer hinzu, aber
     * nur wenn die maximale Teilnehmeranzahl noch nicht erreicht ist.
     *
     * @param teilnehmer
     */
    public void teilnehmerEintragen( Teilnehmer teilnehmer ) {
        if ( teilnehmer == null ) {
            System.out.println( "Fehler: Teilnehmer darf nicht null sein" );
            return;
        }
        boolean teilnehmergrenzeErreicht = ( this.teilnehmer.size() + 1 ) > maxTeilnehmerAnzahl;    // 1
        if ( teilnehmergrenzeErreicht ) {
            System.out.println( "Der Teilnehmer wird nicht in die Kursliste eingetragen. Teilnehmergrenzen erreicht" );
            return;
        }
        this.teilnehmer.add( teilnehmer );
    }

    @Override
    public String toString() {
        return "Kurs{" +
                "name='" + name + '\'' +
                ", maxTeilnehmerAnzahl=" + maxTeilnehmerAnzahl +
                ", von=" + von +
                ", bis=" + bis +
                ", teilnehmer=" + teilnehmer +
                ", trainer=" + trainer +
                '}';
    }

    /**
     * gibt eine Detailierte Kursuebersicht auf der Konsole aus
     */
    public void printKursinfo() {
        System.out.println( "*********************************************" );
        System.out.println( "**************** KURSINFO *******************" );
        System.out.println( "*********************************************" );
        System.out.println( "Name:\t\t\t\t" + name );
        System.out.println( "Trainer:\t\t\t" + trainer.displayName() );
        System.out.println( "Max. Anzahl:\t\t" + maxTeilnehmerAnzahl );
        System.out.println( "Belegung in %:\t\t" + berechneKursbelegungInProzent() );
        System.out.println( "Plätze frei:\t\t" + ( maxTeilnehmerAnzahl - teilnehmer.size() ) );

        Map<Character, Float> kursbelegungNachGeschlecht = kursbelegungNachGeschlecht();

        System.out.println( "Frauenanteil in %:\t" + kursbelegungNachGeschlecht.get( 'w' ) );
        System.out.println( "Männeranteil in %:\t" + kursbelegungNachGeschlecht.get( 'm' ) );
        System.out.println( "Divers in %:\t\t" + kursbelegungNachGeschlecht.get( 'd' ) );
        System.out.println( "Durchschnitt Alter:\t" + berechneDurchschnittsalter() );

        System.out.println( "-------------- Kursteilnehmer: --------------" );
        if ( teilnehmer.isEmpty() ) {
            System.out.println( "--- keine Teilnehmer ---" );
        } else {
            for ( Teilnehmer t : teilnehmer ) {
                t.print();
            }
        }
    }

    private float berechneDurchschnittsalter() {
        int alterAllerTeilnehmer = 0;
        for ( Teilnehmer t : teilnehmer ) {
            int alter = t.berechnetAlter();
            if ( alter != -1 ) {
                alterAllerTeilnehmer += alter;
            }
        }
        return (float) alterAllerTeilnehmer / teilnehmer.size(); // 2
    }

    private float berechneKursbelegungInProzent() {
        return ( (float) teilnehmer.size() / maxTeilnehmerAnzahl ) * 100;  // 2
    }

    private Map<Character, Float> kursbelegungNachGeschlecht() {
        int anzahlFrauen = 0;
        int anzahlMaenner = 0;
        int anzahlDivers = 0;

        for ( Teilnehmer t : this.teilnehmer ) {
            if ( t.getGeschlecht() == 'w' ) {
                anzahlFrauen++;
            } else if ( t.getGeschlecht() == 'm' ) {
                anzahlMaenner++;
            } else {
                anzahlDivers++;
            }
        }

        float frauenanteilInProzent = berechneProzent( anzahlFrauen );
        float maenneranteilInProzent = berechneProzent( anzahlMaenner );
        float diversanteilInProzent = berechneProzent( anzahlDivers );

        Map<Character, Float> resultMap = new HashMap<>();
        resultMap.put( 'w', frauenanteilInProzent );
        resultMap.put( 'm', maenneranteilInProzent );
        resultMap.put( 'd', diversanteilInProzent );
        return resultMap;
    }

    private float berechneProzent( int anzahl ) {
        if ( anzahl == 0 ) {
            return 0.0f;
        }
        return ( (float) anzahl / teilnehmer.size() ) * 100;    // 2
    }

}

/*
    1) Warum habe ich hier eine lokale Variable angelegt. Warum das Statement nicht gleich in die if-Bedingung?

    Das waere natuerlich absolut moeglich. Wenn mehrere Ausdruecke ueber logische Operatoren verknuepft werden,
    macht es jedoch oft Sinn die Teilausdruecke in lokale boolesche Variablen mit sprechenden Namen zu hinterlegen.
    Dem "Computer" ist es egal, aber die Lesbarkeit fuer uns Entwickler steigt dadurch erheblich.
    "Wenn Teilnehmergrenze erreicht dann ... -> lest sich doch fast wie ein normaler Satz, oder? "

    2) Noch nie gesehen?

    Dieses Konstrukt nennt man "cast" bzw. von x auf y "casten".
    Ist hier notwendig um Fliesskommazahl zu bekommen. Denken Sie sich im Kopf durch wenn wir hier mit Ganzzahlen arbeiten wuerden.
    z.b. Maximale Teilnehmeranzahl 10
         aktuelle Teilnehmeranzahl 8
         8/10 ergibt 0,8.
         In Ganzzahlen (int) ausgedrueckt ergibt 8/10 = 0.

    3) warum diese Pruefung, warum nicht gleich rechnen
    Wenn noch kein Teilnehmer eingetragen ist, also die Teilnehmeranzahl 0 ist, ist auch automatisch noch keine Frau eingetragen
    Die Rechnung wuerde also wie folgt aussehen: (0/0)*100
    Eine Division durch 0 ist nicht moeglich, deshalb die Pruefung. Ist mir selbst erst beim Testen aufgefallen 😅

 */
