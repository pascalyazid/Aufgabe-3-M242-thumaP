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
        Robi r = new Robi("localhost", 64028);   // Tragen Sie hier die Portnummer auf Ihrem Simulator ein.
        // hier Robi Methoden aufrufen
        r.templateMethod(5, 5, 2, 2, 2);

    }

    public Robi(String hostname, int portNummer) {
        robi = new RobiAPI(hostname, portNummer);
    }

    /*
     *	Template-Methode für den Robi
     */
    public void templateMethod(int forwardT, int backwardT, int waitFT, int waitBT, int reps) {
        SandUhr uhr = new SandUhr();
        robi.connect();
        robi.getDistSensorValues();
        char orient = 'f';
        boolean drive = false;
        int cnt = 0;
        while(cnt != reps * 2) {
            if(!drive ) {
                switch (orient) {
                    case 'f':
                        System.out.println("Vorwärts");
                        uhr.starten(forwardT * 1000);
                        robi.drive(10);
                        drive = true;
                        break;

                    case 'b':
                        System.out.println("Rückwarts");
                        uhr.starten(forwardT * 1000);
                        robi.drive(-10);
                        drive = true;
                        break;
                }
            }
            if(uhr.abgelaufen()) {
                if(orient == 'f') {
                    System.out.println("Warten V");
                    robi.stop();
                    drive = false;
                    wait(waitFT);
                    orient = 'b';
                }
                else if(orient == 'b') {
                    System.out.println("Warten R");
                    robi.stop();
                    drive = false;
                    wait(waitBT);
                    orient = 'f';
                }
                cnt++;
            }

        }
        //robi.disconnect();
    }


    public void wait(int time) {
        SandUhr uhr = new SandUhr();
        uhr.starten(time * 1000);
        while(!uhr.abgelaufen()) {
            if(uhr.abgelaufen()) {
                break;
            }
        }
    }

}
