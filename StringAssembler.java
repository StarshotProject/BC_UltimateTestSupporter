public class StringAssembler {
	
	static String assemblyFileName(String dateOfPayment, boolean var) {
		if(var) {
		return "kl_" + dateOfPayment.substring(3, 8) + ".swr";
		} else {
			return "ag_" + dateOfPayment.substring(3, 8) + ".swr";
		}
	}

	static String assemblyFileContent(String dateOfPayment, String amount, String transferTittle,
			String accountNumber, String dateOfBankStatement) {
		if(accountNumber.length()==10) {
			return "111," + dateOfPayment + "," + amount
					+ ",16801235,10201026,\"15168012350000300011101853\",\"93102010260000110201929702\",\"01-121 WARSZAWA|||\",\"||| \",00000000,10201026,\""
					+ transferTittle + "\",\"\",\"" + StringAssembler.generateSequence() + "\",\"\",\"ID=0000000000000000" + accountNumber + "\"" + ","
					+ dateOfBankStatement + ",";
		}else {
			return "111," + dateOfPayment + "," + amount
					+ ",16801235,10201026,\"15168012350000300011101853\",\"93102010260000110201929702\",\"01-121 WARSZAWA|||\",\"||| \",00000000,10201026,\""
					+ transferTittle + "\",\"\",\"" + StringAssembler.generateSequence() + "\",\"\",\"ID=" + accountNumber + "\"" + ","
					+ dateOfBankStatement + ",";
		}
	}

	static String generateSequence() {
		String sequence = "0";
		for (int i = 0; i < 15; i++) {
			String tempString = String.valueOf((int) (9 * Math.random()));
			sequence = sequence + tempString;
		}
		return sequence;
	}
}
