package Chararcter.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public abstract class Item {

    protected String name;
    protected int value;
    protected String quality;
    protected Map<String, Double> qualityTAb= new TreeMap<>();


    public Item(String name, int value, String quality) {
        setQualityTAb();
        this.name = name;
        this.value = value;
        this.quality = quality;
    }

    public Item(String name, int value) {//losuje sie jakosc
        setQualityTAb();
        Random r = new Random();
        this.name = name;
        this.value = value;
        this.quality= (String) qualityTAb.keySet().toArray()[r.nextInt(8)];
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

    @Override
    public String toString() {
        return quality +" " + name;
    }
    private void setQualityTAb(){
        qualityTAb.put("Zdewastowany",0.25);
        qualityTAb.put("Lichy",0.5);
        qualityTAb.put("Stary",0.75);
        qualityTAb.put("Zwykły",1.0);
        qualityTAb.put("Solidny",1.25);
        qualityTAb.put("Dorodny",1.5);
        qualityTAb.put("Perfekcyjny",1.75);
        qualityTAb.put("Mityczny",2.0);
    }
}
