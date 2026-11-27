package hotel.model;

import java.math.BigDecimal;

public class PagamentoDinheiro implements FormaPagamento{

    @Override
    public void pagar(BigDecimal valor) {
        System.out.println("Pagamento em dinheiro: " + valor);
    }
}