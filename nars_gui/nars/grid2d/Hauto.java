package nars.grid2d;

import static nars.grid2d.Cell.Logic.AND;
import static nars.grid2d.Cell.Logic.BRIDGE;
import static nars.grid2d.Cell.Logic.NOT;
import static nars.grid2d.Cell.Logic.OFFSWITCH;
import static nars.grid2d.Cell.Logic.OR;
import static nars.grid2d.Cell.Logic.SWITCH;
import static nars.grid2d.Cell.Logic.WIRE;
import static nars.grid2d.Cell.Logic.XOR;

public class Hauto {

    
    boolean is_logic(Cell c){ 
        return (c.logic==OR || c.logic==XOR || c.logic==AND || c.logic==NOT); 
    }
    //put to beginning because we will need this one most often
    public void ExecutionFunction(int t,int i,int j,Cell w,Cell r,Cell left,Cell right,Cell up,
            Cell down,Cell left_up,Cell left_down,Cell right_up,Cell right_down,Cell[][] readcells)
    {
        w.charge=r.charge;
        w.value=r.value;
        w.value2=r.value2;
        w.chargeFront=false;
        //////// WIRE / CURRENT PULSE FLOW /////////////////////////////////////////////////////////////				
        if(r.logic==WIRE)
        {
            if(!r.chargeFront && r.charge==0.0 && NeighborsValue2("op_or", i, j, readcells, "having_charge", 1.0f) != 0) { 
                w.charge = 1.0f;
                w.chargeFront=true;    //it's on the front of the wave of change
            }   
            if(!r.chargeFront && r.charge==1.0 && NeighborsValue2("op_or", i, j, readcells, "having_charge", 0.0f) != 0)
            { 
                w.charge=0.0f;
                w.chargeFront=true;    //it's on the front of the wave of change
            }
            if(r.chargeFront==false && r.charge==0 && (up.logic==SWITCH || down.logic==SWITCH || (left.logic==SWITCH || (is_logic(left) && left.value==1)) || (right.logic==SWITCH || (is_logic(right) && right.value==1))))
            {
                w.charge=1.0f;
                w.chargeFront=true;   //it's on the front of the wave of change
            }
            if(r.chargeFront==false && r.charge==1 && (up.logic==OFFSWITCH || down.logic==OFFSWITCH || (left.logic==OFFSWITCH || (is_logic(left) && left.value==0)) || (right.logic==OFFSWITCH || (is_logic(right) && right.value==0))))
            {
                w.charge=0.0f;
                w.chargeFront=true;    //it's on the front of the wave of change
            }
        }
        //////////// LOGIC ELEMENTS ////////////////////////////////////////////////////////////////////	
        if(r.logic==Cell.Logic.NOT && (up.charge==0 || up.charge==1))
            w.value=(up.charge==0 ? 1 : 0); //eval state from input connection
        if(r.logic==Cell.Logic.NOT && (down.charge==0 || down.charge==1))
            w.value=(down.charge==0 ? 1 : 0); //eval state from input connection
        if(r.logic==Cell.Logic.AND)
            w.value=(up.charge==1 && down.charge==1) ? 1.0f : 0.0f; //eval state from input connections
        if(r.logic==Cell.Logic.OR)
            w.value=(up.charge==1 || down.charge==1) ? 1.0f : 0.0f; //eval state from input connections
        if(r.logic==Cell.Logic.XOR)
            w.value=(up.charge==1 ^ down.charge==1) ? 1.0f : 0.0f;  //eval state from input connections

        //ADD BIDIRECTIONAL LOGIC BRIDGE TO OVERCOME 2D TOPOLOGY
        if(r.logic==BRIDGE)
        {
            if(left.chargeFront && left.logic==WIRE)
                w.value=left.charge;
            else
            if(right.chargeFront && right.logic==WIRE)
                w.value=right.charge;
            
            if(up.chargeFront && up.logic==WIRE)
                w.value2=up.charge;
            else
            if(down.chargeFront && down.logic==WIRE)
                w.value2=down.charge;
        }
        
        if(!r.chargeFront && r.charge==0 && (((right.logic==BRIDGE && right.value==1) || (left.logic==BRIDGE && left.value==1)) || ((down.logic==BRIDGE && down.value2==1) || (up.logic==BRIDGE && up.value2==1))))
        {
            w.charge=1;
            w.chargeFront=true;
        }
        if(!r.chargeFront && r.charge==1 && (((right.logic==BRIDGE && right.value==0) || (left.logic==BRIDGE && left.value==0)) || ((down.logic==BRIDGE && down.value2==0) || (up.logic==BRIDGE && up.value2==0))))
        {
            w.charge=0;
            w.chargeFront=true;
        }
            //w.charge *= w.conductivity;
    }
    
