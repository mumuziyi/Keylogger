public class LCG {
    public static void main(String[] args) {
        long a = 16807;
        long b = 0;
        long m = 2147483647;
        long x0 = 1235123;
        System.out.println(m);

        for(int i = 0 ; i < 100 ; i++){
            x0 = LGC(m,a,b,x0);
            System.out.print( x0 + "  ");
        }
    }
    public static long LGC(long m, long a, long b, long x0){
        return (a*x0 + b) % m;
    }
}
