class User {}

class StaticLookupApi {
    
    static def rightShift(Map params, Class<User> klazz) {
        lookup klazz, params
    }
    
    static def lookup(Class<User> klazz, params) {
        def where = params.collect {k, v -> "$k = '$v'" }?.join(' and ')
        "select * from user where $where"
    }
    
}

class StaticCreationApi {
    static def leftShift(Class<User> klazz, params) {
        def keys = params.keySet().join(',')
        def vals = params.values().join("','")
        "insert into user ($keys) VALUES ('$vals')"
    }
}

use (StaticLookupApi) {
    println User.lookup(id: 1, name: "Dan Woods", twitter: "@danveloper")
    println ([id: 1, name: "Dan Woods", twitter: "@danveloper"] >> User)
}

use (StaticCreationApi) {
    println User << [name: "Dan Woods", twitter: "@danveloper"]
}