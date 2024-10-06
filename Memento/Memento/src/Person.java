import java.util.Date;

public class Person {
    private String imie;
    private String nazwisko;
    private Date dataUrodzenia;
    private String miejsceUrodzenia;
    private String email;
    private int telefon;

    public Person(String imie, String nazwisko, Date dataUrodzenia, String miejsceUrodzenia, String email, int telefon) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.miejsceUrodzenia = miejsceUrodzenia;
        this.email = email;
        this.telefon = telefon;
    }

    //getters and setter
    public String getImie() { return imie; }
    public void setImie(String imie) { this.imie = imie; }
    public String getNazwisko() { return nazwisko; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }
    public Date getDataUrodzenia() { return dataUrodzenia; }
    public void setDataUrodzenia(Date dataUrodzenia) { this.dataUrodzenia = dataUrodzenia; }
    public String getMiejsceUrodzenia() { return miejsceUrodzenia; }
    public void setMiejsceUrodzenia(String miejsceUrodzenia) { this.miejsceUrodzenia = miejsceUrodzenia; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getTelefon() { return telefon; }
    public void setTelefon(int telefon) { this.telefon = telefon; }

    // Tworzenie kopii zapasowej (Memento)
    public PersonBackup saveToBackup() {
        return new PersonBackup(imie, nazwisko, dataUrodzenia, miejsceUrodzenia, email, telefon);
    }

    // Przywracanie stanu z kopii zapasowej
    public void restoreFromBackup(PersonBackup backup) {
        this.imie = backup.getImie();
        this.nazwisko = backup.getNazwisko();
        this.dataUrodzenia = backup.getDataUrodzenia();
        this.miejsceUrodzenia = backup.getMiejsceUrodzenia();
        this.email = backup.getEmail();
        this.telefon = backup.getTelefon();
    }

    @Override
    public String toString() {
        return "Person{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", miejsceUrodzenia='" + miejsceUrodzenia + '\'' +
                ", email='" + email + '\'' +
                ", telefon=" + telefon +
                '}';
    }
}
