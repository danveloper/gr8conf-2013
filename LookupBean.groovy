import java.lang.annotation.*

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
    String table();
}
@Entity(table = 'user')
class User {}

class LookupBeanService {
    enum LookupType {
        Create, Find, Delete
    }
    LookupType cmd
    Class type
    Map<String, String> params
    
    def "find some records"(v) {
        cmd = LookupType.Find
        this
    }    
    def of(Class type) {
        this.type = type
        this
    }
    def given(params) {
        this.params = params
        _lookup()
    }
    private def _lookup() {
        def where = params.collect { k, v -> "$k = '$v'" }.join(' and ')
        def table = this.type.getAnnotation(Entity).table()
        "select * from $table where $where"
    }
}

new LookupBeanService().with {
    "find some records" of(User) given([name: "Dan Woods", twitter: "@danveloper"])
}