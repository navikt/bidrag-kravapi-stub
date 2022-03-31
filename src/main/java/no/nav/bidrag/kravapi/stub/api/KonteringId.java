package no.nav.bidrag.kravapi.stub.api;

import java.time.YearMonth;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.YearMonthDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearMonthSerializer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "KonteringId", description = "Identifiserer en kontering unikt.")
public class KonteringId {
    @Schema(description = "Type transaksjon.", example = "B1", required = true)
    private String transaksjonskode;

    @DateTimeFormat(pattern = "yyyy-MM")
    @Schema(description = "Angir hvilken periode (måned og år) konteringen gjelder.", type = "String", format = "yyyy-mm", example = "2022-04", required = true)
    @JsonSerialize(using = YearMonthSerializer.class)
    @JsonDeserialize(using = YearMonthDeserializer.class)
    private YearMonth periode;

    @Schema(description = "Unik referanse til perioden i vedtaket. I bidragssaken kan en periode strekke over flere måneder, og samme referanse blir da benyttet for alle månedene. Samme referanse kan ikke benyttes to ganger for samme transaksjonskode i samme måned.",
            example = "123456789",
            required = true)
    private String delytelsesId;

}
