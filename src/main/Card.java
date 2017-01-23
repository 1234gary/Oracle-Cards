package main;

final class Pair{
	Card first;
	Card second;
	public Pair(Card first, Card second){
		this.first = first;
		this.second = second;
	}
	public Card getFirst (){
		return first;
	}
	public Card getSecond (){
		return second;
	}
}

public class Card {
	int faction = 0;
	int value = 0;
	String name = "";
	boolean revealed = false;
	boolean marked = false;
	public Card(int f, int v, String m){
		this.faction = f;
		this.value = v;
		this.name = m;
	}
	public Pair OrderChange(Card other){
		if (this.faction == other.faction ){
			if (this.value > other.value){
				return new Pair(this, other);
			}else{
				return new Pair(other, this);
			}
		} else if (this.faction == 1 && other.faction == 3){
			return new Pair(this, other);
		}else if ((this.faction != 3 && this.faction < other.faction) || (this.faction == 3 && other.faction == 1)){
			return new Pair(other, this);
		}else{
			return new Pair(this, other);
		}
	}
	public boolean Order(Card other){
		if (this.faction == other.faction ){
			if (this.value > other.value){
				return true;
			}else{
				return false;
			}
		} else if (this.faction == 1 && other.faction == 3){
			return true;
		}else if ((this.faction != 3 && this.faction < other.faction) || (this.faction == 3 && other.faction == 1)){
			return false;
		}else{
			return true;
		}
	}
	public void Equals(Card other){
		this.faction = other.faction;
		this.value = other.value;
		this.name = other.name;
		this.revealed = other.revealed;
		this.marked = other.marked;
	}
}
