package model;

import javafx.util.Duration;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/* ***************************************************************
* Autor............: Cristhian Kauan Moreno Silveira
* Matricula........: 202210185
* Inicio...........: 27/08/2023
* Ultima alteracao.: 02/09/2023
* Nome.............: Trem
* Funcao...........: Realiza acoes relacionadas ao trem (movimento, alteracao de velocidade, definicao de rota...)
*************************************************************** */

public class Trem {
  private double time = 200;                                     // Tempo que dura a transicao de movimento do trem(em segundos); Considerando rate=01
  private int angulo = -90;                                      // Angulo do trem, definido inicialmente como -90
  private int rota;                                              // Linha que o trem percorrera
  private boolean status = false;                                // Status atual do trem,   false:parado; true:em movimento
  private ImageView trem;                                        // ImageView do trem, objeto que sera movimentado na interface 
  private Path path1;                                            // Caminho que o trem percorrera       
  private PathTransition pathTransition = new PathTransition();  // Inicializacao da biblioteca de transicoes PathTransition, utilizada para dar movimento ao trem  

  /* 
   * Construtor
   * @parametro trem         ImageView que sera movida pelo percurso
   * @parametro rotaInicial  Define o caminho inicial que o trem ira seguir
  */
  public Trem(ImageView trem, int rotaInicial) {
    this.trem = trem;
    this.rota = rotaInicial;
  }

  /* 
   * Define o path inicial que o trem ira seguir e todo os parametro necessario para a utilizacao do path transition (nao inicia movimento)     
  */
  public void iniciarPercurso() {
    switch (rota) {
      case 1:
        path1 = new Path(                                                       // Definicao da rota 1
        new MoveTo(30, 10),
        new LineTo(30, -90),
        new LineTo(67, -90),
        new LineTo(67, -160),
        new LineTo(103, -160),
        new LineTo(103, -360),
        new LineTo(65, -360),
        new LineTo(65, -425),
        new LineTo(28, -425),
        new LineTo(28, -600));
        pathTransition.setPath(path1);
        pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);  // Definicao da forma como o trem realizara a curva
      break;

      case 2:
        path1 = new Path(                                                      // Definicao da rota 2
        new MoveTo(30, 10),
        new LineTo(+30, -90),
        new LineTo(-10, -90),
        new LineTo(-10, -160),
        new LineTo(-48, -160),
        new LineTo(-48, -360),
        new LineTo(-10, -360),
        new LineTo(-10, -425),
        new LineTo(+28, -425),
        new LineTo(+28, -600));
        pathTransition.setPath(path1);
        pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);  // Definicao da forma como o trem realizara a curva
      break;

