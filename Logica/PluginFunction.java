package Logica;

public interface PluginFunction {

	// let the application pass in a parameter
	public void setParameter (int p1, int p2);

	// retrieve a result from the plugin
	public float getResult();

	// return the name of this plugin
	public String getPluginName();

	// can be called to determine whether the plugin
	// aborted execution due to an error condition
	public boolean hasError();
}
