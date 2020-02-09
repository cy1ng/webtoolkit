package com.cying.webtoolkit.study.letcode.string;

import java.util.*;

class Q1258 {

    public static void main(String[] args) {

        /**
         * 输入：
         * synonyms = [["happy","joy"],["sad","sorrow"],["joy","cheerful"]],
         * text = "I am happy today but was sad yesterday"
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/synonymous-sentences
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
        List<List<String>> synonyms = new ArrayList<List<String>>() {
            {
                add(Arrays.asList("happy", "joy"));
                add(Arrays.asList("sad", "sorrow"));
                add(Arrays.asList("joy", "cheerful"));
            }
        };
        Q1258 q1258 = new Q1258();
        List<String> res = q1258.generateSentences(synonyms, "I am happy today but was sad yesterday");

    }

    private Map<String,String> parent = new HashMap<>();

    private Map<String, Set<String>> relMap = new HashMap<>();

    private TreeSet<String> result = new TreeSet<String>();

    public List<String> generateSentences(List<List<String>> synonyms, String text) {

        for(List<String> synonym : synonyms){
            String s1 = findParent(synonym.get(0));
            String s2 = findParent(synonym.get(1));
            // 建立两个关系网的关联
            parent.put(s1,s2);
        }
        // 再循环一遍,防止先后关系的缘故导致关联缺失。同时构建关系map
        for(List<String> synonym : synonyms){
            String s1 = synonym.get(0);
            String s2 = synonym.get(1);
            // 找到s1的根(与s2的根肯定一样)
            String p = findParent(s1);
            relMap.putIfAbsent(p,new HashSet<String>());
            relMap.get(p).add(s1);
            relMap.get(p).add(s2);
        }
        // 使用深度优先,遍历所有情况
        String[] strArray = text.split(" ");
        dfs(strArray,0,"");
        return new ArrayList<>(result);
    }

    private void dfs(String[] strArray, int index, String currText){

        // 先判断递归是否结束
        if(index == strArray.length ){
            result.add(currText.trim());
            return ;
        }
        // 先查出是否有根,如果有说明有近义词
        String root = parent.get(strArray[index]);
        if(root == null){
            dfs(strArray,index+1,currText+" "+strArray[index]);
        }else{
            Set<String> words = relMap.get(root);
            for(String word : words){
                dfs(strArray,index+1,currText+" "+word);
            }
        }

    }

    private String findParent(String s){
        if(!parent.containsKey(s)){
            parent.put(s,s);
        }
        String root = parent.get(s);
        if(root != s){
            parent.put(s,findParent(root));
        }
        return parent.get(s);
    }
}
