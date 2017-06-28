
package org.onebeartoe.math.measurements.temperature.utils;

import java.util.ArrayList;
import java.util.List;
import org.onebeartoe.math.measurements.temperature.TemperaturePoi;

/**
 * This service class maintains a list of points of interest on the temperature scales
 * for Fahrenheit and Celsius.
 * 
 * @author Roberto Marquez <https://www.youtube.com/user/onebeartoe>
 */
public class TemperaturePoiService 
{
    List<TemperaturePoi> pointsOfInterest;
    
    public TemperaturePoiService()
    {
        pointsOfInterest = new ArrayList();
        
        TemperaturePoi poi1 = new TemperaturePoi();
        poi1.description = "Normal Body Temperature";
        poi1.fahrenheit = 98.6;
        poi1.celsius = 37;
        
        TemperaturePoi poi2 = new TemperaturePoi();
        poi2.description = "Water Freezes";
        poi2.fahrenheit = 32;
        poi2.celsius = 0;
        
        pointsOfInterest.add(poi1);
        pointsOfInterest.add(poi2);
    }
    
    public List<TemperaturePoi> getPointsOfInterest()
    {
        return pointsOfInterest;
    }
}
