package constatnt;

public enum ComponentType {

	TILE("tile"), TEXT("text"), PANEL("panel");

	private String value;

	private ComponentType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
