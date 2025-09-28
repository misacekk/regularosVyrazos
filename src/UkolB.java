import java.util.regex.*;

public class UkolB {
    static final int U = Pattern.UNICODE_CASE | Pattern.UNICODE_CHARACTER_CLASS;

    public static void main(String[] args) {
        String in = String.join("\n",
                "ID: 123  something",
                "Price: 250 CZK, another 99 CZK.",
                "BEGIN\nřádek 1\nřádek 2\nEND",
                "ID: 007"
        );

        // 1) Vyhledá "ID: <číslo>" a vytiskne jen číslo
        System.out.println("\n=== 6.1 ID: <číslo> → vypiš číslo ===");
        Matcher m = Pattern.compile("\\bID:\\s*(\\d+)\\b", U).matcher(in);
        while (m.find()){
            System.out.println("ID číslo: " + m.group(1));
        }

        // 2) Nahradí "<číslo> CZK" → "<číslo> Kč"
        System.out.println("\n=== 6.2 Náhrada CZK → Kč ===");
        String replaced = in.replaceAll("\\b(\\d+)\\s*CZK\\b", "$1 Kč");
        System.out.println(replaced);

        // 3) DOTALL: BEGIN ... END přes více řádků
        System.out.println("\n=== 6.3 DOTALL: BEGIN ... END ===");
        Matcher m2 = Pattern.compile("BEGIN(.*?)END", Pattern.DOTALL | U).matcher(in);
        while (m2.find()){
            System.out.println("Mezi BEGIN/END: \"" + m2.group(1).trim() + "\"");
        }
    }
}

