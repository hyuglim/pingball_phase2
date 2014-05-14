package PingballClientServer;

public class Quadruple<One,Two, Three, Four> {

	private final One one;
	private final Two two;
	private final Three three;
	private final Four four;

	public Quadruple(One one, Two two, Three three, Four four) {
		this.one = one;
		this.two = two;
		this.three = three;
		this.four = four;
	}

	public One getOne() { return one; }
	public Two getTwo() { return two; }
	public Three getThree() {return three;}
	public Four getFour() {return four;}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((four == null) ? 0 : four.hashCode());
		result = prime * result + ((one == null) ? 0 : one.hashCode());
		result = prime * result + ((three == null) ? 0 : three.hashCode());
		result = prime * result + ((two == null) ? 0 : two.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quadruple other = (Quadruple) obj;
		if (four == null) {
			if (other.four != null)
				return false;
		} else if (!four.equals(other.four))
			return false;
		if (one == null) {
			if (other.one != null)
				return false;
		} else if (!one.equals(other.one))
			return false;
		if (three == null) {
			if (other.three != null)
				return false;
		} else if (!three.equals(other.three))
			return false;
		if (two == null) {
			if (other.two != null)
				return false;
		} else if (!two.equals(other.two))
			return false;
		return true;
	}

	
}
