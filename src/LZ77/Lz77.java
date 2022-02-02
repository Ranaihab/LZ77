//Rana Ihab Ahmed Fahmy
//ID: 20190207
//CS-2


package LZ77;
import java.util.ArrayList;
import java.util.Scanner;

public class Lz77 {
    private int searchWindow;
    private int lookAhead;
    private ArrayList<Tag> tags;

    public Lz77(int x, int y){
        searchWindow = x;
        lookAhead = y;
        tags = new ArrayList<>();
    }

    // search if the given characters are in the text or not
    private int searchText(String symbol, String text){
        int len = symbol.length();
        for(int i = text.length() - len; i>=0; i--){
            if (text.charAt(i) == symbol.charAt(0)){
                String t ="";
                for(int k = 0; k<len; k++){
                    t+=text.charAt(i+k);
                }
                if(symbol.equals(t))
                    return i;
            }
        }
        return -1;
    }

    private void addTags(int pos, int len, ArrayList<Character> next, ArrayList<Integer> p, ArrayList<Integer> l){
        ArrayList<Integer> ps = new ArrayList<>();
        ArrayList<Integer> ln = new ArrayList<>();
        while(pos != 0 ){
            ps.add(pos%2);
            pos = pos>>> 1;
        }

        while(len != 0 ){
            ln.add(len%2);
            len = len>>> 1;
        }
        for(int i =0; i<next.size(); i++){
            Tag tag=new Tag(ps.size(), ln.size());
            tag.setLength(l.get(i));
            tag.setNextCharacter(next.get(i));
            tag.setPosition(p.get(i));
            tags.add(tag);
        }
        System.out.println("Tags:");
        for(int i = 0; i<tags.size();i++){
            System.out.println(tags.get(i));
        }

    }

    public void compress(){
        Scanner in = new Scanner(System.in);
        System.out.print("Text to compress: ");
        String text =  in.nextLine();

        ArrayList<Character> next = new ArrayList<>();
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> length= new ArrayList<>();
        int i =0;

        while(i < text.length()){
            int len = i-searchWindow;
            if(len < 0)
                len = 0;

            String searchT = "";
            searchT += text.charAt(i);
            int index = searchText(searchT, text.substring(len, i));
            if(index == -1 || i==text.length() - 1){
                pos.add(0);
                length.add(0);
                next.add(text.charAt(i));
                i++;
            }
            else{
                for(int j = 1; j<lookAhead && j+i<text.length(); j++){

                    int newIndex = searchText(searchT+text.charAt(i+j), text.substring(len, i+j));
                    if(newIndex==-1 || j==lookAhead-1 || j+i==text.length()-1){
                        pos.add(i-len-index);
                        length.add(searchT.length());
                        next.add(text.charAt(i+j));
                        i+=searchT.length() + 1;

                        break;
                    }

                    index = newIndex;
                    searchT = searchT+text.charAt(i+j);
                }

            }


        }

        int maxP=0;
        int maxL=0;
        for(int j =0;j<pos.size();j++){
            if(maxP<pos.get(j)){
                maxP= pos.get(j);
            }
            if(maxL<length.get(j)){
                maxL= length.get(j);
            }
        }
        addTags(maxP, maxL, next, pos, length);
    }

    public void decompress(){
        String text="";
        for(int i = 0; i<tags.size();i++){
            int pos = tags.get(i).getPosition();
            int len = tags.get(i).getlength();
            int textLen = text.length();
            for(int j = 0; j<len; j++){
                text+=text.charAt(textLen-pos+j);
            }
            text+=tags.get(i).getNextCharacter();
        }
        System.out.println(text);
    }
}
