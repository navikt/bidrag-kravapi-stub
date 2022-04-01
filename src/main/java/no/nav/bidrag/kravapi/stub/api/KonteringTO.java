package no.nav.bidrag.kravapi.stub.api;

import java.time.LocalDate;
import java.time.YearMonth;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.YearMonthDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.YearMonthSerializer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Kontering",
        description = "En kontering angir hvor mye som skal betales av skyldner til mottaker på vegne av kravhaver.\n\n"
                + "Konteringen kan unikt identifiseres med kombinasjonen transaksjonskode, delytelsesId og periode. Det forutsettes at delytelsesid'n er unik også på tvers av fagsystemid'er.\n"
                + "\n"
                + "Personidenter for gjelderIdent, kravhaverIdent, mottakerIdent og skyldnerIdent angis med enten FNR eller DNR. (Håndtering av BNR og NPID er uavklart.) "
                + "Aktoernummer kan benyttes i kravhaverIdent, mottakerIdent og skyldnerIdent. Aktoernummere er elleve siffer og starter med enten 8 eller 9. \n"
                + "\n"
                + "I testmiljøene må Tenor-identer støttes i stedet for FNR/DNR. Disse identene har 8 eller 9 i tredje siffer.")
public class KonteringTO {

    @Schema(description = "Type transaksjon.\n\n"
            + "Gyldige transaksjonskoder er:\n"
            + "| Kode  | Korreksjonskode | Beskrivelse                                |\n"
            + "|-------|-----------------|--------------------------------------------|\n"
            + "| A1    | A3              | Bidragsforskudd                            |\n"
            + "| B1    | B3              | Underholdsbidrag (m/u tilleggsbidrag)      |\n"
            + "| D1    | D3              | 18årsbidrag                                |\n"
            + "| E1    | E3              | Bidrag til særlige utgifter (særtilskudd)  |\n"
            + "| F1    | F3              | Ekrefellebidrag                            |\n"
            + "| G1    | G3              | Gebyr                                      |\n"
            + "| H1    | H3              | Tilbakekreving                             |\n"
            + "| I1    |                 | Motregning                                 |\n"
            + "| K1    |                 | Ettergivelse                               |\n"
            + "| K2    |                 | Direkte oppgjør (innbetalt beløp)          |\n"
            + "| K3    |                 | Tilbakekreving ettergivelse                |\n", example = "B1", required = true)
    private String transaksjonskode;

    @Schema(description = "Angir om det er en ny transaksjon eller en endring.", example = "NY", required = true)
    private Konteringstype type;

    @Schema(description = "Dersom konteringen representerer et justert beløp settes dette feltet. Justeringstypene er INDEKSREGULERING og ALDERSJUSTERING. Dersom konteringen ikke gjelder en av de automatiske justeringstypene blir ikke feltet benyttet. For blant annet Jackson deserialisering i Java gir dette en NULL-verdi for feltet. Feltet settes kun for første måned med justert beløp.",
            example = "INDEKSREULERING",
            required = false)
    private Justeringstype justering;

    @Schema(description = "Dersom konteringen gjelder gebyr må feltet settes for å angi om det gjelder gebyr for bidragsmottaker eller bidragspliktig. Dersom konteringen ikke gjelder gebyr (G1 eller G3) blir ikke feltet gebyrRolle benyttet.",
            example = "",
            nullable = true,
            required = false)
    private GebyrRolle gebyrRolle;

    @Schema(description = "Personident (FNR/DNR) til bidragsmottaker i bidragssaken. I saker der bidragsmottaker ikke er satt benyttes et dummynr 22222222226", example = "15878598161", required = true)
    private String gjelderIdent;

    @Schema(description = "Personident (FNR/DNR) eller aktoernummer (TSS-ident/samhandler) til kravhaver.\n"
            + "\n"
            + "Kravhaver angis ikke for gebyr.", example = "14871298182", required = false)
    private String kravhaverIdent;

    @Schema(description = "Personident (FNR/DNR) eller aktoernummer (TSS-ident/samhandler) til mottaker av kravet.\n"
            + "\n"
            + "For gebyr settes mottakerIdent til NAVs aktoernummer 80000345435.", example = "15878598161", required = true)
    private String mottakerIdent;

    @Schema(description = "Personident (FNR/DNR) eller aktoernummer (TSS-ident/samhandler) til skyldner. For Bidrag er dette BP i saken.\n"
            + "\n"
            + "For forskudd settes skyldnerIdent til NAVs aktoernummer 80000345435.", example = "28848596401", required = true)
    private String skyldnerIdent;

    @Schema(description = "Konteringens beløp. Positive beløp og 0 regnes som tillegg, negative beløp som fradrag.", example = "2000", required = true)
    private int belop;

    @Schema(description = "Valutakoden for beløpet.", example = "NOK", required = true)
    private String valuta;

    @DateTimeFormat(pattern = "yyyy-MM")
    @Schema(description = "Angir hvilken periode (måned og år) konteringen gjelder.", type = "String", format = "yyyy-mm", example = "2022-04", required = true)
    @JsonSerialize(using = YearMonthSerializer.class)
    @JsonDeserialize(using = YearMonthDeserializer.class)
    private YearMonth periode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Datoen vedtaket er fattet", example = "2022-03-18", required = true)
    private LocalDate vedtaksdato;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Datoen kravet/konteringen gjøres klart for overføring. "
            + "For direkteoverførte online-vedtak blir datoen sannsynligvis det samme som vedtaksdato. "
            + "For påløp blir datoen satt til dagen påløpet genereres.", example = "2022-03-18", required = true)
    private LocalDate kjoredato;

    @Schema(description = "NAVs brukerid for saksbehandler som har fattet vedtaket", example = "a123456", required = true)
    private String saksbehandlerId;

    @Schema(description = "NAVs brukerid for saksbehandler som har attestert vedtaket (sannsynligvis samme som saksbehandlerId over).", example = "a123456", required = true)
    private String attestantId;

    @Schema(description = "Felt hvor utlandsavdelingen legger inn referansenummer (ffu-ref). Dette er et fritekstfelt som kan inneholde spesialtegn.\n"
            + "\n"
            + "TODO: Bedre navn på feltet? Hva blir riktig regnskapsmessig?", example = "VII W → 450 → 40 /11", required = false)
    private String tekst;

    @Schema(description = "Bidragssakens saksnummer angitt som String.", example = "2201234", required = true)
    private String fagsystemId;

    @Schema(description = "Unik referanse til perioden i vedtaket angitt som String. I bidragssaken kan en periode strekke over flere måneder, og samme referanse blir da benyttet for alle månedene. Samme referanse kan ikke benyttes to ganger for samme transaksjonskode i samme måned.",
            example = "123456789",
            required = true)
    private String delytelsesId;

    @Schema(description = "Konteringstypen er NY for nye konteringer for en stønad i en periode. Deretter skal alle konteringer for samme stønad i samme periode markere ENDRING, altså B3-konteringen og for alle påfølgende B1-konteringer.")
    public static enum Konteringstype {
        NY, ENDRING;
    }

    public static enum Justeringstype {
        INDEKSREGULERING, ALDERSJUSTERING;
    }

    public static enum GebyrRolle {
        BIDRAGSMOTTAKER, BIDRAGSPLIKTIG;
    }

}
