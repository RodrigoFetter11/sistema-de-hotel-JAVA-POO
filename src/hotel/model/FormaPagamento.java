package hotel.model;

import java.math.BigDecimal;

public interface FormaPagamento {

    void pagar(BigDecimal valor);

    default void exibirMensagem() {
        System.out.println("Processando pagamento...");
    }

    static void exibirTiposPagamento(){
        System.out.println("Pix, cartão e dinheiro");
    }
}