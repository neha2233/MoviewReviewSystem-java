package com.swiggytask.swiggytask;
import java.util.Random;

class Player{
	
	int health;
	int strength;
	int attack;
	Random logic;
	
	public Player(int health, int strength, int attack) {
		this.health=health;
		this.strength=strength;
		this.attack=attack;
		this.logic=new Random();
		
	}
	int getHealth() {
		return health;
	}
	int attack() {
		int res= logic.nextInt(6)+1;
		return res;
	}
	int defend() {
		return logic.nextInt(6)+1;
	}
	void reduceHealth(int damage) {
		health-=damage;
	}
	boolean isAlive() {
		return health>0;
	}
	
}

class MagicalArena{
	Player playerA;
	Player playerB;
	
	public MagicalArena(Player playerA, Player playerB) {
		this.playerA=playerA;
		this.playerB=playerB;
	}
	void figth() {
		Player attacker= playerA.health<playerB.health ? playerA: playerB;
		Player defender =attacker==playerA?playerB:playerA;
		
		while(playerA.isAlive() && playerB.isAlive()) {
			int attackRoll=attacker.attack();
			int defenseRoll=defender.defend();
			
			int damage = attackRoll * attacker.attack - defenseRoll * defender.strength;
			if(damage>0) {
				defender.reduceHealth(damage);
			}
			Player temp=attacker;
			attacker=defender;
			defender=temp;
		}
		if(playerA.isAlive()) {
			System.out.println("Player A wins!");
		}
		else {
			System.out.println("Player B wins!");
		}
	}
}
public class Main {

	public static void main(String[] args) {
		Player player_A=new Player(50,5,10);
		Player player_B=new Player(100,10,5);
		
		MagicalArena arena=new MagicalArena(player_A, player_B);
		arena.figth();


	}

}
