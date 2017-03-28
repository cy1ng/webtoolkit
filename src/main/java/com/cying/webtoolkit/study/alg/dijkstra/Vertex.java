package com.cying.webtoolkit.study.alg.dijkstra;

import java.util.List;

public class Vertex implements Comparable<Vertex>{

    /**
     * 节点名称(A,B,C,D)
     */
    private String name;
    
    /**
     * 起点到该节点的最短路径长度
     */
    private int distance;
    
    /**
     * 起点到该节点的最短路径序列
     */
    private List<Vertex>shortcut;
    
    /**
     * 节点是否已经出列(是否已经处理完毕)
     */
    private boolean isMarked;
    
    public Vertex(String name){
        this.setName(name);
        this.distance = 1000; //初始设置为无穷大
        this.setMarked(false);
    }
    
    public Vertex(String name, int distance){
        this.setName(name);
        this.distance = distance;
        this.setMarked(false);
    }
    
    @Override
    public int compareTo(Vertex o) {
        return o.distance > distance?-1:1;
    }

	public boolean isMarked() {
		return isMarked;
	}

	public void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public List<Vertex> getShortcut() {
		return shortcut;
	}

	public void setShortcut(List<Vertex> shortcut) {
		this.shortcut = shortcut;
	}

    
	
}