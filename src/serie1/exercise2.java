package serie1;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;


public class exercise2 {

    public static void main(String[] args) {
        String files[] = {"f1.txt", "f2.txt", "f3.txt"};
        String output_file = "f0.txt";
        Scanner scanners[] = new Scanner[files.length];

        for (int i = 0; i < files.length; i++) {
            try {
                scanners[i] = new Scanner(new FileReader(files[i]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File missing: " + files[i]);
                System.exit(-1);
            }
        }

        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(output_file));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to save: " + output_file);
            System.exit(-2);
        }

        int oper = 1, k = 10;

        switch (oper) {
            case 0:
                writeToFile(out, get(k, scanners).toArray());
                break;
            case 1:
                writeToFile(out, sort(k, scanners));
                break;
            default:
                System.exit(-1);
        }
        out.flush();
        out.close();
        for (int i = 0; i < scanners.length; i++) {
            scanners[i].close();
        }
    }

    public static void writeToFile(PrintWriter output, Object[] array){
        for (int i = 0; i < array.length; i++){
            output.println(((Tuple)array[i]).word + " " + ((Tuple)array[i]).counter);
        }
    }

    public static int getMinPosition(String[] words){
        int min_pos = 0;
        for (int i = 0 ; i < words.length; i++){
            if (words[i] == null) continue;
            if (words[min_pos] == null) min_pos = i;
            if (words[min_pos].compareTo(words[i]) > 0) {
                min_pos = i;
            }
        }
        return min_pos;
    }

    public static PriorityQueue<Tuple> get(int k, Scanner scanners[]){
        int counter, min_pos = 0;
        boolean flag;
        String words[] = new String[scanners.length];
        PriorityQueue<Tuple> heap = new PriorityQueue<>((x, y) -> Integer.compare(x.counter, y.counter));
        String current_word = "";
        for (int i = 0; i < scanners.length; i++){
            if (words[i] == null){
                if (scanners[i].hasNextLine())
                    words[i] = scanners[i].nextLine();
                else {
                    continue;
                }
            } else {
                assert words[min_pos] != null;
                if (words[i].compareTo(words[min_pos]) < 0){
                    min_pos = i;
                }
            }
        }
        current_word = words[min_pos];
        counter = 1;
        while (true) {
            if (scanners[min_pos].hasNextLine()){
                words[min_pos] = scanners[min_pos].nextLine();
                if (current_word.compareTo(words[min_pos]) == 0){
                    counter++;
                } else {
                    min_pos = getMinPosition(words);
                    if (current_word.compareTo(words[min_pos]) != 0){
                        Tuple a = new Tuple(current_word, counter);
                        if (heap.size() < k){
                            heap.add(a);
                        } else if(heap.peek().counter < counter) {
                            heap.poll();
                            heap.add(a);
                        }
                        current_word = words[min_pos];
                        counter = 1;
                    }
                }
            } else {
                words[min_pos] = null;
                min_pos = getMinPosition(words);
                flag = false;
                for (int i = 0; i < scanners.length; i++){
                    if (scanners[i].hasNextLine()){
                        flag = true;
                        break;
                    }
                }
                if (flag)
                    continue;
                counter++;
                Tuple a = new Tuple(current_word, counter);
                if (heap.size() < k){
                    heap.add(a);
                } else if(heap.peek().counter < counter) {
                    heap.poll();
                    heap.add(a);
                }
                break;
            }
        }
        return heap;
    }

    public static Tuple[] sort(int k, Scanner scanners[]){
        PriorityQueue<Tuple> heap_by_count = get(k, scanners);
        PriorityQueue<Tuple> heap_by_word = new PriorityQueue<>((x, y) -> x.word.compareTo(y.word));
        int elements = heap_by_count.size();
        for (int i = 0; i < elements; i++){
            heap_by_word.add(heap_by_count.poll());
        }
        Tuple sorted[] = new Tuple[elements];
        for (int i = 0; i < elements; i++){
            sorted[i] = heap_by_word.poll();
        }
        return sorted;
    }

}
