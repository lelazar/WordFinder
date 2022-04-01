import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a pool of letters: ");
        String letters = scanner.nextLine().toUpperCase(Locale.ROOT);

        Map<Character, Integer> letters_count_map = getCharacterCountMap(letters);

        BufferedReader reader = new BufferedReader(new FileReader("src/dictionary.txt"));

        System.out.println("All possible words you can make of those letters:");

        for(String current_word=reader.readLine(); current_word!=null; current_word= reader.readLine()) {
            //System.out.println(current_word);
            Map<Character, Integer> current_word_map = getCharacterCountMap(current_word);
            boolean can_make_current_word = true;
            // keySet() is a set of keys in a Map, in this case, characters in oru dictionary file
            for(Character character : current_word_map.keySet()) {
                int current_word_char_count = current_word_map.get(character);
                int letters_char_count = letters_count_map.containsKey(character) ? letters_count_map.get(character) : 0;

                // If our current word needs more of the letter_char_count available in our pool, we have to break out of the loop
                // It means, we can make words of these letters
                if(current_word_char_count > letters_char_count) {
                    can_make_current_word = false;
                    break;
                }
            }
            if(can_make_current_word) {
                System.out.println(current_word);
            }
        }

        scanner.close();
        reader.close();
    }

    private static Map<Character, Integer> getCharacterCountMap(String letters) {
        Map<Character, Integer> letters_count_map = new HashMap<>();
        // Looping through each character in the letters String
        for(int i = 0; i< letters.length(); i++) {
            char current_char = letters.charAt(i);  // Getting the current character
            int count = letters_count_map.containsKey(current_char) ? letters_count_map.get(current_char) : 0;  // Does it have the letter? If yes, get that character, if not, it is 0
            // Set the current count to one more to the value that was before by putting one new entry on our Map
            // In example, if we have an 'a' character, we put it in the Map and add +1 to it
            // And getting back to the count integer, where we are checking if there is an 'a' and if yes, getting that current count
            letters_count_map.put(current_char, count + 1);
        }

        return letters_count_map;
    }
}
