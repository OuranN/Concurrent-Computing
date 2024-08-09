/* ***************************************************************
 * Autor............: Cristhian Kauan Moreno Silveira
 * Matrícula.........: 202210185
 * Início...........: 10/11/2023
 * Última alteração.: 14/11/2023
 * Nome.............: Cliente
 * Função...........: Classe modelo que manipula as ações dos clientes.
 *************************************************************** */
import java.util.concurrent.Semaphore;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

class Cliente extends Thread {
  public ImageView esperando;
  public ImageView tirandoFoto;
  public ImageView chegando;
  public ImageView saindo;
  public ImageView balaoPensamento;
  public Label vouEmbora;
  public Label estudioCheio;
  public static double velocidade = 1;
  public static double velocidade2 = 1;
  public double tempo = 10000;
  public static boolean pause = true;
  public static boolean cadeira1 = false;
  public static boolean cadeira2 = false;
  public static boolean cadeira3 = false;
  public static boolean cadeira4 = false;
  public static boolean cadeira5 = false;
  public static boolean saida1 = false;
  public static boolean chegada1 = false;
  public static controleTela controle = new controleTela();
  public int saidaOcupada = 0;
  public int cadeiraOcupada = 0;
  public int chegadaOcupada = 0;
  public static Semaphore escolhendoCadeira = new Semaphore(1); // # de clientes esperando por serviço
  public static Semaphore escolhendoChegada = new Semaphore(1); // # de clientes esperando por serviço
  public static Semaphore saindoCadeira = new Semaphore(1); // # de clientes esperando por serviço
  public static Semaphore saindoChegada = new Semaphore(1); // # de clientes esperando por serviço
  public static Semaphore saindoBarbearia = new Semaphore(1); // # de clientes esperando por serviço
  public static Semaphore chegandoBarbearia = new Semaphore(1); // # de clientes esperando por serviço

  // Construtor
  public Cliente(int id, ImageView esperando, ImageView tirandoFoto, ImageView chegando, ImageView saindo,
      Label estudioCheio, Label vouEmbora, ImageView balaoPensamento) {
    this.esperando = esperando;
    this.tirandoFoto = tirandoFoto;
    this.chegando = chegando;
    this.saindo = saindo;
    this.balaoPensamento = balaoPensamento;
    this.vouEmbora = vouEmbora;
    this.estudioCheio = estudioCheio;
  }

  public Cliente() {
  }

  @Override
  public void run() {
    while (true) {
      try {
        pausar();
        sleep((long) (5000 / velocidade));
        chegou();
        sleep(1000);
        controleTela.mutex.acquire(); // entra na região crítica
        if (controleTela.waiting < controleTela.CHAIRS) { // se não houver cadeiras livres, saia
          waiting();
          controleTela.waiting = controleTela.waiting + 1; // incrementa o contador de clientes esperando
          controleTela.customers.release(); // acorda o barbeiro, se necessário
          controleTela.mutex.release(); // libera o acesso a 'esperando'
          controleTela.barbers.acquire();
          getHaircut(); // senta e é atendido
          sleep((long) (10000 / velocidade2));
          exitWaiting(); // finaliza o corte e vai embora
        } else {
          controleTela.mutex.release(); // a loja está cheia; não espere
          irEmbora();
        }
        sleep((long) (5000 / velocidade));
      } catch (InterruptedException e) {
      }
    }
  }

