public class FiboAndFac {

    //**** https://www.geeksforgeeks.org/program-for-nth-fibonacci-number/

    public static double fib(double n){
        if (n <= 1)
            return n;
        return fib(n-1) + fib(n-2);
    }

    //** https://www.javatpoint.com/factorial-program-in-java
    public static double fac(double n){
        int i,fact=1;
        for(i=1;i<=n;i++){
            fact=fact*i;
        }
        return fact;
    }
}
