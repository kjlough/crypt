from Ciphers.ShiftCipher import Shift

short_message = "The quick fox jumps over the lazy brown dog."
# TODO add a cool quote for this long message for testing.
long_message = ""

if __name__ == '__main__':
    # TODO: create unit tests...
    print(f'plaintext:  {short_message}')
    cipher = Shift(3)
    cipher.plaintext = short_message.lower()
    cipher.encrypt()
    print(f'ciphertext: {cipher.ciphertext}')

