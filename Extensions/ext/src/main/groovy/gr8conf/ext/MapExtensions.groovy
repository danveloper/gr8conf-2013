package gr8conf.ext

/**
 * User: danielwoods
 * Date: 7/18/13
 */
class MapExtensions {

    static Object getAt(Map map, def idx) {
        def size = map.keySet().size()
        if (idx > size) throw new IllegalArgumentException("Index size $idx is bigger than keyset size of $size")
        else map[map.keySet()[idx]]
    }
}
