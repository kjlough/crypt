from Ciphers.ShiftCipher import *
import string
import unittest

# TODO: test the break functionality and really verify the test cases
short_message = "The quick fox jumps over the lazy brown dog."
long_message = string.printable[:-5]


class TestClassicalShift(unittest.TestCase):
    plaintext = "Sixty Zippers Were Quickly Picked From the Woven Jute Bag."
    ciphertext = "Zpeaf Gpwwlyz Dlyl Xbpjrsf Wpjrlk Myvt Aol Dvclu Qbal Ihn."
    key = 4
    cipher = ClassicalShift(key)

    def test_encrypt(self):
        self.cipher.plaintext = self.plaintext
        self.cipher.encrypt()
        self.assertTrue(self.cipher.ciphertext == self.ciphertext)

    def test_decrypt(self):
        self.cipher.ciphertext = self.ciphertext
        self.cipher.decrypt()
        self.assertTrue(self.cipher.plaintext == self.plaintext)


class TestASCIIShift(unittest.TestCase):
    def setUp(self):
        key = 15
        self.cipher = ASCII_Shift(key, long_message)

    def test_encrypt(self):
        self.cipher.encrypt()
        self.assertTrue(self.cipher.ciphertext == "?@ABCDEFGHpqrstuvwxyz{|}~ !\"#$%&'()*PQRSTUVWXYZ[\\]_`abcdefghij0123456789:;<=>IJKLMNOklm^no+,-./")

    def test_decrypt(self):
        self.cipher.ciphertext = "?@ABCDEFGHpqrstuvwxyz{|}~ !\"#$%&'()*PQRSTUVWXYZ[\\]_`abcdefghij0123456789:;<=>IJKLMNOklm^no+,-./"
        self.cipher.decrypt()
        self.assertTrue(self.cipher.plaintext == long_message)