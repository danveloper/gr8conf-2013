package gr8conf.ext

/**
 * User: danielwoods
 * Date: 7/18/13
 */
class StringExtensions {

    static Object get(String self, Object key) {
        def map = Eval.me(self)
        assert map instanceof Map

        map[key]
    }

}
