import java.util.Date;

public class MeterReading {
    private User user;
    private int hotWater;
    private int coldWater;
    private int electricity;

    public MeterReading(User user, int hotWater, int coldWater, int electricity) {
        this.user = user;

        this.hotWater = hotWater;
        this.coldWater = coldWater;
        this.electricity = electricity;
    }

    public User getUser() {
        return user;
    }

    public int getHotWater() {
        return hotWater;
    }

    public int getColdWater() {
        return coldWater;
    }

    public int getElectricity() {
        return electricity;
    }

    @Override
    public String toString() {
        return "Данные счетчиков пользователя " + user.getName() +
                ", Горячая вода= " + hotWater +
                ", Холодная вода= " + coldWater +
                ", Электричество= " + electricity +
                "\n";
    }
}
