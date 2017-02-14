/*wykaz dealerski:
numer wykazu;nazwa kontraktu;data wp³yniêcia wykazu;rodzaj kontraktu;Ÿród³o;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
644441177/0002/16;kontrakt;20330403;agencja_centralna;polisy;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
dsp/insurer;nr_biznesowy polisy;nr_biznesowy aneksu;nr everestowy polisy;inkaso_laczne;numer_rejestracyjny;numer nadwozia;umowa Eve;umowa INS;inkaso_na_umowie;producer_code_inkasuj¹cy_EVE;kod_poœrednika_inkasuj¹cy_INS;producer_code_przekazujacy_EVE;kod_poœrednika_przekazujacy_INS;producer_code1_EVE;kod_poœrednika1INS;prowizja %_1;prowizja $_1;kod_poœrednika2EVE;kod_poœrednika2INS;prowizja %_2;prowizja $_2;kod_poœrednika3EVR;kod_poœrednika3INS;prowizja %_3;prowizja $_3;kod_poœrednika4EVR;kod_poœrednika4INS;prowizja %_4;prowizja $_4;kod_poœrednika5EVR;kod_poœrednika5INS;prowizja %_5;prowizja $_5;kod_poœrednika6EVR;kod_poœrednika6INS;prowizja %_6;prowizja $_6;zakres spec od;zakres spec do;status druku;okres od;okres do;kod_promocji;data_dokumentu p³atniczego;numer dokumentu wp³aty;WB;Przyczyna
EVE;business_number;;1001145493;1000;;;;;1000;NPR-POSREDNIK MULTI BC-nrUG71015001-9742638747;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;wplata

pola do uzupe³nienia:
- numer wykazu
- 


*/


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DealerFilesPanel extends JPanel implements ActionListener{
	
	JTextFieldLimit c;	
	

	public DealerFilesPanel() {
		
			  JTextField textfield1;

			  JLabel label1;

			  
			    setLayout(new FlowLayout());
			    label1 = new JLabel("max 10 chars");
			    textfield1 = new JTextField(15);//15 wyœwietla
			    add(label1);
			    add(textfield1);
			    textfield1.setDocument(new JTextFieldLimit(10));
			    
			    setSize(300,300);
			    setVisible(true);
			  }
			
		
		
	



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
