package StandfordNPLCore;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class coreference_resolution_test {
	public static void main(String[] args){
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
		// dcoref for coreference resolution 
		// xac dinh thuc the cua tu
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		String document = "Mr.A is my father. He is very handsome";
		Annotation annotation = new Annotation(document);
		pipeline.annotate(annotation);
		// This is the coreference link graph
		// Each chain stores a set of mentions that link to each other,
		// along with a method for getting the most representative mention
		// Both sentence and token offsets start at 1!
		Map<Integer, CorefChain> graph = annotation.get(CorefChainAnnotation.class);	
	}
}
