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

import DAO.ContaDAO;
import model.Conta;
import model.Plano;

public class TelaListarContas extends JInternalFrame {
	private JTable table;
	private ContaDAO dao = new ContaDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListarContas frame = new TelaListarContas();
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
	public TelaListarContas() {
		setTitle("Lista de contas cadastradas");
		setClosable(true);
		setBounds(100, 100, 907, 479);
		
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
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(404)
							.addComponent(btnFechar)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnFechar)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Participante", "Plano", "Status","Saldo normal", "Saldo adicional", "Saldo Portabilidade", "Último resgate"
			}
		));
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

	}
	
	public void carregarTabela() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		List<Conta> listaDeContas = dao.listarContas();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (Conta c : listaDeContas) {
			
			if(c.getUltimoResgate() == null) {
				String text = " ";
						model.addRow(new Object[] { c.getParticipante().getNome(), c.getParticipante().getPlano().getNome(), 
								c.getParticipante().getStatus(),c.getSaldoNormal(), c.getSaldoAdicional(), c.getSaldoPortabilidade(), text });
			} else {
			
			model.addRow(new Object[] { c.getParticipante().getNome(), c.getParticipante().getPlano().getNome(),c.getParticipante().getStatus(), 
					c.getSaldoNormal(), c.getSaldoAdicional(), c.getSaldoPortabilidade(), c.getUltimoResgate().format(formatter) });
			}
		}
	}
	
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}
