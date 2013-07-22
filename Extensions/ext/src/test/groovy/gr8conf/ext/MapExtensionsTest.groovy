package gr8conf.ext

import org.junit.Test

/**
 * User: danielwoods
 * Date: 7/18/13
 */
class MapExtensionsTest {

    @Test
    void "test de-structuring map"() {
        def m = [id: 1, name: 'Dan Woods', twitter: '@danveloper']

        def (id, name, twitter) = m

        println "id: $id, name: $name, twitter: $twitter"

        assert id == 1
        assert name == 'Dan Woods'
        assert twitter == '@danveloper'
    }
}
