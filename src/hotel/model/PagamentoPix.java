package hotel.model;

import java.math.BigDecimal;

public class PagamentoPix implements FormaPagamento{

    @Override
    public void pagar(BigDecimal valor) {
        System.out.println("Pagamento via Pix: " + valor);
    }
}