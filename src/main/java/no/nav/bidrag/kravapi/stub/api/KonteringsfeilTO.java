package no.nav.bidrag.kravapi.stub.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Konteringsfeil", description = "Beskriver feil i en enkelt kontering.")
public class KonteringsfeilTO {

    @Schema(description = "En kode som angir type feil som har oppstått. Feilkoden er ment å kunne brukes til å maskinelt sortere feil.", example = "TOLKNING")
    private String feilkode;

    @Schema(description = "En beskrivelse av feilen som har oppstått. Feilmeldingen er ment å være forståelig for et menneske ved manuell gjennomgang.", example = "Tolkning feilet i Elin.")
    private String feilmelding;

    @Schema(description = "Identifiserer hvilken kontering som førte til feilen.")
    private KonteringId kontering;
}
