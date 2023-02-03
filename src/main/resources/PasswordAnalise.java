import java.io.*;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;

public class PasswordAnalise {

    public static void main(String[] args) throws IOException {
        long startTime = new Date().getTime();
        System.out.println(normalCheck());
        long endTime = new Date().getTime();
        System.out.println("Time cost " + (endTime - startTime) + "ms");
    }

    public static String normalCheck() throws IOException {

        // 设置记录文件存储路径
        File filename = new File("test.txt");
        // 建立一个输入流对象reader
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";

        //按行读取存储的数据
        while (true){
            line = br.readLine();
            if (line == null){
                break;
            }

            //以空格为分隔，将每一行的数据转为tokens，便于后续分析
            String[] tokens = line.split(" ");
            String password = checkPassword(tokens);
            if (password != ""){
                return password;
            }
        }
        return "Filed to find password";

    }
    public static String checkPassword(String[] tokens){

        //从后往前检查数据，因为enter前才可能是输入的密码
        //实验条件，现实更复杂，比如输完密码不enter，先转到其他窗口按其他按键，再回到密码窗口enter登录
        int length = tokens.length;
        int count = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        StringBuilder sb = new StringBuilder();

        //使用stack来存储backspace的次数
        for (int i = length - 1; i >= 0; i--){
            if (tokens[i].equals("Backspace")){
                stack.push(0);
                continue;
            }else if (!stack.isEmpty()){
                stack.pop();
                continue;
            }
            sb.insert(0,tokens[i]);
            if (PasswordCheck.check(sb.toString())){
                return sb.toString();
            }
        }
        return "";
    }
}
