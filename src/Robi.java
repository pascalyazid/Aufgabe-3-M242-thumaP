/**
 * VERBALE BESCHREIBUNG:
 * Erstellen Sie hier ihre verbale Beschreibung.
 * <p>
 * <p>
 * <p>
 * VIDEO URL: https://youtu.be/dQw4w9WgXcQ
 */
public class Robi {

    private RobiAPI robi;


    // Used to start the Robi
    public static void main(String[] args) {
        Robi r = new Robi("localhost", 62931);   // Tragen Sie hier die Portnummer auf Ihrem Simulator ein.
        // hier Robi Methoden aufrufen
        r.templateMethod();

    }

    public Robi(String hostname, int portNummer) {
        robi = new RobiAPI(hostname, portNummer);
    }

    /*
     *	Template-Methode für den Robi
     */
    public void templateMethod() {
        char zustand = 'a';
        SandUhr uhr = new SandUhr();
        robi.connect();
        robi.getDistSensorValues();

        //Code here

        robi.disconnect();
    }

}