    public void clicked(float x,float y)
    {
        readCells[(int) x][(int) y].charge = selected.charge;
        writeCells[(int) x][(int) y].charge = selected.charge;
        readCells[(int) x][(int) y].logic = selected.logic;
        writeCells[(int) x][(int) y].logic = selected.logic;
        readCells[(int) x][(int) y].material = selected.material;
        writeCells[(int) x][(int) y].material = selected.material;
    }
    
    Cell selected=new Cell();
    
    void click(String label) {
        if(label=="NOT")
        {
            selected.logic=Cell.Logic.NOT;
            selected.charge=0;
        }
        if(label=="AND")
        {
            selected.logic=Cell.Logic.AND;
            selected.charge=0;
        }
        if(label=="OR")
        {
            selected.logic=Cell.Logic.OR;
            selected.charge=0;
        }
        if(label=="XOR")
        {
            selected.logic=Cell.Logic.XOR;
            selected.charge=0;
        }
        if(label=="bridge")
        {
            selected.logic=Cell.Logic.BRIDGE;
            selected.charge=0;
        }
        if(label=="OnWire")
        {
            selected.chargeFront=true;
            selected.charge=1.0f;
            selected.logic=Cell.Logic.WIRE;
        }
        if(label=="OffWire")
        {
            selected.chargeFront=true;
            selected.charge=0.0f;
            selected.logic=Cell.Logic.WIRE;
        }
        if(label=="onswitch")
        {
            selected.charge=1.0f;
            selected.logic=Cell.Logic.SWITCH;
        }
        if(label=="offswitch")
        {
            selected.charge=0.0f;
            selected.logic=Cell.Logic.OFFSWITCH;
        }
    }
    
    public float Neighbor_Value(Cell c,String mode,float data)
    {
        if("having_charge".equals(mode)) {
            if(c.logic!=Cell.Logic.WIRE)
                return -1.0f; //not a charge 
            return c.charge==data ? 1.0f : 0.0f;
        }
        if("just_getcharge".equals(mode)) {
            return c.charge;
        }
        return 0.0f;
    }

    final public static int RIGHT = -9;
    final public static int DOWN = 180;
    final public static int LEFT = 90;
    final public static int UP = 0;
    final public static int UPLEFT = (UP+LEFT)/2;
    final public static int UPRIGHT = (UP+RIGHT)/2;
    final public static int DOWNLEFT = (DOWN+LEFT)/2;
    final public static int DOWNRIGHT = (DOWN+RIGHT)/2;
    
    public int t = 0;
    public Cell[][] readCells; //2D-array(**) of Cell objects(*)
    public Cell[][] writeCells; //2D-array(**) of Cell objects(*)
    public final int w;
    public final int h;
    
