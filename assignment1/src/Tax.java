class Tax {

    private static final double RAW_TAX = 12.5;
    private static final double MANUFACTURED_TAX = 2;
    private static final double IMPORT_DUTY = 10;

    static double taxOnRaw(int price){
        if (price < 0){
            throw new IllegalArgumentException("Price can not be negative");
        }
        return RAW_TAX*price/100;
    }

    static double taxOnManufactured(int price){
        double tax = taxOnRaw(price);
        return tax + (MANUFACTURED_TAX*(tax+price)/100);
    }

    static double taxOnImport(int price){
        double importDuty = IMPORT_DUTY*price/100;
        double tax = taxOnRaw(price);
        double surcharge;
        double finalCost = price + importDuty + tax;
        if (finalCost <= 100){
            surcharge = 5;
        }else if (finalCost <= 200){
            surcharge = 10;
        }else {
            surcharge = 5*finalCost/100;
        }
        return importDuty + tax + surcharge;
    }

}
