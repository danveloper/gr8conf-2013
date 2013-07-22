@Grapes(
    @Grab(group='org.springframework', module='spring-web', version='3.2.3.RELEASE')
)

import org.springframework.web.client.*
import groovy.json.JsonSlurper

class MyRestTemplateBuilder {
    def url, method, vars, checkpoint, retType
    
    def make = { 
        this
    }
    def _method = { m ->
        checkpoint = "method"
        method = m
        this
    }
    def _retType = { t ->
        retType = t
        this
    }
    def a = { query -> 
        this 
    }
    def GET = { to ->
        this._method "GET"
    }
    def POST = { to ->
        this._method "POST"
    }
    def with = { p ->
        this
    }
    def params = { m ->
        vars = Eval.me(m)
        this
    }
    def and = {
        this
    }
    def give = {
        this
    }
    def back = { t ->
        retType = t
        this
    }
    def methodMissing(String name, args) {
        if (checkpoint == "method") this.url = name; this.checkpoint = null
        return this
    }
    def propertyMissing(String name) {
        this.url = name
        return this
    }
    def execute(clos) {
        this.with(clos)
        this._do()
    }
    def _do() {
        def rt = new RestTemplate()
        def res = rt."${method.toLowerCase()}ForObject"(url, String, vars ?: [:])
        switch (retType.toUpperCase()) {
            case "JSON":
                new JsonSlurper().parseText(res)
                break
            case "XML":
                new XmlSlurper().parseText(res)
                break
            default:
                res
        }
    }
}

def json = new MyRestTemplateBuilder().execute {
    make a GET to "http://localhost/groovy/controller.json?id={id}" with params "[id: 1]" and give back "json"
}
println json
assert json instanceof Map
assert json.result