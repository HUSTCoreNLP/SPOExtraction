package KDE.StanfordNPL.SPO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;


public class text2xml {
	private static String readFile(String path) throws IOException{
		String document = new String();
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path));
			while ((sCurrentLine = br.readLine()) != null) {
				document += sCurrentLine;
				//System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
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
		for(int i = 0; i < documents.length; i++){
			Annotation annotation = new Annotation(documents[i]);
			pipeline.annotate(annotation);
			FileOutputStream os = new FileOutputStream(new File("./target/", paths[i]+".xml"));
			pipeline.xmlPrint(annotation, os);
			os.close();
			/*List<CoreLabel> Sentences = annotation.get(CoreAnnotations.TokensAnnotation.class);
			//for(CoreLabel Sentence : Sentences){
				SemanticGraph DepenTree = Sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
				System.out.println(DepenTree.toString(SemanticGraph.OutputFormat.XML));
			//}
*/		}
		
	}
}
