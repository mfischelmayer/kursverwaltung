public class Trainer {
    private String vorname;
    private String nachname;

    public Trainer( String vorname, String nachname ) {
        setVorname( vorname );
        setNachname( nachname );
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
