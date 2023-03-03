import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RequiredDataUtils {
    //at least pressed 'numberNeedBePressed' keys to pass the verification
    public static int numberNeedBePressed = 4;

    public static double requiredPressTime = 2;

    public static Map<String,String> legalKeys = initMap();

    public static Map<String, String> initMap() {
        Map<String,String> initMap = new HashMap<String, String>();
        initMap.put("空格","Blank");
        initMap.put("减号","-");
        initMap.put("等号","=");
        initMap.put("Caps Lock","Capslock");
        initMap.put("右方括号","]");
        initMap.put("左方括号","[");
        initMap.put("逗号",",");
        initMap.put("句点",".");
        initMap.put("斜杠","/");
        initMap.put("分号",";");
        initMap.put("引号","'");
        initMap.put("反斜杠","\\");
        initMap.put("后引号","`");
        initMap.put("Enter","\n");
        return initMap;
    }
}
