import java.io.IOException;
import java.util.Scanner;

class Game {

    private static final String GO_TEXT = "Auf geht's!";
    private static final String EQUIPMENT_TEXT = "Waehle Deine Ausruestung: [Schwert] oder [Bogen]? ";
    private static final String CHOICE_INTRO_TEXT = "Alles klar. Der Weg teilt sich hier auf. Was willst du tun?";
    private static final String CHOICE_TEXT = "Du kannst dem Weg richtung [Wald] folgen. Oder du gehst richtung [Berg]. Du kannst auch hier bleiben und das [Lager] hier aufschlagen. Oder du gehst [abseits] des Weges. ";
    private static final String A_1 = "Der Wald verdichtet sich. Du kaempfst dich mit dem Schwert vor. Es wird dunkel. Willst du [weiter] gehen, oder hier dein [Lager] fuer die Nacht aufbauen? ";
    private static final String A_2 = "Du entdeckst ein Lager. Es scheint niemand dort zu sein. Lager [pluendern] oder [ausweichen]? ";
    private static final String A_3 = "Ein Wolf rennt auf dich zu. Du konntest ihn mit dem Schwert abwehren. Weiter richtung [Osten] oder [Westen]? ";
    private static final String A_4 = "Du hoerst in der Nacht einen Baeren ausserhalb des Zeltes.Im [Zelt] bleiben oder den Baeren [attackieren]? ";
    private static final String B_1 = "Der Wald verdichtet sich. Du kommst hier nicht weiter. Du hoerst ein Geraeusch. Willst du eine [Umweg] suchen, auf einen [Baum] klettern, oder dem Geraeusch [nachgehen]? ";
    private static final String B_2 = "Du entdeckst ein lager. Es ist niemand da, jedoch streift dort ein Baer herum. Einen anderen [Weg] suchen, einen [Pfeil] auf den Baer schiessen, oder [anschleichen]? ";
    private static final String B_3 = "Ein Wolf rennt auf dich zu. Du verfehlst mit dem Bogen. Er toetet dich.";
    private static final String B_4 = "Du hoerst weit entfernt einen Baeren. Du kannst die Silhouette erkennen. Willst du einen [Brandpfeil] in seine Richtung schiessen, oder dich in deinem Zelt [verstecken]? ";
    private static final String A_1_Y = "Du kommst bei einem Dorf aus. Sie entscheiden sich, dir zu helfen. \n SURVIVED";
    private static final String B_1_Y = "Eine Kreatur springt dich aus der Dunkelheit an und zerfleischt dich. \n GAME OVER";
    private static final String B_2_Y = "Du schreckst ihn zurueck. Das Lager gehoert dir. Angreifer wehrst du mit deinem Bogen ab. Hilfe kommt bald. \n SURVIVED";
    private static final String A_4_Y = "Er streift weiter. Eine Patrouille entdeckt dich am naechsten Tag und bringt dich in Sicherheit. \n SURVIVED";
    private static final String A_1_O = "Ein Baum stuerzt auf dein Zelt. Tot. \n GAME OVER";
    private static final String A_2_O = "Das Lager gehoert dir. Es kommen zu viele Angreifer. Du wirst zum Gefangenen. \n GAME OVER";
    private static final String B_2_O = "Der Baer bemerkt dich und du wirst zerfleischt. \n GAME OVER";
    private static final String A_4_O = "Du hast keine Chance. \n GAME OVER";
    private static final String B_1_P = "Du stoesst auf eine Patrouille. Sie bringt dich in Sicherheit. \n SURVIVED";
    private static final String A_2_P = "Du rutscht aus und faellst in die Tiefe. \n GAME OVER";
    private static final String A_3_P = "Du stoesst auf ein Dorf. Sie helfen dir. \n SURVIVED";
    private static final String B_4_P = "Du schreckst ihn zurueck. Am naechsten Tag kommt eine Patrouille und hilft dir. \n SURVIVED";
    private static final String B_1_G = "Du rutscht ab und stuerzt in die Tiefe. \n GAME OVER";
    private static final String B_2_G = "Du rutscht aus und faellst in die Tiefe. \n GAME OVER";
    private static final String A_3_G = "Der Rest des Rudels findet dich. Du kannst nicht alle abwehren. \n GAME OVER";
    private static final String B_4_G = "Der Baer findet dich. Du hast keine Chance. \n GAME OVER";
    private Scanner scanner;

    Game() {
        scanner = new Scanner(System.in);
    }

    void play() {
        System.out.println(GO_TEXT);
        System.out.print(EQUIPMENT_TEXT);
        String equipment = scanner.next();
        System.out.println(CHOICE_INTRO_TEXT);
        System.out.print(CHOICE_TEXT);
        String choice = scanner.next();
        try {
            System.out.println(findResult(evaluate(equipment, choice)));
        } catch(IOException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    private String evaluate(String equipment, String choice) throws IOException {
        if (equipment.equals("Schwert")) {
            switch (choice) {
                case "Wald":
                    System.out.print(A_1);
                    return scanner.next();
                case "Berg":
                    System.out.print(A_2);
                    return scanner.next();
                case "Lager":
                    System.out.print(A_3);
                    return scanner.next();
                case "abseits":
                    System.out.print(A_4);
                    return scanner.next();
                default:
                    throw new IOException("Invalid Input. You're a moron.");
            }
        }
        if (equipment.equals("Bogen")) {
            switch (choice) {
                case "Wald":
                    System.out.print(B_1);
                    return scanner.next();
                case "Berg":
                    System.out.print(B_2);
                    return scanner.next();
                case "Lager":
                    System.out.println(B_3);
                    return "ignore";
                case "abseits":
                    System.out.print(B_4);
                    return scanner.next();
                default:
                    throw new IOException("Invalid Input. You're a moron.");
            }
        }
        throw new IOException("Invalid Input. You're a moron.");

    }

    private String findResult(String choice) throws IOException{
        if(choice.equals("ignore")) {
            return "GAME OVER";
        }
        switch(choice) {
            case "weiter":
                return A_1_Y;
            case "Lager":
                return A_1_O;
            case "Umweg":
                return B_1_P;
            case "Baum":
                return B_1_G;
            case "nachgehen":
                return B_1_Y;
            case "pluendern":
                return A_2_O;
            case "ausweichen":
                return A_2_P;
            case "Weg":
                return B_2_G;
            case "Pfeil":
                return B_2_Y;
            case "anschleichen":
                return B_2_O;
            case "Osten":
                return A_3_P;
            case "Westen":
                return A_3_G;
            case "Zelt":
                return A_4_Y;
            case "attackieren":
                return A_4_O;
            case "Brandpfeil":
                return B_4_P;
            case "verstecken":
                return B_4_G;
            default:
                throw new IOException("Invalid Input. You're a moron.");
        }
    }
}

