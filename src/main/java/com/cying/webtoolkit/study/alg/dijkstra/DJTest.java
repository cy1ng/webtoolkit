/**
 * 
 */
package com.cying.webtoolkit.study.alg.dijkstra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author cying
 *
 */
public class DJTest {
   
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int start = 5;
		int end = sc.nextInt();
		int cityUnreach = sc.nextInt();
		int[][] table = init();
		if (cityUnreach != 0){
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					table[cityUnreach - 1][j] = 1000;
					table[i][cityUnreach - 1] = 1000;
				}

			}
		}
			
		List<Vertex> vertexs = new ArrayList<Vertex>();
        Vertex a = new Vertex("1");
        Vertex b = new Vertex("2");
        Vertex c = new Vertex("3");
        Vertex d = new Vertex("4");
        Vertex e = new Vertex("5",0);
        Vertex f = new Vertex("6");
        vertexs.add(a);
        vertexs.add(b);
        vertexs.add(c);
        vertexs.add(d);
        vertexs.add(e);
        vertexs.add(f);
        Graph graph = new Graph(e, vertexs, table);
        graph.printGraph();
        graph.search();
        System.out.println(a);
      
	}
	
	
	static int[][] init() {

		int[][] m = { { 0, 2, 10, 5, 3, 1000 }, { 1000, 0, 12, 1000, 1000, 10 }, { 1000, 1000, 0, 1000, 7, 1000 },
				{ 2, 1000, 1000, 0, 2, 1000 }, { 4, 1000, 1000, 1, 0, 1000 }, { 3, 1000, 1, 1000, 2, 0 }, };
		return m;
	}

}
