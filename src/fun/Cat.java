package fun;
/**
 * Cat class for use fun
 * 
 *
 */
public class Cat extends Pet
{

	protected boolean isLongHair;
	protected double weight;

	/**
	 * @param name
	 * @param yearOfBirth
	 * @param isDog
	 * @param isLongHair
	 * @param weight
	 */
	public Cat(String name, int yearOfBirth, boolean isDog, boolean isLongHair, double weight)
	{
		super(name, yearOfBirth, isDog);
		this.isLongHair = isLongHair;
		this.weight = weight;
	}

	/**
	 * @return the isLongHair
	 */
	public boolean isLongHair()
	{
		return isLongHair;
	}

	/**
	 * @param isLongHair the isLongHair to set
	 */
	public void setLongHair(boolean isLongHair)
	{
		this.isLongHair = isLongHair;
	}

	/**
	 * @return the weight
	 */
	public double getWeight()
	{
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	@Override
	public String toString()
	{
		return name;
	}

}
