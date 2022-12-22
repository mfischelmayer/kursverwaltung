import java.time.LocalDate;

public class Main {
    public static void main( String[] args ) {
        Institut institut = new Institut( "Lernen 4000 GmbH" );

        Trainer t1 = new Trainer( "Michael", "Fischelmayer" );

        institut.kursHinzufuegen( "Java 101", 10, t1, LocalDate.of( 2022, 9, 1 ), LocalDate.of( 2022, 10, 15 ) );
        institut.kursHinzufuegen( "JDBC für Anfänger", 5, t1, LocalDate.of( 2022, 12, 1 ), LocalDate.of( 2022, 12, 2 ) );

        Teilnehmer tn1 = new Teilnehmer( "Max", "Mustermann", LocalDate.of( 1990, 1, 6 ), 'm' );
        Teilnehmer tn2 = new Teilnehmer( "Julia", "Herbst", LocalDate.of( 1982, 1, 6 ), 'w' );
        Teilnehmer tn3 = new Teilnehmer( "Manfred", "Konstant", LocalDate.of( 1975, 8, 20 ), 'm' );
        Teilnehmer tn4 = new Teilnehmer( "Helmut", "Gausterer", LocalDate.of( 1960, 5, 12 ), 'm' );
        Teilnehmer tn5 = new Teilnehmer( "Jennifer", "York", LocalDate.of( 1998, 3, 11 ), 'd' );
        Teilnehmer tn6 = new Teilnehmer( "Hans", "Oldman", LocalDate.of( 1850, 3, 11 ), 'm' );


        Kurs java101 = institut.sucheKurs( "Java 101" );
        java101.teilnehmerEintragen( tn1 );
        java101.teilnehmerEintragen( tn2 );
        java101.teilnehmerEintragen( tn3 );
        java101.teilnehmerEintragen( tn4 );
        java101.teilnehmerEintragen( tn5 );
        java101.teilnehmerEintragen( tn6 );
        java101.printKursinfo();

        Kurs jdbcKurs = institut.sucheKurs( "JDBC für Anfänger" );
        jdbcKurs.teilnehmerEintragen( tn1 );
        jdbcKurs.teilnehmerEintragen( tn2 );
        jdbcKurs.printKursinfo();


//        System.out.println("Kurse suchen!");
//        // kurse suchen nach datum
//        List<Kurs> gefundeneKurse = institut.sucheKurseZwischen( LocalDate.of( 2022, 9, 1 ), LocalDate.of( 2022, 10, 15 ) );
//        for ( Kurs kurs : gefundeneKurse ) {
//            kurs.printKursinfo();
//            kurs.teilnehmerEintragen( tn1 );
//            kurs.printKursinfo();
//        }

    }
}
