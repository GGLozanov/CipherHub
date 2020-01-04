package com.example.cipherhub;

import org.junit.Test;

import ciphers.CaesarCipher;
import ciphers.VigenereCipher;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void caesar_isCorrect() { // first UnitTest!
        assertEquals(new CaesarCipher(3).CaesarEncoder("abc"), "xyz");
    }

    public void vigenere_isCorrect() {
        assertEquals(new VigenereCipher().VigenereEncode("Hi, Rachel!", "Hello"), "Om, Clqoiw!");
    }
}

