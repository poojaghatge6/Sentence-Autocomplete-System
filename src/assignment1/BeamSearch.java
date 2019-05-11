package assignment1;

import java.util.*;



public class BeamSearch {

	ExtractGraph graph;

	public BeamSearch(ExtractGraph input_graph) {
		graph = input_graph;
	}

	public StringDouble beamSearch(String pre_words, int beamK, int maxToken) {
		//performs level wise traversal(breadth first search) of the graph and selects top k from each level until you reach the goal state.
		boolean goal=false;
		String[] sntc=pre_words.split("\\s+");
		double probability = 1.0;
		for(int i=0;i<sntc.length-1;i++) {
			probability = probability*(graph.getProb(sntc[i], sntc[i+1]));
		}
		StringDouble sd = new StringDouble(pre_words, probability);
		PriorityQueue<StringDouble> topk = new PriorityQueue<StringDouble>( beamK, new Sort1() ); //current level
		PriorityQueue<StringDouble> next_topk = new PriorityQueue<StringDouble>( beamK, new Sort1() ); //next level
		topk.add(sd);
		maxToken = maxToken-sntc.length;
		while(maxToken!=0 ) {
			sd = topk.remove(); 
			sntc = sd.string.split("\\s");
			String word = sntc[sntc.length-1];
			if(graph.graph.containsKey(word)) {
				HashMap<String,Double> hm = graph.graph.get(word);
				for(HashMap.Entry<String,Double> temp: hm.entrySet()) {
					StringBuilder sb = new StringBuilder(sd.string);
					sb.append(" ");
					sb.append(temp.getKey());
					probability = graph.getProb(word, temp.getKey());
					probability = probability*sd.score;
					if(temp.getKey().equals("</s>")) {
						goal = true;
					}
					if(next_topk.size()<beamK) {
						next_topk.add(new StringDouble(sb.toString(),probability));
					} else {
						double minScore = next_topk.peek().score;
						if (probability > minScore ) {
							next_topk.remove(); //deletes the least probability node
							next_topk.add(new StringDouble(sb.toString(),probability));
						}
					}
				}
			}
			if(topk.isEmpty()) {   //make next level as current level and explore the new current level.
				maxToken--;
				if(goal) {
					List<StringDouble> final_list = new ArrayList<StringDouble>(next_topk);
					Sort1 sr1 = new Sort1();
					Collections.sort(final_list,sr1);
					for(int i = final_list.size()-1;i>=0;i--) {
						sd = final_list.get(i);
						if(sd.string.contains("</s>")) return sd;
					}
				}
				topk = next_topk;
				next_topk = new PriorityQueue<StringDouble>( beamK, new Sort1() );
			}
			
		}
		return topk.peek();
	}

}
class Sort1 implements Comparator<StringDouble> {
    
    @Override
    public int compare( StringDouble d1, StringDouble d2) {
        // TODO Auto-generated method stub
        if ( d1.score > d2.score ) return 1;
        if ( d1.score < d2.score ) return -1;
        else                 return 0;
    }
}

