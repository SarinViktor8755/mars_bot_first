import org.example.PasrserString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {


    @Test
    public void fineKMЕtest() {
        assertTrue(PasrserString.fineKM("#км 36265+5=36270"));
        assertFalse(PasrserString.fineKM("#к 36265+5=36270"));

    }

    @Test
    public void parsKmString() {
        assertEquals(PasrserString.parsKmString("#км 36265+5=36270"), "5");
        assertEquals(PasrserString.parsKmString("#км 36233+5+10=36248"), "15");
//        assertEquals(PasrserString.parsKmString("Хэллоу, лето!\n" +
//                "        #км 36135+13=36148"), "13");
    }


}
