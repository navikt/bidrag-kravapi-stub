package no.nav.bidrag.kravapi.stub.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api")
public class KravController {

    @Operation(
            summary = "Sende krav",
            description = "Operasjon for å levere krav fra NAV til regnskapet hos Skatteetaten. Et krav består av en liste med "
                    + "konteringer. Det forventes at disse konteringen behandles samlet. Det vil si at hvis én av konteringene "
                    + "feiler, skal ingen av konteringene i kravet benyttes.\n"
                    + "\n"
                    + "Dersom et krav feiler kan det forsøkes overført på nytt gjentatte ganger inntil kravet er overført."
                    + "Krav som gjelder samme fagsak må leveres i korrekt rekkefølge. Feiler et krav i en sak, skal ikke senere "
                    + "krav i samme sak overføres. Senere krav i andre saker kan overføres, selv om noen av partene fra den "
                    + "feilende fagsaken er involvert.\n"
                    + "\n"
                    + "TODO: Det forventes at et krav alltid inneholder de samme konteringene?? Dersom et nytt vedtak fører til "
                    + "et nytt krav som venter på et tidligere feilende krav, skal ikke konteringene fra det seneste kravet slås "
                    + "sammen med det ventende kravet.\n"
                    + "\n"
                    + "NAV har ansvar for å manuelt følge opp krav som ved flere forsøk ikke kan overføres, og vil løse opp i "
                    + "problemet i samarbeid med Skatteetaten.\n"
                    + "\n"
                    + "Ved månedlig påløp skal ikke dette grensesnittet benyttes. Tilsvarende krav legges i stedet inn i en fil "
                    + "som overføres til Skatteetaten gjennom filslusa.\n"
                    + "\n"
                    + "TODO: Formatet på påløpsfilen skal være tilsvarende det nye grensesnittet, men hvor hvert krav legges "
                    + "inn på egen linje. Alternativt kan gammelt xml-format benyttes, men uten kontaktinformasjon (br20, br30, "
                    + "br40 og br50).",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Alle konteringene i kravet er oppdatert OK. Responsen har tom body.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Dersom klienten ikke er autentisert."),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Dersom klienten ikke har tilgang.") },
            requestBody = @RequestBody(
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Førstegangsfastsettelse bidrag",
                                            description = "3 perioder med bidrag og gebyr til begge parter. DelytelsesId er satt til det "
                                                    + "samme for alle periodene av bidraget. For gebyrkonteringene angir gebyrRolle om "
                                                    + "gebyret gjelder bidragspliktig eller bidragsmottaker. Gebyrlinjene har forskjellig "
                                                    + "delytelsesId. Tekst-feltet er kun utfyllt for bidragskonteringene.",
                                            value = "{\r\n"
                                                    + "  \"konteringer\": [\r\n"
                                                    + "    {\r\n"
                                                    + "      \"transaksjonskode\": \"B1\",\r\n"
                                                    + "      \"type\": \"NY\",\r\n"
                                                    + "      \"gjelderIdent\": \"15878598161\",\r\n"
                                                    + "      \"kravhaverIdent\": \"14871298182\",\r\n"
                                                    + "      \"mottakerIdent\": \"15878598161\",\r\n"
                                                    + "      \"skyldnerIdent\": \"28848596401\",\r\n"
                                                    + "      \"belop\": 2000,\r\n"
                                                    + "      \"valuta\": \"NOK\",\r\n"
                                                    + "      \"periode\": \"2022-02\",\r\n"
                                                    + "      \"vedtaksdato\": \"2022-03-18\",\r\n"
                                                    + "      \"saksbehandlerId\": \"a123456\",\r\n"
                                                    + "      \"attestantId\": \"a123456\",\r\n"
                                                    + "      \"tekst\": \"VII W → 550 → 50 /11\",\r\n"
                                                    + "      \"fagsystemId\": \"2201234\",\r\n"
                                                    + "      \"delytelsesId\": \"123456789\"\r\n"
                                                    + "    },\r\n"
                                                    + "    {\r\n"
                                                    + "      \"transaksjonskode\": \"B1\",\r\n"
                                                    + "      \"type\": \"NY\",\r\n"
                                                    + "      \"gjelderIdent\": \"15878598161\",\r\n"
                                                    + "      \"kravhaverIdent\": \"14871298182\",\r\n"
                                                    + "      \"mottakerIdent\": \"15878598161\",\r\n"
                                                    + "      \"skyldnerIdent\": \"28848596401\",\r\n"
                                                    + "      \"belop\": 2000,\r\n"
                                                    + "      \"valuta\": \"NOK\",\r\n"
                                                    + "      \"periode\": \"2022-03\",\r\n"
                                                    + "      \"vedtaksdato\": \"2022-03-18\",\r\n"
                                                    + "      \"saksbehandlerId\": \"a123456\",\r\n"
                                                    + "      \"attestantId\": \"a123456\",\r\n"
                                                    + "      \"tekst\": \"VII W → 550 → 50 /11\",\r\n"
                                                    + "      \"fagsystemId\": \"2201234\",\r\n"
                                                    + "      \"delytelsesId\": \"123456789\"\r\n"
                                                    + "    },\r\n"
                                                    + "    {\r\n"
                                                    + "      \"transaksjonskode\": \"B1\",\r\n"
                                                    + "      \"type\": \"NY\",\r\n"
                                                    + "      \"gjelderIdent\": \"15878598161\",\r\n"
                                                    + "      \"kravhaverIdent\": \"14871298182\",\r\n"
                                                    + "      \"mottakerIdent\": \"15878598161\",\r\n"
                                                    + "      \"skyldnerIdent\": \"28848596401\",\r\n"
                                                    + "      \"belop\": 2000,\r\n"
                                                    + "      \"valuta\": \"NOK\",\r\n"
                                                    + "      \"periode\": \"2022-04\",\r\n"
                                                    + "      \"vedtaksdato\": \"2022-03-18\",\r\n"
                                                    + "      \"saksbehandlerId\": \"a123456\",\r\n"
                                                    + "      \"attestantId\": \"a123456\",\r\n"
                                                    + "      \"tekst\": \"VII W → 550 → 50 /11\",\r\n"
                                                    + "      \"fagsystemId\": \"2201234\",\r\n"
                                                    + "      \"delytelsesId\": \"123456789\"\r\n"
                                                    + "    },\r\n"
                                                    + "    {\r\n"
                                                    + "      \"transaksjonskode\": \"G1\",\r\n"
                                                    + "      \"type\": \"NY\",\r\n"
                                                    + "      \"gebyrRolle\": \"BIDRAGSMOTTAKER\",\r\n"
                                                    + "      \"gjelderIdent\": \"15878598161\",\r\n"
                                                    + "      \"kravhaverIdent\": \"80000345435\",\r\n"
                                                    + "      \"mottakerIdent\": \"80000345435\",\r\n"
                                                    + "      \"skyldnerIdent\": \"15878598161\",\r\n"
                                                    + "      \"belop\": 1223,\r\n"
                                                    + "      \"valuta\": \"NOK\",\r\n"
                                                    + "      \"periode\": \"2022-03\",\r\n"
                                                    + "      \"vedtaksdato\": \"2022-03-18\",\r\n"
                                                    + "      \"saksbehandlerId\": \"a123456\",\r\n"
                                                    + "      \"attestantId\": \"a123456\",\r\n"
                                                    + "      \"fagsystemId\": \"2201234\",\r\n"
                                                    + "      \"delytelsesId\": \"123456790\"\r\n"
                                                    + "    },\r\n"
                                                    + "    {\r\n"
                                                    + "      \"transaksjonskode\": \"G1\",\r\n"
                                                    + "      \"type\": \"NY\",\r\n"
                                                    + "      \"gebyrRolle\": \"BIDRAGSPLIKTIG\",\r\n"
                                                    + "      \"gjelderIdent\": \"15878598161\",\r\n"
                                                    + "      \"kravhaverIdent\": \"80000345435\",\r\n"
                                                    + "      \"mottakerIdent\": \"80000345435\",\r\n"
                                                    + "      \"skyldnerIdent\": \"28848596401\",\r\n"
                                                    + "      \"belop\": 1223,\r\n"
                                                    + "      \"valuta\": \"NOK\",\r\n"
                                                    + "      \"periode\": \"2022-03\",\r\n"
                                                    + "      \"vedtaksdato\": \"2022-03-18\",\r\n"
                                                    + "      \"saksbehandlerId\": \"a123456\",\r\n"
                                                    + "      \"attestantId\": \"a123456\",\r\n"
                                                    + "      \"fagsystemId\": \"2201234\",\r\n"
                                                    + "      \"delytelsesId\": \"123456791\"\r\n"
                                                    + "    }\r\n"
                                                    + "  ]\r\n"
                                                    + "}")
                            })))
    @PostMapping("/krav")
    public ResponseEntity<Void> lagreKrav(KravTO krav) {
        return null;
    }
}
