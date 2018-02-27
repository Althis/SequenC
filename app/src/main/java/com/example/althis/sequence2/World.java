package com.example.althis.sequence2;

import java.util.*;

public class World {
    private Tile[][] board;
    private TileGenerator tileGen;
    private Tuple selected;
    private Tile next;
    private HashSet numberList;
    private Tuple lastAdded;

    public World(){
        this.board = new Tile[4][4];
        this.selected = null;
        this.numberList = new HashSet();

        numberList.add(0);
        numberList.add(1);
        numberList.add(2);
        numberList.add(3);
        numberList.add(4);
        this.tileGen = new TileGenerator(this);
        this.next = tileGen.nextTile();
        board[3][2] = next;
        this.next = tileGen.nextTile();
    }

    public boolean select(int row, int column){
        if(board[row][column] != null) { //Se clicar num local vazio, nada acontece.
            if (selected == null) {
                //Se clicar num lugar com um  tile, seleciona este tile
                this.selected = new Tuple(row, column);
                return true;
            }
            if (row == selected.getRow() && column == selected.getColumn()) { //Se clicar em cima do clicado, ele desseleciona
                this.selected = null;
                return false;
            }else{
                return false;
            }
        }
        return false;
    }

    public int move(int row, int column){
        if(this.selected != null) {
            if (board[row][column] == null) {
                int originalRow = selected.getRow();
                int originalColumn = selected.getColumn();
                selected.deAssign();
                board[row][column] = selected;
                selected.assign(this.getNorth(row, column), this.getSouth(row, column),
                        this.getWest(row, column), this.getEast(row, column), row, column);
                numberList.add(selected.check1());
                board[originalRow][originalColumn] = next;
                next.assign(this.getNorth(originalRow,originalColumn),this.getSouth(originalRow,originalColumn),
                        this.getWest(originalRow,originalColumn), this.getEast(originalRow,originalColumn), originalRow, originalRow);
                numberList.add(nextcheck1());
                int response = selected.getTile();
                this.lastAdded = board[originalRow][originalColumn];
                this.selected = null;
                this.next = tileGen.nextTile();
                return response;
            }
        }
        return -1;
    }

    public void kill(int row, int column){
        board[row][column] = null;
    }

    public int getNext(){
        return next.getTile();
    }

    public String getTranslate(int row, int column){
        String linha = "";
        switch (row){
            case 0: linha ="a";
                break;
            case 1: linha ="b";
                break;
            case 2: linha ="c";
                break;
            case 3: linha ="d";
                break;
        }
        return (String) linha+(column+1);
    }

    public int getTileAt(int row, int column){
        if(board[row][column] != null){
            return board[row][column].getTile();
        }else{return 0;}
    }

    private Tile getNorth(int row, int column){
        if(row == 0){
            return null;
        }else return board[row-1][column];
    }

    private Tile getSouth(int row, int column){
        if(row == 3){
            return null;
        }else return board[row+1][column];
    }

    private Tile getWest(int row, int column){
        if(column == 0){
            return null;
        }else return board[row][column-1];
    }

    private Tile getEast(int row, int column){
        if(column == 3){
            return null;
        }else return board[row][column+1];
    }

    public int getMaxTile(){
        return (int) Collections.max(numberList);
    }

    public boolean checkEndGame(){
        for (int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(board[i][j] == null){
                    return false;
                }
            }
        }
        return true;
    }

}