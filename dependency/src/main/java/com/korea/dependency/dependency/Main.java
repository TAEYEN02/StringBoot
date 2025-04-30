package com.korea.dependency.dependency;

public class Main {
	public static void main(String[] args) {
		ComputerRoom cr = new ComputerRoom();
		
		cr.com1 = new Samsung();
		cr.com2 = new Apple();
	}
}
