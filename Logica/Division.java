package Logica;

public class Division implements PluginFunction {

	private int operando1, operando2;
	
	@Override
	public void setParameter(int p1, int p2) {
		
		operando1 = p1;
		operando2 = p2;
	}

	@Override
	public float getResult() {
		// TODO Auto-generated method stub
		return operando1 / operando2;
	}

	@Override
	public String getPluginName() {
		// TODO Auto-generated method stub
		return "Division";
	}

	@Override
	public boolean hasError() {
		// TODO Auto-generated method stub
		return false;
	}

}
