package hotel.model;

public class PagamentoPix implements FormaPagamento{

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento via Pix: " + valor);
    }
}