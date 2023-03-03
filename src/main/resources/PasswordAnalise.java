import java.io.*;
import java.util.*;

public class PasswordAnalise {

    public static void main(String[] args) throws IOException {
        //没有进行map之前的检查，从每个enter键开始从后往前检查。
        long startTime = new Date().getTime();
        System.out.println(normalCheck());
        long endTime = new Date().getTime();
        System.out.println("Time cost " + (endTime - startTime) + "ms");

        //进行map之后，暴力破解 位数高于6时间巨长
        long startTime2 = new Date().getTime();
        System.out.println(bruteForceBreak());
        long endTime2 = new Date().getTime();
        System.out.println("Time cost " + (endTime2 - startTime2) + "ms");

    }

    public static BufferedReader fileReader() throws FileNotFoundException {
        // 设置记录文件存储路径
        File filename = new File("test.txt");
        // 建立一个输入流对象reader
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        return new BufferedReader(reader);
    }

    //读取记录文件并进行分析
    public static String normalCheck() throws IOException {

        BufferedReader br = fileReader();
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

    //暴力破解
    public static String bruteForceBreak() throws IOException {
        BufferedReader br = fileReader();
        String line = "";
        //所有可能输入,用于暴力破解
        Set<String> legalSet = Utils.initLegalSet();
        //删除两个特殊符号
        legalSet.remove("Backspace");
        legalSet.remove("\n");
        //转化为数组
        String[] legalArray = legalSet.toArray(new String[0]);

        //按行读取存储的数据
        while (true) {
            line = br.readLine();
            if (line == null) {
                break;
            }
            //以空格为分隔，将每一行的数据转为tokens，便于后续分析
            String[] tokens = line.split(" ");
            //假设长密码长度为16，如果长度大于16，取16.否则取当前行数的长度
            int length = Math.min(16,tokens.length);

            for (int i = 1; i <= length; i++){
                String ans = recursiveBrute(0,i,legalArray,"");
                if (!ans.equals("")){
                    return ans;
                }
            }
        }
        return "Failed";
    }

    //递归遍历密码
    private static String recursiveBrute(int curDepth, int maxDepth, String[] legalArray, String str){
        if (curDepth == maxDepth){
            if (PasswordCheck.check(str)){
                return str;
            }else {
                return "";
            }
        }else {
            String[] temp = new String[legalArray.length];
            for (int i = 0; i < legalArray.length; i++){
                temp[i] = recursiveBrute(curDepth + 1, maxDepth,legalArray,str + legalArray[i]);
            }

            for (int i = 0; i < temp.length; i++){
                if (temp[i] != ""){
                    return temp[i];
                }
            }
            return "";
        }
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
