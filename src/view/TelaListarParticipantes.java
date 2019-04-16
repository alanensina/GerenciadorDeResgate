package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import DAO.ParticipanteDAO;
import model.Participante;
import model.Plano;

public class TelaListarParticipantes extends JInternalFrame {
	private JTable table;
	private ParticipanteDAO dao = new ParticipanteDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarParticipantes frame = new TelaListarParticipantes();
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
	public TelaListarParticipantes() {
		setTitle("Lista de participantes cadastrados");
		setClosable(true);
		setBounds(100, 100, 913, 474);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(403)
							.addComponent(btnFechar)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnFechar)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "CPF", "Plano", "Status","Data de associa\u00E7\u00E3o"
			}
		));
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

	}
	
	public void carregarTabela() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		List<Participante> listaDeparticipantes = dao.listarParticipantes();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		for (Participante p : listaDeparticipantes) {
			model.addRow(new Object[] { p.getNome(), p.getCpf(), p.getPlano().getNome(), p.getStatus() ,p.getDtAssociacao().format(formatter) });
		}
	}
	
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}