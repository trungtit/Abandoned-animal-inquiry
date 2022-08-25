package xml_parsing;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ApiExplorer {

	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			//접속할 url의 정보를 정의
			StringBuffer abandonmentPublicUrl = new StringBuffer();
			abandonmentPublicUrl.append("http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?");
			abandonmentPublicUrl.append("ServiceKey=hqlBQ0W36vOJFw9gGfAhH%2Bk217961UuaFEY0ply54m1cE7g6%2Bl6COxuHIHDh3s52XK82ECmk1f31qUx9XzUCcg%3D%3D");
			
			//웹서버에 접속하기
			URL url = new URL(abandonmentPublicUrl.toString());
//			System.out.println(url.openStream());
			
			//응답 데이터를 읽어와서 저장
			BufferedInputStream data = new BufferedInputStream(url.openStream());
			
			//InputStream객체의 형태로 파싱할 정보를 넘겨준다.
			Document document = builder.parse(data);
			//access to the document's data
			//the root of the document tree
			Element root = document.getDocumentElement();
//			System.out.println(root.getTagName());
			NodeList list = root.getElementsByTagName("item");
			System.out.println("Rows number: "+ list.getLength());
			
			
			for(int i=0; i<list.getLength(); i++) {
				org.w3c.dom.Node node =   list.item(i);
				NodeList item_list = node.getChildNodes();
				for (int j = 0; j < item_list.getLength(); j++) {
					org.w3c.dom.Node item_childs =  item_list.item(j);
					System.out.print(item_childs.getNodeName()+":"+item_childs.getTextContent());
					
				}
				System.out.println();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();			
		} 
		catch (IOException e) {
			e.printStackTrace();			
		}

	}

}
