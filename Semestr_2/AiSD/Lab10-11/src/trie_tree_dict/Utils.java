package trie_tree_dict;

class Utils {
    public static final int ASCII_SIZE = 128;

    public static int getASCII(char c) {
        return (int) c;
    }

    public static char intToASCII(int i) {
        if (i > ASCII_SIZE || i < 0) {
            throw new IllegalArgumentException("Invalid ASCII");
        }

        return (char) i;
    }
}
