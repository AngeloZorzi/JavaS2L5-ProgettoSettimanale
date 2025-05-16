package ProgettoSettimanale;

import java.util.*;
import java.util.stream.Collectors;

public class Archive {
    private Set<Element> elements = new HashSet<>();

    public void addElement(Element e) {
        if (!elements.add(e)) {
            System.out.println("ISBN already registered: " + e.getISBN());
        }
    }

    public void removeElement(String ISBN) {
        boolean removed = elements.removeIf(e -> e.getISBN().equals(ISBN));
        if (!removed) {
            System.out.println("Element not found");
        }
    }

    public void updateElement(String ISBN, Element newElement) {
        removeElement(ISBN);
        addElement(newElement);
    }

    public Element ISBNSearch(String ISBN) throws NotFoundEx {
        return elements.stream()
                .filter(e -> e.getISBN().equals(ISBN))
                .findFirst()
                .orElseThrow(() -> new NotFoundEx("Element with ISBN #" + ISBN + " not found"));
    }

    public List<Element> yearSearch(int year) {
        return elements.stream()
                .filter(e -> e.getYear() == year)
                .collect(Collectors.toList());
    }

    public List<Book> authorSearch(String author) {
        return elements.stream()
                .filter(e -> e instanceof Book)
                .map(e -> (Book) e)
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public void stats() {
        long booksCount = elements.stream().filter(e -> e instanceof Book).count();
        long magazinesCount = elements.stream().filter(e -> e instanceof Magazine).count();

        elements.stream()
                .max(Comparator.comparingInt(Element::getPages))
                .ifPresent(max -> System.out.println("Element with more pages: " + max));

        double avgPages = elements.stream()
                .mapToInt(Element::getPages)
                .average()
                .orElse(0.0);

        System.out.println("Books in archive: " + booksCount);
        System.out.println("Magazines in archive: " + magazinesCount);
        System.out.println("Average pages: " + avgPages);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
}
