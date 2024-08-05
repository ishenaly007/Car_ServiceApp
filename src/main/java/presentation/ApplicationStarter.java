package presentation;

public class ApplicationStarter {
    public static void main(String[] args) {

        /*UserRepository userRepository = new UserRepository();
        UserService userService = new UserServiceImpl(userRepository);

        // Добавляем пользователей
        User admin = new User("Admin User", "admin", "password123", Role.ADMIN);
        User client = new User("Client User", "client", "password123", Role.CLIENT);
        userRepository.add(admin);
        userRepository.add(client);

        // Фильтрация пользователей
        List<User> admins = userService.filterUsers(null, null, Role.CLIENT);
        List<User> namedAdmins = userService.filterUsers("Client User", null, null);
        List<User> namedAdminsByUsername = userService.filterUsers("Admin User", "admin", null);

        System.out.println("Admins: " + admins);
        System.out.println("Named Admins: " + namedAdmins);
        System.out.println("Named Admins by Username: " + namedAdminsByUsername);*/

        /*OrderRepository orderRepository = new OrderRepository();
        User client = new User("Client User", "client", "password123", Role.CLIENT);
        User client2 = new User("Manager User", "manager", "password123", Role.MANAGER);
        Car car = new Car("BMW", "m5", 50000, 2020, CarCondition.NEW);
        Order order1 = new Order(client, car, OrderStatus.PENDING, LocalDate.of(2023, 5, 20), ServiceType.MAINTENANCE);
        Order order2 = new Order(client2, car, OrderStatus.COMPLETED, LocalDate.of(2023, 6, 15), ServiceType.REPAIR);

        orderRepository.add(order1);
        orderRepository.add(order2);

        OrderService orderService = new OrderServiceImpl(orderRepository);

        // Фильтрация заказов
        List<Order> ordersInMay = orderService.filterOrders(LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 31), null, null, null);
        List<Order> pendingOrders = orderService.filterOrders(null, null, null, OrderStatus.COMPLETED, null);
        List<Order> clientOrders = orderService.filterOrders(null, null, client, null, null);

        System.out.println("Orders in May: " + ordersInMay);
        System.out.println("Completed Orders: " + pendingOrders);
        System.out.println("Client Orders: " + clientOrders);*/

        /*UserRepository userRepository = new UserRepository();
        UserService userService = new UserServiceImpl(userRepository);
        AuditService auditService = new AuditService();

        // Регистрация пользователя
        User admin = new User("Admin", "admin", "admin123", Role.ADMIN);
        userService.registerUser(admin);
        auditService.logAction(admin.getUsername(), "Registered new user: " + admin.getUsername());

        // Авторизация пользователя
        User loggedInUser = userService.authenticate("admin", "admin123");
        auditService.logAction(loggedInUser.getUsername(), "Logged in");

        // Выполнение действий в зависимости от роли
        if (loggedInUser.getRole() == Role.ADMIN) {
            System.out.println("Welcome, Admin!");
        }

        // Просмотр журнала logs
        List<String> auditLog = auditService.getAuditLog();
        auditLog.forEach(System.out::println);*/

        /*UserRepository userRepository = new UserRepository();
        CarRepository carRepository = new CarRepository();
        OrderRepository orderRepository = new OrderRepository();
        AuditService auditService = new AuditService();

        UserService userService = new UserServiceImpl(userRepository, auditService);
        CarService carService = new CarServiceImpl(carRepository, auditService);
        OrderService orderService = new OrderServiceImpl(orderRepository, carRepository, auditService);

        // Регистрация администратора
        User admin = new User("Admin", "admin", "admin123", Role.ADMIN);
        userService.registerUser(admin);

        // Авторизация администратора
        User loggedInUser = userService.authenticate("admin", "admin123");

        // Добавление нового автомобиля
        Car car = new Car("BMW", "X5", 2021, 50000, CarCondition.NEW);
        carService.addCar(car);

        // Создание нового заказа
        Order order = new Order(loggedInUser, car, OrderStatus.PENDING, LocalDate.now(), ServiceType.DETAILING);
        orderService.createOrder(order);

        // Обновление статуса заказа
        orderService.updateOrderStatus(order.getOrderId(), OrderStatus.COMPLETED);

        // Логирование действий
        auditService.getAuditLog().forEach(System.out::println);*/

        /*AuditService auditService = new AuditService();

        // Добавление записей в журнал аудита
        auditService.logAction("user1", "Created new car");
        auditService.logAction("user2", "Updated car details");
        auditService.logAction("user1", "Deleted a car");

        // Экспорт журнала аудита в файл
        String filename = "audit_log.txt";
        auditService.exportAuditLog(filename);*/
    }
}