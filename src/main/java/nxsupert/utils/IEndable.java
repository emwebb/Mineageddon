/**
 * 
 */
package nxsupert.utils;

/**
 * 
 * A simple interface for objects that contain internal processes and require ending before nullification.
 * @author nxsupert
 * @version 1.3
 * 
 *
 */
public interface IEndable {
	
	/**
	 * Attempts to end any internal processes within the object ready for nullification.
	 * 
	 * @return Whether or not the internal processes have successfully ended.
	 */
	public boolean end();
	
	/**
	 * @return Whether or not it is safe to nullify this object.
	 */
	public boolean isSafeToNullify();
	
	
	/**
	 * This kills any internal processes. This is not safe!
	 */
	@Deprecated
	public void kill();
}
