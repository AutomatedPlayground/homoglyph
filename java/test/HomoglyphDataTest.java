import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class HomoglyphDataTest {
    private Homoglyph homoglyph;

    @Before
    public void setup() throws IOException {
        List<Set<Integer>> charCodes = Homoglyph.parseCharCodesFile("src/char_codes.txt");
        homoglyph = new Homoglyph(charCodes);
    }

    @Test
    public void testLowerCase(){
        check("free ϲгеԁıｔ!", "credit");
        check("see best ｗ℮Ꮟｃ⍺ｍｓ here", "webcams");
        check("get blue ｐɪ|ǀs", "pills");
        check("саｓℎ prizes!!", "cash");
    }

    @Test
    public void testUpperCase(){
        check("FREE ᏟƦⴹⅅ1Ⲧ", "credit");
        check("SEE BEST ᎳℰᛒℂᴀᗰᏕ HERE", "webcams");
        check("GET BLUE ᑭӀⳑℒＳ", "pills");
        check("ℭᴀՏн PRIZES!!", "cash");
    }

    @Test
    public void testMixedCase(){
        check("free Ꮯгⴹԁ1ｔ!", "credit");
        check("see best ｗℰᛒｃ⍺ᗰｓ here", "webcams");
        check("get blue ᑭɪ|Ｌs", "pills");
        check("ℭaՏℎ prizes!!", "cash");
    }

    private void check(String text, String targetWord){
        List<Homoglyph.SearchResult> r = homoglyph.search(text, Arrays.asList(targetWord));
        assertEquals(1, r.size());
        assertEquals(targetWord, r.get(0).word);
    }
}