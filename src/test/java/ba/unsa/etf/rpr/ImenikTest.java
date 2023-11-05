package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test za klasu imenik
 */
public class ImenikTest {
    private static Imenik imenik = new Imenik();

    //Prije testiranja reinicijaliziramo imenik i popunjavamo ga podacima
    @BeforeEach
    private void imenikPopuni() throws TelefonskiBrojException {
        imenik = new Imenik();
        imenik.dodaj("Eldar", new FiksniBroj(Grad.SARAJEVO, "225-883"));
        imenik.dodaj("Dino", new FiksniBroj(Grad.ZENICA, "225-884"));
        imenik.dodaj("Amir", new MobilniBroj(61, "225-885"));
        imenik.dodaj("Chris", new MedunarodniBroj("+44", "7768878794"));
    }


    @Test
    public void dajImeIma() { // test metode dajIme kada ima kontakt u imeniku sa tim imenom
        TelefonskiBroj broj = new FiksniBroj(Grad.SARAJEVO, "225-883");
        assertEquals( "Eldar", imenik.dajIme(broj));
    }

    @Test
    public void dajImeNema() { // test metode dajIme kada nema kontakt u imeniku sa tim imenom
        TelefonskiBroj broj = new FiksniBroj(Grad.SARAJEVO, "999-999");
        assertNull( imenik.dajIme(broj) );
    }

    @Test
    public void dodajIspravno() { // test metode dodaj, dodavanjem ispravnog medunarodnog broja
        TelefonskiBroj broj = new MedunarodniBroj("+387", "225883");
        imenik.dodaj("Hamo", broj);

        String povratniBroj = imenik.dajBroj("Hamo");
        assertEquals( "+387225883", povratniBroj);
    }

    @Test
    public void kreirajFiksniException(){ // test bacanja izuzetka prilikom pokusaja kreiranja fiksnog broja sa pogresnim podacima
        assertThrows(TelefonskiBrojException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new FiksniBroj(null, "324-531");
            }
        });
        //preko lambda funkcije:
        //assertThrows(TelefonskiBrojException.class, () -> { new FiksniBroj(null, "324-531");});
    }

    @Test
    public void naSlovoIma(){ // testira metodu naSlovo kada ima kontakt cije ime pocinje sa navedenim slovom
        assertEquals("1. Eldar - 033/225-883" + System.lineSeparator(), imenik.naSlovo('E'));
    }

    @Test
    public void izGradaNema(){ // testira metodu iz grada kada nema nikoga iz navedenog grada
        Set<String> imena = imenik.izGrada(Grad.MOSTAR);
        assertTrue(imena.isEmpty());
    }


}
