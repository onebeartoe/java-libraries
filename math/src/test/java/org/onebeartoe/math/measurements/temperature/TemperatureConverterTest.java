
package org.onebeartoe.math.measurements.temperature;

import java.util.List;
import java.util.stream.Collectors;
import org.onebeartoe.math.measurements.temperature.utils.TemperaturePoiService;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class TemperatureConverterTest
{
    private TemperaturePoiService temperaturePoiService;
    
    private TemperatureConverter instance;
    
    public TemperatureConverterTest() 
    {
        instance = new TemperatureConverter();
        
        temperaturePoiService = new TemperaturePoiService();
    }

    @DataProvider(name="fahrenheitToCelsiusData")
    public Object[][] fahrenheitToCelsiusData() throws Exception
    {        
        List<TemperaturePoi> pointsOfInterest = temperaturePoiService.getPointsOfInterest();
        
        List<Object []> rows = pointsOfInterest.stream()
                .map( l -> 
                {
                    Object [] array = new Object[3];
                    
                    array[0] = l.description;
                    array[1] = l.celsius;
                    array[2] = l.fahrenheit;
                    
                    return array;
                }).collect(Collectors.toList());
        
        Object[][] data = new Object[rows.size()][3];
        int r = 0;
        
        for(Object [] row : rows)
        {
            data[r] = row;
            
            r++;
        }
        
        return data;
    }    

    /**
     * Test of fahrenheitToCelsius method, of class TemperatureConverter.
     */
    @Test(dataProvider="fahrenheitToCelsiusData")
    public void testFahrenheitToCelsius(String description, double celsius, double fahrenheit)
    {    
        double converterCelsius = instance.fahrenheitToCelsius(fahrenheit);
        assertEquals(converterCelsius, celsius);
    }

    /**
     * Test of celsiusToFahrenheit method, of class TemperatureConverter.
     */
    @Test(dataProvider="fahrenheitToCelsiusData")
    public void testCelsiusToFahrenheit(String description, double celsius, double fahrenheit)
    {
        double converterFahrenheit = instance.celsiusToFahrenheit(celsius);
        assertEquals(converterFahrenheit, fahrenheit);        
    }
    
}
