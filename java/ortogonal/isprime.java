
package ortogonal;
public class isprime {

    public static void main(String[] args) {
   

    }

    
    public static boolean prime_checked(int a) {
        boolean b = true;

        for (int i = 2; i < a; i++) {

            if (a % i == 0) {
                b = true;
                break;
            } else {
                b = false;
            }
        }
        if(a==2) b=false;
        return b;

    }
}
