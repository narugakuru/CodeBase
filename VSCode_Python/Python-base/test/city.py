import unittest


def get_location(city, country, population=""):
    if population:
        res = city + "-" + country + "-" + str(population)
    else:
        res = city + "-" + country
    return res


class LocaltionTest(unittest.TestCase):
    """ "测试location"""

    def test_get_location(self):
        location = get_location("赣州", "中国")
        self.assertEqual(location, "赣州-中国")

    def test_get_location_population(self):
        location = get_location("台湾", "cn", 50000)
        self.assertEqual(location, "台湾-cn-50000")


unittest.main()
