package io.github.jmgarridopaz.bluezone.hexagon;

/**
 * Application
 * Offers driver ports as API.
 * Has a configurable dependency on driven ports as RI (required interface).
 */
public class AppFromDrivenSide implements BlueZoneApp {

    // Driver ports
    private ForParkingCars carParker;
    private ForCheckingCars carChecker;
    private ForConfiguringApp appConfigurator;
    // Driven ports
    private final ForObtainingRates rateProvider;
    private final ForStoringTickets ticketStore;
    private final ForPaying paymentService;

    public AppFromDrivenSide(ForObtainingRates rateProvider, ForStoringTickets ticketStore, ForPaying paymentService ) {
        this.rateProvider = rateProvider;
        this.ticketStore = ticketStore;
        this.paymentService = paymentService;
    }

    @Override
    public ForParkingCars carParker() {
        if ( this.carParker == null ) {
            this.carParker = new CarParker(this.rateProvider, this.ticketStore, this.paymentService);
        }
        return this.carParker;
    }

    @Override
    public ForCheckingCars carChecker() {
        if ( this.carChecker == null ) {
            this.carChecker = new CarChecker(this.ticketStore);
        }
        return this.carChecker;
    }

    @Override
    public ForConfiguringApp appConfigurator() {
        if ( this.appConfigurator == null ) {
            this.appConfigurator = new AppConfigurator(this.rateProvider,this.ticketStore,this.paymentService);
        }
        return this.appConfigurator;
    }

}
