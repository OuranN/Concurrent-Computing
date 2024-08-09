/* ***************************************************************
* Autor............: Cristhian Kauan Moreno Silveira
* Matricula........: 202210185
* Inicio...........: 23/10/2023
* Ultima alteracao.: 29/10/2023
* Nome.............: controle
* Funcao...........: Manipula os objetos da interface e as classes 
										 modelo.
*************************************************************** */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class controle {

  // Array que armazena os filosofos
  private Filosofos filosofos[] = new Filosofos[5];
  // Array que armazena os ImageView dos garfos.
  private ImageView[] forks = new ImageView[5];
  // Flag para verificar se as threads dos filosofos já foi inicializada.
  private int verificarInicializacao = 0;

  // Elementos FXML dos garfos da mesa
  @FXML
  private ImageView fork0, fork1, fork2, fork3, fork4;

  // Elementos FXML dos filosofos, nessa aplicacao vampiros.
  @FXML
  private ImageView filosofoNormal1, filosofoNormal2, filosofoNormal3, filosofoNormal4, filosofoNormal5;

  @FXML
  private ImageView filosofoPensando1, filosofoPensando2, filosofoPensando3, filosofoPensando4, filosofoPensando5;

  @FXML
  private ImageView filosofoComendo1, filosofoComendo2, filosofoComendo3, filosofoComendo4, filosofoComendo5;

  @FXML
  private Slider sliderComer1, sliderComer2, sliderComer3, sliderComer4, sliderComer5;

  @FXML
  private Slider sliderPensar1, sliderPensar2, sliderPensar3, sliderPensar4, sliderPensar5;

  /*
   * ***************************************************************
   * Metodo: resetAll
   * Funcao: Retornar a aplicacao ao seu estado inicial.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void resetAll(ActionEvent event) throws InterruptedException {
    for (int i = 0; i < 5; i++) {
      filosofos[i].pausar();
      filosofos[i].voltarEstadoInicial();
      filosofos[i].voltaGarfos();
    }

    for (int i = 0; i < 5; i++) {
      filosofos[i].interrupt();
    }
    
    Filosofos.reiniciarSemaforo();
    
    filosofos[0] = new Filosofos(
        0,
        forks,
        filosofoNormal1,
        filosofoPensando1,
        filosofoComendo1);

    filosofos[1] = new Filosofos(
        1,
        forks,
        filosofoNormal2,
        filosofoPensando2,
        filosofoComendo2);

    filosofos[2] = new Filosofos(
        2,
        forks,
        filosofoNormal3,
        filosofoPensando3,
        filosofoComendo3);

    filosofos[3] = new Filosofos(
        3,
        forks,
        filosofoNormal4,
        filosofoPensando4,
        filosofoComendo4);

    filosofos[4] = new Filosofos(
        4,
        forks,
        filosofoNormal5,
        filosofoPensando5,
        filosofoComendo5);

    sliderComer1.setValue(1);
    sliderComer2.setValue(1);
    sliderComer3.setValue(1);
    sliderComer4.setValue(1);
    sliderComer5.setValue(1);

    sliderPensar1.setValue(1);
    sliderPensar2.setValue(1);
    sliderPensar3.setValue(1);
    sliderPensar4.setValue(1);
    sliderPensar5.setValue(1);
    
    verificarInicializacao = 0;
  }

  /*
   * ***************************************************************
   * Metodo: start
   * Funcao: Inicializa as Threads dos filosofos e despausa quando
   * as Threads sao pausadas.
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void start(ActionEvent event) throws InterruptedException {
    if (verificarInicializacao == 0) {
      for (int i = 0; i < 5; i++) {
        filosofos[i].start();
      }
      verificarInicializacao = 1;
    } else {
      for (int i = 0; i < 5; i++) {
        filosofos[i].retomar();
      }
    }
  }

  /*
   * ***************************************************************
   * Metodo: pauseFilosofo1
   * Funcao: Pausa a execucao dos metodos da Thread filosofos[0]
   * Parametros: ActionEvent event
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void pauseFilosofo1(ActionEvent event) {
    filosofos[0].pausar();
  }

  /*
   * ***************************************************************
   * Metodo: pauseFilosofo2
   * Funcao: Pausa a execucao dos metodos da Thread filosofos[1]
   * Parametros: ActionEvent event
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void pauseFilosofo2(ActionEvent event) {
    filosofos[1].pausar();
  }

  /*
   * ***************************************************************
   * Metodo: pauseFilosofo3
   * Funcao: Pausa a execucao dos metodos da Thread filosofos[2]
   * Parametros: ActionEvent event
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void pauseFilosofo3(ActionEvent event) {
    filosofos[2].pausar();
  }

  /*
   * ***************************************************************
   * Metodo: pauseFilosofo4
   * Funcao: Pausa a execucao dos metodos da Thread filosofos[3]
   * Parametros: ActionEvent event
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void pauseFilosofo4(ActionEvent event) {
    filosofos[3].pausar();
  }

  /*
   * ***************************************************************
   * Metodo: pauseFilosofo5
   * Funcao: Pausa a execucao dos metodos da Thread filosofos[4]
   * Parametros: ActionEvent event
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void pauseFilosofo5(ActionEvent event) {
    filosofos[4].pausar();
  }

  /*
   * ***************************************************************
   * Metodo: retomarFilosofo1
   * Funcao: Despausa a execucao dos metodos da Thread filosofos[0]
   * Parametros: ActionEvent event
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void retomarFilosofo1(ActionEvent event) {
    filosofos[0].retomar();
  }

  /*
   * ***************************************************************
   * Metodo: retomarFilosofo2
   * Funcao: Despausa a execucao dos metodos da Thread filosofos[1]
   * Parametros: ActionEvent event
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void retomarFilosofo2(ActionEvent event) {
    filosofos[1].retomar();
  }

  /*
   * ***************************************************************
   * Metodo: retomarFilosofo3
   * Funcao: Despausa a execucao dos metodos da Thread filosofos[2]
   * Parametros: ActionEvent event
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void retomarFilosofo3(ActionEvent event) {
    filosofos[2].retomar();
  }

  /*
   * ***************************************************************
   * Metodo: retomarFilosofo4
   * Funcao: Despausa a execucao dos metodos da Thread filosofos[3]
   * Parametros: ActionEvent event
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void retomarFilosofo4(ActionEvent event) {
    filosofos[3].retomar();
  }

  /*
   * ***************************************************************
   * Metodo: retomarFilosofo5
   * Funcao: Despausa a execucao dos metodos da Thread filosofos[4]
   * Parametros: ActionEvent event
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void retomarFilosofo5(ActionEvent event) {
    filosofos[4].retomar();
  }

  public void initialize() {
    // Armazenando os ImageView dos garfos no array forks[5]
    forks[0] = fork0;
    forks[1] = fork1;
    forks[2] = fork2;
    forks[3] = fork3;
    forks[4] = fork4;

    // Instancia dos filosofos que sao armazenados no array filosofos[5]
    filosofos[0] = new Filosofos(
        0,
        forks,
        filosofoNormal1,
        filosofoPensando1,
        filosofoComendo1);
    filosofos[1] = new Filosofos(
        1,
        forks,
        filosofoNormal2,
        filosofoPensando2,
        filosofoComendo2);
    filosofos[2] = new Filosofos(
        2,
        forks,
        filosofoNormal3,
        filosofoPensando3,
        filosofoComendo3);
    filosofos[3] = new Filosofos(
        3,
        forks,
        filosofoNormal4,
        filosofoPensando4,
        filosofoComendo4);
    filosofos[4] = new Filosofos(
        4,
        forks,
        filosofoNormal5,
        filosofoPensando5,
        filosofoComendo5);

    // Configuracao para que os sliders que definem a velocidade com que os
    // filosofos comem tenham um valor minimo=1 e valor maximo=1000
    sliderComer1.setMax(1000);
    sliderComer1.setMin(1);
    sliderComer2.setMax(1000);
    sliderComer2.setMin(1);
    sliderComer3.setMax(1000);
    sliderComer3.setMin(1);
    sliderComer4.setMax(1000);
    sliderComer4.setMin(1);
    sliderComer5.setMax(1000);
    sliderComer5.setMin(1);

    // Configuracao para os sliders que definem a velocidade com que os filosofos
    // pensam tenham um valor minimo=1 e valor maximo=1000
    sliderPensar1.setMax(1000);
    sliderPensar1.setMin(1);
    sliderPensar2.setMax(1000);
    sliderPensar2.setMin(1);
    sliderPensar3.setMax(1000);
    sliderPensar3.setMin(1);
    sliderPensar4.setMax(1000);
    sliderPensar4.setMin(1);
    sliderPensar5.setMax(1000);
    sliderPensar5.setMin(1);

    // Configurando para que sempre que houver uma alteracao nos slidersComer eles
    // chamem o metodo alterarVelocidadeComer para o filosofo especifico
    sliderComer1.valueProperty().addListener((observable, oldValue, newValue) -> {
      filosofos[0].alterarVelocidadeComer(sliderComer1.getValue());
    });
    sliderComer2.valueProperty().addListener((observable, oldValue, newValue) -> {
      filosofos[1].alterarVelocidadeComer(sliderComer2.getValue());
    });
    sliderComer3.valueProperty().addListener((observable, oldValue, newValue) -> {
      filosofos[2].alterarVelocidadeComer(sliderComer3.getValue());
    });
    sliderComer4.valueProperty().addListener((observable, oldValue, newValue) -> {
      filosofos[3].alterarVelocidadeComer(sliderComer4.getValue());
    });
    sliderComer5.valueProperty().addListener((observable, oldValue, newValue) -> {
      filosofos[4].alterarVelocidadeComer(sliderComer5.getValue());
    });

    // Configurando para que sempre que houver uma alteracao nos slidersPensar eles
    // chamem o metodo alterarVelocidadePensar para o filosofo especifico
    sliderPensar1.valueProperty().addListener((observable, oldValue, newValue) -> {
      filosofos[0].alterarVelocidadePensar(sliderPensar1.getValue());
    });
    sliderPensar2.valueProperty().addListener((observable, oldValue, newValue) -> {
      filosofos[1].alterarVelocidadePensar(sliderPensar2.getValue());
    });
    sliderPensar3.valueProperty().addListener((observable, oldValue, newValue) -> {
      filosofos[2].alterarVelocidadePensar(sliderPensar3.getValue());
    });
    sliderPensar4.valueProperty().addListener((observable, oldValue, newValue) -> {
      filosofos[3].alterarVelocidadePensar(sliderPensar4.getValue());
    });
    sliderPensar5.valueProperty().addListener((observable, oldValue, newValue) -> {
      filosofos[4].alterarVelocidadePensar(sliderPensar5.getValue());
    });
  }
}
