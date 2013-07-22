package gr8conf.metaclass

import org.junit.Test

/**
 * User: danielwoods
 * Date: 7/18/13
 */
class StringTest {

    @Test
    void "test string to map"() {
        def m = "[id: 1]".toMap()

        assert m instanceof Map
        assert m.id == 1
    }

    @Test
    void "test json"() {
        def json = '{ "id": "1", "name": "Dan Woods", "twitter": "@danveloper" }'.toJson()

        assert json instanceof Map
        assert json.id == "1"
        assert json.name == "Dan Woods"
        assert json.twitter == "@danveloper"

    }

}
