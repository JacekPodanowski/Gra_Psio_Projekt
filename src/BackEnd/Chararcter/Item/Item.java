package BackEnd.Chararcter.Item;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public abstract class Item {

    protected String name;
    protected int value;
    protected String quality;
    public Map<String, Double> qualityTab = new TreeMap<>();


    public Item(String name, int value, String quality) {
        setQualityTab();
        this.name = name;
        this.value = value;
        this.quality = quality;
    }

    public Item(String name, int value) {//losuje sie jakosc
        setQualityTab();
        Random r = new Random();
        this.name = name;
        this.value = value;
        this.quality= (String) qualityTab.keySet().toArray()[r.nextInt(8)];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String toString() {
        if(name.charAt(name.length()-1)=='a'){
            return quality.substring(0,quality.length()-1).concat("a ")+name;
        }
        if(name.charAt(name.length()-1)=='o'){
            return quality.substring(0,quality.length()-1).concat("e ")+name;
        }
        return quality+" "+name;
    }

    public String shortName() {
        if(name.charAt(name.length()-1)=='a'){
            return quality.substring(0,quality.length()-1).concat("a ")+name;
        }
        if(name.charAt(name.length()-1)=='o'){
            return quality.substring(0,quality.length()-1).concat("e ")+name;
        }
        return quality+" "+name;
    }

    public void setQualityTab(){
        qualityTab.put("Zdewastowany",0.25);
        qualityTab.put("Lichy",0.5);
        qualityTab.put("Stary",0.75);
        qualityTab.put("Zwykły",1.0);
        qualityTab.put("Solidny",1.25);
        qualityTab.put("Dorodny",1.5);
        qualityTab.put("Perfekcyjny",1.75);
        qualityTab.put("Mityczny",2.0);
    }

    public Map<String, Double> getQualityTab() {
        return qualityTab;
    }

    public abstract String stats();
}
