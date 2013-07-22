package gr8conf.ext

import org.junit.Test

/**
 * User: danielwoods
 * Date: 7/18/13
 */
class StringExtensionsTest {

    @Test
    void "test magical string to map coercion"() {
        def user = "[id: 1, name: 'Dan Woods', twitter: '@danveloper']"

        def id = user.get('id')
        def name = user.get('name')
        def twitter = user.get('twitter')

        println "id: $id, name: $name, twitter: $twitter"

        assert id == 1
        assert name == 'Dan Woods'
        assert twitter == '@danveloper'
    }
}
