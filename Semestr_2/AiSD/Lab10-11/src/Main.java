import trie_tree_dict.TrieDictionary;

public class Main {
    public static void main(String[] args) {
        TrieDictionary<Integer> dict = new TrieDictionary<>();
        dict.insert("a", 1);
        dict.insert("abcc", 10);
        dict.insert("abcdef", 2);
        dict.insert("bcdefghi", 3);
        dict.insert("bcdefghijklm", 4);
        dict.insert("aaaaaa", 20);

        dict.remove("bcdefghi");

        System.out.println(dict.search("abcdef"));
        System.out.println(dict.search("abcc"));
        System.out.println(dict.longestEmptyPath());
    }
}