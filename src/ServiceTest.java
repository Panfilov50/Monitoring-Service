
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ServiceTest {
    Service service = new Service();


    @Test
    void addUser() {
        User user = new User(1, "John", "password", false);
        service.addUser(user);
        assertEquals(1, service.getUsers().size());
    }

    @Test
    void login() {
        User user = new User(1, "John", "password", false);
        service.addUser(user);
        assertEquals(user, service.login("John", "password"));
    }

    @Test
    void registerUser() {
        service.registerUser("John", "password");
        assertEquals(1, service.getUsers().size());
    }

    @Test
    void dateCheck() {
        User user = new User(1, "John", "password", false);
        service.addUser(user);
        assertTrue(service.dateCheck(user, 2020, 1));
        assertFalse(service.dateCheck(user, 1887, 1));
        assertFalse(service.dateCheck(user, 2020, 13));
    }

    @Test
    void addMeterReading() {
        User user = new User(1, "John", "password", false);
        service.addUser(user);
        service.addMeterReading(user, 2020, 1, 10, 20, 30);
        assertEquals(1, service.dataStorage.get(user).size());
    }

    @Test
    void meterReadingsForTheSelectedDate() {
        User user = new User(1, "John", "password", false);
        service.addUser(user);
        service.addMeterReading(user, 2020, 1, 10, 20, 30);
        MeterReading meterReading = service.meterReadingsForTheSelectedDate(user, 2020, 1);
        assertEquals(10, meterReading.getHotWater());
        assertEquals(20, meterReading.getColdWater());
        assertEquals(30, meterReading.getElectricity());
    }

    @Test
    void meterReadingsForAllTime() {
        User user = new User(1, "John", "password", false);
        service.addUser(user);
        service.addMeterReading(user, 2020, 1, 10, 20, 30);
        service.addMeterReading(user, 2020, 2, 20, 30, 40);
        service.meterReadingsForAllTime(user);
        assertEquals(2, service.dataStorage.get(user).size());
    }

}