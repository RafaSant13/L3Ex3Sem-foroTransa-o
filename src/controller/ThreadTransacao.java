package controller;

import java.util.concurrent.Semaphore;

public class ThreadTransacao extends Thread {
	
	public int codigo;
	public double saldo;
	public double valor;
	public Semaphore semaforoS;
	public Semaphore semaforoD;

	public ThreadTransacao(int codigo, double saldo, double valor, Semaphore semaforoS, Semaphore semaforoD) {
		this.codigo = codigo;
		this.saldo = saldo;
		this.valor = valor;
		this.semaforoS = semaforoS;
		this.semaforoD = semaforoD;
	}
	
	public void run() {
		if(caraOuCoroa()) {
			try {
				semaforoD.acquire();
				deposito();
				sleep(1000);
			} catch (Exception e) {
				
			} finally {
				semaforoD.release();
			}
		}
		else {
			try {
				semaforoS.acquire();
				saque();
				sleep(1000);
			} catch (Exception e) {
				
			} finally {
				semaforoS.release();
			}
		}
	}
	
	private void saque() {
		double saldoLiquido = saldo - valor;
		System.out.printf("Saque realizado => Código da Conta: "+codigo+", Saldo incial: %.2fR$, Valor sacado: %.2fR$, Saldo Final: %.2fR$%n", saldo, valor, saldoLiquido);
		
	}

	private void deposito() {
		double saldoLiquido = saldo + valor;
		System.out.printf("Depósito realizado => Código da Conta: "+codigo+", Saldo incial: %.2fR$, Valor depositado: %.2fR$, Saldo Final: %.2fR$%n", saldo, valor, saldoLiquido);
	}

	private boolean caraOuCoroa () {
		int operacao = (int)(Math.random()*10);
		if (operacao<5) {
			return true;
		}
		else {
			return false;
		}
	}

}
