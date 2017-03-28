/**
 * 
 */
package com.cying.webtoolkit.study.alg.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author cying
 *
 */
public class Graph {
    /*
     * 顶点
     */
    private List<Vertex> vertexs;

    /*
     * 边
     */
    private int[][] edges;
    
    /**
     * 起始点
     */
    private Vertex startVertex;

    private Set<Vertex>unMarkedSet; //= HashSet<Vertex>();
    private Set<Vertex>markedSet; 
    
    private void init(){
//    	unMarkedSet = new HashSet<Vertex>(vertexs.size());
//    	markedSet = new HashSet<Vertex>(vertexs.size());
//    	markedSet.add(startVertex); 
    	
    }
    
    public Graph(Vertex startVertex,List<Vertex> vertexs,int[][] edges){
    	this.vertexs = vertexs;
    	this.edges = edges;
    	this.startVertex =startVertex;
    }
    
    //计算从起始节点到其他节点的最短路径
   public void search(){
    	//结果路径
//    	List<Vertex> resultPath = new ArrayList<Vertex>(vertexs.size());
    	//起始节点设置为已经处理
    	startVertex.setMarked(true);
    	markedSet = new HashSet<Vertex>(vertexs.size());
    	markedSet.add(startVertex);
    	//起始节点为距离为0
    	startVertex.setDistance(0);
    	//设置最短路径为本身
    	startVertex.setShortcut(Arrays.asList(startVertex));
    	//当前处理节点
    	Vertex current = startVertex;
    	int currentIndex = vertexs.indexOf(current);
    	//当前
    	while(markedSet.size() != vertexs.size()){
    		Vertex nearestNeibor = getNearestNeighborNotMarked(current, currentIndex);
    		int shortestDistance = Integer.MAX_VALUE;
//    		int shortestIndex = -1;
    		int col = vertexs.indexOf(nearestNeibor);
    		for(Vertex markedVtx : markedSet){
    			int row = vertexs.indexOf(markedVtx);    			
    			int distance = markedVtx.getDistance()+edges[row][col];
    			if(distance < shortestDistance){
    				shortestDistance = distance;
//    				shortestIndex = row;
    			}
    		}
    		//最短的那条路径是基于哪个已标记节点的
    		Vertex resolved = vertexs.get(col);
    		//更新距离
    		resolved.setDistance(shortestDistance);
    		//更新标记
    		resolved.setMarked(true);
    		//记录路径
    		List<Vertex> shortcut = new ArrayList<Vertex>(current.getShortcut());
    		shortcut.add(resolved);
    		resolved.setShortcut(shortcut);
    		//加入到集合中;
    		markedSet.add(resolved);
    		//设置当前
    		current = resolved;
    		
    	}
    	
    }
    
    /**
     * 找到该节点的最近未处理邻居
     * @param vtx
     * @param vtxIndex
     * @return
     */
    private Vertex getNearestNeighborNotMarked(Vertex vtx, int vtxIndex){
    	
    	Vertex nearest = null;
    	int nearestDistance = Integer.MAX_VALUE;
    	//获取vtx最近的未处理邻居节点
    	for(int i=0; i< vertexs.size(); i++){
    		//筛选未处理的,并且距离比较小的
    		if(!vertexs.get(i).isMarked() && edges[vtxIndex][i] < nearestDistance){ //TODO 距离一样的时候怎么处理
    			nearest = vertexs.get(i);
    		}
    	}
    	return nearest;
    }
	public List<Vertex> getVertexs() {
		return vertexs;
	}

	public void setVertexs(List<Vertex> vertexs) {
		this.vertexs = vertexs;
	}

	public int[][] getEdges() {
		return edges;
	}

	public void setEdges(int[][] edges) {
		this.edges = edges;
	}
	  /*
     * 打印图
     */
    public void printGraph() {
        int verNums = vertexs.size();
        for (int row = 0; row < verNums; row++) {
            for (int col = 0; col < verNums; col++) {
                if(Integer.MAX_VALUE == edges[row][col]){
                    System.out.print("X");
                    System.out.print(" ");
                    continue;
                }
                System.out.print(edges[row][col]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
