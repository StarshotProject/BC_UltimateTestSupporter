/*wykaz dealerski:
numer wykazu;nazwa kontraktu;data wp�yni�cia wykazu;rodzaj kontraktu;�r�d�o;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
644441177/0002/16;kontrakt;20330403;agencja_centralna;polisy;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
dsp/insurer;nr_biznesowy polisy;nr_biznesowy aneksu;nr everestowy polisy;inkaso_laczne;numer_rejestracyjny;numer nadwozia;umowa Eve;umowa INS;inkaso_na_umowie;producer_code_inkasuj�cy_EVE;kod_po�rednika_inkasuj�cy_INS;producer_code_przekazujacy_EVE;kod_po�rednika_przekazujacy_INS;producer_code1_EVE;kod_po�rednika1INS;prowizja %_1;prowizja $_1;kod_po�rednika2EVE;kod_po�rednika2INS;prowizja %_2;prowizja $_2;kod_po�rednika3EVR;kod_po�rednika3INS;prowizja %_3;prowizja $_3;kod_po�rednika4EVR;kod_po�rednika4INS;prowizja %_4;prowizja $_4;kod_po�rednika5EVR;kod_po�rednika5INS;prowizja %_5;prowizja $_5;kod_po�rednika6EVR;kod_po�rednika6INS;prowizja %_6;prowizja $_6;zakres spec od;zakres spec do;status druku;okres od;okres do;kod_promocji;data_dokumentu p�atniczego;numer dokumentu wp�aty;WB;Przyczyna
EVE;business_number;;1001145493;1000;;;;;1000;NPR-POSREDNIK MULTI BC-nrUG71015001-9742638747;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;wplata

pola do uzupe�nienia:
- numer wykazu
- 


*/

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DealerFilesPanel extends JPanel implements ActionListener {

	JLabel JLDocketNumber, JLDateOfDocket, JLPolicyBusinessNumber, JLEverestPolicyNumber, JLTotalCollection,
			JLContractCollection, JLFileName;
	JTextField JTextDocketNumber, JTextDateOfDocket, JTextPolicyBusinessNumber, JTextEverestPolicyNumber,
			JTextTotalCollection, JTextContractCollection, JTextFileName;

	public DealerFilesPanel() {

		setLayout(null);
		JLDocketNumber = new JLabel("Numer wykazu:");
		JLDocketNumber.setBounds(5, 5, 80, 30);
		add(JLDocketNumber);
		JTextDocketNumber = new JTextField("XXXXXXXXX/YYYY/ZZ");
		JTextDocketNumber.setBounds(80, 5, 120, 30);
		add(JTextDocketNumber);

		JLDateOfDocket = new JLabel("Data wp�yni�cia wykazu:");
		JLDateOfDocket.setBounds(225, 5, 120, 30);
		add(JLDateOfDocket);
		JTextDateOfDocket = new JTextField("RRRRMMDD");
		JTextDateOfDocket.setBounds(345, 5, 80, 30);
		add(JTextDateOfDocket);
		
		JLPolicyBusinessNumber = new JLabel("Numer biznesowy polisy:");
		JLPolicyBusinessNumber.setBounds(5, 40, 120, 30);
		add(JLPolicyBusinessNumber);
		JTextPolicyBusinessNumber = new JTextField("numer_biznesowy");
		JTextPolicyBusinessNumber.setBounds(125, 40, 100, 30);
		add(JTextPolicyBusinessNumber);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
