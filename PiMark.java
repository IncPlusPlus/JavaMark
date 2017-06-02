import java.util.function.Function;
import java.math.BigInteger;

class Main {
  public static void main(String[] args)
  {
    // for (int i = 0; i < 50; i++) System.out.println(pi(i));
    // System.out.println(piSum());
    System.out.println(factorial(BigInteger.valueOf(200)));
  }
  
  // public static double pi(int top)
  // {
  // 	return 1/((2*Math.sqrt(2)/9801) * summation(x -> piSum(x), 0, top));
  // }
  
  // public static double summation(Function<Integer, Double> function, int k, int top)
  // {
  // 	return top == k ? function.apply(k) : function.apply(top) + summation(function, k, top-1);
  // }
  
  // public static double piSum(int k)
  // {
  // 	return (factorial(4*k)*(1103 + 26390*k))/(Math.pow(factorial(k), 4)*Math.pow(396, 4*k));
  // }

  public static BigInteger factorial(BigInteger n)
  {
  	return n.compareTo(BigInteger.valueOf(2)) < 0 ? BigInteger.valueOf(1) : n.multiply(factorial(n.subtract(BigInteger.valueOf(1))));
  }
}
//https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html
