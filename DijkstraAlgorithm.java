import java.util.*;

public class DijkstraAlgorithm {
    public static class Edge{
        int target;
        int weight;

        public Edge(int target, int weight){
            this.target = target;
            this.weight = weight;
        }
    }

    public static int dijkstra(Map<Integer, List<Edge>> graph, int start, int end){
        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            return -1;
        }

        Map<Integer, Integer> distances = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> queue = new ArrayList<>();

        List<Integer> keys = new ArrayList<>(graph.keySet());
        for(int i = 0; i < keys.size(); i++){
            int node = keys.get(i);
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        queue.add(start);

        while(!queue.isEmpty()){
            int minNode = -1;
            int minDistance = Integer.MAX_VALUE;

            for(int i = 0; i < queue.size(); i++){
                int node = queue.get(i);
                if (!visited.contains(node) && distances.get(node) < minDistance) {
                    minDistance = distances.get(node);
                    minNode = node;
                }
            }

            if (minNode == -1) {
                break;
            }

            queue.remove((Integer) minNode);
            visited.add(minNode);

            if (minNode == end) {
                return distances.get(minNode);
            }

            List<Edge> neighbors = graph.get(minNode);
            if (neighbors == null) {
                continue;
            }

            for(int i = 0; i < neighbors.size(); i++){
                Edge edge = neighbors.get(i);
                int neighbor = edge.target;
                int newDist = distances.get(minNode) + edge.weight;

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);

                    if(!visited.contains(neighbor)){
                        queue.add(neighbor);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Map<Integer, List<Edge>> graph = new HashMap<>();

        List<Edge> edges1 = new ArrayList<>();
        edges1.add(new Edge(2, 1));
        edges1.add(new Edge(3, 1));
        edges1.add(new Edge(5, 1));
        graph.put(1, edges1);

        List<Edge> edges2 = new ArrayList<>();
        edges2.add(new Edge(4, 1));
        graph.put(2, edges2);

        List<Edge> edges3 = new ArrayList<>();
        edges3.add(new Edge(5, 1));
        graph.put(3, edges3);

        List<Edge> edges4 = new ArrayList<>();
        edges4.add(new Edge(3, 1));
        graph.put(4, edges4);

        graph.put(5, new ArrayList<>());

        System.out.println(dijkstra(graph, 1, 2));
        System.out.println(dijkstra(graph, 1, 5));
        System.out.println(dijkstra(graph, 2, 5));
        System.out.println(dijkstra(graph, 5, 1));
        System.out.println(dijkstra(graph, 2, 1));
    }
}
