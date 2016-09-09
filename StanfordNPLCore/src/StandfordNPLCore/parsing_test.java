package StandfordNPLCore;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

public class parsing_test {
	public static void main(String[] args){
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse");
		// xac dinh cau truc ngu phap
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		String text = "i am killed by you";
		Annotation annotation = new Annotation(text);
		pipeline.annotate(annotation);
		List<CoreMap> Sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for(CoreMap Sentence : Sentences){
			Tree tree = Sentence.get(TreeAnnotation.class);
			System.out.println(tree.toString());
			SemanticGraph parsTree = Sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
			System.out.println(parsTree.toList());
			String root = parsTree.getFirstRoot().originalText();
			System.out.println(root);
		}
		// (ROOT (S (NP (NP (NNS Ones)
		//					(NN day)) (, ,) 
		//          	(NP (NN Computer))) 
		//			(VP (MD can) 
		//				(VP (VB understand) 
		//				(S (NP (PRP you)) 
		//					(VP (VB like) 
		//						(NP (PRP$ your) (NN mom))))))))
	}
}
