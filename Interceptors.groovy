class UserService {
    
    def find(params) {
        "${findAll(params)} limit 1"
    }
    
    def findAll(params) {
        "select * from user where ${getWhere(params)}"
    }
    
    private def getWhere(params) {
        params.collect { k, v ->
            "$k = '$v'"
        }.join(' and ')
    }
}

UserService.metaClass.invokeMethod = { String name, args ->
    def paramBuilder = { propString ->
        def i=0
        propString.split("And").inject([:]) { m, prop ->
            m[prop.toLowerCase()] = args[i++]
            m
        }
    }
    if (name =~ /findAllBy/) {
        def propString = (name =~ /findAllBy(.*)/)[0][1]
        def props = paramBuilder(propString)
        return delegate.findAll(props)
    } else if (name =~ /findBy/) {
        def propString = (name =~ /findBy(.*)/)[0][1]
        def props = paramBuilder(propString)
        return delegate.find(props)
    }
    return delegate.metaClass.getMetaMethod(name, args).invoke(delegate, args)
}

new UserService().with {
    println findAllByNameAndTwitter("Dan Woods", "@danveloper")
    println findByNameAndTwitter("Dan Woods", "@danveloper")
}