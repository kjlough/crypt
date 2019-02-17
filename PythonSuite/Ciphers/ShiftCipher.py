from Ciphers.ClassicalCipher import  ClassicalCipher
from Ciphers.Helpers import *


class Shift(ClassicalCipher):

    def __init__(self, key, plaintext="", ciphertext=""):
        ClassicalCipher(plaintext, ciphertext)
        self.key = key;

    def encrypt(self):
        # Clear ciphertext:
        self.ciphertext = ""

        for index, char in enumerate(self.plaintext):
            # TODO: use regex to only operate on word characters
            if char != " " and char != ".":
                # TODO: make code clear so that we don't use random numbers like '97' below
                # in this case the '97' is the start of the lowercase ascii range
                # also note that this code is meant to replicate the process of encrypting via pen and paper, not to be the most efficient.
                self.ciphertext += numToCharDict[(ord(char) + self.key - 97) % ENGLISH_ALPHABET_LENGTH]
            else:
                self.ciphertext += char

    def decrypt(self):
        # TODO: Fill out decrypt method
        return

    def break_cipher(self):
        # TODO: fill out method.
        return
