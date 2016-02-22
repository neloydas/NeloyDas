package com.xf.MyProject;

public class MineSweeperMain {

	public static void main(String[] args) {

		MineSweeperDemo mineSweep = new MineSweeperDemo(4, 4);
		mineSweep.setMineField("*...\n..*.\n....\n.*..");
		System.out.println(mineSweep.getHintField());

	}

}
