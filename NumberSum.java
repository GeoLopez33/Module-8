package Concurrency;
import java.util.Random;

public class NumberSum {

	private static int[] numbers = new int[200000000];
	private static int multiTotal = 0;	
	
	public static synchronized void addToSum(int n) {
		multiTotal += n;
	}
	
	public static void main(String[] args) {
		
		
		//I created the Single Thread first only because
		//it was easy for my head to wrap around first.
		//It will simply run through the whole array and add onto 
		//a variable called "sum" to find the sum of the array
		
		
		Thread soloThread = new Thread (new Runnable() {

			
			public void run() {
				int sum = 0;
			    for (int value : numbers) {
			        sum += value;
				}
				
			    System.out.println("Sum of the random Array: " + sum);
			}
			
		});
		
//		For the multi-threaded approach. I decided to split 
//		the workload into 4 pieces. The four different threads
//		will find the sum of 1 quarter of the array and add its
//		piece to the global total when it is done.
		
		
		
		
		Thread multiThread1 = new Thread (new Runnable() {

			
			public void run() {
				int sum = 0;
			    for (int i =0; i<50000000; i++) {
			        sum += numbers[i];
				}
				
			    addToSum(sum);
			
			}
			
		});
		
		Thread multiThread2 = new Thread (new Runnable() {

			
			public void run() {
				int sum = 0;
			    for (int i =50000000; i<100000000; i++) {
			    	sum += numbers[i];
			    	
				}
				
			    addToSum(sum);
			   
			}
			
		});
		
		Thread multiThread3 = new Thread (new Runnable() {

			
			public void run() {
				int sum = 0;
			    for (int i =100000000; i<150000000; i++) {
			    	sum += numbers[i];
				}
				
			    addToSum(sum);
			   
			}
			
		});

		Thread multiThread4 = new Thread (new Runnable() {

	
			public void run() {
				int sum = 0;
			    for (int i =150000000; i<200000000; i++) {
			    	sum += numbers[i];
			    	
			}
			
		    addToSum(sum);
		   
		}
		
	});
		
		
//		I create the random number array here using 
//		the Random Java class
		
		Random rand = new Random();
		
		for (int i =0; i<200000000; i++) {
			numbers[i] = rand.nextInt(10)+1;
		}
		
		
//		System.out.println(numbers[0]);
//		System.out.println(numbers[2345234]);
//		System.out.println(numbers[43536]);
//		System.out.println(numbers[8653432]);
//		System.out.println(numbers[199999999]);
		
//		I used this commented code to confirm random spots had data in it.
//		Also checked the first and last entry
		
		
//		I created variables to make it easy to track 
//		system time once I run the threads. I use the 
//		difference in start and end time.
		
		long startTime;
		long endTime;
		
		System.out.println("I will start the multi thread now.");
		startTime=System.nanoTime();
		
		multiThread1.start();
		multiThread2.start();
		multiThread3.start();
		multiThread4.start();
		
		try {
			multiThread1.join();
			multiThread2.join();
			multiThread3.join();
			multiThread4.join();
			
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Sum of Array: " + multiTotal);
		endTime=System.nanoTime();
		System.out.println("Time it took to find sum of array: " + (endTime-startTime) + " Nanoseconds");
		
		
		
		
		//First I ran multi thread, 
		//now I will run the single 
		//thread version
		
		System.out.println("\n\nI will start the single thread now.");
		startTime=System.nanoTime();
		
		
		soloThread.start();
		try {
			soloThread.join();	
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		endTime=System.nanoTime();
		System.out.println("Time it took to find sum of array: " + (endTime-startTime) + " Nanoseconds");
		
		
//		In the end, the multi thread option ran faster 
//		than the single thread option
	}
}
