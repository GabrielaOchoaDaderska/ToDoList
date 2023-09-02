import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {

    public static void main(String[] args) {
        ArrayList<String> listaZadan = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int wybor;

        do {
            System.out.println("==== Menu ====");
            System.out.println("1. Dodaj zadanie");
            System.out.println("2. Wyświetl zadania");
            System.out.println("3. Usuń zadanie");
            System.out.println("4. Zakończ");
            System.out.print("Wybierz opcję: ");
            wybor = scanner.nextInt();
            scanner.nextLine(); 

            switch (wybor) {
                case 1:
                    System.out.print("Podaj zadanie do dodania: ");
                    String zadanie = scanner.nextLine();
                    listaZadan.add(zadanie);
                    System.out.println("Zadanie dodane!");
                    break;
                case 2:
                    System.out.println("==== Lista zadań ====");
                    for (int i = 0; i < listaZadan.size(); i++) {
                        System.out.println((i + 1) + ". " + listaZadan.get(i));
                    }
                    break;
                case 3:
                    if (listaZadan.isEmpty()) {
                        System.out.println("Lista zadań jest pusta.");
                    } else {
                        System.out.print("Podaj numer zadania do usunięcia: ");
                        int numer = scanner.nextInt();
                        if (numer >= 1 && numer <= listaZadan.size()) {
                            listaZadan.remove(numer - 1);
                            System.out.println("Zadanie usunięte!");
                        } else {
                            System.out.println("Nieprawidłowy numer zadania.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Zakończono program.");
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór.");
            }

        } while (wybor != 4);

        scanner.close();
    }
}
