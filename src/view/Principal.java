package view;

import java.util.concurrent.Semaphore;

import controller.ThreadTransacao;

public class Principal {

	public static void main(String[] args) {
		int permissao = 1;
		Semaphore semaforoS = new Semaphore(permissao);
		Semaphore semaforoD = new Semaphore(permissao);
		
		for (int i = 1;i<=20;i++) {
			double saldo = Math.random()*1000;
			double valor = Math.random()*500;
			Thread transacao = new ThreadTransacao(i, saldo, valor, semaforoS, semaforoD);
			transacao.start();
		}

	}

}
