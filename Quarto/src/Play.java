import java.io.FileWriter;
import java.io.IOException;

public class Play {
    public static void main(String args[])throws IOException{
        Output output=new Output();
        FileWriter fileWriter=output.writeFile();
        System.out.println("Hi，我是劳拉，我们来玩一局四连环。我用O型棋子，你用X型棋子。");
        fileWriter.write("Hi，我是劳拉，我们来玩一局四连环。我用O型棋子，你用X型棋子。\n");
        System.out.println("游戏规则：双方轮流选择棋盘的列号放进自己的棋子，");
        fileWriter.write("游戏规则：双方轮流选择棋盘的列号放进自己的棋子，\n");
        System.out.println("          若棋盘上有四颗相同型号的棋子在一行、一列或一条斜线上连接起来，");
        fileWriter.write("          若棋盘上有四颗相同型号的棋子在一行、一列或一条斜线上连接起来，\n");
        System.out.println("          则使用该型号棋子是玩家就赢了！");
        fileWriter.write("          则使用该型号棋子是玩家就赢了！\n");
        System.out.println();
        fileWriter.write("\r\n");

        Chessboard chessboard=new Chessboard();
        ChessPieces chessPieces=new ChessPieces();
        chessPieces.setPlayer(1);//电脑先走
        chessboard.setWinner(-1);
        chessboard.init();
        System.out.println("开始了！这是棋盘的初始状态:");
        fileWriter.write("开始了！这是棋盘的初始状态:\n");
        System.out.println(" 1 2 3 4 5 6 7 8 ");
        fileWriter.write(" 1 2 3 4 5 6 7 8 \n");

        for (int i=0;i<6;i++){
            System.out.println("| | | | | | | | |");
            fileWriter.write("| | | | | | | | |\n");
        }
        for (int i=0;i<17;i++){
            System.out.print("-");
            fileWriter.write("-");
        }
        System.out.print("\r\n\r\n");
        fileWriter.write("\r\n\r\n");
        while (chessboard.getWinner()==-1){ //游戏未结束
            chessboard.encapsulation(chessPieces,fileWriter);
        }
        if (chessboard.getWinner()==0) {
            System.out.println("恭喜您赢了！");
            fileWriter.write("恭喜您赢了！\n");
            //fileWriter.close();
        }

        else if (chessboard.getWinner()==1)
        {
            System.out.println("对不起，您输了！");
            fileWriter.write("对不起，您输了！\n");
        }
        else if (chessboard.getWinner()==2){
            System.out.println("平局");
            fileWriter.write("平局\n");
        }
        fileWriter.close();

    }
}
