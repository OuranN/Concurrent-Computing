/* ***************************************************************
* Autor............: Cristhian Kauan Moreno Silveira
* Matricula........: 202210185
* Inicio...........: 10/11/2023
* Ultima alteracao.: 14/11/2023
* Nome.............: controleTela
* Funcao...........: Manipula os objetos da interface e as classes 
										 modelo.
*************************************************************** */
import java.util.concurrent.Semaphore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class controleTela {

  public static final int CHAIRS = 5; // # cadeiras para clientes esperando
  public static int waiting = 0; // clientes estão esperando (não sendo cortados)
  public static Semaphore customers = new Semaphore(0); // # de clientes esperando por serviço
  public static Semaphore barbers = new Semaphore(0); // # de barbeiros esperando por clientes
  public static Semaphore mutex = new Semaphore(1); // para exclusão mútua
  private Cliente cliente1 = new Cliente();
  private Cliente cliente2 = new Cliente();
  private Cliente cliente3 = new Cliente();
  private Cliente cliente4 = new Cliente();
  private Cliente cliente5 = new Cliente();
  private Cliente cliente6 = new Cliente();
  private Cliente cliente7 = new Cliente();
  private Cliente cliente8 = new Cliente();
  private Barbeiro barbeiro = new Barbeiro();

  @FXML
  public ImageView dormindo;
  public ImageView acordado;
  public ImageView clienteEsperando1;
  public ImageView clienteEsperando2;
  public ImageView clienteEsperando3;
  public ImageView clienteEsperando4;
  public ImageView clienteEsperando5;
  public ImageView clienteEsperando6;
  public ImageView clienteEsperando7;
  public ImageView clienteEsperando8;

  public ImageView tirandoFoto1;
  public ImageView tirandoFoto2;
  public ImageView tirandoFoto3;
  public ImageView tirandoFoto4;
  public ImageView tirandoFoto5;
  public ImageView tirandoFoto6;
  public ImageView tirandoFoto7;
  public ImageView tirandoFoto8;

  public ImageView chegando1;
  public ImageView chegando2;
  public ImageView chegando3;
  public ImageView chegando4;
  public ImageView chegando5;
  public ImageView chegando6;
  public ImageView chegando7;
  public ImageView chegando8;

  public ImageView saindo1;
  public ImageView saindo2;
  public ImageView saindo3;
  public ImageView saindo4;
  public ImageView saindo5;
  public ImageView saindo6;
  public ImageView saindo7;
  public ImageView saindo8;

  @FXML
  private Slider sliderVelocidadeBarbeiro;
  @FXML
  private Slider sliderVelocidadeClientes;
  @FXML
  public Label vouEmbora;
  @FXML
  public Label estudioCheio;
  @FXML
  public ImageView balaoPensamento;

  @FXML
  public void abreBarbearia(ActionEvent e) throws InterruptedException {
    Cliente.pause = false;
    Barbeiro.pause = false;

  }

  public void pausaCliente(ActionEvent e) {
    Cliente.pause = true;
  }

  public void retomaCliente(ActionEvent e) {
    Cliente.pause = false;
  }

  public void pausaBarbeiro(ActionEvent e) {
    Barbeiro.pause = true;
  }

  public void retomaBarbeiro(ActionEvent e) {
    Barbeiro.pause = false;
  }

  // Quantidade de cadeiras
  public static final int quantidadeCadeiras = 5;
  // Mutex para o próximo cliente que vai cortar o cabelo
  public static final Semaphore proximoCliente = new Semaphore(0);
  // Quantidade de clientes aguardando
  public static int aguardando = 0;

  public void initialize() throws InterruptedException {

    barbeiro = new Barbeiro(1, dormindo, acordado);
    // Inicialização da Thread do barbeiro
    barbeiro.start();
    // Instanciação do cliente
    cliente1 = new Cliente(1, clienteEsperando1, tirandoFoto1, chegando1, saindo1, estudioCheio, vouEmbora,
        balaoPensamento);
    cliente2 = new Cliente(2, clienteEsperando2, tirandoFoto2, chegando2, saindo2, estudioCheio, vouEmbora,
        balaoPensamento);
    cliente3 = new Cliente(3, clienteEsperando3, tirandoFoto3, chegando3, saindo3, estudioCheio, vouEmbora,
        balaoPensamento);
    cliente4 = new Cliente(4, clienteEsperando4, tirandoFoto4, chegando4, saindo4, estudioCheio, vouEmbora,
        balaoPensamento);
    cliente5 = new Cliente(5, clienteEsperando5, tirandoFoto5, chegando5, saindo5, estudioCheio, vouEmbora,
        balaoPensamento);
    cliente6 = new Cliente(6, clienteEsperando6, tirandoFoto6, chegando6, saindo6, estudioCheio, vouEmbora,
        balaoPensamento);
    cliente7 = new Cliente(7, clienteEsperando7, tirandoFoto7, chegando7, saindo7, estudioCheio, vouEmbora,
        balaoPensamento);
    cliente8 = new Cliente(8, clienteEsperando8, tirandoFoto8, chegando8, saindo8, estudioCheio, vouEmbora,
        balaoPensamento);
    // Inicialização da Thread do cliente
    cliente1.start();
    cliente2.start();
    cliente3.start();
    cliente4.start();
    cliente5.start();
    cliente6.start();
    cliente7.start();
    cliente8.start();

    sliderVelocidadeBarbeiro.setMin(1);
    sliderVelocidadeBarbeiro.setMax(100);
    sliderVelocidadeClientes.setMin(1);
    sliderVelocidadeClientes.setMax(1000);

    sliderVelocidadeClientes.valueProperty().addListener((observable, oldValue, newValue) -> {
      Cliente.velocidade = sliderVelocidadeClientes.getValue();
    });

    sliderVelocidadeBarbeiro.valueProperty().addListener((observable, oldValue, newValue) -> {
      Cliente.velocidade2 = sliderVelocidadeBarbeiro.getValue();
      Barbeiro.velocidade = sliderVelocidadeBarbeiro.getValue();
    });

  }

  public void resetAll(ActionEvent e) throws InterruptedException {
    Barbeiro.pause = true;
    Cliente.pause = true;
    Barbeiro.velocidade = 1;
    Cliente.velocidade = 1;
    Cliente.velocidade2 = 1;
    sliderVelocidadeBarbeiro.setValue(1);
    sliderVelocidadeClientes.setValue(1);
  }

}
