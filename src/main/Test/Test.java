public class Test {
    public static void main(String[] args) {
        String[] legalStr = {"a", "b", "c"};
        //aabbb
        StringBuilder sb = new StringBuilder();

        System.out.println(isRight(3,0,"",legalStr));

    }

    public static String isRight(int maxDepth, int curDepth, String str, String[] legalStr) {

        if (curDepth == maxDepth){
            //check
            if (str.equals("aba")) {
                return str;
            }else {
                return "";
            }
        }else {
            String[] tmp = new String[legalStr.length];
            for (int i = 0; i < legalStr.length; i++){
                tmp[i] = isRight(maxDepth,curDepth+1, str + legalStr[i],legalStr);
            }
//            筛选tmp中间的非空值，如果是非空，返回非空，如果全是空，返回""
            for (int i = 0; i < tmp.length; i++){
                if (tmp[i] != ""){
                    return tmp[i];
                }
            }
            return "";
        }
    }
}
