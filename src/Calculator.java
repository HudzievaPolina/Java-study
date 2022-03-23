import java.util.Map;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите выражение");
        String expression = in.nextLine();

        String[] members = expression.split(" ");
        if (members.length != 3){
            throw new ArithmeticException("Неверный формат выражения");
        }
        Numbers numbers = new Numbers(members);
        Result result = new Result(members, numbers);
        result.showResult(numbers);
    }
}
class Numbers {
    String a;
    private int x;
    private int i = 0;
    String b;
    private int y;
    private int j = 0;

    Numbers(String[] members) {
        this.a = members[0];
        this.b = members[2];

        //Словарь значений (некий "справочник", с которым нужно сравнить входное значение)
        Map<String, Integer> romanIntegers = Map.ofEntries(
                Map.entry("I", 1),
                Map.entry("II", 2),
                Map.entry("III", 3),
                Map.entry("IV", 4),
                Map.entry("V", 5),
                Map.entry("VI", 6),
                Map.entry("VII", 7),
                Map.entry("VIII", 8),
                Map.entry("IX", 9),
                Map.entry("X", 10)
        );
        //ищем в справочнике соответствие, если не нашли, ставим значение по умолчанию -1
        var romanInt = romanIntegers.getOrDefault(a, -1);


        if (romanInt != -1)
        {
            //нашли соответствие, ставим i = 1 для каких-то дальнейших вычислений
            this.x = romanInt;
            this.i = 1;
        }
        else
        {
            //не нашли соответствие в римских цифрах
            //пытаемся распарсить, длина слова максимум может быть 2, так как максимальное число на входе 10
            if (a.length() <= 2)
            {
                //разбиваем входное слово на массив из символов
                var symbolsInWord = a.toCharArray();
                for (var symbol: symbolsInWord) 
                {
                    //проверяем каждый символ, если не число — сразу кидаем исключение, дальше нет смысла вычислять
                    if (!Character.isDigit(symbol))
                    {
                        throw new ArithmeticException("Недопустимые значения операндов");
                    }
                }

                //дошли до сюда — значит, двухсимвольное слово это какое-то неотрицательное число (может быть от 00 до 99)
                //можно смело преобразовать в int
                this.x = Integer.parseInt(a);

                //проверка на несоответствие ограничениям задачи
                if (this.x < 1 || this.x > 10)
                {
                    //кидаем исключение, дальше нет смысла выислять
                    throw new ArithmeticException("Недопустимые значения операндов");
                }
            }
            else
            {
                //число трёхзначное (слово трёхсимвольное) или больше, кидаем исключение, дальше нет смысла выислять
                throw new ArithmeticException("Недопустимые значения операндов");
            }
        }



//        switch (this.a) {
//            case "I":
//                this.x = 1;
//                this.i = 1;
//                break;
//            case "II":
//                this.x = 2;
//                this.i = 1;
//                ;
//                break;
//            case "III":
//                this.x = 3;
//                this.i = 1;
//                ;
//                break;
//            case "IV":
//                this.x = 4;
//                this.i = 1;
//                break;
//            case "V":
//                this.x = 5;
//                this.i = 1;
//                break;
//            case "VI":
//                this.x = 6;
//                this.i = 1;
//                break;
//            case "VII":
//                this.x = 7;
//                this.i = 1;
//                break;
//            case "VIII":
//                this.x = 8;
//                this.i = 1;
//                break;
//            case "IX":
//                this.x = 9;
//                this.i = 1;
//                break;
//            case "X":
//                this.x = 10;
//                this.i = 1;
//                break;
//            default:
//                this.x = Integer.parseInt(a);
//        }

        switch (this.b) {
            case "I":
                this.y = 1;
                this.j = 1;
                break;
            case "II":
                this.y = 2;
                this.j = 1;
                ;
                break;
            case "III":
                this.y = 3;
                this.j = 1;
                ;
                break;
            case "IV":
                this.y = 4;
                this.j = 1;
                break;
            case "V":
                this.y = 5;
                this.j = 1;
                break;
            case "VI":
                this.y = 6;
                this.j = 1;
                break;
            case "VII":
                this.y = 7;
                this.j = 1;
                break;
            case "VIII":
                this.y = 8;
                this.j = 1;
                break;
            case "IX":
                this.y = 9;
                this.j = 1;
                break;
            case "X":
                this.y = 10;
                this.j = 1;
                break;
            default:
                this.y = Integer.parseInt(b);
        }

        if(this.i == 1 && this.j !=1 || this.i != 1 && this.j == 1)
            throw new ArithmeticException("Нельзя использовать разные системы счисления в выражении");
        if (x < y && members[1].equals("/") || this.x <= 0 || this.y <= 0 || this.x > 10 || this.y > 10)
            throw new ArithmeticException("Недопустимые значения операндов");
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getI(){
        return this.i;
    }
    public int getJ(){
        return this.j;
    }
}
class Result {
    private int result;
    private String res;

    Result(String members[], Numbers numbers) {
        switch (members[1]) {
                case "+":
                    this.result = numbers.getX() + numbers.getY();
                    break;
                case "-":
                    this.result = numbers.getX() - numbers.getY();
                    break;
                case "*":
                    this.result = numbers.getX() * numbers.getY();
                    break;
                case "/":
                    this.result = numbers.getX() / numbers.getY();
                    break;
                default:
                    throw new ArithmeticException("Неверный формат выражения");
        }
        if (numbers.getI() == 1 && numbers.getJ() == 1) {
            if (members[1].equals("-")) {
                throw new ArithmeticException("В римской системе нет отрицательных чисел");
            }
            switch (result / 10) {
                    case 0:
                        this.res = "";
                        break;
                    case 1:
                        this.res = "X";
                        break;
                    case 2:
                        this.res = "XX";
                        break;
                    case 3:
                        this.res = "XXX";
                        break;
                    case 4:
                        this.res = "XL";
                        break;
                    case 5:
                        this.res = "L";
                        break;
                    case 6:
                        this.res = "LX";
                        break;
                    case 7:
                        this.res = "LXX";
                        break;
                    case 8:
                        this.res = "LXXX";
                        break;
                    case 9:
                        this.res = "XC";
                        break;
                    case 10:
                        this.res = "C";
                        break;
                }
            switch (result % 10) {
                    case 0:
                        this.res = this.res + "";
                        break;
                    case 1:
                        this.res = this.res + "I";
                        break;
                    case 2:
                        this.res = this.res + "II";
                        break;
                    case 3:
                        this.res = this.res + "III";
                        break;
                    case 4:
                        this.res = this.res + "IV";
                        break;
                    case 5:
                        this.res = this.res + "V";
                        break;
                    case 6:
                        this.res = this.res + "VI";
                        break;
                    case 7:
                        this.res = this.res + "VII";
                        break;
                    case 8:
                        this.res = this.res + "VIII";
                        break;
                    case 9:
                        this.res = this.res + "IX";
                        break;
                }
        }
    }
    void showResult (Numbers numbers){
        if (numbers.getI() == 1 && numbers.getJ() == 1)
            System.out.println("Результат:" + res);
        else System.out.println("Результат:" + result);
    }
}

