import java.time.LocalDate;
import java.time.Period;

public class Teilnehmer {
    private String vorname;
    private String nachname;
    private LocalDate geburtstag;
    private char geschlecht;

    public Teilnehmer( String vorname, String nachname, LocalDate geburtstag, char geschlecht ) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtstag = geburtstag;
        setGeschlecht( geschlecht );
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname( String vorname ) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname( String nachname ) {
        this.nachname = nachname;
    }

    public LocalDate getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag( LocalDate geburtstag ) {
        this.geburtstag = geburtstag;
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

    public void print() {
        System.out.println(this);
    }

    public int berechnetAlter() {
        return Period.between(geburtstag, LocalDate.now()).getYears();
    }
}
