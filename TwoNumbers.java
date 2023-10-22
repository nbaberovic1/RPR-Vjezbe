import java.util.Scanner;

public class TwoNumbers{
	public static void main(String[] args){
		Scanner ulaz = new Scanner(System.in);
		System.out.println("Unesite prvi broj: ");
		int num1 = ulaz.nextInt();
		System.out.println("Unesite drugi broj: ");
		int num2 = ulaz.nextInt();
		
		System.out.println("Prvi uneseni broj je: " + num1);
		System.out.println("Drugi uneseni broj je: " + num2);
	}
}