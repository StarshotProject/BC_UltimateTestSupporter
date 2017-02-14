
/*walidacje na pliku bankowym:
	1. daty:
		- data musi byæ wartoœci¹ numeryczn¹
		- data musi mieæ 8 cyfr
		- data wyciagu bankowego nie mo¿e byæ wczeœniejsza ni¿ data wyci¹gu bankowego
	2. Numer konta: 
 		- numer konta musi byæ wartoœci¹ numeryczn¹
		- musi mieæ 10 lub 26 cyfr
		- je¿eli plik dla agenta, to nie wykonujemy walidacji na numerze konta
	3. kwota:
		- musi byæ wartoœci¹ numeryczn¹. 
*/

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class BankFilesPanel extends JPanel implements ActionListener {

	JTextField dateOfPayment, dateOfBankStatement, accountNumber, transferTittle, amount;
	JButton generateFile;
	String fileContent, dateOfPaymentReady, dateOfBankStatementReady, accountNumberReady, transferTittleReady, fileName,
			sequence;
	JLabel troubleTicket, JLDateOfPayment, JLDateOfBankStatement, JLaccountNumber, JLtransferTittle, JLamount,
			JLAgentClient;
	JRadioButton JRClient, JRAgent;
	ButtonGroup AgentClient;
	boolean clientTypeFile = true;

	static final int shortDigitAccountNumber = 10;
	static final int longDigitAccountNumber = 26;

	public BankFilesPanel() {

		setLayout(null);
		JLDateOfPayment = new JLabel("Data p³atnoœci:");
		JLDateOfPayment.setBounds(5, 5, 75, 30);
		add(JLDateOfPayment);
		dateOfPayment = new JTextField("RRRRMMDD", 8);
		dateOfPayment.setBounds(80, 5, 80, 30);
		dateOfPayment.setDocument(new JTextFieldLimit(8));
		dateOfPayment.setText("RRRRMMDD");
		add(dateOfPayment);

		JLDateOfBankStatement = new JLabel("Data wyci¹gu bankowego:");
		JLDateOfBankStatement.setBounds(180, 5, 130, 30);
		add(JLDateOfBankStatement);
		dateOfBankStatement = new JTextField("RRRRMMDD", 8);
		dateOfBankStatement.setBounds(310, 5, 80, 30);
		dateOfBankStatement.setDocument(new JTextFieldLimit(8));
		dateOfBankStatement.setText("RRRRMMDD");
		add(dateOfBankStatement);

		JLaccountNumber = new JLabel("Numer konta:");
		JLaccountNumber.setBounds(122, 40, 75, 30);
		add(JLaccountNumber);
		accountNumber = new JTextField("9999999999", 10);
		accountNumber.setBounds(190, 40, 200, 30);
		accountNumber.setToolTipText("Podaj 10 cyfr jako numer BC, lub 26 cyfr jako numer konta");
		add(accountNumber);

		JLtransferTittle = new JLabel("Tytu³ przelewu:");
		JLtransferTittle.setBounds(112, 75, 130, 30);
		add(JLtransferTittle);
		transferTittle = new JTextField("tytu³_przelewu");
		transferTittle.setBounds(190, 75, 200, 30);
		add(transferTittle);

		JLamount = new JLabel("Kwota w groszach:");
		JLamount.setBounds(97, 110, 95, 30);
		add(JLamount);
		amount = new JTextField();
		amount.setBounds(190, 110, 200, 30);
		add(amount);

		JLAgentClient = new JLabel("Przelew do klienta czy agenta?");
		JLAgentClient.setBounds(90, 150, 160, 30);
		add(JLAgentClient);

		AgentClient = new ButtonGroup();

		JRClient = new JRadioButton("Klient", true);
		JRClient.setBounds(250, 160, 90, 20);
		AgentClient.add(JRClient);
		add(JRClient);
		JRClient.addActionListener(this);

		JRAgent = new JRadioButton("Agent", false);
		JRAgent.setBounds(340, 160, 100, 20);
		AgentClient.add(JRAgent);
		add(JRAgent);
		JRAgent.addActionListener(this);

		generateFile = new JButton("Generuj plik");
		generateFile.setBounds(125, 200, 200, 50);
		add(generateFile);
		generateFile.addActionListener(this);

		troubleTicket = new JLabel();
		troubleTicket.setBounds(20, 250, 430, 50);
		troubleTicket.setFocusable(true);
		add(troubleTicket);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object eventSource = e.getSource();

		if (eventSource == JRClient) {
			clientTypeFile = true;
		} else if (eventSource == JRAgent) {
			clientTypeFile = false;
		} else if (eventSource == generateFile) {
			System.out.println("Otrzymano zdarzenie typu ActionEvent");
			accountNumberReady = accountNumber.getText().replaceAll("\\W", "");

			troubleTicket.setText("");
			dateOfPayment.setBackground(Color.WHITE);
			dateOfBankStatement.setBackground(Color.WHITE);
			accountNumber.setBackground(Color.WHITE);
			transferTittle.setBackground(Color.WHITE);
			amount.setBackground(Color.WHITE);

			boolean properAccountNumber = false;
			boolean properDates = false;
			boolean properAmount = false;
			boolean properDateOfPayment = false;
			boolean properDateOfBankStatement = false;

			// ************** walidacja pola data p³atnoœci*************//
			if (Validations.isNumeric(dateOfPayment.getText())) {
				if (Validations.is8Digits(dateOfPayment.getText())) {
					properDateOfPayment = true;
				} else {
					troubleTicket.setText("Pole 'data p³atnoœci' nie ma oœmiu cyfr");
					dateOfPayment.setBackground(new Color(255, 150, 150));
				}
			} else {
				troubleTicket.setText("Pole 'data p³atnoœci' nie ma wartoœci liczbowej");
				dateOfPayment.setBackground(new Color(255, 150, 150));
			}

			// **************walidacja pola data wyci¹gu bankowego*******//

			if (properDateOfPayment) {
				if (Validations.isNumeric(dateOfBankStatement.getText())) {
					if (Validations.is8Digits(dateOfBankStatement.getText())) {
						properDateOfBankStatement = true;
					} else {
						troubleTicket.setText("Pola 'data p³atnoœci' nie ma oœmiu cyfr");
						dateOfBankStatement.setBackground(new Color(255, 150, 150));
					}
				} else {
					troubleTicket.setText("Pola 'data p³atnoœci' nie ma wartoœci liczbowej");
					dateOfBankStatement.setBackground(new Color(255, 150, 150));
				}
			}

			// *************walidacja ³¹czna na polach datowych**********//

			if (properDateOfPayment && properDateOfBankStatement) {
				if (Validations.areDatesInProperReliance(dateOfPayment.getText(), dateOfBankStatement.getText())) {
					properDates = true;
				} else {
					troubleTicket.setText("Data wyci¹gu bankowego jest wczeœniejsza ni¿ data p³atnoœci!");
					dateOfBankStatement.setBackground(new Color(255, 150, 150));
				}
			}

			// **************walidacja pola numer konta*******************//

			if (properDates) {
				if (clientTypeFile) {
					if (Validations.isNumeric(accountNumberReady)) {
						if (accountNumberReady.length() < shortDigitAccountNumber) {
							troubleTicket.setText(
									"Numer konta jest za krótki!" + "  (" + accountNumberReady.length() + "cyfr)");
							accountNumber.setBackground(new Color(255, 150, 150));
						} else if ((accountNumberReady.length() == shortDigitAccountNumber)
								|| (accountNumberReady.length() == longDigitAccountNumber)) {
							properAccountNumber = true;
						} else if (accountNumberReady.length() > shortDigitAccountNumber
								&& accountNumberReady.length() < longDigitAccountNumber) {
							troubleTicket.setText("Numer konta jest d³u¿szy ni¿ 10 i krótszy ni¿ 26 cyfr!" + "  ("
									+ accountNumberReady.length() + "cyfr)");
							accountNumber.setBackground(new Color(255, 150, 150));
						} else {
							troubleTicket.setText(
									"Numer konta jest za d³ugi!" + "  (" + accountNumberReady.length() + "cyfr)");
							accountNumber.setBackground(new Color(255, 150, 150));
						}
					} else {
						troubleTicket.setText("Numer konta nie jest wartoœci¹ liczbow¹!");
						accountNumber.setBackground(new Color(255, 150, 150));
					}
				} else {
					properAccountNumber = true;
				}
			}

			// **************walidacje pola kwota******************//

			if (properAccountNumber) {
				if (Validations.isNumeric(amount.getText())) {
					properAmount = true;
				} else {
					troubleTicket.setText("W polu kwota nie ma wartoœci liczbowej!");
					amount.setBackground(new Color(255, 150, 150));
				}
			}

			// **************sk³adanie treœci pliku****************//

			if (properAmount) {
				BankFile.create(StringAssembler.assemblyFileName(dateOfPayment.getText(), clientTypeFile),
						StringAssembler.assemblyFileContent(dateOfPayment.getText(), amount.getText(),
								transferTittle.getText(), accountNumberReady, dateOfBankStatement.getText()));
				troubleTicket.setText("Wygenerowano plik bankowy "
						+ StringAssembler.assemblyFileName(dateOfPayment.getText(), clientTypeFile));

			}
		}
	}
}
