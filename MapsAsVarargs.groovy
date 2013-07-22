class UserService {        
    // More traditional, Java-style implmentation
    def lookupUser(Long id, Date start, Date end) {
        "id: $id, start: $start, end: $end"
    }
        
    // Map-based method params
    def lookupUser(params) {
        Long id = params.id; Date start = params.startDate; Date end = params.endDate
        "params: $params, id: $id, start: $start, end: $end"
    } 
}

def userService = new UserService()

// What are these parameters?
println userService.lookupUser(
    25, 
    Date.parse("yyyy-MM-dd", "2013-03-01"), 
    Date.parse("yyyy-MM-dd", "2013-07-25")
)

// Self-documenting parameters
println userService.lookupUser(
    id: 25, 
    startDate: Date.parse("yyyy-MM-dd", "2013-03-01"),
    endDate: Date.parse("yyyy-MM-dd", "2013-07-25")
)