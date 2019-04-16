package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DAO.ContaDAO;
import controller.ContribuicaoController;
import model.Conta;
import javax.swing.ImageIcon;

public class TelaContribuicao extends JInternalFrame {
	private JTextField txtSaldoNormal;
	private JTextField txtSaldoAdicional;
	private JTextField txtSaldoPortabilidade;
	private ContaDAO dao = new ContaDAO();
	private ContribuicaoController controller = new ContribuicaoController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaContribuicao frame = new TelaContribuicao();
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
	public TelaContribuicao() {
		setTitle("Cadastrar contribuição");
		setBounds(100, 100, 409, 434);
		getContentPane().setLayout(null);
		
		JLabel lblPlano = new JLabel("Plano");
		lblPlano.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPlano.setBounds(12, 242, 49, 15);
		getContentPane().add(lblPlano);
		
		JLabel lblParticipante = new JLabel("Participante");
		lblParticipante.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblParticipante.setBounds(12, 211, 87, 15);
		getContentPane().add(lblParticipante);
		
		JLabel lbPlanoParticipante = new JLabel("");
		lbPlanoParticipante.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbPlanoParticipante.setBounds(94, 242, 334, 15);
		getContentPane().add(lbPlanoParticipante);
		
		List<Conta> contas = dao.listarContasAptas();
		JComboBox cbParticipantes = new JComboBox();
		cbParticipantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conta p1 = (Conta) cbParticipantes.getSelectedItem();
				lbPlanoParticipante.setText(p1.getParticipante().getPlano().getNome());
			}
		});
		cbParticipantes.setBounds(94, 206, 271, 24);
		for (Conta conta: contas) {
			cbParticipantes.addItem(conta);
		}
		getContentPane().add(cbParticipantes);
		
		JLabel lblSaldoNormal = new JLabel("Saldo normal");
		lblSaldoNormal.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSaldoNormal.setBounds(12, 271, 108, 15);
		getContentPane().add(lblSaldoNormal);
		
		txtSaldoNormal = new JTextField();
		txtSaldoNormal.setBounds(140, 269, 225, 19);
		getContentPane().add(txtSaldoNormal);
		txtSaldoNormal.setColumns(10);
		
		JButton btnContNormal = new JButton("Contribuir");
		btnContNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conta c1 = new Conta();
				Conta aux = (Conta) cbParticipantes.getSelectedItem();
				
				c1.setParticipante(aux.getParticipante());
				c1.setId(aux.getId());
				
				if (txtSaldoNormal.getText().isEmpty()) {
					c1.setSaldoNormal(0);
				} else {
					c1.setSaldoNormal(Double.parseDouble(txtSaldoNormal.getText()));
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
				
				controller.receberContribuicao(c1);
				dispose();
			}
		});
		btnContNormal.setBounds(248, 364, 117, 25);
		getContentPane().add(btnContNormal);
		
		JLabel lblSaldoAdicional = new JLabel("Saldo adicional");
		lblSaldoAdicional.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSaldoAdicional.setBounds(12, 303, 108, 15);
		getContentPane().add(lblSaldoAdicional);
		
		txtSaldoAdicional = new JTextField();
		txtSaldoAdicional.setColumns(10);
		txtSaldoAdicional.setBounds(140, 301, 225, 19);
		getContentPane().add(txtSaldoAdicional);
		
		JLabel lblSaldoPortabilidade = new JLabel("Saldo portabilidade");
		lblSaldoPortabilidade.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSaldoPortabilidade.setBounds(12, 335, 122, 15);
		getContentPane().add(lblSaldoPortabilidade);
		
		txtSaldoPortabilidade = new JTextField();
		txtSaldoPortabilidade.setColumns(10);
		txtSaldoPortabilidade.setBounds(140, 333, 225, 19);
		getContentPane().add(txtSaldoPortabilidade);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFechar.setBounds(12, 364, 117, 25);
		getContentPane().add(btnFechar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaContribuicao.class.getResource("/icons/money (1).png")));
		label.setBounds(122, 12, 156, 182);
		getContentPane().add(label);

	}
	
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}
