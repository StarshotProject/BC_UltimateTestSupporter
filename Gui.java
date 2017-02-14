/*dodaæ generatory danych - pesel, regon, 
http://www.bogus.ovh.org/generatory/all.html

- zaznaczanie pola, w którym jest b³¹d.
- zmiana wygl¹du
- Przenieœæ siê na github 
- dodaæ case 10 lub 26 cyfr
- ograniczyæ stosowanie klas anonimowych, bo uniemo¿liwiaj¹ póŸniejsze odwo³ywanie siê do ich instancji

- Analizator skladniowy,  konsumuje stringa do podanej kolumny

 * */

import java.awt.AWTEvent;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class Gui extends JFrame implements ActionListener {

	JMenuBar menuBar;
	JMenu file, options, validations, help, open;
	JMenuItem validationConfig, about, instruction, menuItemOpenBankFile, menuItemOpenDealerFile, menuItemOpenAgencyFile, menuItemExit, menuItemLayout;
	JCheckBoxMenuItem customBankValidations, customDealerValidations, customAgencyValidations, stdBankValidations,
			stdDealerValidations, stdAgencyValidations, alwaysOnTop;
	ButtonGroup bankGroup, dealerGroup, agencyGroup;
	JFrame frame;
	BankFilesPanel bankFilesPanel = new BankFilesPanel();
	

	Gui() {
		System.out.println("Jestem konstruktorem klasy Gui");
		frame = new JFrame("bc_tool by Bartosz Samo³yk");
		frame.setLayout(null);
		frame.setSize(450, 450);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setAlwaysOnTop(false);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		file = new JMenu("Plik");
		menuBar.add(file);
		
		open = new JMenu("Otwórz...");
		file.add(open);
		
		menuItemOpenBankFile = new JMenuItem("Wczytaj plik bankowy");
		open.add(menuItemOpenBankFile);
		menuItemOpenBankFile.addActionListener(this);
		menuItemOpenDealerFile = new JMenuItem("Wczytaj plik dealera");
		open.add(menuItemOpenDealerFile);
		menuItemOpenAgencyFile = new JMenuItem("Wczytaj plikk agencji");
		open.add(menuItemOpenAgencyFile);
		menuItemExit = new JMenuItem("Zakoñcz");
		menuItemExit.addActionListener(this);
		file.addSeparator();
		file.add(menuItemExit);
		
		options = new JMenu("Opcje");
		menuBar.add(options);

		validations = new JMenu("Walidacje");
		options.add(validations);
		options.addSeparator();
		
		menuItemLayout = new JMenuItem("Wygl¹d");
		options.add(menuItemLayout);
		
		alwaysOnTop = new JCheckBoxMenuItem("Zawsze na wierzchu");
		alwaysOnTop.addActionListener(this);
		options.add(alwaysOnTop);

		help = new JMenu("Pomoc");
		menuBar.add(help);

		instruction = new JMenuItem("Instrukcja");
		help.add(instruction);
		help.addSeparator();

		about = new JMenuItem("O programie...");
		help.add(about);

		validationConfig = new JMenuItem("Konfiguracja walidacji...");
		validations.add(validationConfig);
		validations.addSeparator();

		bankGroup = new ButtonGroup();
		stdBankValidations = new JCheckBoxMenuItem("Standardowe walidacje pliku bankowego", true);
		validations.add(stdBankValidations);
		customBankValidations = new JCheckBoxMenuItem("W³asne walidacje pliku bankowego", false);
		validations.add(customBankValidations);
		bankGroup.add(stdBankValidations);
		bankGroup.add(customBankValidations);
		validations.addSeparator();

		dealerGroup = new ButtonGroup();
		stdDealerValidations = new JCheckBoxMenuItem("Standardowe walidacje pliku dealerskiego", true);
		validations.add(stdDealerValidations);
		customDealerValidations = new JCheckBoxMenuItem("W³asne walidacje pliku bankowego", false);
		validations.add(customDealerValidations);
		dealerGroup.add(stdDealerValidations);
		dealerGroup.add(customDealerValidations);
		validations.addSeparator();

		agencyGroup = new ButtonGroup();
		stdAgencyValidations = new JCheckBoxMenuItem("Standardowe walidacje pliku agencji", true);
		validations.add(stdAgencyValidations);
		customAgencyValidations = new JCheckBoxMenuItem("W³asne walidacje pliku agencji", false);
		validations.add(customAgencyValidations);
		agencyGroup.add(stdAgencyValidations);
		agencyGroup.add(customAgencyValidations);

		JTabbedPane jtp = new JTabbedPane();
		jtp.addTab("pliki bankowe", bankFilesPanel);
		jtp.setBounds(0, 0, 446, 425);
		jtp.addTab("pliki dealera", new DealerFilesPanel());
		jtp.setBounds(0, 0, 446, 425);
		jtp.addTab("pliki Agencji", new CentralAgencyFilesPanel());
		jtp.setBounds(0, 0, 446, 425);
		jtp.addTab("Inne generatory", new BankFilesPanel());
		jtp.setBounds(0, 0, 446, 425);
		jtp.addTab("logi", new LogsPanel());
		jtp.setBounds(0, 0, 446, 425);
		frame.add(jtp);
	}

	public void actionPerformed(ActionEvent e) {
		Object eventSource = e.getSource();
		if (eventSource == menuItemOpenBankFile) {
			JFileChooser fC = new JFileChooser();
			if (fC.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File file = fC.getSelectedFile();
				JOptionPane.showMessageDialog(null, "Wybrano plik :" + file.getAbsolutePath());
				Scanner skaner = null;
				try {
					skaner = new Scanner(file);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				// System.out.println(skaner.nextLine());
				String loadedContent = skaner.nextLine();
				System.out.println(loadedContent);

				loadedContent = loadedContent.substring(4, loadedContent.length());// obcinam
																					// 111,
				System.out.println(loadedContent);

				bankFilesPanel.dateOfPayment.setText(loadedContent.substring(0, 8));// zczytujê
																					// datê
				loadedContent = loadedContent.substring(9, loadedContent.length());// obcinam
																					// do
																					// kwoty
				System.out.println(loadedContent);

				bankFilesPanel.amount.setText(loadedContent.substring(0, loadedContent.indexOf(",")));
				loadedContent = loadedContent.substring(loadedContent.indexOf(",") + 1);
				System.out.println(loadedContent);

				loadedContent = loadedContent.substring(loadedContent.lastIndexOf(",00000000,10201026,") + 20,
						loadedContent.length());
				System.out.println(loadedContent);
				bankFilesPanel.transferTittle.setText(loadedContent.substring(0, loadedContent.indexOf("\"")));
				loadedContent = loadedContent.substring(loadedContent.indexOf("ID=") + 3, loadedContent.length());
				System.out.println(loadedContent);
				bankFilesPanel.accountNumber.setText(loadedContent.substring(0, loadedContent.indexOf("\"")));
				bankFilesPanel.dateOfBankStatement
						.setText(loadedContent.substring(loadedContent.length() - 9, loadedContent.length() - 1));
			}
		} else if (eventSource == alwaysOnTop) {
			if (alwaysOnTop.isSelected()) {
			System.out.println("zawsze na wierzchu");
			frame.setAlwaysOnTop(true);
			} else {
				frame.setAlwaysOnTop(false);
				System.out.println("NIE zawsze na wierzchu");
			}
		
		} else if (eventSource == menuItemExit) {
			System.out.println(e.getActionCommand());
			dispose();
		}
	}
}
