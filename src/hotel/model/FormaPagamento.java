package hotel.model;

public interface FormaPagamento {

    void pagar(double valor);

    default void exibirMensagem() {
        System.out.println("Processando pagamento...");
    }

    static void exibirTiposPagamento(){
        System.out.println("Pix, cartão e dinheiro");
    }
}