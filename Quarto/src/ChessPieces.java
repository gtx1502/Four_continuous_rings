
class ChessPieces {
    int player; //0:玩家 1:电脑

    void setPlayer(int player){
        this.player=player;
    }
    int getPlayer(){
        return this.player;
    }
    int getChessLine(){
        return 0;
    }

}

class Computer extends ChessPieces {
    int getChessLine(){
        this.setPlayer(1);
        int l =(int)(8*Math.random())+1;

        //this.setLine(l);
        return l;
    }
}

class Player extends ChessPieces {
    int getChessLine(){
        this.setPlayer(0);
        Input input=new Input();
        int l=input.readInput();

        return l;
    }
}
