package application;

import java.util.Scanner;

public class LAB3_P1 {
	/** Return true if the card number is valid */
	public static boolean isValid(long number) {
		int odd_sum=sumOfOddPlace(number);
		int even_sum=sumOfDoubleEvenPlace(number);
		int siz=getSize(number);
		if(siz<13 || siz>16) {
			return false;
		}
		if(!prefixMatched(number,4) && !prefixMatched(number,5) && !prefixMatched(number, 37) && !prefixMatched(number, 6)) {
			return false;
		}
		if((odd_sum+even_sum)%10!=0) {
			return false;
		}
		return true;
	}

	/** Get the result from Step 2 */
	public static int sumOfDoubleEvenPlace(long num) {
		int sum=0;
		int doubled;
		long number=num;
		while(number>0) {
			number=number/10;
			doubled=getDigit((int)(number%10));
			sum+=doubled;
			number=number/10;
		}
		return sum;
	}

	/**
	 * Return this number if it is a single digit, otherwise, return the sum of
	 * the two digits
	 */
	public static int getDigit(int number) {
		int num=number*2;
		if(num<10) {
			return num;
		}
		return ((num%10)+1);
	}

	/** Return sum of odd place digits in number */
	public static int sumOfOddPlace(long num) {
		long number = num;
		int sum=0;
		while(number>0) {
			sum+=number%10;
			number=number/10;
			number=number/10;
		}
		return sum;
	}

	/** Return true if the digit d is a prefix for number */
	public static boolean prefixMatched(long number, int d) {
		int length_d=getSize(d);
		long prefix=getPrefix(number,length_d);
		if (prefix==d)
			return true;
		return false;
	}

	/** Return the number of digits in d */
	public static int getSize(long d) {
		long digits=d;
		int length=0;
		while(digits>0) {
			length++;
			digits=digits/10;
		}
		return length;
	}

	/**
	 * Return the first k number of digits from number. If the number of digits
	 * in number is less than k, return number.
	 */
	public static long getPrefix(long number, int k) {
		int length=getSize(number);
		int toDelete=length-k;
		long prefix=number;
		if(toDelete>0)
			prefix=(long) (number/(Math.pow(10, toDelete)));
		return prefix;
	}
}
