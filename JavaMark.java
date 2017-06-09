import java.util.Scanner;
public class JavaMark
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to JavaMark, \nthe benchmark utility that is given under the MIT License with an 'as is' warranty.\nBy continuing after this message, you certify that any damage done to your machine \nis not at the legal fault or liability of the developers of this software.");
		System.out.println("Select a benchmark to run by typing its corresponding number, then press enter.\n");
		System.out.print("[1] - Sieve Of Eratosthenes\n[2] - Innacurate pi calculation\n[3] - UNIMPLEMENTED\nType any other number or string to quit\n:");
		// try
		// {
		// 	switch(in.nextInt())
		// 	{
		// 		case 1: sieveOfEratosthenesWrapper();
		// 				break;
		// 		case 2: System.out.print("Sorry. That's not an option yet.\n");//Insert PiMark code
		// 				break;
		// 		case 3: System.out.print("Sorry. That's not an option yet.\n");//the special prime thing Noah is making
		// 				break;
		// 		default: System.out.print("Sorry. That's not an option.\n");
		// 	}
		// }
		// catch(java.util.InputMismatchException e)
		// {
		// 	System.out.println("Quitting...\n");
		// }
		sieveOfEratosthenes(0);
	}
	public static void sieveOfEratosthenesWrapper()
	{
		int top = 0;
		System.out.print("Enter a number to find primes from 2 through that number: ");
		Scanner in = new Scanner(System.in);
		top = in.nextInt();
		in.nextLine();
		if(top > 2100000000)
		{
			System.out.print("Try a number that's smaller.\n");
			sieveOfEratosthenesWrapper();
			return;
		}
		else if(top < 20)
		{
			System.out.print("Try a number that's bigger.\n");
			sieveOfEratosthenesWrapper();
			return;
		}
		sieveOfEratosthenes(top);
		System.out.println("\n");
	}
	public static void sieveOfEratosthenes(int n)
	{
		if(n == 0)
		{
			n = 2100000000;
		}
		boolean[] prime = new boolean[n+1];
		for(int i=0;i<n;i++)
		{
			prime[i] = true;
		}
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
		for(int i = 2; i <= n; i++)
		{
			if(prime[i])
			{
				System.out.print(i + " ");
			}
		}
	}
}
