package lanqiao12;

import java.util.*;

public class t05最短路径2 {
    public static void main(String[] args) {
        new t05最短路径2().run();
    }

    int N = 2021;

    void run() {
        List<Edge>[] graph = new List[N + 1];
        long[] visited = new long[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList();
        for (int v = 1; v < N; v++)
            for (int w = v + 1; w <= min(v + 21, N); w++) {
                graph[v].add(new Edge(w, lcm(v, w)));
                graph[w].add(new Edge(v, lcm(v, w)));
            }
        Queue<Vertex> queue = new PriorityQueue();
        Arrays.fill(visited, Long.MAX_VALUE);
        queue.offer(new Vertex(1, 0));
        Vertex V = null;
        while (queue.size() > 0) {
            V = queue.poll();
            if (V.v == N)
                break;
            if (V.weight >= visited[V.v])
                continue;
            visited[V.v] = V.weight;
            for (Edge edge : graph[V.v])
                queue.offer(new Vertex(edge.w, edge.weight + V.weight));
        }
        System.out.println(V.weight); // 10266837
    }

    int min(int a, int b) {
        return a < b ? a : b;
    }

    int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    class Edge {
        int w, weight;

        Edge(int w, int weight) {
            this.weight = weight;
            this.w = w;
        }
    }

    class Vertex implements Comparable<Vertex> {
        int v;
        long weight;

        Vertex(int v, long weight) {
            this.weight = weight;
            this.v = v;
        }

        @Override
        public int compareTo(Vertex V) {
            return Long.compare(this.weight, V.weight);
        }
    }

}
