import java.util.Objects;

public class Data {

    private int year;
    private int month;

    public Data(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return month == data.month && year == data.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, year);
    }

    @Override
    public String toString() {
        return "Год= " + year +
                ", месяц= " + month;
    }
}