    public static int irand(int max) {
        return (int)(Math.random()*max);
    }
    
    
    public Hauto(int w, int h) {
        this.w = w;
        this.h = h;
        readCells = new Cell[w][];
        writeCells = new Cell[w][];
        for (int i = 0; i < w; i++) {
            readCells[i] = new Cell[h];
            writeCells[i] = new Cell[h];
            for (int j = 0; j < h; j++) {
                readCells[i][j] = new Cell();
                writeCells[i][j] = new Cell();
                
                if ((i == 0) || (i == w-1))
                    readCells[i][j].setBoundary();
                else if ((j == 0) || (j == h-1))
                    readCells[i][j].setBoundary();
            }
        }
        
        copyReadToWrite();
    }

    
    public void copyReadToWrite() {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                writeCells[i][j].copyFrom(readCells[i][j]);                
            }
        }
    }

    public void Exec() {
        this.t++;
        for (int i = 1; i < this.w - 1; i++) {
            for (int j = 1; j < this.h - 1; j++) {
                ExecutionFunction(this.t, i, j, this.writeCells[i][j], this.readCells[i][j], this.readCells[i - 1][j], this.readCells[i + 1][j], this.readCells[i][j + 1], this.readCells[i][j - 1], this.readCells[i - 1][j + 1], this.readCells[i - 1][j - 1], this.readCells[i + 1][j + 1], this.readCells[i + 1][j - 1], this.readCells);
            }
        }
        //change write to read and read to write
        Cell[][] temp2 = this.readCells;
        this.readCells = this.writeCells;
        this.writeCells = temp2;
    }

    public Cell FirstNeighbor(int i, int j, Cell[][] readCells, String Condition, float data) {
        int k;
        int l;
        for (k = i - 1; k <= i + 1; k++) {
            for (l = j - 1; l <= j + 1; l++) {
                if (!(k == i && j == l)) {
                    if (Neighbor_Value(readCells[k][l], Condition, data) != 0) {
                        return readCells[k][l];
                    }
                }
            }
        }
        return null;
    }

    public float NeighborsValue(String op, int i, int j, Cell[][] readCells, String Condition, float data) {
        return Op(op, Op(op, Op(op, Op(op, Op(op, Op(op, Op(op, Neighbor_Value(readCells[i + 1][j], Condition, data), Neighbor_Value(readCells[i - 1][j], Condition, data)), Neighbor_Value(readCells[i + 1][j + 1], Condition, data)), Neighbor_Value(readCells[i - 1][j - 1], Condition, data)), Neighbor_Value(readCells[i][j + 1], Condition, data)), Neighbor_Value(readCells[i][j - 1], Condition, data)), Neighbor_Value(readCells[i - 1][j + 1], Condition, data)), Neighbor_Value(readCells[i + 1][j - 1], Condition, data));
    }

    public float NeighborsValue2(String op, int i, int j, Cell[][] readCells, String Condition, float data) {
        return Op(op, Op(op, Op(op, Neighbor_Value(readCells[i + 1][j], Condition, data), Neighbor_Value(readCells[i - 1][j], Condition, data)), Neighbor_Value(readCells[i][j + 1], Condition, data)), Neighbor_Value(readCells[i][j - 1], Condition, data));
    }
    
    public float Op(String op,float a,float b)
    {
        if("op_or".equals(op)) {
            return (a==1.0f || b==1.0f) ? 1.0f : 0.0f;
        }
        if("op_and".equals(op)) {
            return (a==1.0f && b==1.0f) ? 1.0f : 0.0f;
        }
        if("op_max".equals(op)) {
            return (float) Math.max(a, b);
        }
        if("op_min".equals(op)) {
            return (float) Math.min(a, b);
        }
        if("op_plus".equals(op)) {
            return a+b;
        }
        if("op_mul".equals(op)) {
            return a*b;
        }
        return 0.0f;
    }

    public void forEach(int x1, int y1, int x2, int y2, CellFunction c) {
        x1 = Math.max(1, x1);
        x2 = Math.min(w-1, x2);
        x2 = Math.max(x1, x2);
        y1 = Math.max(1, y1);
        y2 = Math.min(h-1, y2);
        y2 = Math.max(y1, y2);
        
        for (int tx = x1; tx < x2; tx++)
           for (int ty = y1; ty < y2; ty++) {
               c.update(readCells[tx][ty]);
           }
        copyReadToWrite();
    }

    public void at(int x, int y, CellFunction c) {
        c.update(readCells[x][y]);
        copyReadToWrite();
    }
    
    public Cell at(int x, int y) {
        return readCells[x][y];
    }
    
    public static class SetMaterial implements CellFunction {
        private final Cell.Material material;

        public SetMaterial(Cell.Material m) {
            this.material = m;
        }
        
        @Override
        public void update(Cell cell) {
            cell.material = material;
            cell.height = (material == Cell.Material.StoneWall) ? 64 : 1;
        }
        
    }    
}