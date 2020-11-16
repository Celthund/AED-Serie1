package serie1;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

import static java.lang.System.nanoTime;

public class greatestNrOcurrenciesJava {

    public static void main(String[] args) {
        double start_time = nanoTime() * 1e-6;
        if (args.length < 1){
            System.out.println("Missing operation.");
            System.exit(0);
        }

        String operation = args[0];

        if (operation == "exit")
            System.exit(0);

        if (args.length < 2){
            System.out.println("Missing output file.");
            System.exit(0);
        }

        int k = Integer.parseInt(args[1]);

        if (args.length < 3){
            System.out.println("Missing input files.");
            System.exit(0);
        }
        //Stores the input file
        String output_file = args[2];

        if (args.length < 4){
            System.out.println("Missing output files.");
            System.exit(0);
        }
        //Array to store the names of all the files that will be used
        String files[] = new String[args.length - 3];
        for (int i = 3; i < args.length; i++){
            files[i - 3] = args[i];
        }

        //Will store all the input files
        Scanner scanners[] = new Scanner[files.length];

        for (int i = 0; i < files.length; i++) {
            try {

                //Passes the information of the files to the scanners
                scanners[i] = new Scanner(new FileReader(files[i]));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File missing: " + files[i]);
                System.exit(-1);
            }
        }

        //Will write to the output file
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(output_file));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to save: " + output_file);
            System.exit(-2);
        }



        //Switch case to know which tasks the user wants to do
        switch (operation) {
            //Calls the get() method to show the k words that occur more times
            case "get":
                writeToFile(out, get(k, scanners).toArray());
                break;
            case "sort":
                //Calls the sort() method that orders by alphabetic order the k words
                //that occur more times
                writeToFile(out, sort(k, scanners));
                break;
            default:
                System.out.println("No valid operation.");
                //Leaves the program
                System.exit(0);
        }

        //Closes all the files
        out.flush();
        out.close();
        for (int i = 0; i < scanners.length; i++) {
            scanners[i].close();
        }
        double end_time = nanoTime() * 1e-6;
        System.out.printf("%.2f\n", end_time-start_time);
    }

    public static void writeToFile(PrintWriter output, Object[] array){
        for (int i = 0; i < array.length; i++){

            //Writes the words to the output file
            output.println(((Tuple)array[i]).word + " " + ((Tuple)array[i]).counter);
        }
    }

    public static int getMinPosition(String[] words){
        int min_pos = 0;
        for (int i = 0 ; i < words.length; i++){

            //If the current position of the word array doesnt have any word it will just
            //move to the next position
            if (words[i] == null) continue;

            //If there is no current word in the min_pos index, min_pos will
            //take the value of the i
            if (words[min_pos] == null) min_pos = i;

            //Compare to see if the word is alphabetically lower than the current word being checked
            //if it is not, min_pos will take the value of that index
            if (words[min_pos].compareTo(words[i]) > 0) {
                min_pos = i;
            }
        }
        return min_pos;
    }

    public static PriorityQueue<Tuple> get(int k, Scanner scanners[]){
        int counter, min_pos = 0;

        //String to store the words that occur more times
        String words[] = new String[scanners.length];

        //The variable that will be the heap of this method
        PriorityQueue<Tuple> heap = new PriorityQueue<>((x, y) -> Integer.compare(x.counter, y.counter));

        //Current word being checked
        String current_word = "";

        for (int i = 0; i < scanners.length; i++){

            //If there is still now word in the array then the word of the file
            //currently being scanned will become the one with the more occurrences
            if (words[i] == null){

                //If there is no more words in that file it will just move on to the next
                //position of the scanners
                if (scanners[i].hasNextLine())
                    words[i] = scanners[i].nextLine();
                else {
                    continue;
                }


            }

            //If that position already has a word it will compare it, in case the
            //word in the win_pos index is alphabetically greater, min_pos takes the value
            //of the index i
            else {
                assert words[min_pos] != null;
                if (words[i].compareTo(words[min_pos]) < 0){
                    min_pos = i;
                }
            }
        }

        //The current word now is the alphabetically lowest word on the array
        current_word = words[min_pos];
        counter = 1;

        //Cycle that will run trough the file and check for most occurrences of the k words
        while (true) {
            //Checks if the current file has more words
            if (scanners[min_pos].hasNextLine()){

                //Stores that value to the array
                words[min_pos] = scanners[min_pos].nextLine();

                //If the word currently being checked is the same as the word currently being
                //read the counter for that word will increment
                if (current_word.compareTo(words[min_pos]) == 0){
                    counter++;
                } else {

                    //Will check if there is any alphabetically lower words, if there is it will
                    //store in that index in the min_pos
                    min_pos = getMinPosition(words);

                    //If the alphabetically lowest word is different from the current word
                    //being checked it will store it in our Tuple class as a new word to then
                    //check how many times it occurs
                    if (current_word.compareTo(words[min_pos]) == 0)
                        counter++;
                    else if (current_word.compareTo(words[min_pos]) != 0){
                        //Stores the new word in the Tuple class
                        Tuple a = new Tuple(current_word, counter);

                        //Checks to see if the currently heap size is less than k
                        //if it is that means that it has space to store it
                        if (heap.size() < k){
                            heap.add(a);
                        }

                        //If there is no more space, it will check with the root of the heap
                        //if the counter on the current word being check is higher than the one
                        //on the root of the heap that means that the current word occurred more
                        //times so the root of the heap is replaced with that word
                        else if(heap.peek().counter < counter) {
                            heap.poll();
                            heap.add(a);
                        }

                        //the current word will now become the word on the min_pos and the counter
                        //is reset to check for more words
                        current_word = words[min_pos];
                        counter = 1;
                    }
                }
            } else {
                //The word in that position is set to null to check for the next word we will read
                words[min_pos] = null;
                min_pos = getMinPosition(words);
                if (words[min_pos] != null && current_word.equals(words[min_pos]))
                    counter++;

                //If the min position is null it means there are no more elements to count.
                if (words[min_pos] != null)
                    continue;

                //If not that means the it reached the end of all the files it increments the counter
                //on last time to account for the last word being read when it reached the end
                //and adds it to the tuple to then compare if that last words is one of the k
                //words that occurred more times
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

        //Creates a heap of Tuples by calling the get() class
        PriorityQueue<Tuple> heap_by_count = get(k, scanners);

        //Creates another PriorityQueue it will be used to order by alphabetical order
        PriorityQueue<Tuple> heap_by_word = new PriorityQueue<>((x, y) -> x.word.compareTo(y.word));

        //Number of words to order
        int elements = heap_by_count.size();

        //Adds every Tuple in the heap_by_count variable to the heap_by_word and organizes
        //automatically
        for (int i = 0; i < elements; i++){
            heap_by_word.add(heap_by_count.poll());
        }

        //Array that will be send
        Tuple sorted[] = new Tuple[elements];

        //Copys all the Tuples in the heap_by_word heap to the array
        for (int i = 0; i < elements; i++){
            sorted[i] = heap_by_word.poll();
        }
        return sorted;
    }

}
