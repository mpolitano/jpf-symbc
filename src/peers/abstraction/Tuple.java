package abstraction;

/**
 * Simple generic class that captures tuples of objects.
 *
 * @author Nazareno Aguirre
 *
 * @param <T1> is the type of the first component of the tuple
 * @param <T2> is the type of the second component of the tuple
 */
public class Tuple<T1, T2> {

  /**
   * first component
   */
  private T1 first;

  /**
   * second component
   */
  private T2 second;

  /**
   * Constructor, that receives values of first and second components to setup a tuple.
   * @param first is the value for the first component
   * @param second is the value for the second component
   */
  public Tuple(T1 first, T2 second) {
    super();
    this.first = first;
    this.second = second;
  }

  /**
   * Returns the value of the first component of a tuple.
   * @return the first component of the tuple.
   */
  public T1 getFirst() {
    return first;
  }

  /**
   * Sets the value of the first component of the tuple.
   * @param first is the value to be set as first component of the tuple.
   */
  public void setFirst(T1 first) {
    this.first = first;
  }

  /**
   * Returns the value of the second component of a tuple.
   * @return the second component of the tuple.
   */
  public T2 getSecond() {
    return second;
  }

  /**
   * Sets the value of the second component of the tuple.
   * @param second is the value to be set as second component of the tuple.
   */
  public void setSecond(T2 second) {
    this.second = second;
  }
  
  public String toString() {
	  return "(" + first.toString() + "," + second.toString() + ")";
  }

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((first == null) ? 0 : first.hashCode());
	result = prime * result + ((second == null) ? 0 : second.hashCode());
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
	Tuple other = (Tuple) obj;
	if (first == null) {
		if (other.first != null)
			return false;
	} else if (!first.equals(other.first))
		return false;
	if (second == null) {
		if (other.second != null)
			return false;
	} else if (!second.equals(other.second))
		return false;
	return true;
}
  

  
}
