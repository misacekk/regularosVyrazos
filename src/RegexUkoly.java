import java.util.regex.*;
import java.util.*;

public class RegexUkoly {
    // Pomocná metoda: vytiskne všechny shody regexu v daném vstupu
    static void findAll(String title, String regex, String input, int flags) {
        System.out.println("\n=== " + title + " ===");
        Pattern p = Pattern.compile(regex, flags);
        Matcher m = p.matcher(input);
        boolean any = false;
        while (m.find()) {
            any = true;
            if (m.groupCount() >= 1) {
                // vypiš celé shody + zachycené skupiny
                System.out.print("MATCH: \"" + m.group() + "\"");
                for (int i = 1; i <= m.groupCount(); i++) {
                    System.out.print("  [group " + i + " = \"" + m.group(i) + "\"]");
                }
                System.out.println();
            } else {
                System.out.println("MATCH: \"" + m.group() + "\"");
            }
        }
        if (!any) System.out.println("(žádné shody)");
    }

    public static void main(String[] args) {
        final int U = Pattern.UNICODE_CASE | Pattern.UNICODE_CHARACTER_CLASS;

        // ======================
        // ÚKOL 1
        // ======================
        String input1 = "Hello A b Z 9 HelloWorld";
        // 1) Přesně řetězec Hello
        findAll("1.1 Přesně \"Hello\"", "\\bHello\\b", input1, U);

        // 2) Jediné velké písmeno (A–Z)
        findAll("1.2 Jedno velké písmeno", "\\b[A-Z]\\b", input1, U);

        // 3) Jediné libovolné písmeno (malé/velké)
        findAll("1.3 Jedno libovolné písmeno", "\\p{L}", input1, U);

        // 4) Jednociferné číslo
        findAll("1.4 Jednociferné číslo", "\\b\\d\\b", input1, U);


        // ======================
        // ÚKOL 2
        // ======================
        String input2 = "ok OK Ahoj čau 42 12345 ha hahaha hahahaha _a a_ a1 a_b";
        // 1) Slovo pouze z písmen (alespoň 2)
        findAll("2.1 Pouze písmena, 2+", "\\b\\p{L}{2,}\\b", input2, U);

        // 2) Číslo s 3–5 číslicemi
        findAll("2.2 Číslo 3–5 číslic", "\\b\\d{3,5}\\b", input2, U);

        // 3) Výskyty 'ha' opakované 2–4× (např. hahaha)
        findAll("2.3 (ha){2,4}", "\\b(?:ha){2,4}\\b", input2, U);

        // 4) Token typu slovo (písmena/číslice/_), 1+
        findAll("2.4 \\w+ (slovní token)", "[\\p{L}\\p{N}_]+", input2, U);


        // ======================
        // ÚKOL 3
        // ======================
        String input3 = String.join("\n",
                "12345",
                "  ",
                "cat",
                "concatenate",
                "42abc",
                ""
        );
        // 1) Řádky jen číslice
        findAll("3.1 Řádky jen číslice", "^(?:\\d+)$", input3, Pattern.MULTILINE | U);

        // 2) Prázdný řádek (pouze whitespace)
        findAll("3.2 Prázdný řádek (whitespace)", "^\\s*$", input3, Pattern.MULTILINE | U);

        // 3) Celé slovo 'cat' (ne část 'concatenate')
        findAll("3.3 Celé slovo 'cat'", "\\bcat\\b", input3, Pattern.MULTILINE | U);


        // ======================
        // ÚKOL 4
        // ======================
        String input4 = "cz-123 sk-987 cz- sk- abc cz-99999 12:34 99:00 color colour";
        // 1) cz|sk + '-' + čísla
        findAll("4.1 cz|sk-čísla", "\\b(?:cz|sk)-\\d+\\b", input4, U);

        // 2) Čas HH:MM (základní validace 00–99)
        findAll("4.2 Čas HH:MM (00–99)", "\\b\\d{2}:\\d{2}\\b", input4, U);

        // 3) Varianty color/colour
        findAll("4.3 color/colour", "\\bcolou?r\\b", input4, U);


        // ======================
        // ÚKOL 5
        // ======================
        String input5 = String.join("\n",
                "jmeno.prijmeni@domena.cz",
                "x@y.co",
                "abc@domena.travel",
                "psc 123 45",
                "123 45",
                "2025-09-18",
                "1999-1-01",
                "http://example.com",
                "https://sub.example.cz/path?q=1",
                "ftp://nope"
        );
        // 1) E-mail (zjednodušeně)
        // uživatel: písmena/číslice/._%+-
        // doména: písmena/číslice/.-   + TLD 2+ písmen
        findAll("5.1 E-mail (zjednodušeně)",
                "\\b[\\p{L}\\p{N}._%+-]+@[\\p{L}\\p{N}.-]+\\.[A-Za-z]{2,}\\b",
                input5, U);

        // 2) České PSČ 123 45 (mezera povinně)
        findAll("5.2 PSČ '123 45'", "\\b\\d{3}\\s\\d{2}\\b", input5, U);

        // 3) Datum YYYY-MM-DD (bez logické kontroly)
        findAll("5.3 Datum YYYY-MM-DD", "\\b\\d{4}-\\d{2}-\\d{2}\\b", input5, U);

        // 4) URL začínající http:// nebo https://
        findAll("5.4 URL http(s)://", "\\bhttps?://\\S+\\b", input5, U);


        // ======================
        // ÚKOL 6
        // ======================
        String input6 = String.join("\n",
                "ID: 123  something",
                "Price: 250 CZK, another 99 CZK.",
                "BEGIN\nřádek 1\nřádek 2\nEND",
                "ID: 007"
        );

        // 1) Najdi "ID: <číslo>" a vytiskni jen číslo
        System.out.println("\n=== 6.1 ID: <číslo> → vypiš číslo ===");
        Pattern pId = Pattern.compile("\\bID:\\s*(\\d+)\\b", U);
        Matcher mId = pId.matcher(input6);
        while (mId.find()) {
            System.out.println("ID číslo: " + mId.group(1));
        }

        // 2) Náhrada "<číslo> CZK" → "<číslo> Kč"
        System.out.println("\n=== 6.2 Náhrada CZK → Kč ===");
        String replaced = input6.replaceAll("\\b(\\d+)\\s*CZK\\b", "$1 Kč");
        System.out.println(replaced);

        // 3) DOTALL: chytit text od BEGIN po END přes více řádků
        System.out.println("\n=== 6.3 DOTALL: BEGIN ... END ===");
        Pattern pDotall = Pattern.compile("BEGIN(.*?)END", Pattern.DOTALL | U);
        Matcher mDotall = pDotall.matcher(input6);
        while (mDotall.find()) {
            System.out.println("Zachyceno (mezi): \"" + mDotall.group(1).trim() + "\"");
        }


        // ======================
        // ÚKOL 7
        // ======================
        // Zadání pro úkol 7 v dotazu chybí – až ho upřesníš, doplním i řešení.
    }
}