  /* ***************************************************************
   * Método: cutHair
   * Função: Simula o corte de cabelo.
   * Parâmetros: Sem parâmetros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void cutHair() {
    esperando.setVisible(false);
    tirandoFoto.setVisible(true);
  }

  /* ***************************************************************
   * Método: getHaircut
   * Função: Simula o processo de cortar o cabelo.
   * Parâmetros: Sem parâmetros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void getHaircut() throws InterruptedException {
    try {
      saindoCadeira.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    switch (cadeiraOcupada) {
      case 1:
        cadeira1 = false;
        break;
      case 2:
        cadeira2 = false;
        break;
      case 3:
        cadeira3 = false;
        break;
      case 4:
        cadeira4 = false;
        break;
      case 5:
        cadeira5 = false;
        break;
    }
    esperando.setVisible(false);
    tirandoFoto.setVisible(true);
    sleep(1000);
    saindoCadeira.release();
  }

  /* ***************************************************************
   * Método: waiting
   * Função: Simula o cliente esperando por uma cadeira.
   * Parâmetros: Sem parâmetros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void waiting() {
    try {
      escolhendoCadeira.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (!cadeira1) {
      cadeira1 = true;
      cadeiraOcupada = 1;
      esperando.setLayoutX(920);
    } else {
      if (!cadeira2) {
        cadeiraOcupada = 2;
        cadeira2 = true;
        esperando.setLayoutX(960);
      } else {
        if (!cadeira3) {
          cadeiraOcupada = 3;
          cadeira3 = true;
          esperando.setLayoutX(1000);
        } else {
          if (!cadeira4) {
            cadeiraOcupada = 4;
            cadeira4 = true;
            esperando.setLayoutX(1040);
          } else {
            if (!cadeira5) {
              cadeiraOcupada = 5;
              cadeira5 = true;
              esperando.setLayoutX(1090);
            }
          }
        }
      }
    }
    tirandoFoto.setVisible(false);
    chegando.setVisible(false);
    esperando.setVisible(true);
    soltaChegada();
    escolhendoCadeira.release();
  }

  /* ***************************************************************
   * Método: exitWaiting
   * Função: Simula o cliente saindo da espera.
   * Parâmetros: Sem parâmetros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void exitWaiting() {
    chegando.setVisible(false);
    esperando.setVisible(false);
    tirandoFoto.setVisible(false);
  }

  /* ***************************************************************
   * Método: pensando
   * Função: Simula o cliente pensando enquanto espera.
   * Parâmetros: Sem parâmetros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void pensando() throws InterruptedException {
    balaoPensamento.setVisible(true);
    estudioCheio.setVisible(true);
    sleep((long) (1000 / velocidade2));
    estudioCheio.setVisible(false);
    vouEmbora.setVisible(true);
    sleep((long) (2000 / velocidade2));
    balaoPensamento.setVisible(false);
    vouEmbora.setVisible(false);
    sleep((long) (1000 / velocidade2));
  }

  /* ***************************************************************
   * Método: irEmbora
   * Função: Simula o cliente indo embora após o atendimento.
   * Parâmetros: Sem parâmetros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void irEmbora() throws InterruptedException {
    try {
      saindoBarbearia.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    pensando();
    esperando.setVisible(false);
    tirandoFoto.setVisible(false);
    chegando.setVisible(false);
    saindo.setVisible(true);
    soltaChegada();
    try {
      sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    saindo.setVisible(false);
    saindoBarbearia.release();
  }

  /* ***************************************************************
   * Método: chegou
   * Função: Simula o cliente chegando ao estúdio.
   * Parâmetros: Sem parâmetros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void chegou() {
    try {
      escolhendoChegada.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    if (!chegada1) {
      chegada1 = true;
      chegadaOcupada = 1;
    }
    esperando.setVisible(false);
    tirandoFoto.setVisible(false);
    chegando.setVisible(true);
  }

  /* ***************************************************************
   * Método: soltaChegada
   * Função: Libera a chegada do cliente.
   * Parâmetros: Sem parâmetros.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void soltaChegada() {
    try {
      saindoChegada.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    switch (chegadaOcupada) {
      case 1:
        chegada1 = false;
        break;
    }

    tirandoFoto.setVisible(false);
    chegando.setVisible(false);
    escolhendoChegada.release();
    saindoChegada.release();
  }

  /* ***************************************************************
   * Método: pausar
   * Função: Pausa a execução do cliente enquanto a flag de pausa estiver ativa.
   * Parâmetros: Sem parâmetros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void pausar() throws InterruptedException {
    while (pause) {
      sleep(1000);
    }
  }

  /* ***************************************************************
   * Método: reset
   * Função: Reinicia as variáveis de controle do cliente.
   * Parâmetros: Sem parâmetros.
   * Retorno: Sem retorno.
   *************************************************************** */
  public static void reset() {
    escolhendoChegada.release();
    saindoChegada.release();
    saindoBarbearia.release();
    escolhendoChegada.release();
    saindoCadeira.release();
    escolhendoCadeira.release();
    chegandoBarbearia.release();

    pause = true;
    cadeira1 = false;
    cadeira2 = false;
    cadeira3 = false;
    cadeira4 = false;
    cadeira5 = false;
    saida1 = false;
    chegada1 = false;
    controle = new controleTela();
  }
}

