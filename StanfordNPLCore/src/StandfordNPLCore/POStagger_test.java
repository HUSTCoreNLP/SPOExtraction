package StandfordNPLCore;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ling.CoreLabel;
// jj - tinh tu
// jjr - tinh tu so sanh
// jjs - tinh tu so sanh hon nhat
// 

public class POStagger_test {
	public static void main(String[] args){
		Properties props = new Properties();
		props.setProperty("annotators","tokenize, ssplit, pos, lemma, ner");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		
		// tao 1 doi tuong Annotation rong voi noi dung la cau string
		Annotation annotation = new Annotation("The value of local variable p is not used \n The value of local variable p is not used");
		
		// chay toan bo Annotor tren doan Text
		pipeline.annotate(annotation);
		
		// Coremap thuc chat la mot ban do cau voi doi tuong tuy chinh
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for (CoreMap sentence : sentences) { 
			// sentence = cau
			// CoreLabel thuc chat la 1 thanh phan trong Coremap voi cac ham bo sung
		    for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
		    	// this is the text of the token
		        String word = token.get(CoreAnnotations.TextAnnotation.class);
		        // word = tu
		        // this is the POS tag of the token
		        String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
		        System.out.println(word + "/" + pos );
		    }
		}
		
	}
}
