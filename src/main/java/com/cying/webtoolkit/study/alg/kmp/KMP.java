/**
 * 
 */
package com.cying.webtoolkit.study.alg.kmp;

import java.util.Scanner;

/**
 * @author cying
 *
 */
public class KMP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		String parttern = sc.next();
		String src = sc.next();
		if(isMatch(src,parttern)){
			System.out.println("MATCHED!");
		}else{
			System.out.println("NOT MATCHED!");
		}
	}
    
    private static boolean isMatch(String src, String parttern){

    	//计算next,注意求出的next索引是从1开始的,第0个位置无意义
    	int[] next = next2(parttern.toCharArray());
    	//起始比较位置
    	int srcIndex = 0;
    	int partternIndex = 0;
    	boolean matching =false; //是否处于已经有字符匹配的状态,如果已经有匹配,下次匹配不上的时候会增加srcIndex
    	while(srcIndex<src.length() && partternIndex<parttern.length() ){
    		
    		if(src.charAt(srcIndex) != parttern.charAt(partternIndex)){

    			if(!matching){
    				srcIndex++;
    			}
    			//如果next取值为0,此时必须从头开始匹配,即要重置模式索引
    			if(next[partternIndex] == 0){
    				matching=false;
    				partternIndex = 0;
    			}else{
    				partternIndex = next[partternIndex];
    			}
				continue;
			}
    		//如果当前字符匹配,索引移动一个
    		else{
    			matching=true;
				srcIndex++;
				partternIndex++;
			}
    	}
    	//如果最后的比对已经到最后了,说明匹配成功.最后一次匹配到过后index会多加了个1
    	if(partternIndex == parttern.length()){
    		return true;
    	}
    	return false;
    }
	private static int[] next2(char[] parttern){
		
		int[] next = new int[parttern.length+1];
		next[0]=next[1]=0;
		int j =0;
		for(int i=1; i<parttern.length; i++){
			
			//如果与当前值不匹配，一直往前找
			while(j>0 && parttern[i] != parttern[j]){
				j=next[j];
			}
			//如果是找到了匹配的,而不是j变为0导致上面while退出
			if(parttern[i] == parttern[j]){
				j++; //j由于是索引,而next需要的是长度，所以加1
			}
			//写入值
			next[i+1]=j;
		}
		return next;
	}
	
	/**
	 * @deprecated 错误的写法  正确的请参考 <b>next2</b>
	 *
	 */
	@SuppressWarnings("unused")
	@Deprecated //错误的写法
	private static int[] next(char[] parttern){
		
		int[] next = new int[parttern.length];
		//第一个数肯定为0
		next[0] = 0;
		//如果字符不足2个，不必再继续下去了
		if(parttern.length < 2){
			return next;
		}
		//第二个也手动判断出来,如果相等最大长度为1,否则为0
		if(parttern[0] == parttern[1]){
			next[1]=1;
		}else{
			next[1]=0;
		}
		//后面从索引 2到len(即第三个到最后一个)
		for(int i=2; i<next.length; i++){
			//根据前面的结果判断,如果恰好当前字符匹配
			if(parttern[i] == parttern[next[i-1]+1]){
				next[i] = next[i-1]+1;
				continue;
			}else{
				int j  = i-1;
				while(j>=1){
					//前一次的结果索引
					int preIdx = next[j]-1;
					//前一次如果没有那样的
					if(preIdx <0 ){
						next[i] =0;
						break;
					}
					//如果匹配了,就返回否者继续往回找
					if(parttern[next[preIdx]] == parttern[i]){
						next[i] = next[preIdx]+1;
						break;
					}else{
						j--;
					}
				}
			}
		}
		
		return next;
	}
}
