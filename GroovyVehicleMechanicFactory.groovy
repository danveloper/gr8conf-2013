enum VehicleType {
    Car, Truck
}

class Car {
    String make, model
}

class Truck {
    String make, model
    Double bedLength
}

class CarMechanic {
    Car car
    
    String fixWipers() {
        "Car - Wipers Fixed."
    }
    
    String fixHeadLamps() {
        "Car - Headlamps Fixed."
    }
    
    String changeOil() {
        "Car - Oil Changed."
    }
}

class TruckMechanic {
    Truck truck
    
    String fixWipers() {
        "Truck - Wipers Fixed."
    }
    
    String fixHeadLamps() {
        "Truck - Headlamps Fixed."
    }
    
    String changeOil() {
        "Truck - Oil Changed."
    }
    
    String repairBed() {
        "Truck - Bed Repaired."
    }
}

class VehicleService {
    static def createMechanic(def vehicle, Closure callback) {
        assert vehicle.class in [Car, Truck], "No mechanic for ${vehicle.class}"
        
        def mechanic
        switch (vehicle.class) {
            case Car:
                mechanic = new CarMechanic(car: vehicle)
                break;
            case Truck:
                mechanic = new TruckMechanic(truck: vehicle)
        }
        
        callback.delegate = mechanic
        callback()
    }
}

def car = new Car(make: "Honda", model: "Civic")
def truck = new Truck(make: "Ford", model: "F-150")

VehicleService.createMechanic(car) {
    println fixWipers()
    println changeOil()
}

VehicleService.createMechanic(truck) {
    println fixWipers()
    println repairBed()
}