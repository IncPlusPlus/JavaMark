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


      /*
      * timeArr is an array that will hold the completion time of each execution of the sieve.
      * The int "top" influences the number of executions of the sieve.
      * This is to ensure a more consistent number. 50000 is the minimum number of tests.
      * These times will later be averaged to determine the average completion time.
      */
      int[] timeArr = new int[Math.max((int)(Math.sqrt(top)*5),50000)];
      //Create the array of completion times
      for (int i = 0; i < timeArr.length; i++)
         timeArr[i] = sieveOfEratosthenes(top);
      //Output the average time of the array of times
      System.out.println("Elapsed: " + average(timeArr));
	}

	/**
	 * Runs the sieve. The sieve creates an array of true booleans.
	 * The array is iterated across and
	 * @param a the number to determine primes up to
	 * @return an int of the execution time.
	 */
	public static int sieveOfEratosthenes(int a)
	{
		/*
		* Init a local variable to be used as
		* the number to find primes up to
		*/
		int n = a;
		//This will occur only when the auto benchmark is to be run.
		if(n == 0)
		{
			n = 100000000;
		}

		boolean[] prime = new boolean[n+1];	//Create boolean array
		for(int i=0; i<n; i++)
		{
			prime[i] = true;	//Set each boolean to true
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
 		for(int i = 2; i <= n; i++)
 		{
 			if(prime[i])
 			{
 				System.out.print(i + " ");
 			}
 		}
 		return (int)stopwatch.elapsed(TimeUnit.MICROSECONDS);
	}

	/**
	 *
	 * @param arr the array of values to average
	 * @return the average of the elements in the array
	 */
	public static long average(int[] arr)
	{
		long total = 0;
		for (long val : arr)
			total += val;
		return total/arr.length;
	}
}
