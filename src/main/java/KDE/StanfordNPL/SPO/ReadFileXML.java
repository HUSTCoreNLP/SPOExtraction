package KDE.StanfordNPL.SPO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadFileXML {
	public static List<Sentence> readFile (String paths) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document xmlDoc = builder.parse(
				new File(paths));
		
		List<Sentence> sentences = new ArrayList<>();
		NodeList nodes = xmlDoc.getElementsByTagName("sentence");
		for (int i = 0; i < nodes.getLength(); i++) {
			//xu li tung cau:
			Element elem = (Element)nodes.item(i);
			Sentence sentence = new Sentence();
			sentence.setId(Integer.parseInt(elem.getAttributes().getNamedItem("id").getNodeValue()));
			
			//lay tagname dependencies
			List<Token> tmp_tok = new ArrayList<Token>();
			NodeList tokens = elem.getElementsByTagName("token");
			for(int j = 0; j < tokens.getLength(); j++){
				Element token = (Element) tokens.item(j);
				int id = Integer.parseInt(token.getAttributes().getNamedItem("id").getNodeValue());
				String word = token.getElementsByTagName("word").item(0).getTextContent();
				String lemma = token.getElementsByTagName("lemma").item(0).getTextContent();
				//int CharacterOffsetBegin = token.getElementsByTagName("word").item(0).;
				//int CharacterOffsetEnd = Integer.parseInt(token.getAttributes().getNamedItem("CharacterOffsetEnd").getNodeValue());
				String pos = token.getElementsByTagName("POS").item(0).getTextContent();
				String ner = token.getElementsByTagName("NER").item(0).getTextContent();
				Token loop_token = new Token(pos, ner, word, lemma, 0, 0, id);
				tmp_tok.add(loop_token);
			}
			sentence.setTokens(tmp_tok);
			sentence.setPairs(elem.getElementsByTagName("parse").item(0).getTextContent());
			
			NodeList dependencies = elem.getElementsByTagName("dependencies");
			List<Dep> list_dep = new ArrayList<>();
			for (int j = 0; j < dependencies.getLength(); j++) {
				//lay ra dependence thu j
				Element dependence = (Element) dependencies.item(j);
				//lay ra cac dep cua dependence
				//System.out.println(dependence.getAttribute("type"));
				//if(dependence.getAttribute("type") == "basic-dependencies") System.out.println("adf");;
				NodeList deps = dependence.getElementsByTagName("dep");
				//System.out.println(deps.getLength());
				for (int k = 0; k < deps.getLength(); k++) {
					
					//lay ra dep thu k
					Element dep = (Element) deps.item(k);
					//xu li dep thu k
					String type = dep.getAttribute("type");
					int id1,id2;
					NodeList governor = dep.getElementsByTagName("governor");
					id1 = Integer.parseInt(((Element)governor.item(0)).getAttributes().getNamedItem("idx").getNodeValue());
					NodeList dependent = dep.getElementsByTagName("dependent");
					id2 = Integer.parseInt(((Element)dependent.item(0)).getAttributes().getNamedItem("idx").getNodeValue());
								
					Dep loop_dep = new Dep(type, id1, id2);
					//System.out.println(loop_dep.getType());
					list_dep.add(loop_dep);
				}
				Dependence basic = new Dependence("basic-dependencies");
				basic.setDepList(list_dep);
				sentence.setBasic_dep(basic);
			}
			sentences.add(sentence);
		}
		return sentences;
	}
	public static List<Triple> getTripleList(List<Sentence> document){
		List<Triple> triples = new ArrayList<>();
		for(Sentence tmp: document){
			Token tmp_dep_token = tmp.getDependent("ROOT", "root");
			if(tmp_dep_token == null) {
				System.err.println("there is a err, ignore and continue");
				continue;
			}
			else if(tmp_dep_token.getPOS().startsWith("NN")) triples.add(new Triple(tmp_dep_token.getLemma(), null, null));
			else{
				Token S_token,P_token,O_token;
				// P:
				if(tmp.getDependent(tmp_dep_token.getWord(), "ccomp") != null) 
					P_token = tmp.getDependent(tmp_dep_token.getWord(), "ccomp");
				else if(tmp.getDependent(tmp_dep_token.getWord(), "xcomp") != null)
					P_token = tmp.getDependent(tmp_dep_token.getWord(), "xcomp");
				else
					P_token = tmp_dep_token;
				String P = P_token.getLemma();
				//S:
				if(tmp.getDependent(P_token.getWord(), "nsubj") != null && tmp.getDependent(P_token.getWord(), "nsubjpass") == null)
					S_token = tmp.getDependent(P_token.getWord(), "nsubj");
				else if(tmp.getDependent(P_token.getWord(), "nmod:agent") != null)
					S_token = tmp.getDependent(P_token.getWord(), "nmod:agent");
				else
					S_token = tmp.getDependent(tmp_dep_token.getWord(), "nsubj");
				String S;
				if(S_token != null) {
					S = S_token.getLemma();
					if(tmp.getDependent(S_token.getWord(), "nmod") != null)
						S = tmp.getDependent(S_token.getWord(), "nmod").getLemma() +" "+ S;
					if(tmp.getDependent(S_token.getWord(), "nummod") != null)
						S = tmp.getDependent(S_token.getWord(), "nummod").getLemma() +" "+ S;
				}
				else S = null;
				//O:
				if(tmp.getDependent(P_token.getWord(), "nsubjpass") != null)
					O_token = tmp.getDependent(P_token.getWord(), "nsubjpass");
				else if(tmp.getDependent(P_token.getWord(), "dobj") != null)
					O_token = tmp.getDependent(P_token.getWord(), "dobj");
				else
					O_token = tmp.getDependent(tmp_dep_token.getWord(), "iobj");
				String O;
				if(O_token != null) {
					O = O_token.getLemma();
					if(tmp.getDependent(O_token.getWord(), "nmod") != null)
						O = tmp.getDependent(O_token.getWord(), "nmod").getLemma() +" "+ O;
					if(tmp.getDependent(P_token.getWord(), "nummod") != null)
						O = tmp.getDependent(P_token.getWord(), "nummod").getLemma() +" "+ O;
				}
				else O = null;
				triples.add(new Triple(S, P, O));
			}
				
		}
		return triples;
	}
	public static void main(String[] args) throws Exception,ParserConfigurationException,
    SAXException, IOException {
		String path = "/home/jeovach/java workspace/StanfordNPL.SPO/xml";
		String[] paths;
		File file = new File(path);
		paths = file.list();
    	for(int i = 0; i < paths.length; i++){
    		//System.out.println(paths[i]);
    		File file2 = new File("/home/jeovach/java workspace/StanfordNPL.SPO/triple" + "/" + paths[i] + ".triple");      
    		FileWriter fw = new FileWriter(file2.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            List<Sentence> document = readFile(path + "/" + paths[i]);
 //           for(Sentence s:document){
//            	for(Dep k : s.getBasic_dep().getDepList()){
//            		if(k.getType().startsWith("root"))
//            		System.out.println(k.getType());
//            	}
//            }
            List<Triple> triples = getTripleList(document);
            for(Triple t:triples){
            	bw.write("["+t.getS()+";"+t.getP()+";"+t.getO()+"]" + "\n");
            }
            bw.close();
    	}
	}
}
