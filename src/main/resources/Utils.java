import java.util.*;

public class Utils {
    private static final Set<String> legalSet = new HashSet<String>();
    private static final Date date= new Date();
    private static long startTime = date.getTime();

    public static void main(String[] args) {
//        Map<String, String> test = MapRules();
//        Set<String> keySet = test.keySet();
//
//        for (String key: keySet){
//            System.out.println(key+ " -> " + test.get(key));
//        }
    }

    public static Set<String> initLegalSet(){
        String str = "1 2 3 4 5 6 7 8 9 0 - = " +
                "q w e r t y u i o p [ ] { } | < > ? _ + " +
                "a s d f g h j k l ; ' \\ " +
                "` z x c v b n m , . / " +
                " Backspace \n "+
                "Q W E R T Y U I O P A S D F G H J K L Z X C V B N M ";
        String[] legal = str.split(" ");
        legalSet.addAll(Arrays.asList(legal));
        System.out.println("a belong to " + legalSet.contains("a"));
        return legalSet;
    }

    public static boolean timeCheck(long cur){
        if ((cur - startTime) / 1000 > 10){
            startTime = cur;
            return true;
        }else {
            return false;
        }
    }

    //Turn Uppercase to lowercase
    public static String toLower(String str){
        StringBuilder sb = new StringBuilder();
        if (str.length() == 1 && str.charAt(0) >= 'A' && str.charAt(0) <= 'Z'){
            char ch = str.charAt(0);
            sb.append((char) (ch + ('a' - 'A')));
            return sb.toString();
        }
        return str;
    }

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

    //shift Map 之后进行substitution
    public static Map<String, String> MapRules(){

        //需要被shift的按键
        String str = "1 2 3 4 5 6 7 8 9 0 - = " +
                "q w e r t y u i o p [ ] " +
                "a s d f g h j k l ; ' \\ " +
                "' z x c v b n m , . / ";
        String[] legal = str.split(" ");
//        legalSet.addAll(Arrays.asList(legal));

        //用来存储shift之后的数据
        Map<String,String> map = new HashMap<String, String>();
        int length = legal.length;
        //随机向右shift 0 - length(47)位
        int shift = (int)(Math.random()*length);

        System.out.println(length);

        //进行shift过程
        String[] shiftedLegal = new String[length];
        for (int i = 0; i < length; i++){
            if (i + shift >= length){
                shiftedLegal[i] = legal[(i + shift) - length];
            }else {
                shiftedLegal[i] = legal[i + shift];
            }
        }

        //进行substitute
        String[] SiSuLegal = new String[length];
        Set<Integer> repetitionCheck = new HashSet<Integer>();

        //随机替换
        for (int i = 0; i < length; i++){
            int randomNum = (int)(Math.random()*47);
            while (repetitionCheck.contains(randomNum)){
                randomNum = (int)(Math.random()*47);
            }
            SiSuLegal[i] = shiftedLegal[randomNum];
            map.put(legal[i],SiSuLegal[i]);
            repetitionCheck.add(randomNum);
        }

        return map;
    }

    //如果用户按住shift，则进行符号替换
    public static Map<String, String> upperSymbol(){
        Map<String, String> upperSymbol = new HashMap<String, String>();
        upperSymbol.put("-","_");
        upperSymbol.put("=","+");
        upperSymbol.put("[","{");
        upperSymbol.put("]","}");
        upperSymbol.put(";",":");
        upperSymbol.put("'","\"");
        upperSymbol.put("\\","|");
        upperSymbol.put(",","<");
        upperSymbol.put(".",">");
        upperSymbol.put("/","?");

        return upperSymbol;
    }

}
