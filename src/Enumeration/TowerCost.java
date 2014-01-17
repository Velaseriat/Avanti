package Enumeration;

public enum TowerCost {
		ATTIA (50),
		TANYA (100),
		IRIS (125),
		XINA (150),
		KLAIR (200),
		ELNI (200),
		VIVIENNE (150),
		VELASARIAT(1000);
		
		private int cost;
		TowerCost(int cost){
			this.cost = cost;
	    }
		public int getCost() { return cost; }
}
