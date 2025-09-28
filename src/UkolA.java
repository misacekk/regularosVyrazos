import java.util.regex.*;

public class UkolA {
    // má brát v úvahu i Unicode znaky, nejen ASCII a předdefinované regexové zkratky jako \w, \d, \s odpovídají plně podle Unicode standardu, nejen ASCII.
    static final int U = Pattern.UNICODE_CASE | Pattern.UNICODE_CHARACTER_CLASS;

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
        //Vytvori retezec s vice radky (vcetne odradkovani)
        // Stejne jako in = "12345\n" +  "  \n" +  "cat\n" + "concatenate\n" + "42abc\n" +  "\n";
        String in = String.join("\n",
                "12345",
                "  ",
                "cat",
                "concatenate",
                "42abc",
                ""
        );

        // 1) Řádky jen číslice
        findAll("3.1 Jen číslice", "^\\d+$", in, Pattern.MULTILINE | U);

        // 2) Prázdný řádek (jen whitespace)
        findAll("3.2 Prázdný řádek", "^\\s*$", in, Pattern.MULTILINE | U);

        // 3) Celé slovo 'cat'
        findAll("3.3 Slovo 'cat'", "\\bcat\\b", in, Pattern.MULTILINE | U);
    }
}
