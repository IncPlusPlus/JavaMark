import java.util.function.Function;

class PiMark {
  public static void main(String[] args)
  {
    for (int i = 0; i < 10; i++) System.out.println(pi(i));
  }
  
  public static double pi(int top)
  {
  	return 1/((2*Math.sqrt(2)/9801) * summation(x -> piSum(x), 0, top));
  }
  
  public static double summation(Function<Integer, Double> function, int k, int top)
  {
  	return top == k ? function.apply(k) : function.apply(top) + summation(function, k, top-1);
  }
  
  public static double piSum(int k)
  {
  	return (factorial(4*k)*(1103 + 26390*k))/(Math.pow(factorial(k), 4)*Math.pow(396, 4*k));
  }
  
  public static int factorial(int n)
  {
  	return n < 2 ? 1 : n*factorial(n-1);
  }
}
