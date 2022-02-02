//Rana Ihab Ahmed Fahmy
//ID: 20190207
//CS-2

package LZ77;

import java.util.BitSet;

public class Tag {
    private BitSet position;
    private Character nextCharacter;
    private BitSet length;

    //constructor
    public Tag(int p, int l){
        position = new BitSet(p);
        length = new BitSet(l);
    }

    //convert position value from decimal to binary and store it as bits
    public void setPosition(int num){
        int i = 0;
        while(num != 0 ){
            if(num % 2 != 0){
                position.set(i);
            }
            i++;
            num = num >>> 1;
        }
    }

    public void setNextCharacter(Character c){
        nextCharacter = c;
    }

    //convert length value from decimal to binary and store it as bits
    public void setLength(int num){
        int i = 0;
        while(num != 0 ){
            if(num % 2 != 0){
                length.set(i);
            }
            i++;
            num = num >>> 1;
        }
    }

    //convert position from binary to decimal and return it
    public int getPosition(){
        int val = 0;
        for(int i = 0; i< position.length(); i++){
            if(position.get(i)) {
                val += (1 << i);
            }
        }
        return val;
    }

    public Character getNextCharacter(){
        return nextCharacter;
    }

    //convert length from binary to decimal and return it
    public int getlength(){
        int val = 0;
        for(int i = 0; i< length.length(); i++){
            if(length.get(i)) {
                val += (1 << i);
            }
        }
        return val;
    }

    @Override
    public String toString(){
        return "<" + this.getPosition() + ", " +this.getlength()+", "+this.getNextCharacter()+">";
    }
}
