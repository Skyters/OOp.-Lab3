import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static TaskMatrix taskMatrix;
    private static TaskString taskString;
    public static void start() {
        mainMenu();
    }

    //Проверка на ввод значений меню
    public static int readChoice(){
        Scanner scanner = new Scanner(System.in);
        int readed = -1;
        try{
            readed = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException ex){
            System.out.println("Вводите только значения пунктов меню, пожалуйства");
            readed = readChoice();
        }
        return readed;
    }

    //Сохранение через бинарный файл
    private static void arrToBinFile(int[][] taskMatrix, String filename){
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))){
            dos.writeInt(taskMatrix.length);
            for (int i = 0; i < taskMatrix.length; i++)
            {
                for (int j = 0; j < taskMatrix[i].length; j++)
                {
                    dos.writeInt(taskMatrix[i][j]);
                }
            }
            System.out.println("Сохранено успешно в файл " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Плохое имя файла");
        } catch (IOException e) {
            System.out.println("Ошибка записи");
        }
    }
    // Чтение бинарного файла
    private static int[][] arrFromBinFile(String filename){
        int[][] temparr = null;
        try(DataInputStream dis = new DataInputStream(new FileInputStream(filename))){
            int size = dis.readInt();
            temparr = new int[size][size];
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size; j++)
                {
                    temparr[i][j] = dis.readInt();
                }
            }
            System.out.println("Файл успешно открыт");
        } catch (FileNotFoundException e) {
            System.out.println("Такого файла нет");
        } catch (IOException e) {
            System.out.println("Ошибка записи");
        }
        return temparr;
    }
    // Сохранение через текстовый файл
    private static void arrToTXTFile(String line, String filename) {
        try (BufferedWriter writer = new BufferedWriter((new FileWriter(filename)))) {
            writer.write(line);
        } catch (IOException e) {
            System.out.println("Ошибка записи ");
        }
        System.out.println("Файл успешно сохранен " + filename);
    }
    // Чтение через текстовый файл
    private static String arrFromTXTFile(String filename){
        String line = null;
        try (BufferedReader reader = new BufferedReader((new FileReader(filename)))){
            line = reader.readLine();
            System.out.println ("Файл успешно открыт");
        } catch (FileNotFoundException ex){
            System.out.println("Такого файла нет");
        } catch (IOException e) {
            System.out.println("Ошибка записи");
        }
        catch (NullPointerException e)
        {
            System.out.println("Файл пуст");
        }

        return line;
    }

    //Ручной ввод матрицы
    private static int[][] printMatrix(int size){
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = new int[size][size];
        System.out.println("Введите элементы матрицы");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                    matrix[i][j] = Integer.parseInt(scanner.next());
            }
        }
        return matrix;
    }
    //Ручной ввод строки
    private static String printString(){
        Scanner scanner = new Scanner(System.in);
        String str;
        str = scanner.nextLine();
        return str;
    }
    // Пункты задания для матрицы
    private static void numberMatrix(int Number){
        try
        {
            switch (Number)
            {
                case 2: taskMatrix.modifiedMatrix();
                    break;
                case 3: taskMatrix.printMatrix();
                    break;
                case 4: taskMatrix.sum();
                    break;
                case 5: taskMatrix.newString();
                    break;
                case 6:
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Введите будущее название файла");
                    String str = scanner.nextLine();
                    arrToBinFile(taskMatrix.getMatrix(), str);
            }
        } catch (NullPointerException ex)
        {
            if (Number == 6)
            {
                System.out.println("Ничего не сохранять");
            }
            else
            {
                System.out.println("Ошибка! Сначало задайте матрицу");
            }
        }
    }

    private static void numberString(int Number){
        try
        {
            switch (Number)
            {
                case 2: taskString.deleteThreeWord();
                    break;
                case 3:
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Введите будущее название файла");
                    String str = scanner.nextLine();
                    arrToTXTFile(taskString.getString(), str);
                    break;
            }
        } catch (NullPointerException ex)
        {
            if(Number == 3)
            {
                System.out.println("Ничего не сохранять");
            }
            else
            {
                System.out.println("Сначало задайте строку слов");
            }
        }
    }

    // Главное меню
    private  static  void mainMenu(){
        int choice = -1;
        do{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Главное меню");
            System.out.println("1 - Квадратна матрица");
            System.out.println("2 - Строка слов");
            System.out.println("0 - Выход");

            choice = readChoice();

            switch (choice){
                case 1: matrixMenu();
                    break;

                case 2: stringMenu();
                    break;

                case 0: break;
                default:
                    System.out.println("Нет такого пункта меню");
            }
        } while(choice != 0);
    }

    private static void matrixMenu(){
        int choice = -1;
        do{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Квадратна матрица");
            System.out.println("1 - Создать матрицу");
            System.out.println("2 - Преобразовать матрицу в квадрутную");
            System.out.println("3 - Вывести матрицу");
            System.out.println("4 - Найти сумму элементов квадратной матрицы");
            System.out.println("5 - Вывести положительные элементы квадртаной матрицы");
            System.out.println("6 - Сохранить файл");
            System.out.println("0 - Назад");

            choice = readChoice();

            switch (choice){
                case 1: inputMatrix();
                    break;
                case 2: numberMatrix(choice);
                    break;
                case 3: numberMatrix(choice);
                    break;
                case 4: numberMatrix(choice);
                    break;
                case 5: numberMatrix(choice);
                    break;
                case 6: numberMatrix(choice);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Нет такого пункта меню");
            }
        } while(choice != 0);
    }

    private static void inputMatrix(){
        int choice = -1;
        do{
            System.out.println("Создание массива");
            System.out.println("1 - Заполенение случайными числами");
            System.out.println("2 - Прочитать из файла ");
            System.out.println("3 - Заполнить вручную");
            System.out.println("0 - Назад");

            choice = readChoice();

            switch (choice){
                case 1:
                    taskMatrix = new TaskMatrix(3, 3);
                    choice = 0;
                    break;
                case 2:
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Введите название файла который хотите открыть:");
                    String filename = scanner.nextLine();
                    try
                    {
                        int[][] readedArr = arrFromBinFile(filename);
                        taskMatrix = new TaskMatrix(readedArr);
                    }
                    catch (NullPointerException ex) {
                        System.out.println("Ошибка! Файл пуст или не существует");
                    }
                    choice = 0;
                    break;
                case 3:
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("Запишите размерность матрицы: ");
                    try
                    {
                    int size = Integer.parseInt(scanner1.nextLine());

                        if (size < 3){
                            System.out.println("Размерность матрицы должна быть больше 2");
                            break;
                        }
                        else
                        {
                            taskMatrix = new TaskMatrix(printMatrix(size));
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Вводите только числа");
                    }catch (InputMismatchException ex)
                    {
                        System.out.println("Вводите только числа");
                    }
                    choice = 0;
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Нет такого пункта меню");
            }
        } while(choice != 0);
    }
    private static void stringMenu(){
        int choise = -1;
        do {
            System.out.println("Строка слов");
            System.out.println("1 - Задать строку слов");
            System.out.println("2 - Удалить каждое третье слово");
            System.out.println("3 - Сохранить в текстовый файл");
            System.out.println("0 - Назад");
            choise = readChoice();
            switch (choise) {
                case 1: stringInput();
                    break;
                case 2:
                    numberString(choise);
                    break;
                case 3:
                    numberString(choise);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Нет такого пункта");
            }
        }
        while (choise != 0);
    }

    private static void stringInput(){
        int choise = -1;
        do {
            System.out.println("Создание строки");
            System.out.println("1 - Заполенение вручную");
            System.out.println("2 - Прочитать из файла ");
            System.out.println("0 - Назад");
            choise = readChoice();
            switch (choise) {
                case 1:
                    System.out.println("Введите строку: ");
                    taskString = new TaskString(printString());
                    choise = 0;
                    break;
                case 2:
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Введите название файла который хотите открыть");
                    String str = scanner.nextLine();
                    String fromTXT = arrFromTXTFile(str);
                    taskString = new TaskString(fromTXT);
                    choise = 0;
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Нет такого пункта");
            }
        }
        while (choise != 0);
    }
}
