import java.io.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//测试文件无实际作用
public class Filetest {
    public static int time = 60 * 60 * 60;
    public static long midTime;

    public static void main(String[] args) throws IOException {
//        File file = new File("/Users/chen/Code/IdeaProjects/Keylogger/test.txt");
//        PrintStream ps = new PrintStream(new FileOutputStream(file));
//        for (int i = 0; i < 100; i++){
//            ps.append("hello");
//            System.out.println("hello");
//            ps.close();
//        }
//        time3();
        time();

    }

    public static void time(){
        Date date = new Date();
        long startTime = date.getTime();
        System.out.println(startTime);

    }

    /**
     * 使用java.util.Timer类进行倒计时
     */
    private static void time3() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                midTime++;
                long hh = midTime / 60 / 60 % 60;
                long mm = midTime / 60 % 60;
                long ss = midTime % 60;

                System.out.println("还剩" + hh + "小时" + mm + "分钟" + ss + "秒");
            }
        }, 0, 1000);
    }

}
