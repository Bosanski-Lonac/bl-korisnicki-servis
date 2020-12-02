package model;

public enum Rank {
	ZLATO(10000), SREBRO(1000), BRONZA(0);
	
	private final Integer milje;
	
	private Rank(Integer milje) {
		this.milje=milje;
	}
	
	public Integer getMilje() {
		return this.milje;
	}
}
