package ba.unsa.etf.rpr;

import java.util.List;

public class MatVis {

    public static Double min(List<Double> lista){
        double min = Double.MAX_VALUE;
        for(Double x : lista){
            if(x.doubleValue() < min) min = x.doubleValue();
        }
        return min;
    }

    public static Double max(List<Double> lista){
        double max = Double.MIN_VALUE;
        for(Double x : lista){
            if(x.doubleValue() > max) max = x.doubleValue();
        }
        return max;
    }

    public static Double mean(List<Double> lista){
        double sum = 0;
        for(Double x : lista){
            sum = sum + x.doubleValue();
        }
        return sum / lista.size();
    }

    public static Double standardnaDevijacija(List<Double> lista){
        double mean = MatVis.mean(lista);
        double sum = 0;
        for(Double x : lista){
            sum = sum + Math.pow( x-mean, 2);
        }
        return Math.sqrt(sum / (lista.size()-1));
    }

}
