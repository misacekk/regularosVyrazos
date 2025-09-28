
import java.util.regex.*;

public class UkolC {
    static final int U = Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE;
    static void findAll(String title, String regex, String input, int flags) {
        System.out.println("\n=== " + title + " ===");
        Matcher m = Pattern.compile(regex, flags).matcher(input);
        boolean any = false;
        while (m.find()) {
            any = true;
            System.out.println("MATCH: \"" + m.group() + "\"");
        }
        if (!any) {
            System.out.println("(žádné shody)");
        }
    }

    public static void main(String[] args) {
        String in = "cz-123 sk-987 cz- sk- abc cz-99999 12:34 99:00 color colour";

        // 1) cz|sk + '-' + čísla
        findAll("4.1 cz|sk-čísla", "\\b(?:cz|sk)-\\d+\\b", in, U);

        // 2) Čas HH:MM (00–99), základní validace
        findAll("4.2 Čas HH:MM", "\\b\\d{2}:\\d{2}\\b", in, U);
        // Přísnější varianta (00–23, 00–59): \\b(?:[01]\\d|2[0-3]):[0-5]\\d\\b

        // 3) color / colour
        findAll("4.3 color/colour", "\\bcolou?r\\b", in, U);
    }
}
