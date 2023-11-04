package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import model.Trem;

/* ***************************************************************
* Autor............: Cristhian Kauan Moreno Silveira
* Matricula........: 202210185
* Inicio...........: 27/08/2023
* Ultima alteracao.: 02/09/2023
* Nome.............: Controller
* Funcao...........: Controla as alteracoes da interface
*************************************************************** */

public class Controller {

  @FXML
  private Label labelTrem1, labelTrem2;
  @FXML
  private Slider sliderVermelho, sliderMarron, sliderGeral;
  @FXML
  private ImageView tremVermelho, tremMarron;
  TremMarron tm=new TremMarron();
  TremVermelho tv=new TremVermelho();
  

  Trem tremRed;
  Trem tremBrown;

  /**
   * Define acoes a serem executadas ao iniciar a classe
  */
  public void initialize() {

    tm.start();
    tv.start();
    
  }
  
  /* 
   * Reseta todos os trens (busca o status do trem, define os valores dos sliders para zero)
  */  
  @FXML
  public void resetAll(ActionEvent event) {
    if (tremRed.getStatus() == true) {                            // Busca o status do Trem Marron para
      tremRed.reset();                                            // Reseta a posicao e a velocidade do Trem Vermelho
      sliderVermelho.setValue(0);                                 // Zera o slider do Trem Vermelho
    }
    if (tremBrown.getStatus() == true) {                          // Busca o status do Trem Marron para
      tremBrown.reset();                                          // Reseta a posicao e a velocidade do Trem Marron
      sliderMarron.setValue(0);                                   // Zera o slider do Trem Marron
    }
    sliderGeral.setValue(0);                                      // Zera o slider da velocidade geral
  }

  /* 
   * Reposiciona o Trem Vermelho para a parte superior esquerda
  */ 
  @FXML
  public void topLeftRed(ActionEvent event) {
    tremRed.reset();                                              // Reseta a posicao e a velocidade do Trem Vermelho
    sliderVermelho.setValue(0);                                   // Zera o slider do Trem Vermelho
    tremRed.mudarPath(90, 3);                                     // Altera a rota do Trem Vermelho
    tremVermelho.setLayoutX(249);                                 // Altera a posicao X do Trem Vermelho
    tremVermelho.setLayoutY(21);                                  // Altera a posicao Y do Trem Vermelho
  }

  /* 
   * Reposiciona o Trem Vermelho para a parte superior direita
  */ 
  @FXML
  public void topRightRed(ActionEvent event) {
    tremRed.reset();                                              // Reseta a posicao e a velocidade do Trem Vermelho
    sliderVermelho.setValue(0);                                   // Zera o slider do Trem Vermelho
    tremRed.mudarPath(90, 4);                                     // Altera a rota do Trem Vermelho
    tremVermelho.setLayoutX(324);                                 // Altera a posicao X do Trem Vermelho
    tremVermelho.setLayoutY(21);                                  // Altera a posicao Y do Trem Vermelho
    }

  /* 
   * Reposiciona o Trem Vermelho para a parte inferior esquerda
  */ 
  @FXML
  public void bottomLeftRed(ActionEvent event) {
    tremRed.reset();                                              // Reseta a posicao e a velocidade do Trem Vermelho
    sliderVermelho.setValue(0);                                   // Zera o slider do Trem Vermelho
    tremRed.mudarPath(-90, 1);                                    // Altera a rota do Trem Vermelho
    tremVermelho.setLayoutX(252);                                 // Altera a posicao X do Trem Vermelho
    tremVermelho.setLayoutY(555);                                 // Altera a posicao Y do Trem Vermelho
  }

  /* 
   * Reposiciona o Trem Vermelho para a parte inferior direita
  */ 
  @FXML
  public void bottomRightRed(ActionEvent event) {
    tremRed.reset();                                              // Reseta a posicao e a velocidade do Trem Vermelho
    sliderVermelho.setValue(0);                                   // Zera o slider do Trem Vermelho
    tremRed.mudarPath(-90, 2);                                    // Altera a rota do Trem Vermelho
    tremVermelho.setLayoutX(328);                                 // Altera a posicao X do Trem Vermelho
    tremVermelho.setLayoutY(555);                                 // Altera a posicao Y do Trem Vermelho
  }

