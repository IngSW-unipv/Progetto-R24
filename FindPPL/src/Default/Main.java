package Default;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Authenticator authenticator = new Authenticator();
        Database database = new Database(100);
        User currentUser = null;

        System.out.println("Benvenuto nel sistema!");

        boolean running = true;
        while (running) {
            System.out.println("\nSeleziona un'opzione:");
            if (currentUser == null) {
                // Mostra solo opzioni di registrazione, login ed uscita
                System.out.println("1. Registra un nuovo utente");
                System.out.println("2. Effettua il login");
                System.out.println("3. Esci");
            } else {
                // Mostra opzioni avanzate solo dopo il login
                System.out.println("3. Aggiungi un profilo");
                System.out.println("4. Cerca un profilo");
                System.out.println("5. Effettua il logout");
                System.out.println("6. Esci");
            }

            System.out.print("Scelta: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consuma la newline

            switch (choice) {
                case 1: // Registrazione utente
                	if (currentUser == null) {
                	    System.out.print("Inserisci username: ");
                	    String username = scanner.nextLine();
                	    System.out.print("Inserisci password: ");
                	    String password = scanner.nextLine();

                	    // Creazione del profilo
                	    System.out.print("Inserisci il nome: ");
                	    String name = scanner.nextLine();
                	    System.out.print("Inserisci il cognome: ");
                	    String surname = scanner.nextLine();
                	    System.out.print("Inserisci il colore degli occhi: ");
                	    String eyeColor = scanner.nextLine();
                	    System.out.print("Inserisci altezza: ");
                	    double height = scanner.nextDouble();
                	    scanner.nextLine(); // Consuma la newline
                	    System.out.print("Inserisci genere: ");
                	    String gender = scanner.nextLine();
                	    System.out.print("Inserisci età: ");
                	    int age = scanner.nextInt();
                	    scanner.nextLine(); // Consuma la newline

                	    Profile profile = new Profile(name, surname, eyeColor, height, gender, age);

                	    // Registrazione dell'utente con il profilo
                	    if (authenticator.registerUser(username, password, profile)) {
                	        System.out.println("Utente registrato con successo! Ora effettua il log in");
                	    } else {
                	        System.out.println("Username già esistente. Riprova.");
                	    }
                	} else {
                	    System.out.println("Opzione non valida. Effettua prima il logout.");
                	}


                case 2: // Login
                    if (currentUser == null) {
                        System.out.print("Inserisci username: ");
                        String username = scanner.nextLine();
                        System.out.print("Inserisci password: ");
                        String password = scanner.nextLine();
                        currentUser = authenticator.authenticate(username, password);
                        if (currentUser != null) {
                            System.out.println("Login effettuato con successo!");
                        } else {
                            System.out.println("Credenziali errate. Riprova.");
                        }
                    } else {
                        System.out.println("Opzione non valida. Effettua prima il logout.");
                    }
                    break;

                case 3: // Logout o aggiungi profilo (a seconda dello stato)
                    if (currentUser == null) {
                        running = false; // Esci
                        System.out.println("Arrivederci!");
                    } else {
                    	System.out.print("Inserisci il nome: ");
                	    String name = scanner.nextLine();
                	    System.out.print("Inserisci il cognome: ");
                	    String surname = scanner.nextLine();
                        System.out.print("Inserisci il colore degli occhi: ");
                        String eyeColor = scanner.nextLine();
                        System.out.print("Inserisci altezza: ");
                        double height = scanner.nextDouble();
                        scanner.nextLine(); // Consuma la newline
                        System.out.print("Inserisci genere: ");
                        String gender = scanner.nextLine();
                        System.out.print("Inserisci età: ");
                        int age = scanner.nextInt();
                        scanner.nextLine(); // Consuma la newline

                        Profile profile = new Profile(name, surname, eyeColor, height, gender, age);
                        currentUser.setProfile(profile);
                        database.addProfile(profile);

                        System.out.println("Profilo aggiunto con successo!");
                    }
                    break;

                case 4: // Cerca profilo
                    if (currentUser != null) {
                        System.out.print("Inserisci un termine di ricerca (colore occhi o genere): ");
                        String keyword = scanner.nextLine();
                        System.out.println("Risultati della ricerca:");
                        for (Profile foundProfile : database.searchProfiles(keyword)) {
                            System.out.println("- " + foundProfile.getEyeColor() + ", " + foundProfile.getHeight() + "m, " +
                                    foundProfile.getGender() + ", " + foundProfile.getAge() + " anni");
                        }
                    } else {
                        System.out.println("Devi effettuare il login per accedere a questa funzionalità.");
                    }
                    break;

                case 5: // Logout
                    if (currentUser != null) {
                        currentUser = null;
                        System.out.println("Logout effettuato con successo.");
                    } else {
                        System.out.println("Non sei loggato.");
                    }
                    break;

                case 6: // Esci
                    if (currentUser != null) {
                        System.out.println("Effettua prima il logout.");
                    } else {
                        running = false;
                        System.out.println("Arrivederci!");
                    }
                    break;

                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }
        }

        scanner.close();
    }
}
