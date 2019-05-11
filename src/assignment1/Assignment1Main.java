package assignment1;

public class Assignment1Main {

	public static void main(String[] args) throws Exception {
		// Extract graph from assign1_sentences.txt.
		ExtractGraph graph = new ExtractGraph();
		// Test extraction correctness.
		String head_word = "<s>";
		String tail_word = "Water";
		System.out.println("The probability of \"" + tail_word + "\" appearing after \"" + head_word + "\" is "
				+ graph.getProb(head_word, tail_word));
		head_word = "Water";
		tail_word = "<s>";
		System.out.println("The probability of \"" + tail_word + "\" appearing after \"" + head_word + "\" is "
				+ graph.getProb(head_word, tail_word));
		head_word = "planned";
		tail_word = "economy";
		System.out.println("The probability of \"" + tail_word + "\" appearing after \"" + head_word + "\" is "
				+ graph.getProb(head_word, tail_word));
		head_word = ".";
		tail_word = "</s>";
		System.out.println("The probability of \"" + tail_word + "\" appearing after \"" + head_word + "\" is "
				+ graph.getProb(head_word, tail_word));

		// Find the sentence with highest probability.
		BeamSearch beam_search = new BeamSearch(graph);
		StringDouble sentence_prob = beam_search.beamSearch("<s>", 10, 20);
		System.out.println(sentence_prob.score + "\t" + sentence_prob.string);
		sentence_prob = beam_search.beamSearch("<s> Israel and Jordan signed the peace", 10, 40);
		System.out.println(sentence_prob.score + "\t" + sentence_prob.string);
		sentence_prob = beam_search.beamSearch("<s> It is", 10, 15);
		System.out.println(sentence_prob.score + "\t" + sentence_prob.string);
	}

}
