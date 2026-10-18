import java.util.*;

public class Graph {
    private final Map<String, List<String>> adjacencyListMap = new HashMap<>();

    public Graph() {
        adjacencyListMap.put("a", new ArrayList<String>(){{
            add("d");
            add("s");
        }});

        adjacencyListMap.put("b", new ArrayList<String>(){{
            add("d");
            add("e");
            add("s");
        }});

        adjacencyListMap.put("c", new ArrayList<String>(){{
            add("f");
            add("s");
        }});

        adjacencyListMap.put("d", new ArrayList<String>(){{
            add("a");
            add("b");
            add("g");
        }});

        adjacencyListMap.put("e", new ArrayList<String>(){{
            add("b");
            add("g");
            add("h");
            add("i");
        }});

        adjacencyListMap.put("f", new ArrayList<String>(){{
            add("c");
        }});

        adjacencyListMap.put("g", new ArrayList<String>(){{
            add("d");
            add("e");
            add("h");
            add("i");
            add("t");
        }});

        adjacencyListMap.put("h", new ArrayList<String>(){{
            add("e");
            add("g");
            add("i");
            add("t");
        }});

        adjacencyListMap.put("i", new ArrayList<String>(){{
            add("e");
            add("g");
            add("h");
            add("t");
        }});

        adjacencyListMap.put("s", new ArrayList<String>(){{
            add("a");
            add("b");
            add("c");
        }});

        adjacencyListMap.put("t", new ArrayList<>(){{
            add("g");
            add("h");
            add("i");
        }});
    }

    public void BFS(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            System.out.println(node);
            for (String neighbor: adjacencyListMap.get(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public void DFS(String start) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        stack.add(start);
        visited.add(start);

        while (!stack.isEmpty()) {
            String node = stack.pop();
            System.out.println(node);
            for (String neighbor: adjacencyListMap.get(node).reversed()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                }
            }
        }
    }
}
