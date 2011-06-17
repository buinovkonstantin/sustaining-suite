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

/**
 * Implementation of XML-wrapping class. 
 * Intended for usage as parameters bucket.
 * 
 * @author buinok
 */
public class ParamsImpl implements Params {
	
	// default name of root tag in wrapped XML document
	private static final String PARAMETERS_ROOT_DEFAULT = "parameters";
	
	// tag name holding simple [name:value] pair
	private static final String PARAMETER = "parameter";
	private static final String PARAMETER_NAME = "name";
	private static final String PARAMETER_VALUE = "value";
	
	// XML document owning child tags which are wrapped by this class 
	private Document document;
	// root element of XML document
	private Element root;

	/**
	 * Constructor creates new parameters bucket where
	 * its content is duplicated from XML node's content
	 * @param node - source of content for parameters bucket
	 */
	public ParamsImpl(Node node) {
		document = new DocumentImpl();
		if(node == null) {
			root = document.createElement(PARAMETERS_ROOT_DEFAULT);
			document.appendChild(root);
		} else {
			document.appendChild(document.importNode(node, true));
			root = document.getDocumentElement();
		}
	}

	/**
	 * Default constructor. Creates empty parameters bucket
	 * with default root tag name
	 */
	public ParamsImpl() {
		document = new DocumentImpl();
		root = document.createElement(PARAMETERS_ROOT_DEFAULT);
		document.appendChild(root);
	}

	/**
	 * De-facto copy constructor. Creates new parameters bucket
	 * then fill it by duplicated content of @param params argument 
	 */
	public ParamsImpl(Params params) {
		document = new DocumentImpl();
		if(params == null) {
			root = document.createElement(PARAMETERS_ROOT_DEFAULT);
			document.appendChild(root);
		} else {
			document.appendChild(document.importNode(params.getXmlContent(),true));
			root = document.getDocumentElement();
		}
	}

	/**
	 * Sets name of root tag to @param name if it's not null,
	 * otherwise just ignores call
	 */
	@Override
	public void setName(String name) {
		if(name != null)
			document.renameNode(root, null, name);
	}
	
	/**
	 * Tries to get String value of parameter with specified @param name
	 * @return String value if it exists in parameters bucket
	 * @throws ParamsException if there is a corruption in wrapped
	 * 		XML document
	 * @throws ParamsException if there are no parameters with specified @param name
	 */
	@Override
	public String getString(String name) throws ParamsException {
		if(name == null)
			throw new ParamsException("Invalid parameter name ["+name+"]");
		
		// enumerate through all tags with endpoint parameters
		NodeList list = root.getElementsByTagName(PARAMETER);
		for(int index = 0; index < list.getLength(); index++) {
			NamedNodeMap attributes = list.item(index).getAttributes();
			// check only tags with existing attributes
			if(attributes != null) {
				// try to find name attribute
				Node nameAttribute = attributes.getNamedItem(PARAMETER_NAME);
				if(nameAttribute == null)
					throw new ParamsException("Missing parameter name");
				
				String nameString = nameAttribute.getNodeValue();
				if(nameString == null)
					throw new ParamsException("Void parameter name");
				
				if(!nameString.equals(name))
					continue;
					
				// try to find value attribute
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

	/**
	 * Store string parameter into bucket
	 * @param name - name to store parameter with
	 * @param value - value of stored parameter
	 */
	@Override
	public void putString(String name, String value) {
		if(name == null || value == null)
			throw new IllegalArgumentException("name ["+name+
					"] and value ["+value+"] are mandatory");
		
		Element node = document.createElement(PARAMETER);
		node.setAttribute(PARAMETER_NAME, name);
		node.setAttribute(PARAMETER_VALUE, value);
		
		root.appendChild(node);
	}

	/**
	 * Stores parameters bucket as a parameter for another bucket.
	 * All nodes of stored bucket are duplicated
	 * @param name - name to store parameter with
	 * @param params - content of stored parameters bucket
	 */
	@Override
	public void putParams(String name, Params params) {
		if(name == null || params == null)
			throw new IllegalArgumentException("name ["+name+
					"] and parameter ["+params+"] are mandatory");
		
		Element node = document.createElement(name);
		
		Element xmlContent = params.getXmlContent();
		NodeList list = xmlContent.getChildNodes();
		
		for(int nodeIndex = 0; nodeIndex < list.getLength(); nodeIndex++) {
			node.appendChild(document.importNode(list.item(nodeIndex),true));
		}
		
		root.appendChild(node);
	}
	
	/**
	 * Returns all parameters buckets which are stored under actual one
	 * @param - name of all parameters bucket to retrieve
	 */
	@Override
	public Params[] getAllParamsWithName(String name) {
		if(name == null)
			throw new IllegalArgumentException("name ["+name+
					"] is mandatory");
		
		NodeList list = root.getElementsByTagName(name);
		
		Params[] paramsArray = new Params[list.getLength()];
		for(int index = 0; index < list.getLength(); index++) {
			paramsArray[index] = new ParamsImpl(list.item(index));
		}
		
		return paramsArray;
	}

	/**
	 * Return root wrapped XML tag 
	 */
	@Override
	public final Element getXmlContent() {
		return root;
	}

	/**
	 * Implements standard method to serialize hits object to String
	 */
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
}
