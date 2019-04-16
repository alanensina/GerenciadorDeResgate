package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.ZoneId;

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

import controller.CadPlanoController;
import model.Endereco;
import model.Plano;
import model.UF;
import javax.swing.ImageIcon;

public class TelaCadPlano extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtCNPJ;
	private JTextField txtLogradouro;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCEP;
	private JTextField txtCidade;
	private CadPlanoController controller = new CadPlanoController();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadPlano frame = new TelaCadPlano();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadPlano() {
		initialize();
	}

	@SuppressWarnings("unchecked")
	public void initialize() {
		setClosable(true);
		setTitle("Cadastro de planos");
		setBounds(100, 100, 450, 588);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados principais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 154, 416, 121);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNome.setBounds(12, 28, 41, 15);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(129, 26, 275, 19);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCnpj.setBounds(12, 55, 41, 15);
		panel.add(lblCnpj);

		javax.swing.text.MaskFormatter maskCNPJ = null;
		try {
			maskCNPJ = new javax.swing.text.MaskFormatter("##.###.###/0001-##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		JFormattedTextField txtCNPJ = new JFormattedTextField(maskCNPJ);
		txtCNPJ.setBounds(129, 53, 275, 19);
		panel.add(txtCNPJ);
		txtCNPJ.setColumns(10);

		JLabel lblDataDeFundao = new JLabel("Data de fundação");
		lblDataDeFundao.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDataDeFundao.setBounds(12, 86, 126, 15);
		panel.add(lblDataDeFundao);

		JDateChooser dtFundacao = new JDateChooser();
		dtFundacao.setBounds(129, 82, 275, 19);
		panel.add(dtFundacao);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 280, 416, 227);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblLogradouro.setBounds(12, 34, 95, 15);
		panel_1.add(lblLogradouro);

		txtLogradouro = new JTextField();
		txtLogradouro.setBounds(129, 32, 275, 19);
		panel_1.add(txtLogradouro);
		txtLogradouro.setColumns(10);

		JLabel lblNmero = new JLabel("Número");
		lblNmero.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNmero.setBounds(12, 61, 70, 15);
		panel_1.add(lblNmero);

		txtNumero = new JTextField();
		txtNumero.setBounds(129, 59, 275, 19);
		panel_1.add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblComplemento.setBounds(12, 85, 95, 15);
		panel_1.add(lblComplemento);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(129, 83, 275, 19);
		panel_1.add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBairro.setBounds(12, 112, 70, 15);
		panel_1.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setBounds(129, 110, 275, 19);
		panel_1.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCep.setBounds(12, 139, 70, 15);
		panel_1.add(lblCep);

		javax.swing.text.MaskFormatter maskCEP = null;
		try {
			maskCEP = new javax.swing.text.MaskFormatter("##.###-###");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		JFormattedTextField txtCEP = new JFormattedTextField(maskCEP);

		txtCEP.setBounds(129, 137, 275, 19);
		panel_1.add(txtCEP);
		txtCEP.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCidade.setBounds(12, 163, 70, 15);
		panel_1.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(129, 161, 275, 19);
		panel_1.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblUf = new JLabel("UF");
		lblUf.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblUf.setBounds(12, 190, 70, 15);
		panel_1.add(lblUf);

		JComboBox cbUf = new JComboBox();
		cbUf.setBounds(129, 185, 275, 24);
		UF[] ufs = UF.values();
		@SuppressWarnings("rawtypes")
		ComboBoxModel cbmodel = new DefaultComboBoxModel(ufs);
		cbUf.setModel(cbmodel);
		panel_1.add(cbUf);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Plano p1 = new Plano();
				p1.setNome(txtNome.getText());
				p1.setCnpj(txtCNPJ.getText());
				p1.setDtFundacao(dtFundacao.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

				Endereco e1 = new Endereco();
				e1.setLogradouro(txtLogradouro.getText());
				e1.setBairro(txtBairro.getText());
				e1.setCep(txtCEP.getText());
				e1.setComplemento(txtComplemento.getText());
				e1.setCidade(txtCidade.getText());
				e1.setUf(cbUf.getSelectedItem().toString());
				e1.setNumero(txtNumero.getText());
				p1.setEndereco(e1);
				controller.enviarProService(p1);
				dispose();
			}
		});
		btnCadastrar.setBounds(311, 519, 117, 25);
		getContentPane().add(btnCadastrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(12, 519, 117, 25);
		getContentPane().add(btnCancelar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtCNPJ.setText("");
				txtLogradouro.setText("");
				txtNumero.setText("");
				txtComplemento.setText("");
				txtCEP.setText("");
				txtBairro.setText("");
				txtCidade.setText("");
				dtFundacao.setDate(null);
			}
		});
		btnLimpar.setBounds(163, 519, 117, 25);
		getContentPane().add(btnLimpar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaCadPlano.class.getResource("/icons/solution.png")));
		label.setBounds(150, 12, 148, 130);
		getContentPane().add(label);
	}

	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}