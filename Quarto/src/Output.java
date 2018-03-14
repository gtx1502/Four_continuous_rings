import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Output {
    public FileWriter writeFile()throws IOException{
        int num=(int)((Math.random()*9+1)*10000);
        //File file=new File("四连环Log-"+num+".txt");
        FileWriter fileWriter=new FileWriter("四连环Log-"+num+".txt",true);
        return fileWriter;
    }
}
