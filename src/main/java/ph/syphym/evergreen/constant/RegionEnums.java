package ph.syphym.evergreen.constant;

public enum RegionEnums {

    US("United States"),
    CAN("Canada");


    private final String countryName;

    RegionEnums(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }
}
