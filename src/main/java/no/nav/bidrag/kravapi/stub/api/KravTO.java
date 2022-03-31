package no.nav.bidrag.kravapi.stub.api;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Krav", description = "Et krav består av en liste med konteringer. ")
public class KravTO {

    @Schema(required = true, description = "En liste med konteringene i kravet.")
    private List<KonteringTO> konteringer;

}
