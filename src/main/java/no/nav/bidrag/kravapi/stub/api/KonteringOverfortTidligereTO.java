package no.nav.bidrag.kravapi.stub.api;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Kontering overført tidligere")
public class KonteringOverfortTidligereTO {
    private List<KonteringId> konteringer;
}
