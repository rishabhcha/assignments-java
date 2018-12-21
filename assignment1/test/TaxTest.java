import org.junit.Test;

import static org.junit.Assert.*;

public class TaxTest {

    @Test
    public void calculateTaxOnRawIfPriceIsNonNegative() {
        assertEquals(12.5, Tax.taxOnRaw(100), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateTaxOnRawIfPriceIsNegative() {
        double taxOnRaw = Tax.taxOnRaw(-100);
    }

    @Test
    public void calculateTaxOnManufacturedIfPriceIsNonNegative() {
        assertEquals(14.75, Tax.taxOnManufactured(100), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateTaxOnManufacturedIfPriceIsNegative() {
        double taxOnManufactured = Tax.taxOnManufactured(-100);
    }

    @Test
    public void calculateTaxOnImportedIfPriceIsNonNegative() {
        assertEquals(32.5, Tax.taxOnImport(100), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateTaxOnImportedIfPriceIsNegative() {
        double taxOnManufactured = Tax.taxOnImport(-100);
    }

}