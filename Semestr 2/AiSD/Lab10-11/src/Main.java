import trie_tree_dict.TrieDictionary;

public class Main {
    public static void main(String[] args) {
        TrieDictionary<Integer> dict = new TrieDictionary<>();
        dict.insert("abc", 10);
        dict.insert("abcd", 11);
        dict.insert("ab", 12);
        dict.insert("abe", 13);
        dict.insert("b", 13);
        dict.insert("", 14);

        System.out.println(dict.remove("abc"));
        System.out.println(dict.remove("abcd"));
        System.out.println(dict.remove("a"));

        System.out.println(dict.search(""));
        System.out.println(dict.remove(""));
    }
}