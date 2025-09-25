
import java.util.regex.*;

public class Ukol1 {

    static void findAll(String title, String regex, String input) {
        System.out.println("\n=== " + title + " ===");
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
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
        String in = "Hello A b Z 9 HelloWorld";

        // 1) Přesně "Hello"
        findAll("1.1 Hello", "\\bHello\\b", in);

        // 2) Jediné velké písmeno (A–Z)
        findAll("1.2 Jedno velké písmeno", "\\b[A-Z]\\b", in);

        // 3) Jediné libovolné písmeno (malé/velké)
        findAll("1.3 Libovolné písmeno", "\\p", in);

        // 4) Jednociferné číslo
        findAll("1.4 Jednociferné číslo", "\\b\\d\\b", in);
    }
}
