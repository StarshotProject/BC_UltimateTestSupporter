import java.math.BigInteger;

//kontener walidacji

public class Validations {

	public static boolean isNumeric(String string) {
		boolean thisIsNumber = false;
		try {
			BigInteger value = new BigInteger(string);
		} catch (NumberFormatException nfe) {
			System.out.println("nieliczba");
			return thisIsNumber;

		}
		System.out.println("liczba");
		return !thisIsNumber;
	}

	public static boolean areDatesInProperReliance(String dateOfPayment, String dateOfBankStatement) {
		boolean properReliance = false;

		if (Integer.parseInt(dateOfBankStatement) >= Integer.parseInt(dateOfPayment)) {
			properReliance = true;
		}
		return properReliance;
	}

	public static boolean is8Digits(String date) {
		if (date.length() == 8) {
			return true;
		} else {
			return false;
		}
	}

}
