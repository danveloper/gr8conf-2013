class RulesProcessor {
    def rulesClass
    def interrogant
    
    void setRulesClass(Class klazz) {
        rulesClass = klazz.newInstance()
    }
    
    def interrogate() {
        def results = [:]
        rulesClass.class.declaredFields.each { f ->
            if (!f.isSynthetic() && f.name.endsWith("Rule")) {
                println "calling $f.name"
                def result = rulesClass."$f.name"(interrogant)
                println "$result"
                results[f.name] = result
            }
        }
        results.findAll { k, v -> !v } ?: null
    }
}

class CreditCard {
    String number
    Date expiration
}

class PaymentRules {
    def validNumberRule = { CreditCard card ->
        Integer.parseInt(card.number.substring(0,1)) in [3, 4, 5, 6]
    }
    def validExpirationRule = { CreditCard card ->
        card.expiration.after(new Date())
    }
}

def mastercard = new CreditCard(number: "5444444444444444", expiration: Date.parse("yyyy-MM-dd", "2013-07-31"))

def processor = new RulesProcessor(rulesClass: PaymentRules, interrogant: mastercard)
def failures = processor.interrogate()

if (failures) {
    throw new RuntimeException("CreditCard failed validation rules: ${failures.keySet()}")
} else {
    println "Credit Card looks good..."
}

