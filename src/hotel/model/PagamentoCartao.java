package hotel.model;

public class PagamentoCartao implements FormaPagamento{

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento via cartão: " + valor);
    }
}