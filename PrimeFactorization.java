import java.util.*;
import java.time.*;
import java.math.*;


public class PrimeFactorization {
 
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int inp1;
      search: //label for the loop
      do{
        System.out.println("\nHow many digits do you want? ");
        // if(sc.hasNextInt()) {
        //      inp1 = sc.nextInt();
        // }
        // else {
        //     System.out.println("\nAre you serious? Try again.");
        //     continue search;
        // 
        // couldnt try this because it will run in an ongoing loop.
        // tried with try catch and that also ened with same result.
        // please try to input only integers.
        // }
        inp1 = sc.nextInt();
        System.out.println("Generating random numbers....");
        Instant first = Instant.now();
        BigInteger num1 = generateBigIntegerWithNumberOfDigits(inp1); 
        //anything >= 20 digits will take a huge amount of time as I tried.
        System.out.println("num1: " + num1);
        System.out.println("size of num1: "+ num1.toString().length()+" digits.");
        String str = "" + PrimeFactorization.primeBigFactorize(num1);
        Instant second = Instant.now();
        System.out.printf(str);
        Duration duration = Duration.between(first, second);
        System.out.printf("\nTime taken: "+ duration.toNanos()+" Nanoseconds."+"\nOr");
        System.out.printf("\nTime taken: "+ duration.toMillis()+" Milliseconds.");
        System.out.println("\nWant to try again? y/n: ");
        char yn = sc.next().charAt(0);
        if(yn=='n' || yn=='N'){
          break search;
        }
        else if(yn=='y' || yn=='Y'){
          continue search;
        }
        else{
         System.out.println("It was just y or n that you had to input. I'm a computer. Give me some break.");
         break search;
        }
        }
      while(true);
      sc.close();
      System.out.println("Bye!");
    }


  private static void setBigPrimeFactor(HashMap<BigInteger, BigInteger> primeFactors, BigInteger primeFactor) {
    BigInteger multiplicity = primeFactors.containsKey(primeFactor) ? primeFactors.get(primeFactor) : BigInteger.ZERO;
    primeFactors.put(primeFactor, multiplicity.add(BigInteger.ONE));
  }

  public static HashMap<BigInteger, BigInteger> primeBigFactorize(BigInteger n) {
    HashMap<BigInteger, BigInteger> primeFactors = new HashMap<>();
    BigInteger primeFactor = BigInteger.ZERO;
    BigInteger i = new BigInteger("2");

    while (i.compareTo(n.divide(i)) <= 0) {
      if (n.mod(i).longValue() == 0) {
        primeFactor = i;
        setBigPrimeFactor(primeFactors, primeFactor);
        n = n.divide(i);
      } else {
        i = i.add(BigInteger.ONE);
      }
    }
    
    if (primeFactor.compareTo(n) < 0) primeFactor = n;
    setBigPrimeFactor(primeFactors, primeFactor);
    
    return primeFactors;
  }


  //this is the algorithm from the final module from COMPSCI 3130 BigIntegerExample.zip
  private static BigInteger generateBigIntegerWithNumberOfDigits(int n) {
		BigInteger mybig = null;
		int initrand = (int) (Math.random() * 9 + 1);
		if (n > 0) {
			mybig = new BigInteger("" + initrand);
			for (int i = 1; i < n; i++) {
				int crtdigit = (int) (Math.random() * 10);
				BigInteger big1 = new BigInteger("" + crtdigit);
				mybig = mybig.multiply(new BigInteger("10"));
				mybig = mybig.add(big1);
			}
		} else {
			mybig = new BigInteger("0");
		}
		return mybig;
	}


}
