package net._01001111.text;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoremIpsumTest {

	private Log log = LogFactory.getLog(getClass());
	private LoremIpsum loremIpsum = new LoremIpsum();
	private Random r = new Random();
	private final String _n = System.getProperty("line.separator");

	private void testString(String s) {
		log.debug(s);
		assertNotNull(s);
		assertNotSame("String Not Empty", "", s);
	}

	private int countChunks(String s, String d) {
		return s.split(d).length;
	}

	private int countWords(String s) {
		return countChunks(s, "[\\s]");
	}

	private int countSentences(String s) {
		return countChunks(s, "[.]");
	}

	private int countParagraphs(String s) {
		return countChunks(s, _n+_n);
	}
	
	private int randomint() {
		 return r.nextInt(20) + 1;
	}

	@Test
	public void randomWord() {
		testString(loremIpsum.randomWord());
	}

	@Test
	public void randomPunctuation() {
		testString(loremIpsum.randomPunctuation());
	}

	@Test
	public void words() {
		int n = randomint();
		String s = loremIpsum.words(n);
		testString(s);
		int c = countWords(s);
		log.debug("expecting " + n + " words, counted " + c);
		assertEquals(n, c);
	}

	@Test
	public void sentenceFragment() {
		int m = 3;
		String s = loremIpsum.sentenceFragment();
		testString(s);
		int c = countWords(s);
		log.debug("expecting >= " + m + " words, counted " + c);
		assertTrue("Enough words", c >= m);
	}

	@Test
	public void sentence() {
		int m = 3;
		String s = loremIpsum.sentence();
		testString(s);
		int c = countWords(s);
		log.debug("expecting >= " + m + " words, counted " + c);
		assertTrue("Enough words", c >= m);
		assertTrue("First character uppercase", s.charAt(0) == s.toUpperCase()
				.charAt(0));
		assertTrue("Is Punctuated", s.endsWith(".") || s.endsWith("?"));
	}

	@Test
	public void sentences_count() {
		int n = randomint();
		String s = loremIpsum.sentences(n);
		testString(s);
		int c = countSentences(s);
		log.debug("expecting " + n + " sentences, counted " + c);
		assertEquals(n, c);
	}

	@Test
	public void paragraph_useStandard() {
		int m = 2;
		String s = loremIpsum.paragraph(true);
		testString(s);
		int c = countSentences(s);
		log.debug("expecting > " + m + " sentences, counted " + c);
		assertTrue(c > m);
		assertTrue("Starts with standard", s
				.startsWith("Lorem ipsum dolor sit amet"));
	}

	@Test
	public void paragraph() {
		int m = 2;
		String s = loremIpsum.paragraph();
		testString(s);
		int c = countSentences(s);
		log.debug("expecting >= " + m + " sentences, counted " + c);
		assertTrue("Enough sentences", c >= m);
	}

	@Test
	public void paragraphs_count_useStandard() {
		int n = randomint();
		String s = loremIpsum.paragraphs(n, true);
		testString(s);
		int c = countParagraphs(s);
		log.debug("expecting " + n + " paragraphs, counted " + c);
		assertEquals(n, c);
		assertTrue("Starts with standard", s
				.startsWith("Lorem ipsum dolor sit amet"));
	}

	@Test
	public void paragraphs_count() {
		int n = randomint();
		String s = loremIpsum.paragraphs(n);
		testString(s);
		int c = countParagraphs(s);
		log.debug("expecting " + n + " paragraphs, counted " + c);
		assertEquals(n, c);
	}
}