package common.protocol.tasks;

public class Log {
	
	public enum Level { Debug, Verbose, Status, Warning, Error };
	public enum FilterType { Message };

	public class Filter {
		
		private FilterType type;
		private String value;
		
		public Filter(FilterType type, String value) {
			this.setType(type);
			this.setValue(value);
		}
		
		public void setType(FilterType type) {
			this.type = type;
		}

		public FilterType getType() {
			return type;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

}
