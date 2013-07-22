public class MyDanCorpRestConsumer {
    def proto
    def port
    def host
    
    void setUrl(url) {
        def (base, proto, c, host, c1, port) = (url =~ /^(.*:)?(\/\/)?([a-z\-.]+)(:)?([0-9]+)?(.*)$/)[0]
        assert host, "Couldn't extract host from url"
        
        this.host = host
        this.proto = proto?.replaceAll(/:$/,"") ?: 'http'
        this.port = port ?: 80
    }
    
    def doRestfulThings() {
        "host: $host, port: $port, proto: $proto"
    }
}

new MyDanCorpRestConsumer(url: "www.google.com").with {
    println doRestfulThings()
}

// Advanced User's Usage
new MyDanCorpRestConsumer(url: "www.google.com", proto: 'https').with {
    println doRestfulThings()
}