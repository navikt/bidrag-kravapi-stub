package no.nav.bidrag.kravapi.stub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Bidragskrav",
        version = "0.1",
        description = "Grensesnitt for overføring av krav/transaksjoner mellom bidragsområdet og Elin/Navi.",
        contact = @Contact(name = "NAV - Team Bidrag", url = "https://github.com/navikt/bidrag-kravapi-stub")))
public class KravapiStubApplication {
    public static void main(String[] args) {
        SpringApplication.run(KravapiStubApplication.class, args);
    }

}
