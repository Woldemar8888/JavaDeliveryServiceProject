package by.epamtc.aladzyin.bean;

import java.io.Serializable;

public class Car implements Serializable {

	private static final long serialVersionUID = 3827051386454744047L;
	
	private String number;
	private String ownerName;
	private String model;
	private String year;
	private double carrying;
	private double luggageVolume;
	
	public Car() {}
	
	public Car(String number, String ownerName, String model, String year, double carrying, double luggageVolume) {
		super();
		this.number = number;
		this.ownerName = ownerName;
		this.model = model;
		this.year = year;
		this.carrying = carrying;
		this.luggageVolume = luggageVolume;
	}
	

	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}

	public String getOwnerName() {
		return ownerName;
	}



	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public double getCarrying() {
		return carrying;
	}

	public void setCarrying(double carrying) {
		this.carrying = carrying;
	}

	public double getLuggageVolume() {
		return luggageVolume;
	}



	public void setLuggageVolume(double luggageVolume) {
		this.luggageVolume = luggageVolume;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(carrying);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(luggageVolume);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		Car other = (Car) obj;
		if (Double.doubleToLongBits(carrying) != Double.doubleToLongBits(other.carrying))
			return false;
		if (Double.doubleToLongBits(luggageVolume) != Double.doubleToLongBits(other.luggageVolume))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (ownerName == null) {
			if (other.ownerName != null)
				return false;
		} else if (!ownerName.equals(other.ownerName))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [number=" + number + ", ownerName=" + ownerName + ", model=" + model + ", year=" + year
				+ ", carrying=" + carrying + ", luggageVolume=" + luggageVolume + "]";
	}
}
