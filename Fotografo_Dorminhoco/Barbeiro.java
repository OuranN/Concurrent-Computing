/* ***************************************************************
 * Autor............: Cristhian Kauan Moreno Silveira
 * Matricula.........: 202210185
 * Inicio...........: 10/11/2023
 * Ultima alteração.: 14/11/2023
 * Nome.............: Barbeiro
 * Funcao...........: Classe modelo que manipula as acoes do barbeiro.
 *************************************************************** */

import javafx.scene.image.ImageView;

class Barbeiro extends Thread {
  // Declaracao de variaveis e objetos
  public ImageView dormindo;
  public ImageView acordado;
  public static double velocidade = 1;
  public static boolean pause = true;

  // Construtor
  public Barbeiro(int id, ImageView dormindo, ImageView acordado) {
    this.dormindo = dormindo;
    this.acordado = acordado;
  }

  public Barbeiro() {}

  @Override
  public void run() {
    while (true) {
      try {
        // Verifica se ha clientes aguardando
        if (controleTela.waiting == 0) {
          sleep(); // Se nao houver, o barbeiro dorme
        }

        controleTela.customers.acquire(); // Barbeiro aguarda se o numero de clientes for 0
        wakeUp(); // Barbeiro acorda e se prepara para cortar o cabelo

        controleTela.mutex.acquire(); // Adquire acesso a variavel de controle "esperando"
        controleTela.waiting = controleTela.waiting - 1; // Decrementa o contador de clientes esperando
        controleTela.barbers.release(); // Um barbeiro esta pronto para cortar o cabelo

        controleTela.mutex.release(); // Libera a variavel "esperando"
        cutHair(); // Corta o cabelo (fora da regiao critica)
        sleep((long) (10000 / velocidade)); // Simula o tempo de corte de cabelo

        // Se não houver mais clientes, o barbeiro volta a dormir
        if (controleTela.waiting == 0) {
          sleep();
        }
      } catch (InterruptedException e) {
      }
    }
  }

  /* ***************************************************************
   * Metodo: cutHair
   * Funcao: Simula o corte de cabelo.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void cutHair() throws InterruptedException {
    dormindo.setVisible(false);
    acordado.setVisible(true);
  }

  /* ***************************************************************
   * Metodo: wakeUp
   * Funcao: Acorda o barbeiro.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void wakeUp() {
    try {
      sleep(1000); // Aguarda um segundo antes de acordar
    } catch (InterruptedException e) {
    }
    dormindo.setVisible(false);
    acordado.setVisible(true);
    pausar(); // Pausa o barbeiro
  }

  /* ***************************************************************
   * Metodo: sleep
   * Funcao: Faz o barbeiro dormir.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void sleep() throws InterruptedException {
    sleep(1000); // Barbeiro dorme por um segundo
    dormindo.setVisible(true);
    acordado.setVisible(false);
    pausar(); // Pausa o barbeiro
  }

  /* ***************************************************************
   * Metodo: pausar
   * Funcao: Pausa o barbeiro enquanto a flag de pausa estiver ativa.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void pausar() {
    while (pause) {
      try {
        sleep(1000); // Barbeiro permanece pausado por um segundo
      } catch (InterruptedException e) {
      }
    }
  }
}

