import com.sun.org.apache.xpath.internal.operations.Bool;

//模仿撞库，密码在这里存储
public class PasswordCheck {
    private static String password = "1234567aA<>/";
    public static Boolean check(String str){
        if (str.equals(password)){
            return true;
        }else {
            return false;
        }
    }
}
