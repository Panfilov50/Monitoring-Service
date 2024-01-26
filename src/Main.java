import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        Scanner scanner = new Scanner(System.in);
        service.addUser(new User(1, "Admin", "123", true));
        service.registerUser("1", "1");

        User currentUser = null;
        boolean running = true;

        while (running) {
            if (currentUser == null) {
                System.out.println("1. Вход");
                System.out.println("2. Регистрация");
                System.out.println("3. Выход");
                System.out.print("Выберите опцию: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        System.out.print("Введите логин: ");
                        String username = scanner.nextLine();
                        System.out.print("Введите пароль: ");
                        String password = scanner.nextLine();
                        currentUser = service.login(username, password);
                        if (currentUser == null) {
                            System.out.println("Неправильное имя или пароль.");
                        }
                        break;
                    case 2:
                        System.out.println("Введите логин: ");
                        String newUserName = scanner.nextLine();
                        System.out.println("Введите пароль: ");
                        String newPassword = scanner.nextLine();
                        service.registerUser(newUserName, newPassword);
                        System.out.println("Пользователь успешно зарегистрирован.");
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Неизвестная команда. Повторите ввод.");
                }
            } else {
                while (running) {
                    if (currentUser.isAdmin()) {
                        System.out.println("1. Показать список пользователей");
                        System.out.println("2. Показать показания счетчика конкретного пользователя");
                        System.out.println("3. Отредактировать показания счетчика конкретного пользователя");
                        System.out.println("4. Дать пользователю права администратора");
                        System.out.println("5. Удалить пользователя");
                        System.out.println("6. Выход");
                        System.out.print("Выберите опцию: ");
                        int option = scanner.nextInt();
                        switch (option) {
                            case 1:
                                for (User user : service.getUsers()) {
                                    System.out.print(user);
                                }
                                break;
                            case 2:
                                System.out.println("Введите ID пользователя: ");
                                int idUser = scanner.nextInt();
                                service.adminMeterReadingsForAllTime(idUser);
                                break;
                            case 3:
                                System.out.println("Введите ID пользователя для изменения показателей: ");
                                int idToEditMeterReading = scanner.nextInt();
                                System.out.println("Введите год: ");
                                int year = scanner.nextInt();
                                System.out.println("Введите месяц: ");
                                int month = scanner.nextInt();
                                if (service.adminDateCheck (idToEditMeterReading, year, month)) {
                                    System.out.println("Пользователь не передавал показания счетчиков в этом месяце. Продолжить?");
                                    System.out.println("1. Да");
                                    System.out.println("2. Вернуться");
                                    int nextComand = scanner.nextInt();
                                    if (nextComand == 2) {
                                        break;
                                    }
                                }
                                    System.out.println("Введите показатель горячей воды: ");
                                    int hotWater = scanner.nextInt();
                                    System.out.println("Введите показатель холодной воды: ");
                                    int coldWater = scanner.nextInt();
                                    System.out.println("Введите показатель электричества: ");
                                    int electricity = scanner.nextInt();
                                    service.AdminAddMeterReading(idToEditMeterReading, year, month, hotWater, coldWater, electricity);
                                break;
                            case 4:
                                System.out.println("Введите ID пользователя для назначения администратором: ");
                                int idToEdit = scanner.nextInt();
                                service.editUser(idToEdit);
                                break;
                            case 5:
                                System.out.println("Введите ID пользователя для удаления: ");
                                int idToRemove = scanner.nextInt();
                                service.deleteUser(idToRemove);
                                break;
                            case 6:
                                System.out.println("Выход");
                                return;
                            default:
                                System.out.println("Неизвестная команда. Повторите ввод.");
                        }

                    } else {
                        System.out.println("1. Передать показания счетчиков");
                        System.out.println("2. Посмотреть свои показания счетчиков за выбранную дату");
                        System.out.println("3. Посмотреть все свои ранние переданный показания счетчиков");
                        System.out.println("4. Сменить пароль");
                        System.out.println("5. Выход");
                        System.out.println("Введите номер команды: ");
                        int option = scanner.nextInt();
                        switch (option) {
                            case 1:
                                System.out.println("Введите год: ");
                                int year = scanner.nextInt();
                                System.out.println("Введите месяц: ");
                                int month = scanner.nextInt();
                                if (service.dateCheck (currentUser, year, month)) {
                                    System.out.println("Введите показатель горячей воды: ");
                                    int hotWater = scanner.nextInt();
                                    System.out.println("Введите показатель холодной воды: ");
                                    int coldWater = scanner.nextInt();
                                    System.out.println("Введите показатель электричества: ");
                                    int electricity = scanner.nextInt();
                                    service.addMeterReading(currentUser, year, month, hotWater, coldWater, electricity);
                                    System.out.println("Показатели успешно переданы");
                                    break;
                                } else {
                                    System.out.println(("Вы уже передавали показания счетчиков в этом месяце"));
                                }
                                break;
                            case 2:
                                System.out.println("Введите год: ");
                                int yearToSearch = scanner.nextInt();
                                System.out.println("Введите месяц: ");
                                int monthToSearch = scanner.nextInt();
                                if (!service.dateCheck (currentUser, yearToSearch, monthToSearch)) {
                                    System.out.println(service.meterReadingsForTheSelectedDate(currentUser, yearToSearch, monthToSearch));
                                }
                                else {
                                    System.out.println("Вы не передавали показания счетчиков в этом месяце");
                                }
                                break;
                            case 3:
                                service.meterReadingsForAllTime(currentUser);
                                break;
                            case 4:
                                System.out.print("Введите новый пароль: ");
                                String newPassword = scanner.nextLine();
                                service.changePassword(currentUser, newPassword);
                                return;
                            case 5:
                                System.out.println("Выход");
                                return;
                            default:
                                System.out.println("Неизвестная команда. Повторите ввод.");
                        }
                    }
                }

            }
        }
        scanner.close();
    }
}