package KDE.StanfordNPL.SPO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.util.CoreMap;

public class text2xml {
	private static String readFile(String path) throws IOException{
		FileInputStream In = new FileInputStream(path);
		String document = new String();
		int size = In.available();
		for(int i = 0; i < size; i++){
			document += (char)In.read() ;
		}
		In.close();
		return document;
		
	}
	
	static String[] paths;  
	private static String[] readDir(final String path) throws IOException{
		File file = new File(path);
    	paths = file.list();
    	String[] listDocument = new String[paths.length];
    	for(int i = 0; i < paths.length; i++){
            listDocument[i] = readFile(path + "/" + paths[i]);
    	}
    	return listDocument;		
	}
	public static void main(String[] args) throws IOException{
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		//System.out.println(readFile("/home/jeovach/java workspace/StanfordNPL.SPO/data/business_2016_sep_06_eurozone-consumer-spending-slows-but-exports-rise"));
		String path = "/home/jeovach/java workspace/StanfordNPL.SPO/data";
		String[] documents = readDir(path);
		for(String document: documents){
			Annotation annotation = new Annotation(document);
			pipeline.annotate(annotation);
			List<CoreMap> Sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
			for(CoreMap Sentence : Sentences){
				SemanticGraph DepenTree = Sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
				System.out.println(DepenTree.toString(SemanticGraph.OutputFormat.XML));
			}
		}
		
	}
}
