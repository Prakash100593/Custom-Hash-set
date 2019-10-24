public interface SetI<E> {
	boolean	add(E e);

	boolean	addAll(MyHashSet <E> c);
	boolean	containsAll(MyHashSet <E> c);
	boolean	removeAll(MyHashSet <E> c);
	void	clear();
	boolean	contains(Object o);
	boolean	equals(Object o);
	int	hashCode();
	boolean	isEmpty();
	boolean	remove(Object o);
	int	size();
	Object[]	toArray();
}