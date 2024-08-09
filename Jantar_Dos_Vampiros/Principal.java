
/* ***************************************************************
* Autor............: Cristhian Kauan Moreno Silveira
* Matricula........: 202210185
* Inicio...........: 27/08/2023
* Ultima alteracao.: 02/09/2023
* Nome.............: Principal
* Funcao...........: Inicia o programa
*************************************************************** */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Principal extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/jantar.fxml"));
    controle controle = new controle(); // Instancia do controlador
    loader.setController(controle); // Define o controlador para o loader
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setResizable(false); // Não permite que a janela que vai ser carrega seja redimensionada
    stage.setTitle("Jantar dos Vampiros"); // Titulo da Janela
    stage.setOnCloseRequest(t -> {
      Platform.exit();
      System.exit(0);
    }); // Ao fechar a janela todos os processos sao fechados tambem
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}