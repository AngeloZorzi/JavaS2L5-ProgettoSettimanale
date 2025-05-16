package ProgettoSettimanale;

public class Magazine extends Element{
    private Periodicity periodicity;

    public Magazine(String ISBN, String title, int year, int pages, Periodicity periodicity) {
        super(ISBN, title, year, pages);
        this.periodicity = periodicity;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "periodicity=" + periodicity +
                '}';
    }
}
