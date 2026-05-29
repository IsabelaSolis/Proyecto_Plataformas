package com.gym.system.config;

import com.gym.system.model.Cliente;
import com.gym.system.model.Pago;
import com.gym.system.repository.ClienteRepository;
import com.gym.system.repository.PagoRepository;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GymRoutes extends RouteBuilder {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public void configure() throws Exception {
        // RUTA 1: LISTA DE CLIENTES
        // Se activa cada 10 segundos, muestra todos los clientes
        // y los enruta según su sexo

        from("timer:clienteNuevo?period=10000")
            .process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                    List<Cliente> clientes = clienteRepository.findAll();
                    exchange.getIn().setBody(clientes);
                    exchange.setProperty("sinClientes", clientes.isEmpty());
                }
            })
            .log("Lista de clientes — total: ${body.size}")
            .choice()
                .when(simple("${exchangeProperty.sinClientes} == true"))
                    .log("Sin clientes registrados aún.")
                .otherwise()
                    .split(body())
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                Cliente c = exchange.getIn().getBody(Cliente.class);
                                String sexo = c.getSexo() != null ? c.getSexo().toLowerCase() : "";
                                exchange.setProperty("esHombre", sexo.equals("hombre") || sexo.equals("h"));
                                exchange.setProperty("esMujer",  sexo.equals("mujer")  || sexo.equals("m"));
                            }
                        })
                        .choice()
                            .when(simple("${exchangeProperty.esHombre} == true"))
                                .to("direct:bienvenidaHombre")
                            .when(simple("${exchangeProperty.esMujer} == true"))
                                .to("direct:bienvenidaMujer")
                            .otherwise()
                                .to("direct:bienvenidaGeneral")
                        .end()
                    .end()
            .end();

        from("direct:bienvenidaHombre")
            .log("${body.idCliente} — ${body.nombre} ${body.apellido} | Hombre");

        from("direct:bienvenidaMujer")
            .log("${body.idCliente} — ${body.nombre} ${body.apellido} | Mujer");

        from("direct:bienvenidaGeneral")
            .log("${body.idCliente} — ${body.nombre} ${body.apellido} | Sin genero definido");

        // RUTA 2: VERIFICACION DE PAGOS 
        // Se activa cada 15 segundos, revisa todos los pagos
        // y los enruta segun su metodo: EFECTIVO, TARJETA, TRANSFERENCIA

        from("timer:verificarPagos?period=15000")
            .process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                    List<Pago> pagos = pagoRepository.findAll();
                    exchange.getIn().setBody(pagos);
                    exchange.setProperty("totalPagos", pagos.size());
                }
            })
            .log("Verificando ${exchangeProperty.totalPagos} pagos registrados...")
            .split(body())
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Pago pago = exchange.getIn().getBody(Pago.class);
                        String metodo = pago.getMetodoPago() != null
                                ? pago.getMetodoPago().toUpperCase()
                                : "DESCONOCIDO";
                        exchange.setProperty("metodoPago", metodo);
                    }
                })
                .choice()
                    .when(simple("${exchangeProperty.metodoPago} == 'EFECTIVO'"))
                        .to("direct:pagoEfectivo")
                    .when(simple("${exchangeProperty.metodoPago} == 'TARJETA'"))
                        .to("direct:pagoTarjeta")
                    .otherwise()
                        .to("direct:pagoTransferencia")
                .end()
            .end();

        from("direct:pagoEfectivo")
            .log("Pago ID ${body.idPago} — EFECTIVO     | Cliente: ${body.idCliente}");

        from("direct:pagoTarjeta")
            .log("Pago ID ${body.idPago} — TARJETA      | Cliente: ${body.idCliente}");

        from("direct:pagoTransferencia")
            .log("Pago ID ${body.idPago} — TRANSFERENCIA | Cliente: ${body.idCliente}");
    }
}