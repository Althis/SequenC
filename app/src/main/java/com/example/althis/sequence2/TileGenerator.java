package com.example.althis.sequence2;

import java.util.*;

public class TileGenerator {
    private HashSet globalList;
    private World world;


    public TileGenerator(World world){
        this.world = world;
        this.globalList = new HashSet();

    }

    public Tile nextTile(){
        int max = world.getMaxTile();
        int number = 1;
        if (max > 5){
            number = randTile(max);
        }else{
            Random r = new Random();
            number = r.nextInt(max);
            while (number == 0){
                number = r.nextInt(max);
            }
        }
        Tile newTile = new Tile(number, globalList, world);
        return newTile;
    }

    private int randTile(int k){
        int d = k/4;
        double[] m = new double[k];
        int i = 0;

        double con = 15.0/d;
        for(; i < d; i++) m[i] = 30.0 - (i * con);

        con = 7.0/(2*d);
        for(int j = 0, len = i + (2*d); i < len; i++, j++) m[i] = 15.0 - (j * con);

        con = 3.0/d;
        for(int j = 0; i < k; i++, j++) m[i] = 8.0 - (j * con);

        double sum = 0;
        for(int j = 0; j < k; j++) sum += m[j];

        Random r = new Random();
        double dist = sum * r.nextDouble();
        for(int j = 0; j < k; j++){
            if(m[j] >= dist) return j + 1;
            else dist -= m[j];
        }

        return k;
    }
}
