/**
 * Sanduhren dienen dazu, Verzoegerungen zu realisieren. Sie koennen zu einem bestimmten Zeitpunkt
 * unter Angabe einer Laufdauer gestartet werden. Danach kann die Uhr gefragt werden, ob ihre
 * Laufdauer schon erreicht ist oder noch nicht.
 *
 * @author NTB Zo
 * @version 1.0
 */
public class SandUhr
{
    private long startZeit;  // in Millisekunden
    private int dauer;  // in Millisekunden

    /**
     * Erzeugt eine Instanz der Klasse.
     */
    public SandUhr()
    {
        startZeit = System.currentTimeMillis();
    }

    /**
     * Die Sanduhr wird umgedreht.
     *
     * @param  dauer   Die Laufzeit der Sanduhr in Millisekunden.
     */
    public void starten(int dauerInMilliSekunden)
    {
        startZeit = System.currentTimeMillis();
        dauer = dauerInMilliSekunden;
    }

    /**
     * Frage, ob die vorgesehene Laufzeit schon erreicht worden ist oder nicht.
     *
     * @return  Die vorgesehene Laufzeit ist erreicht.
     */
    public boolean abgelaufen(){
        return System.currentTimeMillis() - startZeit >= dauer;
    }

    public void warten(int zeit){
      starten(zeit);
      while(!abgelaufen()){

        }

    }
}
