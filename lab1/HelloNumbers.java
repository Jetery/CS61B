public class HelloNumbers {
	public static void main(String[] args) {
		int i = 0, sum = i;
		while (i < 10) {
			System.out.print(sum + " ");
			i++;
			sum += i;
		}
	}
}