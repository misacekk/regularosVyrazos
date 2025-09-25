
import java.util.regex.*;

public class Ukol2 {

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
        String input2 = "ok OK Ahoj čau 42 12345 ha hahaha hahahaha _a a_ a1 a_b";


        // 1) Slovo pouze z písmen (alespoň 2)
        findAll("2.1 Pouze písmena, 2+", "\\b\\p{L}{2,}\\b", input2);

        // 2) Číslo s 3–5 číslicemi
        findAll("2.2 Číslo 3–5 číslic", "\\b\\d{3,5}\\b", input2);

        // 3) Výskyty 'ha' opakované 2–4× (např. hahaha)
        findAll("2.3 (ha){2,4}", "\\b(ha){2,4}\\b", input2);

        // 4) Token typu slovo (písmena/číslice/_), 1+
        findAll("2.4 \\w+ (slovní token)", "[\\p{L}\\p{N}_]+", input2);
    }
}
