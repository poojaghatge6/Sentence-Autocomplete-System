package assignment1;

import java.util.HashMap;


import java.io.*;
public class ExtractGraph {

	// String key is head word; and HashMap<String, Double> value stores the next words
	// and corresponding probability.
	public HashMap<String, HashMap<String, Double>> graph;
	public BufferedReader br;
	public String sentences_add = "data\\assign1_sentences.txt";

	public ExtractGraph() throws IOException{
		// Extract the directed weighted graph, and save to HashMap<String,
		// HashMap<String, Double>> graph.
		graph=new HashMap<String, HashMap<String, Double>>(); //used to store the directed graph edges node and their children.
		HashMap<String, Double> next;
		String head = null;
		double freq = 0.0;
		br=new BufferedReader(new InputStreamReader(new FileInputStream(sentences_add)));
		String sentence=null;
		while( (sentence = br.readLine()) != null) {
			String[] words = sentence.split("\\s+"); // split on white spaces.
			for(String word:words) {
//				if( !word.equals(",") && !word.equals(".")) {  /*unsure weather to remove Punctuation or not*/
					
					if(graph.containsKey(head)) {
						next = graph.get(head);	
						if(next.containsKey(word)) {
							freq = next.get(word);
							next.replace(word,freq+1.0);
						}
						else {
							next.put(word,1.0);
						}
						graph.replace(head, next);
					}
					else if(head != null){   //head null indicates that <s> is not yet inserted in the graph.
						next = new HashMap<String,Double>();
						next.put(word,1.0);
						graph.put(head,next);
					}
					head=word;
				}
//			}
		}
	}

	public double getProb(String head_word, String next_word) {
		double result = 0.0;
		double sum = 0.0;
		if(graph.containsKey(head_word)) {
			HashMap<String, Double> next = graph.get(head_word);
			if(next.containsKey(next_word) && next!=null) {
					for (HashMap.Entry<String,Double> i : next.entrySet()) { 
			         	   sum = sum+ i.getValue();
					}
					result= next.get(next_word)/sum ;
			} else {
				result = 0.0;
			}
			return result;
		}
		else	return 0.0;
	}

}
