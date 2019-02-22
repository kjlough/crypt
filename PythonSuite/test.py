from tests import ShiftCipherTests
import unittest

if __name__ == '__main__':
    # initialize the test suite
    loader = unittest.TestLoader()
    suite = unittest.TestSuite()

    # add tests to the test suite
    suite.addTests(loader.loadTestsFromModule(ShiftCipherTests))

    # Set up runner and run tests
    runner = unittest.TextTestRunner(verbosity=2)  # verbosity=2 means "verbose"
    result = runner.run(suite)
