package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import DAO.PlanoDAO;
import controller.CadParticipanteController;
import model.Endereco;
import model.Participante;
import model.Plano;
import model.StatusParticipante;
import model.UF;
import javax.swing.ImageIcon;

public class TelaCadParticipante extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtCelular;
	private JTextField txtEmail;
	private JTextField txtLogradouro;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCEP;
	private JTextField txtCidade;
	private PlanoDAO pdao = new PlanoDAO();
	private CadParticipanteController controller = new CadParticipanteController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadParticipante frame = new TelaCadParticipante();
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
	public TelaCadParticipante() {
		setTitle("Cadastro de participantes");
		setBounds(100, 100, 776, 501);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados pessoais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 148, 324, 172);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNome.setBounds(12, 33, 49, 15);
		panel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(79, 31, 233, 19);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCpf.setBounds(12, 60, 49, 15);
		panel.add(lblCpf);
		
		javax.swing.text.MaskFormatter maskCPF = null;
		try {
			maskCPF = new javax.swing.text.MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		JFormattedTextField txtCPF = new JFormattedTextField(maskCPF);
		txtCPF.setBounds(79, 58, 233, 19);
		panel.add(txtCPF);
		txtCPF.setColumns(10);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCelular.setBounds(12, 87, 70, 15);
		panel.add(lblCelular);
		
		javax.swing.text.MaskFormatter maskPhone = null;
		try {
			maskPhone = new javax.swing.text.MaskFormatter("(##) #####-####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		JFormattedTextField txtCelular = new JFormattedTextField(maskPhone);
		
		txtCelular.setBounds(79, 85, 233, 19);
		panel.add(txtCelular);
		txtCelular.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblEmail.setBounds(12, 113, 70, 15);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(79, 111, 233, 19);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDataDeNascimento.setBounds(12, 140, 136, 15);
		panel.add(lblDataDeNascimento);
		
		JDateChooser dtNascimento = new JDateChooser();
		dtNascimento.setBounds(143, 136, 169, 19);
		panel.add(dtNascimento);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(341, 148, 416, 227);
		getContentPane().add(panel_1);
		
		JLabel label = new JLabel("Logradouro");
		label.setFont(new Font("Dialog", Font.PLAIN, 12));
		label.setBounds(12, 34, 95, 15);
		panel_1.add(label);
		
		txtLogradouro = new JTextField();
		txtLogradouro.setColumns(10);
		txtLogradouro.setBounds(129, 32, 275, 19);
		panel_1.add(txtLogradouro);
		
		JLabel label_1 = new JLabel("Número");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_1.setBounds(12, 61, 70, 15);
		panel_1.add(label_1);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(129, 59, 275, 19);
		panel_1.add(txtNumero);
		
		JLabel label_2 = new JLabel("Complemento");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_2.setBounds(12, 85, 95, 15);
		panel_1.add(label_2);
		
		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(129, 83, 275, 19);
		panel_1.add(txtComplemento);
		
		JLabel label_3 = new JLabel("Bairro");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_3.setBounds(12, 112, 70, 15);
		panel_1.add(label_3);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(129, 110, 275, 19);
		panel_1.add(txtBairro);
		
		JLabel label_4 = new JLabel("CEP");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_4.setBounds(12, 139, 70, 15);
		panel_1.add(label_4);
		
		javax.swing.text.MaskFormatter maskCEP = null;
		try {
			maskCEP = new javax.swing.text.MaskFormatter("##.###-###");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		JFormattedTextField txtCEP = new JFormattedTextField(maskCEP);
		txtCEP.setColumns(10);
		txtCEP.setBounds(129, 137, 275, 19);
		panel_1.add(txtCEP);
		
		JLabel label_5 = new JLabel("Cidade");
		label_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_5.setBounds(12, 163, 70, 15);
		panel_1.add(label_5);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(129, 161, 275, 19);
		panel_1.add(txtCidade);
		
		JLabel label_6 = new JLabel("UF");
		label_6.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_6.setBounds(12, 190, 70, 15);
		panel_1.add(label_6);
		
		JComboBox cbUf = new JComboBox();
		cbUf.setBounds(129, 185, 275, 24);
		UF[] ufs = UF.values();
        ComboBoxModel cbmodel = new DefaultComboBoxModel(ufs);
        cbUf.setModel(cbmodel);
		panel_1.add(cbUf);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Plano", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(12, 328, 324, 129);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNome_1.setBounds(12, 31, 51, 15);
		panel_2.add(lblNome_1);
		
		List<Plano> planos = pdao.listarPlanos();
		JComboBox cbPlanos = new JComboBox();
		cbPlanos.setBounds(79, 26, 233, 24);
		
		for (Plano plano : planos) {
			cbPlanos.addItem(plano);
		}
		panel_2.add(cbPlanos);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblStatus.setBounds(12, 58, 61, 15);
		panel_2.add(lblStatus);
		
		JComboBox cbStatus = new JComboBox();
		cbStatus.setBounds(79, 53, 233, 24);
		StatusParticipante[] sts = StatusParticipante.values();
		ComboBoxModel cbmodelStatus = new DefaultComboBoxModel(sts);
		cbStatus.setModel(cbmodelStatus);
		panel_2.add(cbStatus);
		
		JLabel lblDataDeAssociao = new JLabel("Data de Associação");
		lblDataDeAssociao.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDataDeAssociao.setBounds(12, 87, 138, 15);
		panel_2.add(lblDataDeAssociao);
		
		JDateChooser dtAssociacao = new JDateChooser();
		dtAssociacao.setBounds(147, 83, 165, 19);
		panel_2.add(dtAssociacao);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Endereco e1 = new Endereco();
				e1.setLogradouro(txtLogradouro.getText());
				e1.setBairro(txtBairro.getText());
				e1.setCep(txtCEP.getText());
				e1.setComplemento(txtComplemento.getText());
				e1.setCidade(txtCidade.getText());
				e1.setUf(cbUf.getSelectedItem().toString());
				e1.setNumero(txtNumero.getText());
				
				Participante p1 = new Participante();
				p1.setNome(txtNome.getText());
				p1.setCelular(txtCelular.getText());
				p1.setCpf(txtCPF.getText());
				p1.setEmail(txtEmail.getText());
				p1.setStatus(cbStatus.getSelectedItem().toString());
				p1.setPlano((Plano)cbPlanos.getSelectedItem());
				p1.setEndereco(e1);
				p1.setDtNascimento(dtNascimento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				p1.setDtAssociacao(dtAssociacao.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				
				controller.receberParticipante(p1);
				dispose();
			}
		});
		btnCadastrar.setBounds(640, 401, 117, 39);
		getContentPane().add(btnCadastrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(344, 401, 117, 39);
		getContentPane().add(btnCancelar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtCelular.setText("");
				txtCEP.setText("");
				txtCPF.setText("");
				txtBairro.setText("");
				txtCidade.setText("");
				txtEmail.setText("");
				txtLogradouro.setText("");
				txtNumero.setText("");
				txtCidade.setText("");
				txtComplemento.setText("");
				dtAssociacao.setDate(null);
				dtNascimento.setDate(null);
			}
		});
		btnLimpar.setBounds(499, 401, 117, 39);
		getContentPane().add(btnLimpar);
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon(TelaCadParticipante.class.getResource("/icons/reward.png")));
		label_7.setBounds(324, 12, 137, 134);
		getContentPane().add(label_7);
	}
	
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
	
}