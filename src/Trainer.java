public class Trainer {
    private String vorname;
    private String nachname;

    public Trainer( String vorname, String nachname ) {
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String displayName() {
        return vorname + " " + nachname;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                '}';
    }
}
