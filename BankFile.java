import java.io.IOException;
import java.io.PrintWriter;

public class BankFile {

	static void create(String fileName, String fileContent) {
		try (PrintWriter out = new PrintWriter(fileName)) {
			out.println(fileContent);
		} catch (IOException io) {
		}
	}
}