      case 3:
        path1 = new Path(                                                       // Definicao da rota 3
        new MoveTo(30, 0),
        new LineTo(30, 88),
        new LineTo(67, 88),
        new LineTo(67, 155),
        new LineTo(105, 155),
        new LineTo(105, 352),
        new LineTo(67, 352),
        new LineTo(67, 420),
        new LineTo(30, 420),
        new LineTo(30, 610));
        pathTransition.setPath(path1);
        pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);  // Definicao da forma como o trem realizara a curva
      break;

      case 4:
        path1 = new Path(                                                       // Definicao da rota 4
        new MoveTo(30, 0),
        new LineTo(30, 88),
        new LineTo(-8, 88),
        new LineTo(-8, 155),
        new LineTo(-45, 155),
        new LineTo(-45, 352),
        new LineTo(-7, 352),
        new LineTo(-7, 420),
        new LineTo(31, 420),
        new LineTo(31, 610));
        pathTransition.setPath(path1);
        pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);  // Definicao da forma como o trem realizara a curva
      break;
    }

    pathTransition.setNode(trem);                                              // Definindo o imageView do trem que realizara o movimento
    pathTransition.setPath(path1);                                             // Definindo o caminho que o trem ira seguir
    pathTransition.setDuration(Duration.seconds(time));                        // Definindo a duracao da transicao (a duracao esta diretamente ligada a velocidade, logo esse parametro e apenas uma inicializacao do  tempo maximo que o trajeto demorara)
}

  /* 
   * Retorna o trem para sua ultima posicao definida, zera a velocidade dele, e para o movimento     
  */
  public void reset() {
    pathTransition.stop();                                                     // Para o movimento do trem
    trem.setTranslateX(0);                                                     // Move o trem para a posicao X inicial
    trem.setTranslateY(0);                                                     // Move o trem para a posicao Y inicial
    trem.setRotate(angulo);                                                    // Define o angulo do trem (utilizado para que ao resetar o trem  em movimento ele nao volte com o mesmo angulo que estava enquanto se movia)
  }

  /* 
   * Realiza alteracoes no movimento do trem, para isso, altera o rate da transicao
   * @parametro velocidade                                                           Usado para pegar a variacao de velocidade que o usuario deseja       
  */
  public void alterarVelocidade(double velocidade) {
    if (velocidade == 0.0) {                                                   // Se a velocidade for 0.0 o movimento nao ocorre (ou e interrompido)
      pathTransition.pause();
    }  else { 
         this.status = true;                                                   // O trem recebe o status true: movimentando
         pathTransition.pause();                                               // Pausa o movimento para que seja possivel realizar as alteracoes
         pathTransition.setPath(path1);                                        // Define o caminho que sera percorrido
         pathTransition.setRate(velocidade);                                   // Altera a velocidade do movimento
         pathTransition.setCycleCount(PathTransition.INDEFINITE);              // Coloca o movimento em loop
         pathTransition.play();                                                // Inicia o movimento
    }
  }

  /* 
   * Altera a rota que o trem esta seguindo
   * @parametro angulo                       Define a direcao do trem
   * @parametro rota                         Define a nova rota do trem 
  */
  public void mudarPath(int angulo, int rota) {
    this.angulo = angulo;
    this.rota = rota;
    trem.setRotate(angulo);                                                      // Definindo o novo angulo do trem
    
    switch (this.rota) {
      case 1:
        path1 = new Path(                                                       // Definicao da rota 1
        new MoveTo(30, 10),
        new LineTo(30, -90),
        new LineTo(67, -90),
        new LineTo(67, -160),
        new LineTo(103, -160),
        new LineTo(103, -360),
        new LineTo(65, -360),
        new LineTo(65, -425),
        new LineTo(28, -425),
        new LineTo(28, -600));
        pathTransition.setPath(path1);                                         // Alterando o path1, para que o trem percorra a nova rota
        pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);  // Definicao da forma como o trem realizara a curva
     break;

      case 2:
        path1 = new Path(                                                       // Definicao da rota 2
        new MoveTo(30, 10),
        new LineTo(+30, -70),
        new LineTo(-10, -70),
        new LineTo(-10, -150),
        new LineTo(-50, -150),
        new LineTo(-50, -350),
        new LineTo(-10, -350),
        new LineTo(-10, -410),
        new LineTo(+25, -410),
        new LineTo(+25, -600));
        pathTransition.setPath(path1);                                         // Alterando o path1, para que o trem percorra a nova rota
        pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);  // Definicao da forma como o trem realizara a curva
      break;
      
      case 3:
        path1 = new Path(                                                       // Definicao da rota 3
        new MoveTo(30, 0),
        new LineTo(30, 110),
        new LineTo(67, 110),
        new LineTo(67, 170),
        new LineTo(105, 170),
        new LineTo(105, 370),
        new LineTo(67, 370),
        new LineTo(67, 440),
        new LineTo(30, 440),
        new LineTo(30, 610));
        pathTransition.setPath(path1);                                         // Alterando o path1, para que o trem percorra a nova rota
        pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);  // Definicao da forma como o trem realizara a curva
      break;

      case 4:
        path1 = new Path(                                                       // Definicao da rota 4
        new MoveTo(30, 0),
        new LineTo(30, 110),
        new LineTo(-8, 110),
        new LineTo(-8, 170),
        new LineTo(-45, 170),
        new LineTo(-45, 370),
        new LineTo(-7, 370),
        new LineTo(-7, 440),
        new LineTo(31, 440),
        new LineTo(31, 610));
        pathTransition.setPath(path1);                                         // Alterando o path1, para que o trem percorra a nova rota
        pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);  // Definicao da forma como o trem realizara a curva
      break;
    }
  }

  /* 
   * Retorna o status atual do trem, utilizado para evitar conflito de metodos
  */
  public boolean getStatus(){
    return this.status;
  }
}