//Rana Ihab Ahmed Fahmy
//ID: 20190207
//CS-2

package LZ77;

public class Main {
    public static void main(String[] args){
        Lz77 lz =new Lz77(21, 21);

        lz.compress();
        System.out.println();
        System.out.print("Text after decompression: ");
        lz.decompress();
    }
}
