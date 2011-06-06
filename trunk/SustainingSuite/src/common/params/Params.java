package common.params;

import org.w3c.dom.Element;

public interface Params {

	void setName(String name);

	String getString(String name) throws ParamsException;
	void putString(String name, String value);

	void putParams(String name, Params params);
	
	Params[] getAllParamsWithName(String name);

	Element getXmlContent();



}
