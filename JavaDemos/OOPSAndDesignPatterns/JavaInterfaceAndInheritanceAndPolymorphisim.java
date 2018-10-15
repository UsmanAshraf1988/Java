/**
 * @file JavaInterfaceAndInheritanceAndPolymorphisim.java
 *
 *
 * @brief It is the demo of interface, abstract class, inheritance, polymorhpisim
 *        and object factory design pattern in a thread safe way in Java.
 * 
 *
 * @Author Usman Ashraf 
 * @email  u.ashraf1988@gmail.com
 *
 * GNU Lesser General Public License v3.0, free to use, re-use, distriution and modification
 * provided this above header information. Author take no responsibility for any outcome of
 * this code. 
 *
 * If you need more information, please, contact me at my above email address.
 */

import java.io.*;

/* Interface. */
interface Vehicle{
	
	public final double toleranceFactor = 0.95;
	
	public abstract void setSpeed(double speed);
	public abstract double getSpeed();
	
}


/* Abstract lazy implemention of an interface. */
abstract class AbstractCar implements Vehicle{
	
	public String toString(){
		return "AbstractCar";
	}
	
}


/* Inheritance. */
class Car extends AbstractCar{
	public double mSpeed;
	
	public Car(){
		System.out.println("Car");
	}
	
	@Override
	public synchronized void setSpeed(double speed){
		mSpeed = speed*(toleranceFactor*1);
	}
	
	@Override
	public double getSpeed(){
		return mSpeed;
	}
	
	@Override
	public String toString(){
		return "Car";
	}
	
}

/* Inheritance. */
class BMW extends Car{
	public BMW(){
		System.out.println("BMW");
	}	
	
	@Override
	public synchronized void setSpeed(double speed){
		mSpeed = speed*(toleranceFactor*1.05);
	}
	
	@Override
	public String toString(){
		return "BMW";
	}	
}

/* Inheritance. */
class WV extends Car{
	public WV(){
		System.out.println("WV");
	}
	
	@Override
	public synchronized void setSpeed(double speed){
		mSpeed = speed*(toleranceFactor*1.03);
	}
	
	@Override
	public String toString(){
		return "WV";
	}	
}

/* Object Factory Design Pattern and Polymorphisim. */
class ObjectFactory{
	public static BMW bmw;
	public static WV wv;

	public ObjectFactory(){
		System.out.println(this.toString());
	}

	/**
	 * polymorphisim: specialized bmw or wv are being owned by car which can 
	 * in further be owned by a Vehicle, an abstract car or a car.
	 */
	public static synchronized Car getObject(String str){
		if( str=="BMW" ){
			if(bmw==null){
				bmw = new BMW();
			}
			return bmw;
		} else if ( str=="WV" ){
			if(wv==null){
				wv = new WV();
			}
			return wv;
		} else {
			return null;
		}
	}
	
	public String toString(){
		return "ObjectFactory";
	}		
}

public class JavaInterfaceAndInheritanceAndPolymorphisim{
	
	public static void main(String[] args){
		System.out.println();
		
		/* Polymorphisim */
		AbstractCar car; 
		car = ObjectFactory.getObject("BMW");
		if (car!=null){
			car.setSpeed(1.0);
			System.out.println("BMW speed: " + car.getSpeed() + "\n");
		}
		
		car = ObjectFactory.getObject("WV");
		if (car!=null){
			car.setSpeed(1.0);
			System.out.println("WV speed: " + car.getSpeed() + "\n");
		}	
	}
}
