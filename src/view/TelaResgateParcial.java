package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import DAO.ContaDAO;
import controller.ResgateController;
import model.Conta;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class TelaResgateParcial extends JInternalFrame {
	private JTextField txtValor;
	private ContaDAO dao = new ContaDAO();
	private ResgateController controller = new ResgateController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaResgateParcial frame = new TelaResgateParcial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaResgateParcial() {
		setTitle("Solicitação de resgate parcial");
		setClosable(true);
		setBounds(100, 100, 409, 535);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Dados da conta atual", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel.setBounds(12, 173, 379, 159);
		getContentPane().add(panel);
		
		JLabel label = new JLabel("Plano");
		label.setFont(new Font("Dialog", Font.PLAIN, 12));
		label.setBounds(12, 51, 54, 15);
		panel.add(label);
		
		JLabel lbNomePlano = new JLabel("");
		lbNomePlano.setBounds(146, 51, 221, 15);
		panel.add(lbNomePlano);
		
		JLabel lblSaldoNormal = new JLabel("Saldo normal");
		lblSaldoNormal.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSaldoNormal.setBounds(12, 78, 89, 15);
		panel.add(lblSaldoNormal);
		
		JLabel lbNormal = new JLabel("");
		lbNormal.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbNormal.setBounds(146, 78, 221, 15);
		panel.add(lbNormal);
		
		JLabel label_4 = new JLabel("Saldo adicional");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_4.setBounds(12, 105, 99, 15);
		panel.add(label_4);
		
		JLabel lbSaldoAdicional = new JLabel("");
		lbSaldoAdicional.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbSaldoAdicional.setBounds(146, 105, 221, 15);
		panel.add(lbSaldoAdicional);
		
		JLabel label_6 = new JLabel("Saldo portabilidade");
		label_6.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_6.setBounds(12, 133, 131, 15);
		panel.add(label_6);
		
		JLabel lbSaldoPortabilidade = new JLabel("");
		lbSaldoPortabilidade.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbSaldoPortabilidade.setBounds(146, 133, 221, 15);
		panel.add(lbSaldoPortabilidade);
		
		JLabel label_10 = new JLabel("Participante");
		label_10.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_10.setBounds(12, 24, 89, 15);
		panel.add(label_10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Dados da solicita\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 344, 375, 110);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_8 = new JLabel("Data da solicitação");
		label_8.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_8.setBounds(12, 29, 131, 15);
		panel_1.add(label_8);
		
		JDateChooser dtSolicitacao = new JDateChooser();
		dtSolicitacao.setBounds(150, 24, 217, 24);
		panel_1.add(dtSolicitacao);
		
		JLabel lblNormal = new JLabel("Tipo de resgate");
		lblNormal.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNormal.setBounds(12, 56, 131, 15);
		panel_1.add(lblNormal);
		
		JLabel lbValor = new JLabel("Valor");
		lbValor.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbValor.setBounds(12, 82, 99, 15);
		panel_1.add(lbValor);
		
		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(150, 80, 217, 19);
		panel_1.add(txtValor);
		
		JComboBox cbTipoResgate = new JComboBox();
		cbTipoResgate.setModel(new DefaultComboBoxModel(new String[] {"Normal", "Adicional", "Portabilidade"}));
		cbTipoResgate.setBounds(150, 51, 217, 24);
		panel_1.add(cbTipoResgate);
		
		List<Conta> contas = dao.listarContasAptas();
		JComboBox cbParticipantes = new JComboBox();
		cbParticipantes.setBounds(146, 19, 221, 24);
		cbParticipantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conta c1 = (Conta) cbParticipantes.getSelectedItem();
				lbNomePlano.setText(c1.getParticipante().getPlano().toString());
				lbNormal.setText(arredondar(c1.getSaldoNormal()));
				lbSaldoAdicional.setText(arredondar(c1.getSaldoAdicional()));
				lbSaldoPortabilidade.setText(arredondar(c1.getSaldoPortabilidade()));				
			}
		});	
		for (Conta conta: contas) {
			cbParticipantes.addItem(conta);
		}
		panel.add(cbParticipantes);
		
		JButton btnSolicitar = new JButton("Solicitar");
		btnSolicitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conta c1 = (Conta) cbParticipantes.getSelectedItem();
				LocalDate dataResgate =  dtSolicitacao.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				double valor = Double.parseDouble(txtValor.getText());
				String tipo = cbTipoResgate.getSelectedItem().toString();
				controller.receberResgateParcial(c1, dataResgate, valor, tipo);
				dispose();
			}
		});
		btnSolicitar.setBounds(270, 466, 117, 25);
		getContentPane().add(btnSolicitar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtValor.setText("");
				dtSolicitacao.setDate(null);
			}
		});
		btnLimpar.setBounds(141, 466, 117, 25);
		getContentPane().add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(12, 466, 117, 25);
		getContentPane().add(btnCancelar);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(TelaResgateParcial.class.getResource("/icons/wallet.png")));
		label_1.setBounds(138, 12, 137, 149);
		getContentPane().add(label_1);
		
		

	}
	
	private static String arredondar(Double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return decimalFormat.format(value);
    }
	
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}