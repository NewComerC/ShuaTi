package test;

public class Trie {

	private TrieNode root;
	
	private class TrieNode{
		
		private TrieNode[] links;
		
		private boolean end;
		
		private static final int R=26;
		
		public TrieNode() {
			links=new TrieNode[R];
		}
	
		public boolean containsKey(char key) {
			return links[key-'a']!=null;
		}
		
		public void put(char c,TrieNode node) {
			links[c-'a']=node;
		}
		
		public TrieNode get(char c) {
			return links[c-'a'];
		}
		
		public void setEnd() {
			end=true;
		}
		
		public boolean isEnd() {
			return end;
		}
		
		
	}
	
	/** Initialize your data structure here. */
    public Trie() {
        root=new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
    		TrieNode node=root;
        for(int i=0;i<word.length();i++) {
        		char cur=word.charAt(i);
        		if(!node.containsKey(cur)) {
        			node.put(cur, new TrieNode());
        		}
        		node=node.get(cur);
        }
        node.setEnd();
    }
    
    //分离出函数效率提高了。。
    public TrieNode searchPrefix(String word) {
    		TrieNode node=root;
        for(int i=0;i<word.length();i++) {
        		char cur=word.charAt(i);
        		if(node.containsKey(cur)) {
        			node=node.get(cur);
        		}else {
        			return null;
        		}
        }
        return node;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
    		TrieNode node=searchPrefix(word);
    		return node!=null&&node.isEnd();
       
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
    		return searchPrefix(prefix)!=null;
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie trie=new Trie();
		trie.insert("p");
		System.out.println(trie.startsWith("pr"));
		System.out.println(trie.search("p"));
		trie.insert("pr");
		System.out.println(trie.startsWith("pre"));
		System.out.println(trie.search("pr"));
		trie.insert("pre");
		System.out.println(trie.startsWith("pre"));
		System.out.println(trie.search("pre"));
	}

}
