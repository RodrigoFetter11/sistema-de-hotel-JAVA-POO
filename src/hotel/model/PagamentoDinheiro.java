package hotel.model;

public class PagamentoDinheiro implements FormaPagamento{

    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento em dinheiro: " + valor);
    }
}