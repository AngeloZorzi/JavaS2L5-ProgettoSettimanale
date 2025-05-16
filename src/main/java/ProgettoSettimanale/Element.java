package ProgettoSettimanale;

abstract class Element {
    private String ISBN;
    private String title;
    private int year;
    private int pages;

    public Element(String ISBN, String title, int year, int pages){
        this.ISBN = ISBN;
        this.title = title;
        this.year = year;
        this.pages = pages;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Element{" +
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", pages=" + pages +
                '}';
    }
}
