package common.params;

import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;

public class ParamsImpl implements Params {
	
	private static final String PARAMETERS_ROOT = "parameters";
	private static final String PARAMETER = "parameter";
	private static final String PARAMETER_NAME = "name";
	private static final String PARAMETER_VALUE = "value";
	
	private Document document;
	private Element root;

	public ParamsImpl(Node node) {
		document = new DocumentImpl();
		document.appendChild(document.importNode(node, true));
		root = document.getDocumentElement();
	}

	public ParamsImpl() {
		document = new DocumentImpl();
		root = document.createElement(PARAMETERS_ROOT);
		document.appendChild(root);
	}

	public ParamsImpl(Params params) {
		document = new DocumentImpl();
		document.appendChild(document.importNode(params.getXmlContent(),true));
		root = document.getDocumentElement();
	}

	@Override
	public void setName(String name) {
		document.renameNode(root, null, name);
	}
	
	@Override
	public String getString(String name) throws ParamsException {
		NodeList list = root.getElementsByTagName(PARAMETER);
		for(int index = 0; index < list.getLength(); index++) {
			NamedNodeMap attributes = list.item(index).getAttributes();
			if(attributes != null) {
				Node nameAttribute = attributes.getNamedItem(PARAMETER_NAME);
				if(nameAttribute == null)
					throw new ParamsException("Missing parameter name");
				
				String nameString = nameAttribute.getNodeValue();
				if(nameString == null)
					throw new ParamsException("Void parameter name");
				
				if(!nameString.equals(name))
					continue;
					
				Node valueAttribute = attributes.getNamedItem(PARAMETER_VALUE);
				if(valueAttribute == null)
					throw new ParamsException("Missing parameter value");
				
				String valueString = valueAttribute.getNodeValue();
				if(valueString == null)
					throw new ParamsException("Void parameter value");
				
				return valueString;
			}
		}
		
		throw new ParamsException("There is no parameter ["+name+"]");
	}

	@Override
	public void putString(String name, String value) {
		Element node = document.createElement(PARAMETER);
		node.setAttribute(PARAMETER_NAME, name);
		node.setAttribute(PARAMETER_VALUE, value);
		
		root.appendChild(node);
	}

	@Override
	public void putParams(String name, Params params) {
		Element node = document.createElement(name);
		
		Element xmlContent = params.getXmlContent();
		NodeList list = xmlContent.getChildNodes();
		
		for(int nodeIndex = 0; nodeIndex < list.getLength(); nodeIndex++) {
			node.appendChild(document.importNode(list.item(nodeIndex),true));
		}
		
		root.appendChild(node);
	}
	
	@Override
	public Params[] getAllParamsWithName(String name) {
		NodeList list = root.getElementsByTagName(name);
		
		Params[] paramsArray = new Params[list.getLength()];
		for(int index = 0; index < list.getLength(); index++) {
			paramsArray[index] = new ParamsImpl(list.item(index));
		}
		
		return paramsArray;
	}
	
	@Override
	public final Element getXmlContent() {
		return root;
	}
	
	@Override
	public String toString() {
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer();
			Source source = new DOMSource(document);
			StringWriter writer = new StringWriter();
			Result result = new StreamResult(writer);
			transformer.transform(source, result);
			return writer.toString();

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
    public static void main(String[] args) {
		Document document = new DocumentImpl();
		Element root = document.createElement("root");
		document.appendChild(root);
		
		Element node = document.createElement(PARAMETER);
		node.setAttribute(PARAMETER_NAME, "oldName");
		node.setAttribute(PARAMETER_VALUE, "oldConnection");
		
		root.appendChild(node.cloneNode(true));
		
		node.setAttribute(PARAMETER_NAME, "newName");
		node.setAttribute(PARAMETER_VALUE, "newConnection");
		
		root.appendChild(node.cloneNode(true));
		
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer();
			Source source = new DOMSource(document);
			StringWriter writer = new StringWriter();
			Result result = new StreamResult(writer);
			transformer.transform(source, result);
			System.out.println(writer.toString());
			
			source = new DOMSource(root);
			writer = new StringWriter();
			result = new StreamResult(writer);
			transformer.transform(source, result);
			System.out.println(writer.toString());
			
			document.renameNode(root, null, PARAMETER);
			source = new DOMSource(root);
			writer = new StringWriter();
			result = new StreamResult(writer);
			transformer.transform(source, result);
			System.out.println(writer.toString());

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		

    }
}
