package view;

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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import DAO.ContaDAO;
import controller.ResgateController;
import model.Conta;
import javax.swing.ImageIcon;

public class TelaResgateTotal extends JInternalFrame {
	private ContaDAO dao = new ContaDAO();
	private ResgateController controller = new ResgateController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaResgateTotal frame = new TelaResgateTotal();
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
	public TelaResgateTotal() {
		setClosable(true);
		setTitle("Solicitação de resgate total");
		setBounds(100, 100, 413, 559);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados do resgate", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 193, 379, 285);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblPlano = new JLabel("Plano");
		lblPlano.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPlano.setBounds(12, 51, 54, 15);
		panel.add(lblPlano);

		JLabel lbNomeDoPlano = new JLabel("");
		lbNomeDoPlano.setBounds(107, 51, 260, 15);
		panel.add(lbNomeDoPlano);

		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSaldo.setBounds(12, 78, 54, 15);
		panel.add(lblSaldo);

		JLabel lbValorSaldoNormal = new JLabel("");
		lbValorSaldoNormal.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbValorSaldoNormal.setBounds(178, 78, 189, 15);
		panel.add(lbValorSaldoNormal);

		JLabel lblSaldoAdicional = new JLabel("Saldo adicional");
		lblSaldoAdicional.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSaldoAdicional.setBounds(12, 105, 99, 15);
		panel.add(lblSaldoAdicional);

		JLabel lbValorSaldoAdicional = new JLabel("");
		lbValorSaldoAdicional.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbValorSaldoAdicional.setBounds(178, 105, 189, 15);
		panel.add(lbValorSaldoAdicional);

		JLabel lblSaldoPortabilidade = new JLabel("Saldo portabilidade");
		lblSaldoPortabilidade.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSaldoPortabilidade.setBounds(12, 133, 131, 15);
		panel.add(lblSaldoPortabilidade);

		JLabel lbValorSaldoPortabilidade = new JLabel("");
		lbValorSaldoPortabilidade.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbValorSaldoPortabilidade.setBounds(178, 133, 189, 15);
		panel.add(lbValorSaldoPortabilidade);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTotal.setBounds(12, 160, 54, 15);
		panel.add(lblTotal);

		JLabel lbValorTotal = new JLabel("");
		lbValorTotal.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbValorTotal.setBounds(178, 160, 189, 15);
		panel.add(lbValorTotal);

		JLabel lblParticipante = new JLabel("Participante");
		lblParticipante.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblParticipante.setBounds(12, 24, 89, 15);
		panel.add(lblParticipante);

		List<Conta> contas = dao.listarContasAptas();
		JComboBox cbParticipantes = new JComboBox();
		cbParticipantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conta p1 = (Conta) cbParticipantes.getSelectedItem();
				lbNomeDoPlano.setText(p1.getParticipante().getPlano().toString());
				lbValorSaldoNormal.setText(String.valueOf(arredondar(p1.getSaldoNormal())));
				lbValorSaldoAdicional.setText(String.valueOf(arredondar(p1.getSaldoAdicional())));
				lbValorSaldoPortabilidade.setText(String.valueOf(arredondar(p1.getSaldoPortabilidade())));
				double soma = p1.getSaldoNormal() + p1.getSaldoAdicional() + p1.getSaldoPortabilidade();
				lbValorTotal.setText(String.valueOf(arredondar(soma)));
			}
		});
		cbParticipantes.setBounds(107, 19, 260, 24);
		for (Conta conta : contas) {
			cbParticipantes.addItem(conta);
		}
		panel.add(cbParticipantes);

		JLabel lblAgendarResgatePara = new JLabel("Data da solicitação");
		lblAgendarResgatePara.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAgendarResgatePara.setBounds(12, 192, 131, 15);
		panel.add(lblAgendarResgatePara);

		JDateChooser dtResgate = new JDateChooser();
		dtResgate.setBounds(150, 187, 217, 24);
		panel.add(dtResgate);

		JLabel lblRecebimento = new JLabel("Recebimento");
		lblRecebimento.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRecebimento.setBounds(12, 232, 112, 23);
		panel.add(lblRecebimento);

		JRadioButton rdbtnIntegral = new JRadioButton("Integral");
		rdbtnIntegral.setFont(new Font("Dialog", Font.PLAIN, 12));
		rdbtnIntegral.setBounds(114, 232, 81, 23);
		rdbtnIntegral.setSelected(true);
		panel.add(rdbtnIntegral);

		JRadioButton rdbtnParcelado = new JRadioButton("Parcelado");
		rdbtnParcelado.setFont(new Font("Dialog", Font.PLAIN, 12));
		rdbtnParcelado.setBounds(221, 232, 99, 23);
		panel.add(rdbtnParcelado);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rdbtnParcelado);
		grupo.add(rdbtnIntegral);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(12, 490, 117, 25);
		getContentPane().add(btnCancelar);

		JButton btnSolicitar = new JButton("Solicitar");
		btnSolicitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conta c1 = (Conta) cbParticipantes.getSelectedItem();
				LocalDate dataResgate = dtResgate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				String pagamento;

				if (rdbtnIntegral.isSelected()) {
					pagamento = "Integral";
					controller.receberConta(c1, dataResgate, pagamento);
					dispose();
				} else {
					pagamento = "Parcelado";
					controller.receberConta(c1, dataResgate, pagamento);
					dispose();
				}
			}
		});
		btnSolicitar.setBounds(274, 490, 117, 25);
		getContentPane().add(btnSolicitar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaResgateTotal.class.getResource("/icons/money-bag-with-dollar-symbol.png")));
		label.setBounds(136, 12, 137, 169);
		getContentPane().add(label);

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