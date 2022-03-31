package no.nav.bidrag.kravapi.stub.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestControllerAdvice
public class KravExceptionHandler {

    @ExceptionHandler(KravUgyldigException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400",
            description = "Dersom én av konteringene ikke går gjennom validering forkastes alle konteringene i kravet "
                    + "og en liste over konteringer som har feilet returneres, sammen med informasjon om hva som er feil.\n\n"
                    + "Det er ingen garanti for at konteringer som ikke kommer med på listen over feilede konteringer er feilfrie.")
    public KravFeilTO kravFeilet(KravUgyldigException e) {
        return new KravFeilTO();
    }
}
