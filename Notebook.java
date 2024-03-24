import java.util.*;

public class Notebook {
    private String модель;
    private int озу;
    private int объемЖД;
    private String операционнаяСистема;
    private String цвет;

    // Конструктор
    public Notebook(String модель, int озу, int объемЖД, String операционнаяСистема, String цвет) {
        this.модель = модель;
        this.озу = озу;
        this.объемЖД = объемЖД;
        this.операционнаяСистема = операционнаяСистема;
        this.цвет = цвет;
    }

    // Геттеры
    public String getМодель() {
        return модель;
    }

    public int getОзу() {
        return озу;
    }

    public int getОбъемЖД() {
        return объемЖД;
    }

    public String getОперационнаяСистема() {
        return операционнаяСистема;
    }

    public String getЦвет() {
        return цвет;
    }

    // Метод для фильтрации ноутбуков
    public static Set<Notebook> фильтроватьНоутбуки(Set<Notebook> ноутбуки, Map<String, Object> критерии) {
        Set<Notebook> результат = new HashSet<>();
        for (Notebook ноутбук : ноутбуки) {
            boolean подходит = true;
            for (Map.Entry<String, Object> entry : критерии.entrySet()) {
                String критерий = entry.getKey();
                Object значение = entry.getValue();
                switch (критерий) {
                    case "ОЗУ":
                        if (ноутбук.getОзу() < (int) значение) {
                            подходит = false;
                        }
                        break;
                    case "ОбъемЖД":
                        if (ноутбук.getОбъемЖД() < (int) значение) {
                            подходит = false;
                        }
                        break;
                    case "ОперационнаяСистема":
                        if (!ноутбук.getОперационнаяСистема().equals(значение)) {
                            подходит = false;
                        }
                        break;
                    case "Цвет":
                        if (!ноутбук.getЦвет().equals(значение)) {
                            подходит = false;
                        }
                        break;
                    default:
                        // Неизвестный критерий, игнорируем его
                        break;
                }
            }
            if (подходит) {
                результат.add(ноутбук);
            }
        }
        return результат;
    }

    public static void main(String[] args) {
        // Создаем несколько ноутбуков
        Notebook ноутбук1 = new Notebook("Модель 1", 8, 512, "Windows", "Черный");
        Notebook ноутбук2 = new Notebook("Модель 2", 16, 1024, "MacOS", "Серебряный");
        Notebook ноутбук3 = new Notebook("Модель 3", 4, 256, "Linux", "Красный");

        // Создаем множество ноутбуков
        Set<Notebook> ноутбуки = new HashSet<>();
        ноутбуки.add(ноутбук1);
        ноутбуки.add(ноутбук2);
        ноутбуки.add(ноутбук3);

        // Запрашиваем критерии фильтрации у пользователя
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> критерии = new HashMap<>();
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        int выбор = scanner.nextInt();
        switch (выбор) {
            case 1:
                System.out.println("Введите минимальное количество ОЗУ:");
                int озу = scanner.nextInt();
                критерии.put("ОЗУ", озу);
                break;
            case 2:
                System.out.println("Введите минимальный объем ЖД:");
                int объемЖД = scanner.nextInt();
                критерии.put("ОбъемЖД", объемЖД);
                break;
            case 3:
                System.out.println("Введите операционную систему:");
                String операционнаяСистема = scanner.next();
                критерии.put("ОперационнаяСистема", операционнаяСистема);
                break;
            case 4:
                System.out.println("Введите цвет:");
                String цвет = scanner.next();
                критерии.put("Цвет", цвет);
                break;
            default:
                System.out.println("Некорректный ввод");
                return;
        }

        // Фильтруем ноутбуки и выводим результат
        Set<Notebook> отфильтрованныеНоутбуки = фильтроватьНоутбуки(ноутбуки, критерии);
        System.out.println("Найденные ноутбуки:");
        for (Notebook ноутбук : отфильтрованныеНоутбуки) {
            System.out.println(ноутбук.getМодель());
        }
    }
}
