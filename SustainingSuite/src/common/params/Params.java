package common.params;

import org.w3c.dom.Element;

public interface Params {

	public void setName(String name);

	public String getString(String name) throws ParamsException;
	public String getString(String name, String defaultValue);
	public void putString(String name, String value);

	public Params getParams(String name) throws ParamsException;
	public void putParams(String name, Params params);
	
	public Params[] getAllParamsWithName(String name);

	Element getXmlContent();

}
