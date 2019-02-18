from Ciphers.Cipher import *
from Ciphers.HelperMethods import *
import re


class ClassicalShift(ClassicalCipher):
    """
        This class will only shift letters of the
        alphabet just like the classical cipher. The
        code is intended to replicate the process of
        encrypting a message with pen and paper.
    """

    def __init__(self, key, plaintext="", ciphertext=""):
        super(ClassicalShift, self).__init__(plaintext, ciphertext)
        self.key = key;

    def encrypt(self):
        self.ciphertext = ""

        for index, char in enumerate(self.plaintext):
            # Shift only on the regex word characters (letters of the alphabet)
            if re.match(r'\w', char):
                self.ciphertext += numToCharDict[(charToNumDict[char] - self.key) % ENGLISH_ALPHABET_LENGTH]
            else:
                self.ciphertext += char

    def decrypt(self):
        self.plaintext = ""

        for index, char in enumerate(self.ciphertext):
            # Shift only on the regex word characters (letters of the alphabet)
            if re.match(r'\w', char):
                self.plaintext += numToCharDict[(charToNumDict[char] + self.key) % ENGLISH_ALPHABET_LENGTH]
            else:
                self.plaintext += char

        return

    def break_cipher(self):
        # TODO: fill out method.
        return


class ASCII_Shift(ClassicalCipher):
    """
        This class will shift over all printable characters
        in the ascii table (including spaces but excluding tabs and
        newlines etc.)
    """

    def __init__(self, key, plaintext="", ciphertext=""):
        super(ASCII_Shift, self).__init__(plaintext, ciphertext)
        self.key = key

    def encrypt(self):
        self.ciphertext = str.translate(self.plaintext, get_maketrans_table(self.key))

    def decrypt(self):
        self.plaintext = str.translate(self.ciphertext, get_maketrans_table(0 - self.key))
        return

    def break_cipher(self):
        # TODO: fill out method.
        return
