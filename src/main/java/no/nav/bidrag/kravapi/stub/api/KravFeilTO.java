package no.nav.bidrag.kravapi.stub.api;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Kravfeil", description = "Lister feil i et krav.")
public class KravFeilTO {
    private List<KonteringsfeilTO> konteringsfeil;
}
