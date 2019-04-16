package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainScreen extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;

	private JFrame frame;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainScreen() {
		frame = new JFrame();
		//frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setTitle("Gerenciador de resgate");
		frame.setResizable(false);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Open the frame maximized
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		JLabel labelBackground = new JLabel();
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/background.png"));
		Image image = icon.getImage();
		labelBackground.setIcon(icon);
		desktopPane = new JDesktopPane() {

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		desktopPane.add(labelBackground, BorderLayout.CENTER);
		desktopPane.setBounds(131, 57, 1, 1);
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 128, 21);
		frame.setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		JMenuItem cadPlano = new JMenuItem("Plano");
		cadPlano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadPlano screen = new TelaCadPlano();
				desktopPane.add(screen);
				screen.setPosicao();
				screen.setVisible(true);

			}
		});
		mnCadastro.add(cadPlano);

		JMenuItem cadParticipante = new JMenuItem("Participante");
		cadParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadParticipante screen = new TelaCadParticipante();
				desktopPane.add(screen);
				screen.setPosicao();
				screen.setVisible(true);				
			}
		});
		mnCadastro.add(cadParticipante);

		JMenuItem cadConta = new JMenuItem("Conta");
		cadConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadConta screen = new TelaCadConta();
				desktopPane.add(screen);
				screen.setPosicao();
				screen.setVisible(true);
			}
		});
		mnCadastro.add(cadConta);

		JMenu mnListar = new JMenu("Listar");
		menuBar.add(mnListar);

		JMenuItem listarPlanos = new JMenuItem("Planos");
		listarPlanos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListarPlanos screen = new TelaListarPlanos();
				screen.carregarTabela();
				desktopPane.add(screen);
				screen.setPosicao();
				screen.setVisible(true);
				
			}
		});
		mnListar.add(listarPlanos);

		JMenuItem listarParticipantes = new JMenuItem("Participantes");
		listarParticipantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListarParticipantes screen = new TelaListarParticipantes();
				screen.carregarTabela();
				desktopPane.add(screen);
				screen.setPosicao();
				screen.setVisible(true);
				
				
			}
		});
		mnListar.add(listarParticipantes);

		JMenuItem listarContas = new JMenuItem("Contas");
		listarContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListarContas screen = new TelaListarContas();
				screen.carregarTabela();
				desktopPane.add(screen);
				screen.setPosicao();
				screen.setVisible(true);
				
			}
		});
		mnListar.add(listarContas);

		JMenu mnContribuio = new JMenu("Contribuição");
		menuBar.add(mnContribuio);

		JMenuItem mntmContribuir = new JMenuItem("Contribuir");
		mntmContribuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaContribuicao screen = new TelaContribuicao();
				desktopPane.add(screen);
				screen.setPosicao();
				screen.setVisible(true);
			}
		});
		mnContribuio.add(mntmContribuir);

		JMenu mnResgate = new JMenu("Resgate");
		menuBar.add(mnResgate);

		JMenuItem resgateParcial = new JMenuItem("Parcial");
		resgateParcial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaResgateParcial screen = new TelaResgateParcial();
				desktopPane.add(screen);
				screen.setPosicao();
				screen.setVisible(true);
				
			}
		});
		mnResgate.add(resgateParcial);

		JMenuItem resgateTotal = new JMenuItem("Total");
		resgateTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaResgateTotal screen = new TelaResgateTotal();
				desktopPane.add(screen);
				screen.setPosicao();
				screen.setVisible(true);
			}
		});
		mnResgate.add(resgateTotal);
		frame.setVisible(true);
		
	}
}