import java.time.LocalDate;
import java.time.Period;

public class Teilnehmer {
    private String vorname;
    private String nachname;
    private LocalDate geburtstag;
    private char geschlecht;

    public Teilnehmer( String vorname, String nachname, LocalDate geburtstag, char geschlecht ) {
        setVorname( vorname );
        setNachname( nachname );
        setGeburtstag( geburtstag );
        setGeschlecht( geschlecht );
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname( String vorname ) {
        if(vorname == null || vorname.isEmpty()) {
            this.vorname = "-";
        } else {
            this.vorname = vorname;
        }
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname( String nachname ) {
        if(nachname == null || nachname.isBlank() ) {
            this.nachname = "-";
        } else {
            this.nachname = nachname;
        }
    }

    public LocalDate getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag( LocalDate geburtstag ) {
        if ( LocalDate.now().minusYears( 130 ).isAfter( geburtstag ) ) {
            System.out.println("Geburtstag ungültig. Wird auf null gesetzt");
            this.geburtstag = null;
        } else {
            this.geburtstag = geburtstag;
        }
    }

    public char getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht( char geschlecht ) {
        if(geschlecht == 'm' ||geschlecht == 'w' ||geschlecht == 'd') {
            this.geschlecht = geschlecht;
        } else {
            this.geschlecht = '-';
        }
    }

    @Override
    public String toString() {
        return "Teilnehmer{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geburtstag=" + geburtstag +
                ", geschlecht=" + geschlecht +
                '}';
    }

    /**
     * Gibt die Informationen des Teilnehmers auf die Konsole aus
     */
    public void print() {
        System.out.println(this);
    }

    /**
     *
     * @return das Alter als Ganzzahl oder -1 wenn ein kein Geburtstag vorhanden
     */
    public int berechnetAlter() {
        if ( geburtstag == null ) {
            return -1;
        }
        return Period.between(geburtstag, LocalDate.now()).getYears();
    }
}
