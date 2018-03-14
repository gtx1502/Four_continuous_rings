import java.io.FileWriter;
import java.io.IOException;

class Chessboard {
    private int position[][] = new int[6][8];
    int winner;

    void setWinner(int winner){
        this.winner=winner;
    }
    int getWinner(){
        return this.winner;
    }

    void init(){    //初始化棋盘，每个位置均为空，记为-1
        for (int i=0;i<6;i++){
            for (int j=0;j<8;j++){
                position[i][j]=-1;
            }
        }
    }

    private void printChessBoard(FileWriter fileWriter)throws IOException{
        System.out.println(" 1 2 3 4 5 6 7 8");
        fileWriter.write(" 1 2 3 4 5 6 7 8\n");
        for (int i=0;i<6;i++){
            for (int j=0;j<8;j++){
                if (position[i][j]==-1){
                    System.out.print("| ");
                    fileWriter.write("| ");
                }

                else if (position[i][j]==0){
                    System.out.print("|X");
                    fileWriter.write("|X");
                }
                else if (position[i][j]==1){
                    System.out.print("|O");
                    fileWriter.write("|O");
                }
            }
            System.out.print("|\n");
            fileWriter.write("|\n");
        }
        for (int i=0;i<17;i++){
            System.out.print("-");
            fileWriter.write("-");
        }
        System.out.print("\r\n\r\n");
        fileWriter.write("\r\n\r\n");
    }

    private void putDownChess(ChessPieces chessPieces,FileWriter fileWriter)throws IOException{
        int chessLine;
        int player=chessPieces.getPlayer();
        if (player==0){
            Player myPlayer=new Player();
            System.out.print("轮到你了，你放X棋子，请选择列号（1-8）：");
            fileWriter.write("轮到你了，你放X棋子，请选择列号（1-8）：");
            chessLine=myPlayer.getChessLine();
            fileWriter.write(chessLine+"\n");
        }
        else {
            Computer computer=new Computer();
            do {
                chessLine=computer.getChessLine();
            }while (position[0][chessLine-1]!=-1);
            System.out.println("轮到我了，我把O棋子放在第"+chessLine+"列");
            fileWriter.write("轮到我了，我把O棋子放在第"+chessLine+"列\n");
        }
        if (chessLine<1||chessLine>8){
            System.out.println("请重新输入1到8之间的自然数");
            fileWriter.write("请重新输入1到8之间的自然数\n");
            chessPieces.getChessLine();
        }
        else if (position[0][chessLine-1]!=-1){    //落子列已满（最上面的位置不为空）
            System.out.println("第"+chessLine+"列已满，请重新输入1到8之间的自然数");
            fileWriter.write("第"+chessLine+"列已满，请重新输入1到8之间的自然数\n");
            chessPieces.getChessLine();
        }
        else {
            for (int i=5;i>=0;i--){  //更新棋盘
                if (position[i][chessLine-1]==-1){
                    position[i][chessLine-1]=player;
                    if (chessPieces.getPlayer()==0)
                        chessPieces.setPlayer(1);
                    else if (chessPieces.getPlayer()==1)
                        chessPieces.setPlayer(0);
                    break;
                }
            }
            this.printChessBoard(fileWriter);
        }
    }

    private int gameFinished(){

        //判断是否一方胜出，返回胜出方
        for (int i=0;i<8;i++){  //列四子相连
            a:for (int j=5;j>=3;j--){
                if (position[j][i]!=-1){
                    for (int k=j-1;k>=j-3;k--){
                        if (position[k][i]!=position[j][i])
                            continue a;
                    }
                    return position[j][i];
                }
            }
        }
        for (int i=0;i<6;i++){  //行四子相连
            b:for (int j=0;j<5;j++){
                if (position[i][j]!=-1){
                    for (int k=j+1;k<=j+3;k++){
                        if (position[i][k]!=position[i][j])
                            continue b;
                    }
                    return position[i][j];
                }
            }
        }
        for (int i=0;i<6;i++){  //斜向四子相连(左上到右下)
            c:for (int j=0;j<5;j++){
                if (position[i][j]!=-1){
                    for (int k=j+1;k<=j+3;k++){
                        if (i-j+k>=6)
                            continue c;
                        if (i-j+k<6){
                            if (position[i-j+k][k]!=position[i][j])
                                continue c;
                        }
                    }
                    return position[i][j];
                }
            }
        }
        for (int i=0;i<6;i++){  //斜向四子相连(左下到右上)
            c:for (int j=0;j<5;j++){
                if (position[i][j]!=-1){
                    for (int k=j+1;k<=j+3;k++){
                        if (i-k+j<0)
                            continue c;
                        if (i-k+j>=0){
                            if (position[i-k+j][k]!=position[i][j])
                                continue c;
                        }
                    }
                    return position[i][j];
                }
            }
        }

        for (int i=0;i<6;i++){
            for (int j=0;j<8;j++){
                if (position[i][j]==-1)
                    return -1;  //存在空位，游戏继续
                }
        }

        return 2;//数组已满，平局
    }

    int encapsulation(ChessPieces chessPieces,FileWriter fileWriter)throws IOException{
        this.putDownChess(chessPieces,fileWriter);
        this.setWinner(gameFinished());
        return winner;
    }
}
