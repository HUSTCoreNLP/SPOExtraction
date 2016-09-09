package StandfordNPLCore;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class Entity_tagger_test {
	public static void main(String[] args){
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner"); // ner for Named Entity Tagger -- xac dinh thuc the cua tu
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		/*list ner:
		Recognizes named (PERSON, LOCATION, ORGANIZATION, MISC)
		numerical (MONEY, NUMBER, ORDINAL, PERCENT)
		temporal (DATE, TIME, DURATION, SET) */
		String text = "HaNoi is the capital of Vietnam and the country's second largest city.";
		Annotation annotation = new Annotation(text);
		pipeline.annotate(annotation);
		
		List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);
		for(CoreMap sentence: sentences){
			for(CoreLabel token:sentence.get(TokensAnnotation.class)){
				String word = token.get(TextAnnotation.class);
				String Ner = token.get(NamedEntityTagAnnotation.class);
				System.out.println(word + "\t" + Ner);
				
			}
		}
	   
	}
}
