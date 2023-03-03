import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class KeyPressed implements NativeKeyListener {



    public static boolean EnterPressed = false;

    public static Set<String> pressedKeys = new HashSet<String>();

    public static boolean flag;

    private static Date date = new Date();

    public void nativeKeyPressed(NativeKeyEvent e) {
        // get input
        String pressed = NativeKeyEvent.getKeyText(e.getKeyCode());

        // map keys, better for process
        if (RequiredDataUtils.legalKeys.containsKey(pressed)){
            pressed = RequiredDataUtils.legalKeys.get(pressed);
        }
        System.out.println(pressed);




        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("key released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
//        Date releasedTime = new Date();
//        flag = false;
//        System.out.println("pressed time :" + (releasedTime.getTime() - date.getTime()) / 1000);
    }


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

        GlobalScreen.addNativeKeyListener(new KeyPressed());
    }
}