package fun;
/**
 * Dog class for fun
 *
 */
public class Dog extends Pet
{
	protected String breed;
	protected double weight;

	/**
	 * @param name
	 * @param yearOfBirth
	 * @param isDog
	 * @param breed
	 * @param weight
	 */
	public Dog(String name, int yearOfBirth, boolean isDog, String breed, double weight)
	{
		super(name, yearOfBirth, isDog);
		this.breed = breed;
		this.weight = weight;
	}

	/**
	 * @return the breed
	 */
	public String getBreed()
	{
		return breed;
	}

	/**
	 * @param breed the breed to set
	 */
	public void setBreed(String breed)
	{
		this.breed = breed;
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
