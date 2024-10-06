import java.util.Date;

public class Main {
    public static void main(String[] args) {
        //obiekt person dodajemy pierwsza osobe
        Person person = new Person("Jan", "Kowalski", new Date(), "Warszawa", "jan.kowalski@example.com", 123456789);

        //kopia zapasowa
        PersonBackup backup = person.saveToBackup();
        System.out.println("Oryginalna osoba: " + person);

        //modyfikacja pierwszej osoby
        person.setImie("Adam");
        person.setNazwisko("Nowak");
        System.out.println("Zmodyfikowana osoba: " + person);

        //przywracanie kopii zapasowej
        person.restoreFromBackup(backup);
        System.out.println("Przywrócona osoba: " + person);
    }
}
