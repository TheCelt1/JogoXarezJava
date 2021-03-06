package xadrez.pecasXadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

	private PartidaXadrez partidaXadrez;

	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColuna()];

		Posicao pos = new Posicao(0, 0);

		if (getCor() == Cor.BRANCO) {

			// adiante 1 casa
			pos.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().existePosicao(pos) && !getTabuleiro().existePeca(pos)) {
				matriz[pos.getLinha()][pos.getColuna()] = true;
			}

			// adiante 2 casas
			pos.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao pos2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());

			boolean acessoPosicao1 = getTabuleiro().existePosicao(pos) && !getTabuleiro().existePeca(pos);
			boolean acessoPosicao2 = getTabuleiro().existePosicao(pos2) && !getTabuleiro().existePeca(pos2);

			if (acessoPosicao1 && acessoPosicao2 && getContagemMovimentos() == 0) {
				matriz[pos.getLinha()][pos.getColuna()] = true;
			}

			// captura pe�a diagonal esquerda
			pos.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().existePosicao(pos) && existePecaOponente(pos)) {
				matriz[pos.getLinha()][pos.getColuna()] = true;
			}

			// captura pe�a diagonal direita
			pos.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().existePosicao(pos) && existePecaOponente(pos)) {
				matriz[pos.getLinha()][pos.getColuna()] = true;
			}

			// jogada en passant
			if(posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if(getTabuleiro().existePosicao(esquerda) && existePecaOponente(esquerda) && getTabuleiro().posicaoPeca(esquerda) == partidaXadrez.getenPassantVulneravel()) {
					matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if(getTabuleiro().existePosicao(direita) && existePecaOponente(direita) && getTabuleiro().posicaoPeca(direita) == partidaXadrez.getenPassantVulneravel()) {
					matriz[direita.getLinha() - 1][direita.getColuna()] = true;
				}
		}

	}else {

		// adiante 1 casa
		pos.setValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().existePosicao(pos) && !getTabuleiro().existePeca(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// adiante 2 casas
		pos.setValores(posicao.getLinha() + 2, posicao.getColuna());
		Posicao pos2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());

		boolean acessoPosicao1 = getTabuleiro().existePosicao(pos) && !getTabuleiro().existePeca(pos);
		boolean acessoPosicao2 = getTabuleiro().existePosicao(pos2) && !getTabuleiro().existePeca(pos2);

		if (acessoPosicao1 && acessoPosicao2 && getContagemMovimentos() == 0) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// captura pe�a diagonal esquerda
		pos.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().existePosicao(pos) && existePecaOponente(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// captura pe�a diagonal direita
		pos.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().existePosicao(pos) && existePecaOponente(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}
		
		// jogada en passant
		if(posicao.getLinha() == 4) {
			Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
			if(getTabuleiro().existePosicao(esquerda) && existePecaOponente(esquerda) && getTabuleiro().posicaoPeca(esquerda) == partidaXadrez.getenPassantVulneravel()) {
				matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
			}
			Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
			if(getTabuleiro().existePosicao(direita) && existePecaOponente(direita) && getTabuleiro().posicaoPeca(direita) == partidaXadrez.getenPassantVulneravel()) {
				matriz[direita.getLinha() + 1][direita.getColuna()] = true;
			}
		}
	}

	return matriz;
	}

	@Override
	public String toString() {
		return "P";
	}

}
