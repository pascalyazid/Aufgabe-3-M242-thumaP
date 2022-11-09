import java.io.IOException;

/**
 * Objekte dieser Klasse bieten verschiedene Methoden zur Ansteuerung der
 * Roboter an. Diese Methoden bilden das "API, application programming interface"
 * und damit die Basis zur Programmierung von Steuerprogrammen.<br>
 *
 * Wird eine Methode aufgerufen, so wird die Bezeichnung der Methode und die
 * aktuellen Parameter im Hintergrund ueber eine Verbindung zur realen Roboter
 * uebertragen. Dort werden entsprechende Funktionen aktiviert, die das
 * Gewuenschte bewirken. Am Ende sendet der reale Roboter ueber die Verbindung
 * eine Antwort zurueck, die allenfalls Rueckgabeparameter enthaelt. Damit ist die
 * aufgerufene Methode fertig ausgefuehrt.
 *
 * @author NTB Zo
 * @version 1.1
 */

public class RobiAPI {
	private final String LINE_SEPARATOR = "\r\n";
	private Verbindung verbindung;
	public boolean protokollierung = false;

	private int[] sensorwerte = new int[16];

	/**
	 * Ein Objekt, welches einen Robi repraesentiert, wird erzeugt.
	 *
	 * @param hostname
	 *            Der Hostname des Roboters, beispielsweise frieda.ntb.ch .
	 *
	 */
	public RobiAPI(String hostname) {
		 verbindung = new Verbindung(hostname);
	}

	public RobiAPI(String hostname, int port) {
		verbindung = new Verbindung(hostname, port);
	}

	/**
	 * Die Verbindung zum Robi wird hergestellt. Das Robi-Objekt kann nun genutzt
	 * werden.
	 */
	@Deprecated
	public void verbinden() {
		verbindung.verbinden();
	}

	/**
	 * Die Verbindung zum Robi wird hergestellt. Das Robi-Objekt kann nun genutzt
	 * werden.
	 */
	public void connect() {
		verbindung.verbinden();
	}

	/**
	 * Die Verbindung zum Robi wird getrennt. Das Robi-Objekt ist nicht mehr
	 * weiter benutzbar.
	 */
	@Deprecated
	public void trennen() {
		verbindung.trennen();
	}

	/**
	 * Die Verbindung zum Robi wird getrennt. Das Robi-Objekt ist nicht mehr
	 * weiter benutzbar.
	 */
	public void disconnect() {
		verbindung.trennen();
	}

	/**
	 * Steuert den linken Motor an.
	 *
	 * @param speed
	 *            [-100 .. 0 .. +100], positive Werte fuer Gegenuhrzeigersinn
	 *            (mit Blick aus Rad)
	 */
	public void setLeftDriveSpeed(int speed) {
		String auftrag = "setLeftDriveSpeed " + speed;
		verbindung.out.print(auftrag + LINE_SEPARATOR);
		verbindung.out.flush();

		String antwort = null;
		try {
			antwort = verbindung.in.readLine(); // Warten auf leere Antwortzeile
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (protokollierung) {
			System.out.println(auftrag + " " + antwort);
		}
	}

	/**
	 * Steuert den rechten Motor an.
	 *
	 * @param speed
	 *            [-100 .. 0 .. +100], positive Werte fuer Gegenuhrzeigersinn
	 *            (mit Blick aus Rad)
	 */
	public void setRightDriveSpeed(int speed) {
		if (verbindung.gibPort() == 2000) {
			speed = -speed;
		} // if
		String auftrag = "setRightDriveSpeed " + speed;
		verbindung.out.print(auftrag + LINE_SEPARATOR);
		verbindung.out.flush();

		String antwort = null;
		try {
			antwort = verbindung.in.readLine(); // Warten auf leere Antwortzeile
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (protokollierung) {
			System.out.println(auftrag + " " + antwort);
		}
	}

	/**
	 * Steuert beide Motoren an.
	 *
	 * @param speed
	 *            [-100 .. 0 .. +100], positive Werte fuer Vorwaertsrichtung
	 */
	public void drive(int speed) {
		String auftrag = "drive " + speed;
		verbindung.out.print(auftrag + LINE_SEPARATOR);
		verbindung.out.flush();

		String antwort = null;
		try {
			antwort = verbindung.in.readLine(); // Warten auf leere Antwortzeile
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (protokollierung) {
			System.out.println(auftrag + " " + antwort);
		}
	}

	/**
	 * Steuert beide Motoren so an, dass der Robi an Ort dreht.
	 *
	 * @param speed
	 *            [-100 .. 0 .. +100], positive Werte fuer Drehen im
	 *            Uhrzeigersinn
	 */
	public void turn(int speed) {
		String auftrag = "turn " + speed;
		verbindung.out.print(auftrag + LINE_SEPARATOR);
		verbindung.out.flush();

		String antwort = null;
		try {
			antwort = verbindung.in.readLine(); // Warten auf leere Antwortzeile
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (protokollierung) {
			System.out.println(auftrag + " " + antwort);
		}
	}

	/**
	 * Stoppt beide Motoren.
	 */
	public void stop() {
		String auftrag = "stop";
		verbindung.out.print(auftrag + LINE_SEPARATOR);
		verbindung.out.flush();

		String antwort = null;
		try {
			antwort = verbindung.in.readLine(); // Warten auf leere Antwortzeile
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (protokollierung) {
			System.out.println(auftrag + " " + antwort);
		}
	}

	/**
	 * Abfragen der zuvor mit getDistSensorValues() aktualisierten Werte aller Sensoren (Reflexlichtschranken). Sie
	 * werden lokal auf Seiten des Steuerprogramms zwischengespeichert und
	 * koennten einzel abgefragt werden.
	 *
	 */
	public int readSensor(int sensorNumber) {
		return sensorwerte[sensorNumber];
	}

	/**
	 * Holt die aktuellen Werte aller Sensoren (Reflexlichtschranken). Sie
	 * werden lokal auf Seiten des Steuerprogramms zwischengespeichert und
	 * koennen mit der Methode readSensor einzeln abgefragt werden.
	 *
	 */
	public void getDistSensorValues() {
		String auftrag = "getDistSensorValues";
		verbindung.out.print(auftrag + LINE_SEPARATOR);
		verbindung.out.flush();

		String antwort = null;
		String[] werte = null;
		try {
			antwort = verbindung.in.readLine();
			if (antwort != null) {
				werte = antwort.split(" ");

				for (int i = 0; i < 16; i++) {
					sensorwerte[i] = Integer.parseInt(werte[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (protokollierung) {
			System.out.print(auftrag + " ");
			if (werte != null) {
				for (String s : werte) {
					System.out.print(s + " ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * Set the state of the chosen pattern LED. Pattern LEDs are the red ones.
	 * Examples: setPatternLED(0,0,true) turns LED11 on,
	 * setPatternLED(3,2,false) turns LED13 off
	 *
	 * @param r
	 *            row of the LED(range 0..3)
	 * @param c
	 *            column of the LED (range 0..2)
	 * @param state
	 *            the state of the LED (true = on, false = off)
	 */
	public void setPatternLED(int r, int c, boolean state) {
		String auftrag = "setPatternLED " + r + " " + c + " " + state;
		verbindung.out.print(auftrag + LINE_SEPARATOR);
		verbindung.out.flush();

		String antwort = null;
		try {
			antwort = verbindung.in.readLine(); // Warten auf leere Antwortzeile
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (protokollierung) {
			System.out.println(auftrag + " " + antwort);
		}
	}
}