  /* 
   * Reposiciona o Trem Marron para a parte superior esquerda
  */ 
  @FXML
  public void topLeftBrown(ActionEvent event) {
    tremBrown.reset();                                             // Reseta a posicao e a velocidade do Trem Marron
    sliderMarron.setValue(0);                                      // Zera o slider do Trem Marron
    tremBrown.mudarPath(90, 3);                                    // Altera a rota do Trem Marron
    tremMarron.setLayoutX(249);                                    // Altera a posicao X do Trem Marron
    tremMarron.setLayoutY(21);                                     // Altera a posicao Y do Trem Marron
  }

  /* 
   * Reposiciona o Trem Marron para a parte superior direita
  */ 
  @FXML
  public void topRightBrown(ActionEvent event) {
    tremBrown.reset();                                             // Reseta a posicao e a velocidade do Trem Marron
    sliderMarron.setValue(0);                                      // Zera o slider do Trem Marron
    tremBrown.mudarPath(90, 4);                                    // Altera a rota do Trem Marron
    tremMarron.setLayoutX(324);                                    // Altera a posicao X do Trem Marron
    tremMarron.setLayoutY(21);                                     // Altera a posicao Y do Trem Marron
  }

  /* 
   * Reposiciona o Trem Marron para a parte inferior esquerda
  */ 
  @FXML
  public void bottomLeftBrown(ActionEvent event) {
    tremBrown.reset();                                             // Reseta a posicao e a velocidade do Trem Marron
    sliderMarron.setValue(0);                                      // Zera o slider do Trem Marron
    tremBrown.mudarPath(-90, 1);                                   // Altera a rota do Trem Marron
    tremMarron.setLayoutX(252);                                    // Altera a posicao X do Trem Marron
    tremMarron.setLayoutY(555);                                    // Altera a posicao Y do Trem Marron
  }

  /* 
   * Reposiciona o Trem Marron para a parte inferior direita
  */ 
  @FXML
  public void bottomRightBrown(ActionEvent event) {
    tremBrown.reset();                                             // Reseta a posicao e a velocidade do Trem Marron
    sliderMarron.setValue(0);                                      // Zera o slider do Trem Marron
    tremBrown.mudarPath(-90, 2);                                   // Altera a rota do Trem Marron
    tremMarron.setLayoutX(328);                                    // Altera a posicao X do Trem Marron
    tremMarron.setLayoutY(555);                                    // Altera a posicao Y do Trem Marron
  }

  class TremMarron extends Thread{
    public void run(){
    
    tremBrown = new Trem(tremMarron, 2);
    tremBrown.iniciarPercurso();                                                          // Define as propriedade necessarias para iniciar o percurso do Trem Vermelho

    sliderMarron.valueProperty().addListener((observable, oldValue, newValue) -> {
      double valor = newValue.doubleValue();
      labelTrem2.setVisible(true);                                                        // Torna a label de velocidade do trem Marron visivel
      labelTrem2.setText(" " + (int) valor + " km/h ");                                   // Exibe a velocidade na label do trem Marron
      tremBrown.alterarVelocidade(sliderMarron.getValue());});                            // Utiliza  o valor do slider para alterar a velocidade do trem marron

    sliderGeral.valueProperty().addListener((observable, oldValue, newValue) -> {         // Realiza as mesmas acoes dos sliders anteriores, mas simultaneamente em ambos
      double valor = newValue.doubleValue();
      labelTrem2.setVisible(true);
      labelTrem2.setText(" " + (int) valor + " km/h ");
      tremBrown.alterarVelocidade(sliderGeral.getValue());
      labelTrem1.setVisible(true);
      labelTrem1.setText(" " + (int) valor + " km/h ");
      tremRed.alterarVelocidade(sliderGeral.getValue());});
      
    }
  }
  
  class TremVermelho extends Thread{
    public void run(){
      tremRed = new Trem(tremVermelho, 1);
      tremRed.iniciarPercurso();
      
      sliderVermelho.valueProperty().addListener((observable, oldValue, newValue) -> {
      double valor = newValue.doubleValue();
      labelTrem1.setVisible(true);                                                        // Torna a label de velocidade do trem vermelho visivel
      labelTrem1.setText(" " + (int) valor + " km/h ");                                   // Exibe a velocidade na label do trem vermelho
      tremRed.alterarVelocidade(sliderVermelho.getValue());});                            // Utiliza  o valor do slider para alterar a velocidade do trem vermelho
        while(true){
          tremRed.areaCritica();
        }
        
      
      
    }
  }
}
