package application;

import javafx.beans.property.SimpleStringProperty;

public class Step {
	private SimpleStringProperty stepnum;
	private SimpleStringProperty step;
	private SimpleStringProperty data;
	private SimpleStringProperty result;
	
	public Step() {
		
	}
	public Step(String stepnum, String step, String data, String result) {
		super();
		this.stepnum = new SimpleStringProperty(stepnum);
		this.step = new SimpleStringProperty(step);
		this.data = new SimpleStringProperty(data);
		this.result = new SimpleStringProperty(result);
	}
	/**
	 * @return the stepnum
	 */
	public String getStepnum() {
		return stepnum.get();
	}
	/**
	 * @param stepnum the stepnum to set
	 */
	public void setStepnum(String stepnum) {
		this.stepnum.set(stepnum);
	}
	/**
	 * @return the step
	 */
	public String getStep() {
		return step.get();
	}
	/**
	 * @param step the step to set
	 */
	public void setStep(String step) {
		this.step.set(step);
	}
	/**
	 * @return the data
	 */
	public String getData() {
		return data.get();
	}
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data.set(data);
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result.get();
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result.set(result);
	}

	

}
