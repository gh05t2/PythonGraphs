import java.util.*;
 
public class Dijkstra {
   private static final Graph.Dam[] GRAPH = { 
      new Graph.Dam("Barcelona", "Logroño", 398),
      new Graph.Dam("Barcelona", "Madrid", 168),
      new Graph.Dam("Barcelona", "Cuenca", 474),
      new Graph.Dam("Logroño", "Madrid", 438),
      new Graph.Dam("Logroño", "Sevilla", 970),
      new Graph.Dam("Madrid", "Sevilla", 314),
      new Graph.Dam("Madrid", "Cuenca", 227),
      new Graph.Dam("Sevilla", "Murcia", 268),
      new Graph.Dam("Murcia", "Cuenca", 120),
      new Graph.Dam("Cuenca","Barcelona",540),
      new Graph.Dam("Barcelona","URSS",9001),
   };
 
public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      Graph g = new Graph(GRAPH);
      System.out.println("Introduce el lugar de partida: ");
      g.dijkstra(sc.nextLine());
      System.out.println("");
      System.out.println("Introduce el destino: ");
      g.printPath(sc.nextLine());
      
  
   }
}

class Graph {
   private final Map<String, Vertice> graph; // mapping of vertex names to Vertex objects, built from a set of Edges
 
   /** One edge of the graph (only used by Graph constructor) */
   public static class Dam {
      public final String v1, v2;
      public final int dist;
      public Dam(String v1, String v2, int dist) {
         this.v1 = v1;
         this.v2 = v2;
         this.dist = dist;
      }
   }
 
 
   /** 
   * Crea una clase Vértice que implementa la clase Comparable,
   * que compara un objeto vertice con otro objeto vertice adyacente
   * en un mapa virtual
   */
  public static class Vertice implements Comparable<Vertice>{
	public final String name;
	public int dist = Integer.MAX_VALUE; 
	public Vertice previous = null;
	public final Map<Vertice, Integer> neighbours = new HashMap<>();
 
	public Vertice(String name)
	{
		//Está guardando en la variable global de la clase el valor del objeto que se le da al crearlo
		this.name = name; //this. es el global que lo igualas a la variable por teclado. Es un objeto.
	}
  /** Comprueba si la ubicacion elegida existe */
	private void printPath()
	{      
		if (this == this.previous)
		{
			System.out.printf("%n%s :", this.name);
		}
		else if (this.previous == null)
		{
			System.out.printf("%s(No se ha elegido un punto de partida válido)", this.name);
		}
		else
		{
			this.previous.printPath();
                        
			System.out.printf("%n- Está a %dKm de %s.", this.dist, this.name);
                        
		}
	}
 /**  @Override permite modificar otros metodos. Con el siguiente método comparamos la distancia entre dos puntos*/
        @Override public int compareTo(Vertice other)
	{
		if (dist == other.dist)
			return name.compareTo(other.name);
 
		return Integer.compare(dist, other.dist);
	}
 
	@Override public String toString()
	{
		return "(" + name + ", " + dist + ")";
	}
}
 
   /** Crea un grafo a partir de un conjunto de bordes */
   public Graph (Dam[] edges) {
      graph = new HashMap<>(edges.length);
 
      /** Crea un nuevo mapa recorriendo todos los vertices*/
      for (Dam Murcia : edges) {
         if (!graph.containsKey(Murcia.v1)) graph.put(Murcia.v1, new Vertice(Murcia.v1));
         if (!graph.containsKey(Murcia.v2)) graph.put(Murcia.v2, new Vertice(Murcia.v2));
      }
 
      //Otra paso para colocar los vertices
      for (Dam Murcia : edges) {
         graph.get(Murcia.v1).neighbours.put(graph.get(Murcia.v2), Murcia.dist);
         //Con esto conseguimos que no sea unidireccional
      }
   }
 
   /** Ejecuta Dijkstra usando vértice origen */ 
   public void dijkstra(String startName) {
      if (!graph.containsKey(startName)) {
         System.err.printf("La ciudad \"%s\"\n no está recogida en el grafo", startName);
         return;
      }
      final Vertice source = graph.get(startName);
      NavigableSet<Vertice> q = new TreeSet<>();
 
      // Coloca los vertices
      for (Vertice v : graph.values()) {
         v.previous = v == source ? source : null;
         v.dist = v == source ? 0 : Integer.MAX_VALUE;
         q.add(v);
      }
 
      dijkstra(q);
   }
 
   /** Implementa el algoritmo de Dijkstra */
   private void dijkstra(final NavigableSet<Vertice> q) {      
      Vertice u, v;
      while (!q.isEmpty()) {
 
         u = q.pollFirst(); // Vertice con la menor distancia (La primera interacción se devolverá)
         if (u.dist == Integer.MAX_VALUE) break; // ignoramos los vertices inalcanzables
 
         //Comprueba la distancia entre los vertices vecinos
         for (Map.Entry<Vertice, Integer> Barcelona : u.neighbours.entrySet()) {
            v = Barcelona.getKey(); 
 
            final int alternateDist = u.dist + Barcelona.getValue();
            if (alternateDist < v.dist) {
               q.remove(v);
               v.dist = alternateDist;
               v.previous = u;
               q.add(v);
            } 
         }
      }
   }
 
   /**Imprime en pantalla una ruta desde el origen hasta el vértice especificado */
   public void printPath(String endName) {
      if (!graph.containsKey(endName)) {
         System.err.printf("La ciudad \"%s\"\n no está recogida en el grafo", endName);
         return;
      }
 
      graph.get(endName).printPath();
      System.out.println();
   }
   /** Imprime la ruta desde el origen a cada vértice */
   public void printAllPaths() {
      for (Vertice v : graph.values()) {
         v.printPath();
         System.out.println();
      }
   }
}
}
