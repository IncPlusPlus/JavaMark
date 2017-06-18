import com.google.common.base.Stopwatch; //http://google.github.io/guava/releases/20.0/api/docs/com/google/common/base/Stopwatch.html
//Since we're not using an IDE, we need to specify the classpath during compilation
//See https://stackoverflow.com/questions/9840521/how-to-add-jar-file-to-the-java-code
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * This class is the main class of JavaMark.
 * Run this class to run JavaMark.
 * Depending on the calculation, this program can use a substantial amount of memory.
 * Run this with {@code java -Xmx2048m -jar JavaMark.jar}.
 * It is recommended to have at least 2048 MB of ram available to run this.
 * @author Ryan Cloherty
 * @author Noah Levitt
 */
public class JavaMark
{
	/**
	 * Main method prompts the user to select an option
	 */
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to JavaMark, \nthe benchmark utility that is given under the MIT License with an 'as is' warranty.\nBy continuing after this message, you certify that any damage done to your machine \nis not at the legal fault or liability of the developers of this software.");
		System.out.println("Select a benchmark to run by typing its corresponding number, then press enter.\n");
		System.out.print("[1] - Sieve Of Eratosthenes\n[2] - (UNIMPLEMENTED): Innacurate pi calculation\n[3] - UNIMPLEMENTED\nType any other number or string to quit\n:");
		/* The user may enter a non int */
		try
		{
			switch(in.nextInt())
			{
				case 1: sieveOfEratosthenesWrapper();							//Run the sieve wrapper
						break;
				case 2: System.out.print("Sorry. That's not an option yet.\n");	//PiMark code unimplemented
						break;
				case 3: System.out.print("Sorry. That's not an option yet.\n");	//Any other future tests
						break;
				default: System.out.print("Sorry. That's not an option.\n");
						break;
			}
		}
		/* In which case exit gracefully */
		catch(java.util.InputMismatchException e)
		{
			System.out.println("Quitting...\n");
		}
	}

	/**
	 * This function acts as the conduit between the user and the sieve itself.
	 * The sieve is run from this function varying based on the number passed to it.
	 */
	public static void sieveOfEratosthenesWrapper()
	{
		int top;    // Init the value to find primes up to
		System.out.print("Enter a number to find primes from 2 through that number: ");
		Scanner in = new Scanner(System.in);
		top = in.nextInt();	//Take user's input
		in.nextLine();	//Clear the line character for possible next inputs

		/*Finding primes above this number will take an extremely long time.*/
		if(top > 100000000)
		{
			System.out.print("Try a number that's smaller.\n");
			sieveOfEratosthenesWrapper();	//Run the prompt again
			return;							//Do not continue with this number
		}
		/*Too many inconsitencies will occur when calculating below this number.*/
		else if(top < 50)
		{
			System.out.print("Try a number that's bigger.\n");
			sieveOfEratosthenesWrapper();	//Run the prompt again
			return;							//Do not continue with this number
		}


      /**/
      int[] timeArr = new int[Math.max((int)(Math.sqrt(top)*5),50000)];
      for (int i = 0; i < timeArr.length; i++)
         timeArr[i] = sieveOfEratosthenes(top);
		System.out.println("Elapsed: " + average(timeArr));
	}
	public static int sieveOfEratosthenes(int a)
	{
		int n = a;
		if(n == 0)
		{
			n = 100000000;
		}
      
		//Add a try/catch of initializing the boolean array to check if enough memory is allocated
		boolean[] prime = new boolean[n+1];
		for(int i=0;i<n;i++)
		{
			prime[i] = true;
		}
      Stopwatch stopwatch = Stopwatch.createStarted();
		for(int p = 2; p*p <=n; p++)
		{
			if(prime[p])
			{
				// Update all multiples of p
				for(int i = p*p; i <= n; i += p)
				{
					prime[i] = false;
				}
			}
		}
      stopwatch.stop();
// 		for(int i = 2; i <= n; i++)
// 		{
// 			if(prime[i])
// 			{
// 				System.out.print(i + " ");
// 			}
// 		}
      return (int)stopwatch.elapsed(TimeUnit.MICROSECONDS);
	}
   
   public static long average(int[] arr)
   {
      long avg = 0;
      for (long val : arr)
         avg += val;
      return avg/arr.length;
   }
}
