package ProgettoSettimanale;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Archive archive = new Archive();

        while(true){
            System.out.println("\n1. Add Book\n2. Add Magazine\n3. Search By ISBN\n4. Remove ISBN\n5. Search By Year\n6. Search By Author\n7. Update Element\n8. Stats\n0. Exit");
            System.out.println();
            String choice = scanner.nextLine();
            try {
                switch (choice){
                    case "1"->{
                        System.out.println("ISBN: ");
                        String ISBN = scanner.nextLine();
                        System.out.println("Title: ");
                        String title = scanner.nextLine();
                        System.out.println("Year:  ");
                        int year = Integer.parseInt(scanner.nextLine());
                        System.out.println("Pages: ");
                        int pages = Integer.parseInt(scanner.nextLine());
                        System.out.println("Author: ");
                        String author = scanner.nextLine();
                        System.out.println("Genre: ");
                        String genre = scanner.nextLine();
                        archive.addElement(new Book(ISBN,title,year,pages,author,genre));
                    }
                    case "2" ->{
                        System.out.println("ISBN: ");
                        String ISBN = scanner.nextLine();
                        System.out.println("Title: ");
                        String title = scanner.nextLine();
                        System.out.println("Year:  ");
                        int year = Integer.parseInt(scanner.nextLine());
                        System.out.println("Pages: ");
                        int pages = Integer.parseInt(scanner.nextLine());
                        System.out.println("Periodicity (SETTIMANALE, MENSILE, SEMESTRALE): ");
                        Periodicity periodicity = Periodicity.valueOf(scanner.nextLine().toUpperCase());
                        archive.addElement(new Magazine(ISBN,title,year,pages,periodicity));
                    }
                    case "3" -> {
                        System.out.println("Type ISBN: ");
                        String ISBN = scanner.nextLine();
                        System.out.println(archive.ISBNSearch(ISBN));
                    }
                    case "4" -> {
                        System.out.println("Type ISBN To Remove: ");
                        archive.removeElement(scanner.nextLine());
                    }
                    case "5" -> {
                        if (archive.isEmpty()) {
                            System.out.println("Archive is empty.");
                        } else {
                            System.out.println("Type Year: ");
                            int year = Integer.parseInt(scanner.nextLine());
                            List<Element> results = archive.yearSearch(year);
                            if (results.isEmpty()) {
                                System.out.println("No element found for this year.");
                            } else {
                                results.forEach(System.out::println);
                            }
                        }
                    }
                    case "6" -> {
                        if (archive.isEmpty()) {
                            System.out.println("Archive is empty.");
                        } else {
                            System.out.println("Type Author: ");
                            List<Book> results = archive.authorSearch(scanner.nextLine());
                            if (results.isEmpty()) {
                                System.out.println("No book found by this author.");
                            } else {
                                results.forEach(System.out::println);
                            }
                        }
                    }

                    case "7" -> {
                        System.out.println("Type ISBN To Update: ");
                        String ISBN = scanner.nextLine();
                        Element oldISBN = archive.ISBNSearch(ISBN);
                        if (oldISBN instanceof Book){
                            System.out.println("Title: ");
                            String title = scanner.nextLine();
                            System.out.println("Year:  ");
                            int year = Integer.parseInt(scanner.nextLine());
                            System.out.println("Pages: ");
                            int pages = Integer.parseInt(scanner.nextLine());
                            System.out.println("Author: ");
                            String author = scanner.nextLine();
                            System.out.println("Genre: ");
                            String genre = scanner.nextLine();
                            archive.updateElement(ISBN, new Book(ISBN,title,year,pages,author,genre));
                        } else if (oldISBN instanceof Magazine) {
                            System.out.println("Title: ");
                            String title = scanner.nextLine();
                            System.out.println("Year:  ");
                            int year = Integer.parseInt(scanner.nextLine());
                            System.out.println("Pages: ");
                            int pages = Integer.parseInt(scanner.nextLine());
                            System.out.println("Periodicity (SETTIMANALE, MENSILE, SEMESTRALE): ");
                            Periodicity periodicity = Periodicity.valueOf(scanner.nextLine().toUpperCase());
                            archive.updateElement(ISBN, new Magazine(ISBN,title,year,pages,periodicity));
                        }
                    }
                    case "8" -> {
                        if (archive.isEmpty()) {
                            System.out.println("Archive is empty.");
                        } else {
                            archive.stats();
                        }
                    }
                    case "0" -> {
                        System.out.println("Exit");
                        return;
                    }
                    default -> System.out.println("Invalid request");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please type a valid number");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Invalid value or format.");
            } catch (NullPointerException e) {
                System.out.println("Error: Missing element.");
            } catch (NotFoundEx e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }
}
