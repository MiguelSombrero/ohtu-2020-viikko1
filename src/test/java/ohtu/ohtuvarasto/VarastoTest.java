package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisellaTilavuudellaKayttokelvotonVarasto() {
    	Varasto v = new Varasto(-5);
    	assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisellaTilavuudellaKayttokelvotonVarasto2() {
    	Varasto v = new Varasto(-5, 5);
    	
    	// tilavuus ei voi olla negatiivinen
    	assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudenVarastonSaldoVahintaanNolla() {
    	Varasto v = new Varasto(5, -2);
    	assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenVarastonSaldoEnintaanTilavuudenVerran() {
    	Varasto v = new Varasto(5, 10);
    	assertEquals(5, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void paljonkoMahtuuPalauttaaOikeanMaaran() {
    	assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void paljonkoMahtuuPalauttaaOikeanMaaran2() {
    	Varasto v = new Varasto(10.0, 5.3);
    	assertEquals(4.7, v.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoaEnintaanTilavuudenVerran() {
    	varasto.lisaaVarastoon(15);
    	assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenLuvunLisaaminenEiKasvataSaldoa() {
    	varasto.lisaaVarastoon(-2);
    	assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenLuvunLisaysEiPienennaVapaataTilaa() {
    	varasto.lisaaVarastoon(-1);
    	assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysPienentaaVapaataTilaaEnintaanTilavuudenVerran() {
    	varasto.lisaaVarastoon(20);
    	assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenPalauttaaEnintaanSaldonVerran() {
    	varasto.lisaaVarastoon(9);
    	double saatuMaara = varasto.otaVarastosta(10);
    	assertEquals(9, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenLuvunOttaminenPalauttaaNollan() {
    	varasto.lisaaVarastoon(9);
    	double saatuMaara = varasto.otaVarastosta(-5);
    	assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenLisaaTilaaEnintaanTilavuudenVerran() {
    	varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(15);
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void tulostaaOikein() {
    	assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

}