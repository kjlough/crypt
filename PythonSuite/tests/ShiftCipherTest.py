from Ciphers.ShiftCipher import *
import string
import unittest

# TODO: test the break functionality and really verify the test cases
short_message = "The quick fox jumps over the lazy brown dog."
long_message = string.printable[:-5]


class TestClassicalShift(unittest.TestCase):
    cipher = ClassicalShift(3, short_message)

    def test_encrypt(self):
        self.cipher.encrypt()
        self.assertTrue(self.cipher.ciphertext == "qeb nrfzh clu grjmp lsbo qeb ixwv yoltk ald.")

    def test_decrypt(self):
        self.cipher.ciphertext = "qeb nrfzh clu grjmp lsbo qeb ixwv yoltk ald."
        self.cipher.decrypt()
        self.assertTrue(self.cipher.plaintext == short_message.lower())


class TestASCIIShift(unittest.TestCase):
    def setUp(self):
        self.cipher = ASCII_Shift(15, long_message)

    def test_encrypt(self):
        self.cipher.encrypt()
        self.assertTrue(self.cipher.ciphertext == "?@ABCDEFGHpqrstuvwxyz{|}~ !\"#$%&'()*PQRSTUVWXYZ[\\]_`abcdefghij0123456789:;<=>IJKLMNOklm^no+,-./")

    def test_decrypt(self):
        self.cipher.ciphertext = "?@ABCDEFGHpqrstuvwxyz{|}~ !\"#$%&'()*PQRSTUVWXYZ[\\]_`abcdefghij0123456789:;<=>IJKLMNOklm^no+,-./"
        self.cipher.decrypt()
        self.assertTrue(self.cipher.plaintext == long_message)