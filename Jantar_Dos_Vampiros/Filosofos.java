/* ***************************************************************
* Autor............: Cristhian Kauan Moreno Silveira
* Matricula........: 202210185
* Inicio...........: 23/10/2023
* Ultima alteracao.: 29/10/2023
* Nome.............: Filosofos
* Funcao...........: Define os moldes dos filosofos, definindo as 
										 acoes que serao realizadas por eles
*************************************************************** */
import java.util.concurrent.Semaphore;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Filosofos extends Thread {

  //Variavel que armazena o numero de filosofos
  private static final int N = 5;

  //Variaveis que definem o estado do filosofo
  private static final int pensando = 0;
  private static final int fome = 1;
  private static final int comendo = 2;

  //Variavel que identifica o filosofo
  private int id;

  //Flag para controlar o pause e retomar do processo
  private boolean pause = false;

  //Array que armazena as ImageView dos garfos da mesa
  private ImageView[] garfos;

  //ImageView do estado Pensado do filosofo
  private ImageView filosofoPensando;
  //ImageView do estado Normal do filosofo
  private ImageView filosofoNormal;
  //ImageView do estado Comendo do filosofo
  private ImageView filosofoComendo;

  //Definicao da velocidade inicial para as acoes de comer e pensar dos filosofos
  private double tempoComer = 1;
  private double tempoPensar = 1;

  //Array que armazena o estado dos filosofos
  private static int[] estado = new int[N];

  //Semaforos para garantir o acesso unico ao recurso compartilhado
  private static Semaphore mutex = new Semaphore(1);
  private static Semaphore mutex2 = new Semaphore(1);
  private static Semaphore mutex3 = new Semaphore(1);
  private static Semaphore mutex4 = new Semaphore(1);
  private static Semaphore mutex5 = new Semaphore(1);
  private static Semaphore mutex6 = new Semaphore(1);
  private static Semaphore[] semaforos = new Semaphore[N];

  /* ***************************************************************
   * Metodo: Filosofos
   * Funcao: Construtor do objeto Filosofo.
   * Parametros: Id do filosofo, array dos garfos, estados normal,
   * 						 pensando e comendo do filosofo.
   * Retorno: Sem retorno.
   *************************************************************** */
  public Filosofos(
    int id,
    ImageView[] garfo,
    ImageView filosofoNormal,
    ImageView filosofoPensando,
    ImageView filosofoComendo
  ) {
    this.id = id;
    semaforos[this.id] = new Semaphore(0);
    this.garfos = garfo;
    this.filosofoNormal = filosofoNormal;
    this.filosofoPensando = filosofoPensando;
    this.filosofoComendo = filosofoComendo;
  }

  /* ***************************************************************
   * Metodo: run
   * Funcao: Executa o processo do filosofo.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void run() {
    while (!Thread.interrupted()) {
      try {
        pensar();
        pegarGarfos();
        comer();
        largarGarfos();
      } catch (InterruptedException e) {}
    }
  }

  /* ***************************************************************
   * Metodo: pensar
   * Funcao: Realiza a etapa de pensamento do filosofo, alem de alterar
   * 				 a velocidade com que pensa.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void pensar() throws InterruptedException {
    sleep(((long) (2000 / tempoPensar)));
    pausando();
  }

  /* ***************************************************************
   * Metodo: pensar
   * Funcao: Realiza a etapa de alimentacao do filosofo, alem de
   * 				 alterar a velocidade com que come
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void comer() throws InterruptedException {
    sleep(((long) (2000 / tempoComer)));
    pausando();
  }

  /* ***************************************************************
   * Metodo: pegarGarfos
   * Funcao: Realiza a manipulacao dos garfos na mesa, altera o estado
   * 				 do filosofo e suas ImageViews para que a etapa de pensar
   * 				 possa ser realizada.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void pegarGarfos() throws InterruptedException { // i=no do filosofo
    
    try {
      pausando();
      mutex.acquire(); // Entra na RC
      estado[id] = fome; // Lembra que o filosofo i quer comer
      testarGarfo(id); // tenta adquirir 2 garfos
      mutex.release(); // sai da rc
      semaforos[id].acquire(); // bloqueia se não pegar 2 garfos
      sleep((1000));
      moveGarfos();

      sleep(2);
      Platform.runLater(() -> {
        filosofoNormal.setVisible(false);
        filosofoPensando.setVisible(false);
        filosofoComendo.setVisible(true);
      });
      sleep(2);
    } catch (InterruptedException e) {}
    
  } // fim do metodo pegar garfos

  /* ***************************************************************
   * Metodo: largarGarfos
   * Funcao: Realiza a manipulacao dos garfos na mesa, altera o estado
   * 				 do filosofo e suas ImageViews para que a etapa de comer
   * 				 possa ser realizada.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void largarGarfos() { // i=no do filosofo
    try {
      pausando();
      mutex.acquire(); // Entra na RC
      estado[id] = pensando; // filosofo libera os 2 garfos
      testarGarfo(vizinhoEsquerda(id)); // olha se o vizinho ESQUERDA pode comer agora
      testarGarfo(vizinhoDireita(id)); // olha se o vizinho DIREITA pode comer agora
      mutex.release(); // sai da RC
      sleep(1000);
      voltaGarfos();
      sleep(2);
      Platform.runLater(() -> {
        filosofoNormal.setVisible(false);
        filosofoPensando.setVisible(true);
        filosofoComendo.setVisible(false);
      });
      sleep(2);
    } catch (InterruptedException e) {}
  } // fim do metodo largar garfos

  /* ***************************************************************
   * Metodo: vizinhoEsquerda
   * Funcao: Verifica quem e o vizinho a esquerda do filosofo.
   * Parametros: id do filosofo.
   * Retorno: O id do vizinho a esquerda do filosofo atual.
   *************************************************************** */
  public int vizinhoEsquerda(int i) {
    return (i + N - 1) % 5;
  }

  /* ***************************************************************
   * Metodo: vizinhoDireita
   * Funcao: Verifica quem e o vizinho a direita do filosofo.
   * Parametros: id do filosofo.
   * Retorno: O id do vizinho a direita do filosofo atual.
   *************************************************************** */
  public int vizinhoDireita(int i) {
    return (i + 1 + N) % 5;
  }

  /* ***************************************************************
   * Metodo: testarGarfo
   * Funcao: testa se e possivel adquirir os 2 garfos (direita e esquerda),
   * 				 se nao for possivel ele bloqueia o estado e espera ate que seja
   * 				 possivel.
   * Parametros: id do filosofo.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void testarGarfo(int i) throws InterruptedException {
    pausando();
    if (
      estado[i] == fome &&
      estado[vizinhoEsquerda(i)] != comendo &&
      estado[vizinhoDireita(i)] != comendo
    ) {
      estado[i] = comendo;
      semaforos[i].release();
    }
  }

  /* ***************************************************************
   * Metodo: pausando
   * Funcao: Pausa a thread.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void pausando() throws InterruptedException {
    while (pause) {
      sleep(1);
    }
  }

  /* ***************************************************************
   * Metodo: pausar
   * Funcao: Altera o estado da flag utilizada para pausar/retomar a Thread.
   * 				 Parando a thread e todo o seu ciclo.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void pausar() {
    pause = true;
  }

  /* ***************************************************************
   * Metodo: retomar
   * Funcao: Altera o estado da flag utilizada para pausar/retomar a Thread.
   * 				 fazendo com que a Thread volte para seu fluxo normal.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void retomar() {
    pause = false;
  }

  /* ***************************************************************
   * Metodo: alterarVelocidadeComer
   * Funcao: Altera a velocidade com que o filosofo passa pelo processo
   * 				 comer.
   * Parametros: Valor double.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void alterarVelocidadeComer(double velocidade) {
    this.tempoComer = velocidade;
  }

  /* ***************************************************************
   * Metodo: alterarVelocidadePensar
   * Funcao: Altera a velocidade com que o filosofo passa pelo processo
   * 				 pensar.
   * Parametros: Valor double.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void alterarVelocidadePensar(double velocidade) {
    this.tempoPensar = velocidade;
  }

  public void moveGarfos() throws InterruptedException {
    switch (id) {
      case 0:
        mutex2.acquire();
        mutex6.acquire();
        sleep(2);
        Platform.runLater(() -> {
          garfos[0].setX(20);
          garfos[0].setY(30);
          garfos[1].setX(-30);
          garfos[1].setY(30);
        });
        sleep(2);
        break;

      case 1:
        mutex2.acquire();
        mutex3.acquire();
        sleep(2);
        Platform.runLater(() -> {
          garfos[1].setX(39);
          garfos[1].setY(-16);
          garfos[2].setX(13);
          garfos[2].setY(33);
        });
        sleep(2);
        break;

      case 2:
        mutex3.acquire();
        mutex4.acquire();
        sleep(2);
        Platform.runLater(() -> {
          garfos[2].setX(-5);
          garfos[2].setY(-39);
          garfos[3].setX(33);
          garfos[3].setY(9);
        });
        sleep(2);
        break;

      case 3:
        mutex4.acquire();
        mutex5.acquire();
        sleep(2);
        Platform.runLater(() -> {
          garfos[3].setX(-37);
          garfos[3].setY(-16);
          garfos[4].setX(24);
          garfos[4].setY(-40);
        });
        sleep(2);
        break;

      case 4:
        mutex5.acquire();
        mutex6.acquire();
        sleep(2);
        Platform.runLater(() -> {
          garfos[4].setX(-21);
          garfos[4].setY(43);
          garfos[0].setX(-40);
          garfos[0].setY(-34);
        });
        sleep(2);

        break;
    }
  }

  public void voltaGarfos() throws InterruptedException {
    switch (id) {
      case 0:
        sleep(2);
        Platform.runLater(() -> {
          garfos[0].setX(0);
          garfos[0].setY(0);
          garfos[1].setX(0);
          garfos[1].setY(0);
        });
        sleep(2);
        mutex2.release();
        mutex6.release();
        break;

      case 1:
        sleep(2);
        Platform.runLater(() -> {
          garfos[1].setX(0);
          garfos[1].setY(0);
          garfos[2].setX(0);
          garfos[2].setY(0);
        });
        sleep(2);
        mutex2.release();
        mutex3.release();
        break;

      case 2:
        sleep(2);
        Platform.runLater(() -> {
          garfos[2].setX(0);
          garfos[2].setY(0);
          garfos[3].setX(0);
          garfos[3].setY(0);
        });
        sleep(2);
        mutex3.release();
        mutex4.release();
        break;

      case 3:
        sleep(2);
        Platform.runLater(() -> {
          garfos[3].setX(0);
          garfos[3].setY(0);
          garfos[4].setX(0);
          garfos[4].setY(0);
        });
        sleep(2);
        mutex4.release();
        mutex5.release();
        break;
      case 4:
        sleep(2);
        Platform.runLater(() -> {
          garfos[4].setX(0);
          garfos[4].setY(0);
          garfos[0].setX(0);
          garfos[0].setY(0);
        });
        sleep(2);

        mutex5.release();
        mutex6.release();
        break;
    }
  }

  /* ***************************************************************
   * Metodo: voltarEstadoInicial
   * Funcao: Retorna as ImageVieww dos filosofos para o estado Inicial/Normal.
   * Parametros: sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void voltarEstadoInicial() throws InterruptedException{
      sleep(2);
      Platform.runLater(() -> {
        filosofoNormal.setVisible(true);
        filosofoPensando.setVisible(false);
        filosofoComendo.setVisible(false);
      });
      sleep(2);
  }

  public static void reiniciarSemaforo(){
    mutex = new Semaphore(1);
    mutex2 = new Semaphore(1);
    mutex3 = new Semaphore(1);
    mutex4 = new Semaphore(1);
    mutex5 = new Semaphore(1);
    mutex6 = new Semaphore(1);
    semaforos = new Semaphore[N];
    estado = new int[N];
  }
}
