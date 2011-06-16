package common.params;

import org.w3c.dom.Element;

public interface Params {

	public void setName(String name);

	public String getString(String name) throws ParamsException;
	public void putString(String name, String value);

	public void putParams(String name, Params params);
	
	public Params[] getAllParamsWithName(String name);

	Element getXmlContent();
}
