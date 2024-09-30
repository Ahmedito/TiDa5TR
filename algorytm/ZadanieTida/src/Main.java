import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String inputFile = "src/5TR_Zadanie programistyczne pod INF.04 - Raport obecności 8-09-24 (5TR).csv";
        String outputFile = "5TR_Zadanie-INF04.txt"; // Zmieniamy rozszerzenie na .txt
        List<String[]> uczestnicy = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(inputFile)), StandardCharsets.UTF_16))) {
            String line;
            boolean inParticipantSection = false;

            // Przetwarzanie pliku CSV (sekcja #2)
            while ((line = br.readLine()) != null) {
                if (line.contains("Uczestnicy")) {
                    inParticipantSection = true;
                    continue;
                }
                if (inParticipantSection) {
                    if (line.trim().isEmpty()) break;
                    String[] data = line.split("\t"); // tab = separator
                    if (data.length >= 4) {
                        String nazwisko = data[0].trim();
                        String imie = data[1].trim();
                        String czas = data[3].trim(); // Czas udziału
                        if (nazwisko.isEmpty()) nazwisko = "Nieznane";
                        if (imie.isEmpty()) imie = "Nieznane";

                        // Sprawdzanie minut
                        int minutes = parseMinutes(czas);
                        boolean dostanieZaswiadczenie = minutes >= 60;
                        String status = dostanieZaswiadczenie ? "Zaświadczenie" : "Brak zaświadczenia";
                        uczestnicy.add(new String[]{nazwisko, imie, status, czas});
                    }
                }
            }

            // Sortowanie po nazwisku
            uczestnicy.sort(Comparator.comparing(o -> o[0]));

            // Wyświetlanie  w terminalu
            displayParticipants(uczestnicy);

            // Zapis TXT
            saveToTxt(uczestnicy, outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metoda do wyświetlania w terminalu
    private static void displayParticipants(List<String[]> uczestnicy) {
        System.out.println("Uczestnicy:");
        for (String[] uczestnik : uczestnicy) {
            System.out.println(String.join(", ", uczestnik));
        }
    }

    // Zapis do pliku TXT
    private static void saveToTxt(List<String[]> uczestnicy, String outputFile) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(outputFile), StandardCharsets.UTF_16)) { // Używamy UTF-16
            bw.write("Nazwisko,Imię,Status_zaświadczenia,Czas_uczestnictwa\n");
            for (String[] uczestnik : uczestnicy) {
                bw.write(String.join(",", uczestnik) + "\n");
            }
        }
        System.out.println("Dane zapisane do pliku TXT: " + outputFile);
    }

    // Metoda do konwersji czasu na minuty
    private static int parseMinutes(String czas) {
        try {
            String[] parts = czas.split(" ");
            int totalMinutes = 0;
            for (String part : parts) {
                if (part.endsWith("min")) {
                    totalMinutes += Integer.parseInt(part.replace("min", "").trim());
                } else if (part.endsWith("godz.")) {
                    totalMinutes += Integer.parseInt(part.replace("godz.", "").trim()) * 60;
                }
            }
            return totalMinutes;
        } catch (NumberFormatException e) {
            return 0; 
        }
    }
}
