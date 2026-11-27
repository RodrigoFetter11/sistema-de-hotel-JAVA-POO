package hotel.model;

import java.math.BigDecimal;

public class PagamentoCartao implements FormaPagamento{

    @Override
    public void pagar(BigDecimal valor) {
        System.out.println("Pagamento via cartão: " + valor);
    }
}