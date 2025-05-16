package ProgettoSettimanale;

import java.util.*;
import java.util.stream.Collectors;

public class Archive {
    private Map<String, Element> elements = new HashMap<>();
    public void addElement (Element e){
        if (elements.containsKey(e.getISBN())){
            System.out.println("ISBN already registered" + e.getISBN());
        } else {
            elements.put(e.getISBN(),e);
        }
    }
    public void removeElement(String ISBN){
        if (elements.remove(ISBN) == null){
            System.out.println("Element not found");
        }
    }
    public void updateElement(String ISBN, Element newElement){
        if (elements.containsKey(ISBN)){
            elements.put(ISBN, newElement);
        }else {
            System.out.println("Element not found, please try again");
        }
    }


    public Element ISBNSearch (String ISBN) throws NotFoundEx{
        Element e = elements.get(ISBN);
        if (e == null){
            throw new NotFoundEx("Element with ISBN #"+ISBN+" not found");
        }
        return e;
    }
    public List<Element> yearSearch(int year){
        return elements.values().stream().filter(element -> element.getYear()==year).collect(Collectors.toList());
    }
    public List<Book> authorSearch(String  author){
        return elements.values().stream().filter(element -> element instanceof Book).map(element -> (Book) element).filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public void stats(){
        long booksNu = elements.values().stream().filter(element -> element instanceof Book).count();
        long magazinesNu = elements.values().stream().filter(element -> element instanceof Magazine).count();

        elements.values().stream().max(Comparator.comparingInt(Element::getPages)).ifPresent(max -> System.out.println("Element with more pages: "+max));

       double averagePages = elements.values().stream().mapToInt(Element::getPages).average().orElse(0.0);
        System.out.println("Books in archive: "+ booksNu);
        System.out.println("Magazines in archive: "+ magazinesNu);
        System.out.println("Average pages: "+ averagePages );
    }
    public boolean isEmpty() {
        return elements.isEmpty();
    }
}
