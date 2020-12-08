package Logica;

public class Suma implements PluginFunction{

	private int operando1, operando2;
	
	@Override
	public void setParameter(int p1, int p2) {
		operando1 = p1;
		operando2 = p2;
		
	}

	@Override
	public float getResult() {
		
		return operando1 + operando2;
	}

	@Override
	public String getPluginName() {
		// TODO Auto-generated method stub
		return "Suma";
	}

	@Override
	public boolean hasError() {
		// TODO Auto-generated method stub
		return false;
	}

}
