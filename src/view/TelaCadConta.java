package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import DAO.ParticipanteDAO;
import controller.CadContaController;
import model.Conta;
import model.Participante;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class TelaCadConta extends JInternalFrame {
	private JTextField txtSaldoInicial;
	private JTextField txtSaldoAdicional;
	private JTextField txtSaldoPortabilidade;
	private ParticipanteDAO dao = new ParticipanteDAO();
	private CadContaController controller = new CadContaController();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadConta frame = new TelaCadConta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadConta() {
		setClosable(true);
		setTitle("Cadastro de contas");
		setBounds(100, 100, 450, 457);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Detalhes da conta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 178, 416, 198);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblParticipante = new JLabel("Participante");
		lblParticipante.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblParticipante.setBounds(12, 35, 97, 15);
		panel.add(lblParticipante);

		JLabel lbPlano = new JLabel("");
		lbPlano.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbPlano.setBounds(150, 66, 139, 15);
		panel.add(lbPlano);

		List<Participante> participantes = dao.listarParticipantesAptos();
		JComboBox cbParticipante = new JComboBox();
		cbParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Participante p1 = (Participante) cbParticipante.getSelectedItem();
				lbPlano.setText(p1.getPlano().getNome());
			}
		});
		cbParticipante.setBounds(121, 30, 283, 24);
		for (Participante participante : participantes) {
			cbParticipante.addItem(participante);
		}
		panel.add(cbParticipante);

		JLabel lblPlano = new JLabel("Plano");
		lblPlano.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPlano.setBounds(12, 62, 49, 24);
		panel.add(lblPlano);

		JLabel lblSaldoInicial = new JLabel("Saldo inicial");
		lblSaldoInicial.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSaldoInicial.setBounds(12, 98, 88, 15);
		panel.add(lblSaldoInicial);

		JFormattedTextField txtSaldoInicial = new JFormattedTextField();
		txtSaldoInicial.setBounds(150, 94, 254, 19);
		panel.add(txtSaldoInicial);
		txtSaldoInicial.setColumns(10);

		JLabel lblSaldoAdicional = new JLabel("Saldo adicional");
		lblSaldoAdicional.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSaldoAdicional.setBounds(12, 129, 119, 15);
		panel.add(lblSaldoAdicional);

		JFormattedTextField txtSaldoAdicional = new JFormattedTextField();
		txtSaldoAdicional.setColumns(10);
		txtSaldoAdicional.setBounds(150, 125, 254, 19);
		panel.add(txtSaldoAdicional);

		JLabel lblSaldoPortabilidade = new JLabel("Saldo portabilidade");
		lblSaldoPortabilidade.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSaldoPortabilidade.setBounds(12, 160, 132, 15);
		panel.add(lblSaldoPortabilidade);

		JFormattedTextField txtSaldoPortabilidade = new JFormattedTextField();
		txtSaldoPortabilidade.setColumns(10);
		txtSaldoPortabilidade.setBounds(150, 156, 254, 19);
		panel.add(txtSaldoPortabilidade);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conta c1 = new Conta();
				c1.setParticipante((Participante) cbParticipante.getSelectedItem());

				if (txtSaldoInicial.getText().isEmpty()) {
					c1.setSaldoNormal(0);
				} else {
					c1.setSaldoNormal(Double.parseDouble(txtSaldoInicial.getText()));
				}

				if (txtSaldoAdicional.getText().isEmpty()) {
					c1.setSaldoAdicional(0);
				} else {
					c1.setSaldoAdicional(Double.parseDouble(txtSaldoAdicional.getText()));
				}

				if (txtSaldoPortabilidade.getText().isEmpty()) {
					c1.setSaldoPortabilidade(0);
				} else {
					c1.setSaldoPortabilidade(Double.parseDouble(txtSaldoPortabilidade.getText()));
				}

				controller.receberConta(c1);

				dispose();
			}
		});
		btnCadastrar.setBounds(313, 388, 117, 25);
		getContentPane().add(btnCadastrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(25, 388, 117, 25);
		getContentPane().add(btnCancelar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSaldoAdicional.setText("");
				txtSaldoInicial.setText("");
				txtSaldoPortabilidade.setText("");

			}
		});
		btnLimpar.setBounds(170, 388, 117, 25);
		getContentPane().add(btnLimpar);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(TelaCadConta.class.getResource("/icons/accounting.png")));
		logo.setBounds(164, 12, 165, 143);
		getContentPane().add(logo);
	}
	
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}
