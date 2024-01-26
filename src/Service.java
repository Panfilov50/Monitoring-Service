
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Service {
    HashMap <User, HashMap<Data, MeterReading>> dataStorage = new HashMap<>();
    private long id = dataStorage.size();

    public void addUser(User user) {
        HashMap<Data, MeterReading> userData = new HashMap<>();
        dataStorage.put(user, userData);
    }

    public User login(String username, String password) {
        return  dataStorage.entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .filter(user -> user.getName().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void registerUser(String name, String password) {
        User newUser = new User(id, name, password, false);
        HashMap<Data, MeterReading> userData = new HashMap<>();
        dataStorage.put(newUser, userData);
    }

    public List<User> getUsers() {
        return dataStorage.entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .toList();
    }

    public void adminMeterReadingsForAllTime (int id) {
        meterReadingsForAllTime(dataStorage.entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .filter(user -> user.getId() == id)
                .findFirst()
                .get());
    }

    public boolean adminDateCheck (int id, int year, int month) {
        return dateCheck(dataStorage.entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .filter(user -> user.getId() == id)
                .findFirst()
                .get(), year, month);
    }
    public void AdminAddMeterReading(int id, int year, int month, int hotWater, int coldWater, int electricity) {
        addMeterReading(dataStorage.entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .filter(user -> user.getId() == id)
                .findFirst()
                .get(),year, month, hotWater, coldWater, electricity);
    }


    public void editUser(int id) {
        dataStorage.entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .filter(user -> user.getId() == id)
                .findFirst()
                .get().setAdmin(true);
    }

    public void deleteUser(int id) {
        dataStorage.remove(dataStorage.entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .filter(user -> user.getId() == id)
                .findFirst()
                .get());
    }

    public boolean dateCheck (User user, int year, int month) {
        if (year < 1888 ){
            System.out.println("Год указан некорректно");
            return false;
        }
        if (month < 1 || month > 12) {
            System.out.println("Месяц должен быть между 1 и 12.");
            return false;
        }
        Data data = new Data(year, month);
        return !dataStorage.get(user).containsKey(data);
    }

    public void addMeterReading(User user, int year, int month, int hotWater, int coldWater, int electricity) {
        Data data = new Data(year, month);
        MeterReading meterReading = new MeterReading(user, hotWater, coldWater, electricity);
        dataStorage.get(user).put(data, meterReading);

    }

    public MeterReading meterReadingsForTheSelectedDate(User user, int year, int month) {
        Data data = dataStorage
                .get(user)
                .keySet()
                .stream()
                .filter(date -> date.getYear() == year && date.getMonth() == month)
                .findFirst()
                .get();
        return dataStorage.get(user).get(data);
    }

    public void meterReadingsForAllTime(User user) {
      System.out.println(dataStorage.get(user).toString());
    }

    public void changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
    }
}
