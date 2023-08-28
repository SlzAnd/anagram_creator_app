package com.example.task1_anagram;


import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test;

class AnagramCreatorTest {

    private lateinit var anagramCreator: AnagramCreator

    @Before
    fun setup() {
        anagramCreator = AnagramCreator()
    }

    @Test
    fun `empty input fields returns empty string result`() {
        val result = anagramCreator.createAnagram("","")
        assertThat(result).isEmpty()
    }

    @Test
    fun `empty input and not empty filter field returns empty string result`() {
        val result = anagramCreator.createAnagram("","123 one two three")
        assertThat(result).isEmpty()
    }

    @Test
    fun `input only with digits and non alphabetic symbols and empty filter`() {
        val result = anagramCreator.createAnagram("1+2=3! <-:/*.,?;->","")
        assertThat(result).isEqualTo("1+2=3! <-:/*.,?;->")
    }

    @Test
    fun `not empty input and empty filter(with white space symbols)`() {
        val result = anagramCreator.createAnagram("Foxminded! cool 24/7","     ")
        assertThat(result).isEqualTo("dednimxoF! looc 24/7")
    }

    @Test
    fun `input without digits and non alphabetic symbols and empty filter`() {
        val result = anagramCreator.createAnagram("uoY lliw reven klaw enola","")
        assertThat(result).isEqualTo("You will never walk alone")
    }

    @Test
    fun `input and not empty filter`() {
        val result = anagramCreator.createAnagram("a1bcd efglh","xl")
        assertThat(result).isEqualTo("dcb1a hgfle")
    }

    @Test
    fun `input without filtered symbols and not empty filter`() {
        val result = anagramCreator.createAnagram("couch potato","my !+123")
        assertThat(result).isEqualTo("hcuoc otatop")
    }

    @Test
    fun `cyrillic input with filtered symbols in filter field`() {
        val result = anagramCreator.createAnagram("мої вітання","н я")
        assertThat(result).isEqualTo("їом атівння")
    }

}