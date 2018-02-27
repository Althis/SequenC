package com.example.althis.sequence2;

public class Tuple {
    private int y;
    private int x;

    public Tuple(int y, int x){
        this.y = y;
        this.x = x;
    }

    public int getRow(){
        return y;
    }
    public int getColumn(){
        return x;
    }

    public void assign(int row, int column){
        this.y = row;
        this.x = column;
    }

    public void clear(){
        this.y = -10;
        this.x = -10;
    }

}
