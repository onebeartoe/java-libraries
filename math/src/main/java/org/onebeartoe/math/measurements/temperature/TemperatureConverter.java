
package org.onebeartoe.math.measurements.temperature;

import javax.measure.converter.UnitConverter;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;

/**
 * Reference for usign the Temperature API came from this stack overflow question 
 * and answer:
 * 
 *      https://stackoverflow.com/a/11618134/803890
 * 
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class TemperatureConverter
{
    public double fahrenheitToCelsius(double fahrenheit)
    {
        UnitConverter converter = NonSI.FAHRENHEIT.getConverterTo(SI.CELSIUS);
        
        double c = converter.convert(fahrenheit);
        
        // there has to be a better way to round!
        String s = String.format("%1.1f", c);
        
        c = Double.valueOf(s);

        return c;
    }
            
    public double celsiusToFahrenheit(double celsius)
    {
        UnitConverter converter = SI.CELSIUS.getConverterTo(NonSI.FAHRENHEIT);
        
        double fahrenheit = converter.convert(celsius);
        
        // there has to be a better way to round!
        String s = String.format("%1.1f", fahrenheit);
        
        fahrenheit = Double.valueOf(s);
        
        return fahrenheit;
    }
}