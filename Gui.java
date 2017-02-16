
/*dodaæ generatory danych - pesel, regon, 
http://www.bogus.ovh.org/generatory/all.html

- zmiana wygl¹du

- Analizator skladniowy do csv,  konsumuje stringa do podanej kolumny
- mo¿liwoœæ dodawania wielu linii w jednym pliku

 * */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

import GuiOperations.CustomColorSetter;

public class Gui extends JFrame implements ActionListener {

	JFrame frame;
	JMenuBar menuBar;
	JMenu file, options, validations, help, open, menuLayout;
	JMenuItem validationConfig, about, instruction, menuItemOpenBankFile, menuItemOpenDealerFile,
			menuItemOpenAgencyFile, menuItemExit;
	JCheckBoxMenuItem customBankValidations, customDealerValidations, customAgencyValidations, stdBankValidations,
			stdDealerValidations, stdAgencyValidations, alwaysOnTop, menuItemRed, menuItemGreen, menuItemBlue,
			menuItemPink, menuItemYellow, menuItemCustom, menuItemStandardColor;
	ButtonGroup bankGroup, dealerGroup, agencyGroup, layoutGroup;
	JColorChooser colorChooser;

	BankFilesPanel bankFilesPanel = new BankFilesPanel();
	DealerFilesPanel dealerFilesPanel = new DealerFilesPanel();
	CentralAgencyFilesPanel centralAgencyPanel = new CentralAgencyFilesPanel();
	OtherGeneratorsPanel otherGeneratorsPanel = new OtherGeneratorsPanel();
	LogsPanel logsPanel = new LogsPanel();
	
	CustomColorSetter customRGB;

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

		menuLayout = new JMenu("Wygl¹d");
		options.add(menuLayout);
		menuItemRed = new JCheckBoxMenuItem("Czerwony", false);
		menuItemRed.addActionListener(this);
		menuLayout.add(menuItemRed);
		menuItemGreen = new JCheckBoxMenuItem("Zielony", false);
		menuItemGreen.addActionListener(this);
		menuLayout.add(menuItemGreen);
		menuItemBlue = new JCheckBoxMenuItem("Niebieski", false);
		menuItemBlue.addActionListener(this);
		menuLayout.add(menuItemBlue);
		menuItemPink = new JCheckBoxMenuItem("Ró¿owy", false);
		menuItemPink.addActionListener(this);
		menuLayout.add(menuItemPink);
		menuItemYellow = new JCheckBoxMenuItem("¯ó³ty", false);
		menuItemYellow.addActionListener(this);
		menuLayout.add(menuItemYellow);
		menuItemYellow.addActionListener(this);
		menuLayout.addSeparator();
		menuItemCustom = new JCheckBoxMenuItem("W³asny kolor...", false);
		menuItemCustom.addActionListener(this);
		menuLayout.add(menuItemCustom);
		menuLayout.addSeparator();
		menuItemStandardColor = new JCheckBoxMenuItem("Kolor standardowy", true);
		menuLayout.add(menuItemStandardColor);
		menuItemStandardColor.addActionListener(this);
		layoutGroup = new ButtonGroup();
		layoutGroup.add(menuItemRed);
		layoutGroup.add(menuItemGreen);
		layoutGroup.add(menuItemBlue);
		layoutGroup.add(menuItemPink);
		layoutGroup.add(menuItemYellow);
		layoutGroup.add(menuItemCustom);
		layoutGroup.add(menuItemStandardColor);

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
		jtp.addTab("pliki dealera", dealerFilesPanel);
		jtp.addTab("pliki Agencji", centralAgencyPanel);
		jtp.addTab("Inne generatory", otherGeneratorsPanel);
		jtp.addTab("logi", logsPanel);
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
			frame.dispose();
		} else if (eventSource == menuItemRed) {
			bankFilesPanel.setBackground(Color.RED);
			dealerFilesPanel.setBackground(Color.RED);
			centralAgencyPanel.setBackground(Color.RED);
			otherGeneratorsPanel.setBackground(Color.RED);
			logsPanel.setBackground(Color.RED);
		} else if (eventSource == menuItemGreen) {
			bankFilesPanel.setBackground(Color.GREEN);
			dealerFilesPanel.setBackground(Color.GREEN);
			centralAgencyPanel.setBackground(Color.GREEN);
			otherGeneratorsPanel.setBackground(Color.GREEN);
			logsPanel.setBackground(Color.GREEN);
		} else if (eventSource == menuItemBlue) {
			bankFilesPanel.setBackground(Color.BLUE);
			dealerFilesPanel.setBackground(Color.BLUE);
			centralAgencyPanel.setBackground(Color.BLUE);
			otherGeneratorsPanel.setBackground(Color.BLUE);
			logsPanel.setBackground(Color.BLUE);
		} else if (eventSource == menuItemPink) {
			bankFilesPanel.setBackground(Color.PINK);
			dealerFilesPanel.setBackground(Color.PINK);
			centralAgencyPanel.setBackground(Color.PINK);
			otherGeneratorsPanel.setBackground(Color.PINK);
			logsPanel.setBackground(Color.PINK);
		} else if (eventSource == menuItemYellow) {
			bankFilesPanel.setBackground(Color.YELLOW);
			dealerFilesPanel.setBackground(Color.YELLOW);
			centralAgencyPanel.setBackground(Color.YELLOW);
			otherGeneratorsPanel.setBackground(Color.YELLOW);
			logsPanel.setBackground(Color.YELLOW);
		} else if (eventSource == menuItemStandardColor) {
			bankFilesPanel.setBackground(null);
			dealerFilesPanel.setBackground(null);
			centralAgencyPanel.setBackground(null);
			otherGeneratorsPanel.setBackground(null);
			logsPanel.setBackground(null);
		} else if(eventSource == menuItemCustom) {
			colorChooser = new JColorChooser();
			Color color = colorChooser.showDialog(null, "Wybierz w³asny kolor", Color.PINK);
			bankFilesPanel.setBackground(color);
			dealerFilesPanel.setBackground(color);
			centralAgencyPanel.setBackground(color);
			otherGeneratorsPanel.setBackground(color);
			logsPanel.setBackground(color);
		}
	}
}
