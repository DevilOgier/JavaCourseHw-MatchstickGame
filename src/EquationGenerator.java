package equationgenerator;

public class EquationGenerator{

    private int match_Num;
    private int left_Num;
    private int game_Type;
    private int digt_Length;

    private int length = 0;         //处理后的等式
    private int[] queArray = new int[20];   //-1表示+ -2表示- -3表示=
    private String answer;


    public EquationGenerator(int match_num, int left_num, int game_type, int digt_length){
        match_Num = match_num;
        left_Num = left_num;
        game_Type = game_type;
        digt_Length = digt_length;
        answer = "";
    }

    public int[] getQueArray()  {
        return queArray;
    }

    public int getLength(){
        return length;
    }

    public String getAnswer(){
        return answer;
    }

    public void generateGuess() {

        int[] ev = new int[5];  //存储生成的等式,ev[4]存储等式类型，+=：1，-=：2，++=：11，+-=：12，-+=：13，--=：14
        ev = generateEv();

        int[] numberArray = new int[20];   //-1表示+ -2表示- -3表示=
        int i = 0;
        while (i < 4) {
            if (i == 1) {
                switch (ev[4]) {
                    case 1:
                    case 11:
                    case 12: //+= ++= +-=
                        numberArray[length++] = -1;
                        break;
                    case 2:
                    case 13:
                    case 14: //-= -+= --=
                        numberArray[length++] = -2;
                        break;
                }
            }
            if (i == 2) {
                switch (ev[4]) {
                    case 11:
                    case 13: //++=
                        numberArray[length++] = -1;
                        break;
                    case 12:
                    case 14: //+-=
                        numberArray[length++] = -2;
                        break;
                }
            }
            if (i == 3) {
                numberArray[length++] = -3;
            }
            if ((ev[4] < 10 && (i == 0 || i == 1 || i == 3)) || ev[4] > 10) {
                int k = ev[i];
                //System.out.println("****"+i+" "+k);
                if (k < 10) {
                    numberArray[length++] = k;
                } else if (k < 100) {
                    numberArray[length++] = k / 10;
                    numberArray[length++] = k % 10;
                } else if (k < 1000) {
                    numberArray[length++] = k / 100;
                    numberArray[length++] = (k % 100) / 10;
                    numberArray[length++] = k % 10;
                } else {
                    numberArray[length++] = k / 1000;
                    numberArray[length++] = (k % 1000) / 100;
                    numberArray[length++] = (k % 100) / 10;
                    numberArray[length++] = k % 10;
                }
            }
            i++;
        }
        /*
        for (i = 0; i < length; i++) System.out.print(numberArray[i] + " ");
        System.out.println("");
        */

        generateQuestion(numberArray);

    }

    int[]  generateEv(){
        int maxsize = (int)Math.pow(10,digt_Length);
        int type = 1;
        if (left_Num == 2) {
            type = 1 + (int)(Math.random()*maxsize) % 2;
        }
        else{
            type = 11 + (int)(Math.random()*maxsize) % 4;
        }
        int[] ev = new int[5];  //存储生成的等式,ev[4]存储等式类型，+=：1，-=：2，++=：11，+-=：12，-+=：13，--=：14
        switch (type)
        {
            case 1: // +=
                ev[0] = (int)(maxsize*Math.random());
                ev[1] = (int)(maxsize*Math.random());
                ev[3] = ev[0] + ev[1];
                ev[4] = type;
                break;
            case 2: // -=
                ev[0] = (int)(maxsize*Math.random());
                ev[1] = (int)(ev[0]*Math.random());
                ev[3] = ev[0] - ev[1];
                ev[4] = type;
                break;
            case 11: //++=
                ev[0] = (int)(maxsize*Math.random());
                ev[1] = (int)(maxsize*Math.random());
                ev[2] = (int)(maxsize*Math.random());
                ev[3] = ev[0]+ev[1]+ev[2];
                ev[4] = type;
                break;
            case 12: //+-=
                ev[0] = (int)(maxsize*Math.random());
                ev[1] = (int)(maxsize*Math.random());
                ev[2] = (int)((ev[0]+ev[1])*Math.random());
                if (ev[2] > maxsize) ev[2] = (int)(maxsize*Math.random());
                ev[3] = ev[0]+ev[1]-ev[2];
                ev[4] = type;
                break;
            case 13: //-+=
                ev[0] = (int)(maxsize*Math.random());
                ev[2] = (int)(maxsize*Math.random());
                ev[1] = (int)((ev[0]+ev[2])*Math.random());
                if (ev[1] > maxsize) ev[1] = (int)(maxsize*Math.random());
                ev[3] = ev[0]-ev[1]+ev[2];
                ev[4] = type;
                break;
            case 14: //--=
                ev[0] = (int)(maxsize*Math.random());
                ev[1] = (int)(ev[1]*Math.random());
                ev[2] = (int)((ev[0-ev[1]])*Math.random());
                ev[3] = ev[0]-ev[1]-ev[2];
                ev[4] = type;
                break;
        }
        return ev;

    }

