package service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

import DAO.ContaDAO;
import DAO.ParticipanteDAO;
import model.Conta;

public class ResgateService {
	private ContaDAO cdao = new ContaDAO();
	private ParticipanteDAO pdao = new ParticipanteDAO();

	public ResgateService() {
	}

	public void validarResgateTotal(Conta c1, LocalDate dataResgate, String pagamento) {
		if (verificaCarencia(c1, dataResgate) && pagamento.equals("Integral") &&(c1.getParticipante().getStatus().equals("ATIVO")
				|| c1.getParticipante().getStatus().equals("SUSPENSO")
				|| c1.getParticipante().getStatus().equals("VINCULADO"))) {

			double saldoTotal = c1.getSaldoAdicional() + c1.getSaldoNormal() + c1.getSaldoPortabilidade();

			LocalDate dataPagamento = dataResgate.plusDays(30);

			// Zerar os saldos
			c1.setSaldoNormal(0);
			c1.setSaldoAdicional(0);
			c1.setSaldoPortabilidade(0);
			cdao.atualizarSaldo(c1);
			cdao.atualizaUltimoResgate(dataPagamento, c1);
			pdao.cancelarParticipante(c1.getParticipante().getId());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			JOptionPane.showMessageDialog(null,
					"Resgate realizado com sucesso, o pagamento será realizado no dia: "
							+ dataPagamento.format(formatter) + "\n" + "O valor a ser recebido será de R$ "
							+ arredondar(saldoTotal));

		} 
		else if (verificaCarencia(c1, dataResgate) && pagamento.equals("Parcelado") &&(c1.getParticipante().getStatus().equals("ATIVO")
				|| c1.getParticipante().getStatus().equals("SUSPENSO")
				|| c1.getParticipante().getStatus().equals("VINCULADO"))) {
			
			double saldoTotal = c1.getSaldoAdicional() + c1.getSaldoNormal() + c1.getSaldoPortabilidade();
			double valorParcelas = saldoTotal/60;

			LocalDate dataPagamento = dataResgate.plusDays(30);

			// Zerar os saldos
			c1.setSaldoNormal(0);
			c1.setSaldoAdicional(0);
			c1.setSaldoPortabilidade(0);
			cdao.atualizarSaldo(c1);
			cdao.atualizaUltimoResgate(dataPagamento, c1);
			pdao.cancelarParticipante(c1.getParticipante().getId());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			JOptionPane.showMessageDialog(null,
					"Resgate realizado com sucesso." + "\n" + "O pagamento será feito em 60 parcelas mensais e a primeira parcela será realizado no dia: "
							+ dataPagamento.format(formatter) +"\n" +"O valor a ser recebido por parcela será de R$ "
							+ arredondar(valorParcelas)  +"\n" +"O valor do montante total é de R$ " + arredondar(saldoTotal));			
		} else {
			JOptionPane.showMessageDialog(null,
					"Não será possível realizar o resgate total," + "\n" + "pois não cumpre os requisitos de 36 meses de carência e/ou Status do participante não compatível");
		}
	}

	public void validarResgateParcial(Conta c1, LocalDate dataSolicitacao, double valor, String tipo) {
		if (valor > 0) {

			System.out.println(valor <= (c1.getSaldoNormal() * 0.2));
			System.out.println(tipo);

			if (tipo.equals("Normal") && valor <= (c1.getSaldoNormal() * 0.2) && verificaUltimoResgate(c1, dataSolicitacao)) {
				double novoSaldo = c1.getSaldoNormal() - valor;
				cdao.atualizaSaldoNormal(c1, novoSaldo);
				LocalDate dataPagamento = dataSolicitacao.plusDays(30);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				cdao.atualizaUltimoResgate(dataPagamento, c1);

				JOptionPane.showMessageDialog(null,
						"Resgate realizado com sucesso, o pagamento será realizado no dia: "
								+ dataPagamento.format(formatter) + "\n" + "O valor a ser recebido será de R$ "
								+ arredondar(valor));
			}

			else if (tipo.equals("Adicional") && c1.getSaldoAdicional() >= valor) {
				double novoSaldo = c1.getSaldoAdicional() - valor;
				cdao.atualizaSaldoAdicional(c1, novoSaldo);
				LocalDate dataPagamento = dataSolicitacao.plusDays(30);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				JOptionPane.showMessageDialog(null,
						"Resgate realizado com sucesso, o pagamento será realizado no dia: "
								+ dataPagamento.format(formatter) + " e o valor a ser recebido será de R$ "
								+ arredondar(valor));
			}

			else if (tipo.equals("Portabilidade") && c1.getSaldoPortabilidade() >= valor) {
				double novoSaldo = c1.getSaldoPortabilidade() - valor;
				cdao.atualizaSaldoPortabilidade(c1, novoSaldo);
				LocalDate dataPagamento = dataSolicitacao.plusDays(30);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				JOptionPane.showMessageDialog(null,
						"Resgate realizado com sucesso, o pagamento será realizado no dia: "
								+ dataPagamento.format(formatter) + " e o valor a ser recebido será de R$ "
								+ arredondar(valor));
			}

			else if ((c1.getSaldoNormal() < valor && tipo.equals("Normal")) || (c1.getSaldoAdicional() < valor && tipo.equals("Adicional"))
					|| (c1.getSaldoPortabilidade() < valor) && tipo.equals("Portabilidade")) {
				JOptionPane.showMessageDialog(null,
						"Não foi possível realizar o resgate parcial pois o valor desejado é maior do que o saldo atual.");
			}

			else if (valor > c1.getSaldoNormal() * 0.2 && tipo.equals("Normal")) {
				JOptionPane.showMessageDialog(null,
						"Não foi possível realizar o resgate parcial pois o valor desejado é maior "
						+ "que o limite permitido para resgate em contas normais.");
			}
		}

		else {
			JOptionPane.showMessageDialog(null,
					"Não foi possível realizar o resgate o valor desejado é menor que zero.");
		}

	}

	public boolean verificaCarencia(Conta c1, LocalDate data) {
		long qtMeses = ChronoUnit.MONTHS.between(c1.getParticipante().getDtAssociacao(), data);
		System.out.println(qtMeses);
		if (qtMeses >= 36) {
			return true;
		}
		JOptionPane.showMessageDialog(null, "O resgate não poderá ser realizado pois a solicitação feita antes do término da carência.");
		return false;
	}

	private static String arredondar(Double value) {
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		decimalFormat.setRoundingMode(RoundingMode.DOWN);
		return decimalFormat.format(value);
	}

	public boolean verificaUltimoResgate(Conta c1, LocalDate data) {
		if (c1.getUltimoResgate() == null) {
			return verificaCarencia(c1, data);
		} else {
			long qtMeses = ChronoUnit.MONTHS.between(c1.getUltimoResgate(), data);
			if (qtMeses >= 24) {
				return true;
			}
			JOptionPane.showMessageDialog(null, "O resgate não poderá ser realizado pois a solicitação feita antes do prazo de 24 meses do último resgate.");
			return false;
		}
	}
}