import java.util.Scanner;
import equationgenerator.*;
import questionprinter.*;

public class Game {

    private int match_Num;          //移动火柴棒个数
    private int left_Num;           //等式左边数字个数（2/3）
    private int game_Type;          //游戏类型（移动/移除/添加）
    private int digt_Length;        //数字最大位数（1/2/3位数）

    public Game() { match_Num = left_Num = game_Type = digt_Length = 0; }

    void getsData(){
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入最大的位数（1～3）：");
        digt_Length = scan.nextInt();
        System.out.print("请输入等号左边数字等个数（2～3）：");
        left_Num = scan.nextInt();
        System.out.print("请输入游戏类型编号（1：添加；2：移除；3：移动）：");
        game_Type = scan.nextInt();
        System.out.print("请输入移动火柴棒根数（1～2）：");
        match_Num = scan.nextInt();
    }

    public static void main (String[] args){
        Game MatchGame = new Game();
        MatchGame.getsData();

        EquationGenerator EqGenerator
                = new EquationGenerator(MatchGame.match_Num,MatchGame.left_Num,MatchGame.game_Type,MatchGame.digt_Length);
        EqGenerator.generateGuess();
        int[] queArray = new int[20];
        queArray = EqGenerator.getQueArray();
        String answer = EqGenerator.getAnswer();
        int length = EqGenerator.getLength();

        QuestionPrinter QPrinter =  new QuestionPrinter(queArray,length);
        QPrinter.printQuestion();

        Scanner scan = new Scanner(System.in);
        while (true)
        {
            System.out.println("请输入你认为正确对表达式：");
            String userInput = scan.nextLine();
            if (userInput.compareTo("") == 0) {
                System.out.println("正确答案："+answer);
                break;
            }
            else if (userInput.compareTo(answer) == 0) {
                System.out.println("答案正确！");
                break;
            }
            else System.out.println("答案错误。");
        }

    }
}
