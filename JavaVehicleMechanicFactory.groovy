public interface Vehicle {
    public enum VehicleType {
        Car, Truck
    }
    
    VehicleType getType();
}

public class Car implements Vehicle {
    private String make;
    private String model;
    
    public String getMake() {
        return this.make;
    }
    
    public String getModel() {
        return this.model;
    }
    
    public void setMake(String make) {
        this.make = make;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public Vehicle.VehicleType getType() {
        return Vehicle.VehicleType.Car;
    }
}
public class Truck implements Vehicle {
    private String make;
    private String model;
    private Double bedLength;
    
    public String getMake() {
        return this.make;
    }
    public String getModel() {
        return this.model;
    }
    public Double getBedLength() {
        return this.bedLength;
    }
    
    public void setMake(String make) {
        this.make = make;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setBedLength(Double bedLength) {
        this.bedLength = bedLength;
    }

    public Vehicle.VehicleType getType() {
        return Vehicle.VehicleType.Truck;
    }
}

public interface Mechanic {
    void setVehicle(Vehicle vehicle);
}

public class CarMechanic implements Mechanic {
    private Car car;
    
    public String fixWipers() {
        return "Car - Wipers Fixed."
    }
    
    public String fixHeadLamps() {
        return "Car - Headlamps Fixed."
    }
    
    public String changeOil() {
        return "Car - Oil Changed."
    }
    
    public void setVehicle(Vehicle car) {
        this.car = car;
    }
}

public class TruckMechanic implements Mechanic {
    private Truck truck;

    public String fixWipers() {
        return "Truck - Wipers Fixed."
    }
    
    public String fixHeadLamps() {
        return "Truck - Headlamps Fixed."
    }
    
    public String changeOil() {
        return "Truck - Oil Changed."
    }
    
    public String repairBed() {
        return "Truck - Bed Repaired."
    }
    
    public void setVehicle(Vehicle truck) {
        this.truck = truck;
    }
}

public class VehicleMechanicFactory {
    
    public static Mechanic createMechanic(Vehicle vehicle) throws IllegalArgumentException {
        Mechanic mechanic = null;
        switch (vehicle.getType()) {
            case Vehicle.VehicleType.Car:
                mechanic = new CarMechanic();
                break;
            case Vehicle.VehicleType.Truck:
                mechanic = new TruckMechanic();
                break;
            default:
                throw new IllegalArgumentException("Could not find a mechanic for vehicle type " + vehicle.getType());
        }
        mechanic.setVehicle(vehicle);
        return mechanic;
    }
    
}

Car car = new Car();
car.setMake("Honda");
car.setModel("Civic");
CarMechanic carMechanic = (CarMechanic)VehicleMechanicFactory.createMechanic(car);

System.out.println( carMechanic.fixWipers() );
System.out.println( carMechanic.changeOil() );

Truck truck = new Truck();
truck.setMake("Ford");
truck.setModel("F-150");
TruckMechanic truckMechanic = (TruckMechanic)VehicleMechanicFactory.createMechanic(truck);

System.out.println( truckMechanic.fixWipers() );
System.out.println( truckMechanic.repairBed() );