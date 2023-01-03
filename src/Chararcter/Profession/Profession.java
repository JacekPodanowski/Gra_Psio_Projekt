package Chararcter.Profession;

import Chararcter.Character;
import Chararcter.Item.Weapon;

public interface Profession  {


    void attributesInitiation(Character character);

    void specialAbility(Character character);

     private void warrior_attac(int strength,Weapon weapon){ //w tym miejscu potrzebuje napisac pobieranie danych z klasy weapon ale nie moge wiedziec jak tak samo jak podpiąć to pod interface
         double warrior_base_attac= strength;//*//tutaj ma być broń woja
         //bedzie trzeba to połączyć w klasie character z odpowiadającym interface skills


     }


    private void mage_attac(int intelligence,Weapon weapon){ //w tym miejscu potrzebuje napisac pobieranie danych z klasy weapon ale nie moge wiedziec jak tak samo jak podpiąć to pod interface
        double mage_base_attac= intelligence;//*//tutaj ma być broń maga
        //bedzie trzeba to połączyć w klasie character z odpowiadającym interface skills


    }

    private void archer_attac(int agility,Weapon weapon){ //w tym miejscu potrzebuje napisac pobieranie danych z klasy weapon ale nie moge wiedziec jak tak samo jak podpiąć to pod interface
        double archer_base_attac= agility;//*//tutaj ma być broń łucznika
        //bedzie trzeba to połączyć w klasie character z odpowiadającym interface skills


    }

}
