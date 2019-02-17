class ClassicalCipher:
    plaintext = ""
    ciphertext = ""

    # ---------------- Classic Cipher Methods ----------------
    # TODO: add cipher type? (Transposition or substitution)
    def __init__(self, plaintext="", ciphertext=""):
        self.plaintext = plaintext
        self.ciphertext = ciphertext

    def encrypt(self):
        self.ciphertext = self.plaintext

    def decrypt(self):
        self.plaintext = self.ciphertext

    def break_cipher(self):
        self.plaintext = "The ability to break this cipher hasn't been implemented yet."
