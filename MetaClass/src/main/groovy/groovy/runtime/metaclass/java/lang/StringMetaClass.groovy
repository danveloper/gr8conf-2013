package groovy.runtime.metaclass.java.lang

import groovy.json.JsonSlurper

/**
 * User: danielwoods
 * Date: 7/18/13
 */
class StringMetaClass extends DelegatingMetaClass {

    StringMetaClass(MetaClass meta) {
        super(meta)
    }

    public Map toMap(String input) {
        (Map)Eval.me(input)
    }

    public def toJson(String input) {
        new JsonSlurper().parseText(input)
    }

    def invokeMethod(Object object, String method, Object[] args) {
        if (method == "toMap") toMap(object)
        else if (method == "toJson") toJson(object)
        else super.invokeMethod(object, method, args)
    }
}
