public class Ukol3 {
    public static void main(String[] args) {

        // 1) 123 CZK  ->  123 Kč
        String prices = "Ceny: 50 CZK, 199 CZK, 5CZK a 12345 CZK.";
        String pricesOut = prices.replaceAll("\\b(\\d+)\\s*CZK\\b", "$1 Kč");
        System.out.println(pricesOut);
        // -> Ceny: 50 Kč, 199 Kč, 5 Kč a 12345 Kč.

        // 2) Slova začínající velkým písmenem obalit do hranatých závorek
        String words = "Praha je Krásná. A Cesko taky. USA je zkratka.";
        String wordsOut = words.replaceAll("\\b\\p{Lu}\\p{L}*\\b", "[$0]");
        System.out.println(wordsOut);
        // -> [Praha] je [Krásná]. A [Cesko] taky. [USA] je zkratka.

        // 3) Všechny vícečetné mezery -> jedna mezera (pouze space, ne nové řádky)
        String spaces = "Ahoj   světe!  Tohle   má   více   mezer.";
        String spacesOut = spaces.replaceAll(" {2,}", " ");
        System.out.println(spacesOut);
        // -> Ahoj světe! Tohle má více mezer.

        // 4) V datech 2025-09-24 nahradit pomlčky za lomítka
        String dates = "Dnes: 2025-09-24, včera: 2025-09-23.";
        String datesOut = dates.replaceAll("\\b(\\d{4})-(\\d{2})-(\\d{2})\\b", "$1/$2/$3");
        System.out.println(datesOut);
        // -> Dnes: 2025/09/24, včera: 2025/09/23.
    }

}
