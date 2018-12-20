package questionprinter;

public class QuestionPrinter{
    private int length;
    private int[] queArray = new int[20];   //-1表示+ -2表示- -3表示=

    public QuestionPrinter(int[] qArray, int len) {
        queArray = qArray;
        length = len;
    }

    public void printQuestion(){
        String[][] digtPrint = new String[20][9];

        //Initialization
        for (int i=0; i<20; i++){
            for (int j=0; j<9; j++)
                digtPrint[i][j]="             ";
        }

        //for (int i=0; i<length; i++) System.out.println(queArray[i]);

        //0
        digtPrint[0][0] = "   ═══════   ";
        digtPrint[0][1] = "  ║       ║  ";
        digtPrint[0][2] = "  ║       ║  ";
        digtPrint[0][3] = "  ║       ║  ";
        digtPrint[0][4] = "             ";
        digtPrint[0][5] = "  ║       ║  ";
        digtPrint[0][6] = "  ║       ║  ";
        digtPrint[0][7] = "  ║       ║  ";
        digtPrint[0][8] = "   ═══════   ";

        //1
        digtPrint[1][0] = "             ";
        digtPrint[1][1] = "      ║      ";
        digtPrint[1][2] = "      ║      ";
        digtPrint[1][3] = "      ║      ";
        digtPrint[1][4] = "             ";
        digtPrint[1][5] = "      ║      ";
        digtPrint[1][6] = "      ║      ";
        digtPrint[1][7] = "      ║      ";
        digtPrint[1][8] = "             ";

        //2
        digtPrint[2][0] = "   ═══════   ";
        digtPrint[2][1] = "          ║  ";
        digtPrint[2][2] = "          ║  ";
        digtPrint[2][3] = "          ║  ";
        digtPrint[2][4] = "   ═══════   ";
        digtPrint[2][5] = "  ║          ";
        digtPrint[2][6] = "  ║          ";
        digtPrint[2][7] = "  ║          ";
        digtPrint[2][8] = "   ═══════   ";

        //3
        digtPrint[3][0] = "   ═══════   ";
        digtPrint[3][1] = "          ║  ";
        digtPrint[3][2] = "          ║  ";
        digtPrint[3][3] = "          ║  ";
        digtPrint[3][4] = "   ═══════   ";
        digtPrint[3][5] = "          ║  ";
        digtPrint[3][6] = "          ║  ";
        digtPrint[3][7] = "          ║  ";
        digtPrint[3][8] = "   ═══════   ";

        //4
        digtPrint[4][0] = "             ";
        digtPrint[4][1] = "  ║       ║  ";
        digtPrint[4][2] = "  ║       ║  ";
        digtPrint[4][3] = "  ║       ║  ";
        digtPrint[4][4] = "   ═══════   ";
        digtPrint[4][5] = "          ║  ";
        digtPrint[4][6] = "          ║  ";
        digtPrint[4][7] = "          ║  ";
        digtPrint[4][8] = "             ";

        //5
        digtPrint[5][0] = "   ═══════   ";
        digtPrint[5][1] = "  ║          ";
        digtPrint[5][2] = "  ║          ";
        digtPrint[5][3] = "  ║          ";
        digtPrint[5][4] = "   ═══════   ";
        digtPrint[5][5] = "          ║  ";
        digtPrint[5][6] = "          ║  ";
        digtPrint[5][7] = "          ║  ";
        digtPrint[5][8] = "   ═══════   ";

        //6
        digtPrint[6][0] = "   ═══════   ";
        digtPrint[6][1] = "  ║          ";
        digtPrint[6][2] = "  ║          ";
        digtPrint[6][3] = "  ║          ";
        digtPrint[6][4] = "   ═══════   ";
        digtPrint[6][5] = "  ║       ║  ";
        digtPrint[6][6] = "  ║       ║  ";
        digtPrint[6][7] = "  ║       ║  ";
        digtPrint[6][8] = "   ═══════   ";

        //7
        digtPrint[7][0] = "   ═══════   ";
        digtPrint[7][1] = "          ║  ";
        digtPrint[7][2] = "          ║  ";
        digtPrint[7][3] = "          ║  ";
        digtPrint[7][4] = "             ";
        digtPrint[7][5] = "          ║  ";
        digtPrint[7][6] = "          ║  ";
        digtPrint[7][7] = "          ║  ";
        digtPrint[7][8] = "             ";

        //8
        digtPrint[8][0] = "   ═══════   ";
        digtPrint[8][1] = "  ║       ║  ";
        digtPrint[8][2] = "  ║       ║  ";
        digtPrint[8][3] = "  ║       ║  ";
        digtPrint[8][4] = "   ═══════   ";
        digtPrint[8][5] = "  ║       ║  ";
        digtPrint[8][6] = "  ║       ║  ";
        digtPrint[8][7] = "  ║       ║  ";
        digtPrint[8][8] = "   ═══════   ";

        //9
        digtPrint[9][0] = "   ═══════   ";
        digtPrint[9][1] = "  ║       ║  ";
        digtPrint[9][2] = "  ║       ║  ";
        digtPrint[9][3] = "  ║       ║  ";
        digtPrint[9][4] = "   ═══════   ";
        digtPrint[9][5] = "          ║  ";
        digtPrint[9][6] = "          ║  ";
        digtPrint[9][7] = "          ║  ";
        digtPrint[9][8] = "   ═══════   ";

        //+
        digtPrint[10][0] = "             ";
        digtPrint[10][1] = "             ";
        digtPrint[10][2] = "             ";
        digtPrint[10][3] = "      ║      ";
        digtPrint[10][4] = "   ═══╬═══   ";
        digtPrint[10][5] = "      ║      ";
        digtPrint[10][6] = "             ";
        digtPrint[10][7] = "             ";
        digtPrint[10][8] = "             ";

        //-
        digtPrint[11][0] = "             ";
        digtPrint[11][1] = "             ";
        digtPrint[11][2] = "             ";
        digtPrint[11][3] = "             ";
        digtPrint[11][4] = "   ═══════   ";
        digtPrint[11][5] = "             ";
        digtPrint[11][6] = "             ";
        digtPrint[11][7] = "             ";
        digtPrint[11][8] = "             ";

        //=
        digtPrint[12][0] = "             ";
        digtPrint[12][1] = "             ";
        digtPrint[12][2] = "             ";
        digtPrint[12][3] = "   ═══════   ";
        digtPrint[12][4] = "             ";
        digtPrint[12][5] = "   ═══════   ";
        digtPrint[12][6] = "             ";
        digtPrint[12][7] = "             ";
        digtPrint[12][8] = "             ";

        for (int i=0; i<=8; i++)
        {
            String LinePrinter = "";
            for (int j=0; j<length; j++) {
                if (queArray[j]<0) queArray[j] = queArray[j]*(-1) + 9;
                LinePrinter+=digtPrint[queArray[j]][i];
            }
            System.out.println(LinePrinter);
        }

    }
}