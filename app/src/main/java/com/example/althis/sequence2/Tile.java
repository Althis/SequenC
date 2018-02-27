package com.example.althis.sequence2;

import java.util.*;

public class Tile {
    private Tile north;
    private Tile south;
    private Tile west;
    private Tile east;
    private int row;
    private int column;
    private int num;
    private static HashSet globalList;
    private Tile voidTile;
    private World world;

    public Tile(){
        this.num = -2;

		/* To be assigned by the assign function */
        this.north = null;
        this.south = null;
        this.west = null;
        this.east = null;
    }

    public Tile(Void e){
        this.num = -2;

		/* To be assigned by the assign function */
        this.north = null;
        this.south = null;
        this.west = null;
        this.east = null;
    }

    public Tile(int num, HashSet globalList, World world){
        this.num = num;
        this.globalList = globalList;
        this.world = world;

		/* To be assigned by the assign function */
        this.north = null;
        this.south = null;
        this.west = null;
        this.east = null;
        this.row = -1;
        this.column = -1;
    }

    public int getTile(){
        return this.num;
    }

    public void deAssign(){
        this.row = -1;
        this.column = -1;
        if (this.north != null){
            this.north.byeSouth();
        }
        if(this.south != null){
            this.south.byeNorth();
        }
        if(this.west != null){
            this.west.byeEast();
        }
        if(this.east != null){
            this.east.byeWest();
        }
    }

    public void assign(Tile north, Tile south, Tile west, Tile east, int row, int column){
        this.row = row;
        this.column = column;
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;
        if(north != null){
            north.helloSouth(this);
        }if(south != null){
            south.helloNorth(this);
        }if(west != null){
            west.helloEast(this);
        }if(east != null){
            east.helloWest(this);
        }
    }

    public void die(){
        if(this.north != null){
            this.north.byeSouth();
        }
        if (this.south != null){
            this.south.byeNorth();
        }
        if (this.west != null){
            this.west.byeEast();
        }
        if (this.east != null){
            this.east.byeWest();
        }
        this.north = null;
        this.south = null;
        this.west = null;
        this.east = null;

        world.kill(row, column);
    }

    public void runMin(int ckNum){
        if (this.num == ckNum - 1){
            globalList.add(this.num);
            if(this.north != null){
                north.runMin(this.num);
            }
            if(this.south !=null){
                south.runMin(this.num);
            }
            if(this.west != null){
                west.runMin(this.num);
            }
            if(this.east != null){
                east.runMin(this.num);
            }
        }else return;
    }

    public void runMax(int ckNum){
        if (this.num == ckNum + 1){
            globalList.add(this.num);
            if(this.north != null){
                north.runMax(this.num);
            }
            if(this.south !=null){
                south.runMax(this.num);
            }
            if(this.west != null){
                west.runMax(this.num);
            }
            if(this.east != null){
                east.runMax(this.num);
            }
        }
    }

    public void dieMin(int ckNum){
        if (this.num == ckNum - 1){
            if(this.north != null){
                north.dieMin(this.num);
            }
            if(this.south !=null){
                south.dieMin(this.num);
            }
            if(this.west != null){
                west.dieMin(this.num);
            }
            if(this.east != null){
                east.dieMin(this.num);
            }
            this.die();
        }else return;
    }

    public void dieMax(int ckNum){
        if (this.num == ckNum + 1){
            if(this.north != null){
                north.dieMax(this.num);
            }
            if(this.south !=null){
                south.dieMax(this.num);
            }
            if(this.west != null){
                west.dieMax(this.num);
            }
            if(this.east != null){
                east.dieMax(this.num);
            }
            this.die();
        }else return;
    }

    public void dieMax2(int ckNum){
        if(this.num == (int) Collections.max(globalList)){
            this.num++;
        }
        if (this.num == ckNum - 1){
            north.dieMax(this.num);
            south.dieMax(this.num);
            west.dieMax(this.num);
            east.dieMax(this.num);
            this.die();
        }else return;
    }

    public int check1(){
        globalList.add(this.num);
        if(this.north != null){
            north.runMin(this.num);
        }
        if(this.south !=null){
            south.runMin(this.num);
        }
        if(this.west != null){
            west.runMin(this.num);
        }
        if(this.east != null){
            east.runMin(this.num);
        }

        if(this.north != null){
            north.runMax(this.num);
        }
        if(this.south !=null){
            south.runMax(this.num);
        }
        if(this.west != null){
            west.runMax(this.num);
        }
        if(this.east != null){
            east.runMax(this.num);
        }

        if (globalList.size() > 2){
            if(this.north != null){
                north.dieMin(this.num);
            }
            if(this.south !=null){
                south.dieMin(this.num);
            }
            if(this.west != null){
                west.dieMin(this.num);
            }
            if(this.east != null){
                east.dieMin(this.num);
            }

            if(this.north != null){
                north.dieMax(this.num);
            }
            if(this.south !=null){
                south.dieMax(this.num);
            }
            if(this.west != null){
                west.dieMax(this.num);
            }
            if(this.east != null){
                east.dieMax(this.num);
            }
            this.num++;
        }
        globalList.clear();
        return this.num;
    }

    public int check2(){
        north.runMin(num);
        north.runMax(num);
        south.runMin(num);
        south.runMax(num);
        west.runMin(num);
        west.runMax(num);
        east.runMin(num);
        east.runMax(num);

        if (globalList.size() > 2){
            north.dieMin(num);
            north.dieMax2(num);
            south.dieMin(num);
            south.dieMax2(num);
            west.dieMin(num);
            west.dieMax2(num);
            east.dieMin(num);
            east.dieMax2(num);
            this.die();
        }
        int returnValue = (int) Collections.max(globalList);
        globalList.clear();
        return returnValue;
    }

    public void byeNorth(){
        this.north = null;
    }

    public void byeSouth(){
        this.south = null;
    }

    public void byeWest(){
        this.west = null;
    }

    public void byeEast(){
        this.east = null;
    }

    public void helloNorth(Tile neighbor){
        this.north = neighbor;
    }

    public void helloSouth(Tile neighbor){
        this.south = neighbor;
    }

    public void helloWest(Tile neighbor){
        this.west = neighbor;
    }

    public void helloEast(Tile neighbor){
        this.east = neighbor;
    }
}