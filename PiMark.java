import java.util.ArrayList;
import java.util.Scanner;
class Main {
  public static void main(String[] args)
  {
  	ArrayList<Long> list = new ArrayList<Long>();
  	long top = 0;
    System.out.print("Enter a number to find primes from 2 through that number: ");
    Scanner in = new Scanner(System.in);
    top = in.nextLong();
    in.nextLine();
    for(long i = 2; i <= top; i++)
    {
    	list.add(i);
    }
    removeNonPrimes(list);
    System.out.println(list);
  }
  public static void removeNonPrimes(ArrayList<Long> list)
  {
  	ArrayList<Long> composites = new ArrayList<Long>(0); 
  	long n = list.get(list.size()-1);
  	for(long l : list)
  	{
  		if (composites.contains(l))
  		{
  			continue;
  		}
  		if (l*l > n)
  		{
  			break;
  		}
  		for(long i = l*l; i <= n; i += l)
	  	{
	  		composites.add(i);
	  	}
  	}
  	list.removeAll(composites);
  }
}