    void generateQuestion(int[] numberArray){

        final int[][] DIGT_MATRIX =
                {       {0,0,0,0,0,0,3,0,1,3},
                        {0,0,0,0,2,0,0,1,0,0},
                        {0,0,0,3,0,4,0,0,2,0},
                        {0,0,3,0,0,3,0,-2,2,1},
                        {0,-2,0,0,0,0,0,0,0,2},
                        {0,0,4,3,0,0,1,0,2,1},
                        {3,0,0,0,0,-1,0,0,1,3},
                        {0,-1,0,0,0,0,0,0,0,0},
                        {-1,0,2,-2,0,-2,-1,0,0,-1},
                        {3,0,0,-1,-2,-1,3,0,1,0}
                };
        //数字转换矩阵
        /*
         * 1 加一根火柴棒能变换
         * -1 减一根火柴棒能变换
         * 2 加两根火柴棒能变换
         * -2 减两根火柴棒能变换
         * 3 移动一根火柴棒能变换
         * 4 移动两根火柴棒能变换
         * */

        int[][] randomQuestion = new int[20000][20];
        int questionN = 0;

        if (match_Num == 1){
            //变换一根火柴棒
            switch (game_Type){
                case 1: //增加一根火柴棒
                    for (int i=0; i<length; i++){
                        if (questionN>10000) break;
                        if (numberArray[i]>=0){
                            for (int j=0; j<=9; j++)
                                if (DIGT_MATRIX[numberArray[i]][j] == -1) {
                                    for (int k = 0; k < length; k++) randomQuestion[questionN][k] = numberArray[k];
                                    randomQuestion[questionN][i] = j;
                                    questionN++;
                                }
                        }
                    }
                    break;
                case 2: //减少一根火柴棒
                    for (int i=0; i<length; i++){
                        if (questionN>10000) break;
                        if (numberArray[i]>=0){
                            for (int j=0; j<=9; j++)
                                if (DIGT_MATRIX[numberArray[i]][j] == 1) {
                                    for (int k = 0; k < length; k++) randomQuestion[questionN][k] = numberArray[k];
                                    randomQuestion[questionN][i] = j;
                                    questionN++;
                                }
                        }
                    }
                    break;
                case 3:
                    //移动一根火柴棒：内部移动
                    for (int i=0; i<length; i++){
                        if (questionN>10000) break;
                        if (numberArray[i]>=0){
                            for (int j=0; j<=9; j++)
                                if (DIGT_MATRIX[numberArray[i]][j] == 3) {
                                    for (int k = 0; k < length; k++) randomQuestion[questionN][k] = numberArray[k];
                                    randomQuestion[questionN][i] = j;
                                    questionN++;
                                }
                        }
                    }
                    //移动一根火柴棒：从一个数字移动到另一个数字
                    for (int i=0; i<length; i++){
                        if (questionN>10000) break;
                        if (numberArray[i]>=0){
                            for (int j=0; j<length; j++){
                                if (questionN>10000) break;
                                if (numberArray[j]>=0 && i!=j){
                                    int flag1 = 0;
                                    int flag2 = 0;
                                    int x,y;
                                    for (x=0; x<=9; x++){
                                        if (DIGT_MATRIX[numberArray[i]][x] == -1) {
                                            flag1 = 1;
                                            break;
                                        }
                                    }
                                    for (y=0; y<=9; y++){
                                        if (DIGT_MATRIX[numberArray[j]][y] == 1) {
                                            flag2 = 1;
                                            break;
                                        }
                                    }
                                    if (flag1==1 && flag2==1){
                                        for (int k = 0; k < length; k++) randomQuestion[questionN][k] = numberArray[k];
                                        randomQuestion[questionN][i] = x;
                                        randomQuestion[questionN][j] = y;
                                        questionN++;
                                    }

                                }
                            }
                        }
                    }
                    break;

            }
        }
        else{
            //变换两根火柴棒
            switch (game_Type){

                case 1: //增添两根火柴棒
                    //一个数字上删除两个火柴棒
                    for (int i=0; i<length; i++){
                        if (questionN>10000) break;
                        if (numberArray[i]>=0){
                            for (int j=0; j<=9; j++)
                                if (DIGT_MATRIX[numberArray[i]][j] == -2) {
                                    for (int k = 0; k < length; k++) randomQuestion[questionN][k] = numberArray[k];
                                    randomQuestion[questionN][i] = j;
                                    questionN++;
                                }
                        }
                    }
                    //两个数字上各删除一根火柴棒
                    for (int i=0; i<length; i++){
                        if (questionN>10000) break;
                        if (numberArray[i]>=0){
                            for (int j=0; j<length; j++){
                                if (questionN>10000) break;
                                if (numberArray[j]>=0 && i!=j){
                                    int flag1 = 0;
                                    int flag2 = 0;
                                    int x,y;
                                    for (x=0; x<=9; x++){
                                        if (DIGT_MATRIX[numberArray[i]][x] == -1) {
                                            flag1 = 1;
                                            break;
                                        }
                                    }
                                    for (y=0; y<=9; y++){
                                        if (DIGT_MATRIX[numberArray[j]][y] == -1) {
                                            flag2 = 1;
                                            break;
                                        }
                                    }
                                    if (flag1==1 && flag2==1){
                                        for (int k = 0; k < length; k++) randomQuestion[questionN][k] = numberArray[k];
                                        randomQuestion[questionN][i] = x;
                                        randomQuestion[questionN][j] = y;
                                        questionN++;
                                    }

                                }
                            }
                        }
                    }

                    break;

                case 2: //减少两根火柴棒
                    //一个数字上增加两个火柴棒
                    for (int i=0; i<length; i++){
                        if (questionN>10000) break;
                        if (numberArray[i]>=0){
                            for (int j=0; j<=9; j++)
                                if (DIGT_MATRIX[numberArray[i]][j] == 2) {
                                    for (int k = 0; k < length; k++) randomQuestion[questionN][k] = numberArray[k];
                                    randomQuestion[questionN][i] = j;
                                    questionN++;
                                }
                        }
                    }
                    //两个数字上各增加一根火柴棒
                    for (int i=0; i<length; i++){
                        if (questionN>10000) break;
                        if (numberArray[i]>=0){
                            for (int j=0; j<length; j++){
                                if (questionN>10000) break;
                                if (numberArray[j]>=0 && i!=j){
                                    int flag1 = 0;
                                    int flag2 = 0;
                                    int x,y;
                                    for (x=0; x<=9; x++){
                                        if (DIGT_MATRIX[numberArray[i]][x] == 1) {
                                            flag1 = 1;
                                            break;
                                        }
                                    }
                                    for (y=0; y<=9; y++){
                                        if (DIGT_MATRIX[numberArray[j]][y] == 1) {
                                            flag2 = 1;
                                            break;
                                        }
                                    }
                                    if (flag1==1 && flag2==1){
                                        for (int k = 0; k < length; k++) randomQuestion[questionN][k] = numberArray[k];
                                        randomQuestion[questionN][i] = x;
                                        randomQuestion[questionN][j] = y;
                                        questionN++;
                                    }

                                }
                            }
                        }
                    }

                    break;

                case 3: //移动两根火柴棒
                    //一个数字内移动两根火柴棒
                    for (int i=0; i<length; i++){
                        if (questionN>10000) break;
                        if (numberArray[i]>=0){
                            for (int j=0; j<=9; j++)
                                if (DIGT_MATRIX[numberArray[i]][j] == 4) {
                                    for (int k = 0; k < length; k++) randomQuestion[questionN][k] = numberArray[k];
                                    randomQuestion[questionN][i] = j;
                                    questionN++;
                                }
                        }
                    }

                    //两个数字内各移动一根火柴棒
                    for (int i=0; i<length; i++){
                        if (questionN>10000) break;
                        if (numberArray[i]>=0){
                            for (int j=0; j<length; j++){
                                if (questionN>10000) break;
                                if (numberArray[j]>=0 && i!=j){
                                    int flag1 = 0;
                                    int flag2 = 0;
                                    int x,y;
                                    for (x=0; x<=9; x++){
                                        if (DIGT_MATRIX[numberArray[i]][x] == 3) {
                                            flag1 = 1;
                                            break;
                                        }
                                    }
                                    for (y=0; y<=9; y++){
                                        if (DIGT_MATRIX[numberArray[j]][y] == 3) {
                                            flag2 = 1;
                                            break;
                                        }
                                    }
                                    if (flag1==1 && flag2==1){
                                        for (int k = 0; k < length; k++) randomQuestion[questionN][k] = numberArray[k];
                                        randomQuestion[questionN][i] = x;
                                        randomQuestion[questionN][j] = y;
                                        questionN++;
                                    }

                                }
                            }
                        }
                    }

                    //一个数字内移动一根火柴棒，再将等式中一个数字对火柴棒移动到另一个数字上
                    for (int i=0; i<length; i++)
                        if (numberArray[i]>=0){
                            if (questionN>10000) break;
                            int flag = 0;
                            int z = 0;
                            for (int k=0; k<9; k++) if (DIGT_MATRIX[numberArray[i]][k] == 3){
                                flag = 1;
                                z = k;
                                break;
                            }
                            if (flag == 1) {
                                for (int j=0; j<length; j++)
                                    for (int k=0; k<length; k++)
                                        if (numberArray[j]>=0 && numberArray[k]>=0 && i!=j && j!=k && k!=i){
                                            int flag1 = 0;
                                            int flag2 = 0;
                                            int x,y;
                                            for (x=0; x<=9; x++){
                                                if (DIGT_MATRIX[numberArray[j]][x] == 1) {
                                                    flag1 = 1;
                                                    break;
                                                }
                                            }
                                            for (y=0; y<=9; y++){
                                                if (DIGT_MATRIX[numberArray[k]][y] == -1) {
                                                    flag2 = 1;
                                                    break;
                                                }
                                            }
                                            if (flag==1 && flag1==1 && flag2==1){
                                                for (int t = 0; t < length; t++) randomQuestion[questionN][t] = numberArray[t];
                                                randomQuestion[questionN][i] = z;
                                                randomQuestion[questionN][j] = x;
                                                randomQuestion[questionN][k] = y;
                                                questionN++;
                                            }

                                        }
                            }

                        }


                    //等式中两个数字对火柴棒移动到两个数字上
                    for (int i1=0; i1<length; i1++) if (questionN<=10000 && numberArray[i1]>=0)
                        for (int j1=0; j1<length; j1++) if (questionN<=10000 && numberArray[j1]>=0 && i1!=j1)
                            for (int i2=0; i2<length; i2++) if (questionN<=10000 && numberArray[i2]>=0 && i2!=i1 && i2!=j1)
                                for (int j2=0; j2<length; j2++) if (questionN<=10000 && numberArray[j2]>=0 && j2!=i1 && j2!=i2 && j2!=j1){
                                    int flag1,flag2,flag3,flag4;
                                    flag1 = flag2 = flag3 = flag4 = 0;
                                    int a,b,c,d;
                                    a = b = c = d = 0;
                                    for (a=0; a<=9; a++){
                                        if (DIGT_MATRIX[numberArray[i1]][a] == -1){
                                            flag1 = 1;
                                            break;
                                        }
                                    }
                                    for (b=0; b<=9; b++){
                                        if (DIGT_MATRIX[numberArray[j1]][b] == 1){
                                            flag2 = 1;
                                            break;
                                        }
                                    }
                                    for (c=0; c<=9; c++){
                                        if (DIGT_MATRIX[numberArray[i2]][c] == -1){
                                            flag3 = 1;
                                            break;
                                        }
                                    }
                                    for (d=0; d<=9; d++){
                                        if (DIGT_MATRIX[numberArray[j2]][d] == 1){
                                            flag4 = 1;
                                            break;
                                        }
                                    }
                                    if (flag1 == 1 && flag2 == 1 && flag3 == 1 && flag4 == 1){
                                        for (int t = 0; t < length; t++) randomQuestion[questionN][t] = numberArray[t];
                                        randomQuestion[questionN][i1] = a;
                                        randomQuestion[questionN][j1] = b;
                                        randomQuestion[questionN][i2] = c;
                                        randomQuestion[questionN][j2] = d;
                                        questionN++;
                                    }
                                }

                    break;
            }

        }

        int questionIndex = (int)(Math.random()*questionN);
        for (int i = 0; i<length; i++)  queArray[i] = randomQuestion[questionIndex][i];
        for (int i = 0; i<length; i++) {
            char answerCharacter = ' ';
            switch ( (numberArray[i]) ){
                case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
                    answerCharacter = (char)(48 + numberArray[i]);
                    break;
                case -1:
                    answerCharacter = '+';
                    break;
                case -2:
                    answerCharacter = '-';
                    break;
                case -3:
                    answerCharacter = '=';
                    break;
            }
            answer = answer + answerCharacter;
        }
        //System.out.println("A: "+answer);
    }


}