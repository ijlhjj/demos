from unittest import TestCase

from com.sweetmanor.algorithm.search.generic_search import *


class Test(TestCase):

    def test_linear_contains(self):
        self.assertTrue(linear_contains([1, 5, 15, 15, 15, 15, 20], 5))

    def test_binary_contains(self):
        self.assertTrue(binary_contains(["a", "d", "e", "f", "z"], "f"))
        self.assertFalse(binary_contains(["john", "mark", "ronald", "sarah"], "sheila"))
