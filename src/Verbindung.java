import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Objekte dieser Klasse sind die Klientenseite von Verbindungen zu einem
 * Roboter. Ueber eine Verbindung wird mit UTF-8 Zeichen kommuniziert.<br>
 *
 * Der Aufruf einer Robifunktion besteht aus einer Textzeile mit dem Namen der
 * Funktion (z.B. gibSensorWerte) gefolgt von allfaelligen Parametern,
 * dargestellt als Zeichenketten. Die Woerter sind mit einem Leerzeichnen
 * getrennt.<p>
 *
 * Eine Antwort ist ebenfalls eine Textzeile, die alle Rueckgabewerte enthaelt.
 * <p>
 * Beispiel: Lesen aller aktuellen Sensorwerte<p>
 *
 * gesendet wird: getDistSensorValues<p>
 * empfangen wird: 12 345 678 987 546<p>
 *
 * @author NTB Zo
 * @version 1.0
 */
public class Verbindung {

    private int port = 2000;
    private String hostname;
    private Socket socket;

    /** zum Senden von Textzeilen, genau gleich wie auf System.out. Beispielsweise in.println("abc"); */
    public PrintWriter out;
    /** zum Empfangen von Textzeilen, genau gleich wie von System.in. Beispielsweise in.readLine(); */
    public BufferedReader in;

    /**
     * Eine Verbindung wird erzeugt. Die Verbindung wird aber noch nicht hergestellt.
     * Dazu muss zuerst die Methode verbinden() aufgerufen werden. Die Portnummer ist fix eingestellt.
     *
     * @param hostname
     *            Der Hostname des Roboters, beispielsweise robiInf001.ntb.ch .
     *
     */
    public Verbindung(String hostname) {
        this(hostname, 2000);
    }
    public Verbindung(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Eine Verbindung wird hergestellt. Die Portnummer ist fix eingestellt.
     */
    public void verbinden() {
        try {
            socket = new Socket(hostname, port);
            out = new PrintWriter(new OutputStreamWriter(
                    socket.getOutputStream()), true);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
        } catch (Exception e) {
            System.out.println("Verbindung kann nicht hergestellt werden!");
           // e.printStackTrace();
        }
    }


    /**
     * Trennen der Verbindung zum Robi.
     */
    public void trennen() {
        try {
            if (out != null)
                out.close();
            if (in != null)
                in.close();
            if (socket != null)
                socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int gibPort () {
    	return port;
    }

}
