import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class Main {
    private int length;
    private SecureRandom random;

    public Main(int length) {
        if (length != 4 && length != 6 && length != 12 && length != 16) {
            throw new IllegalArgumentException("Długość musi być 4, 6, 12 lub 16.");
        }
        this.length = length;
        this.random = new SecureRandom();
    }

    public String generateCode() {
        StringBuilder code = new StringBuilder();

        if (length == 4 || length == 6) {
            // Generowanie kodu PIN (0-9)
            for (int i = 0; i < length; i++) {
                code.append(random.nextInt(10)); // Losowanie cyfr 0-9
            }
        } else {
            // Generowanie tokena
            String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(characters.length());
                code.append(characters.charAt(index));
            }
        }

        return code.toString();
    }

    public void saveToFile(String fileName, String code) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(code);
            System.out.println("Kod zapisano do pliku: " + fileName);
        } catch (IOException e) {
            System.err.println("Błąd zapisu do pliku: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Main generator = new Main(4);
        String code = generator.generateCode();
        System.out.println("Wygenerowany kod: " + code);

        // zapis do pliku
        generator.saveToFile("generated_code.txt", code);
    }
}
