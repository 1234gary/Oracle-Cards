package main;

import inout.In;

import java.awt.*;

public class Main {
	static Player your = new Player();
	static Player their = new Player();
	static Card[] cardIdentity = new Card[14];
	static int count = 1;
	static int damage = 0;
	static int random = 0;
	static int[] input = new int[4];
	static int amount = 0;
	static int damageModifier = 1;
	static Pair temp;
	public static void initialize (){
		your.name = "Player 1";
		their.name = "Player 2";
		Card empty = new Card(0,0,"");
		Card lover = new Card(1,1,"Lovers");
		Card temp = new Card(1,2,"Temperance");
		Card hang = new Card(1,3,"HangedMan");
		Card fool = new Card(1,4,"Fool");
		Card mage = new Card(2,1,"Magician");
		Card just = new Card(2,2,"Judgement");
		Card queen = new Card(2,3,"Empress");
		Card king = new Card(2,4,"Emperor");
		Card high = new Card(3,1,"HighPriestess");
		Card death = new Card(3,2,"Death");
		Card pope = new Card(3,3,"Hierophant");
		Card devil = new Card(3,4,"Devil");
		Card hermit = new Card(1,5,"Hermit");
		cardIdentity[0] = lover;
		cardIdentity[1] = temp;
		cardIdentity[2] = hang;
		cardIdentity[3] = fool;
		cardIdentity[4] = mage;
		cardIdentity[5] = just;
		cardIdentity[6] = queen;
		cardIdentity[7] = king;
		cardIdentity[8] = high;
		cardIdentity[9] = death;
		cardIdentity[10] = pope;
		cardIdentity[11] = devil;
		cardIdentity[12] = hermit;
		cardIdentity[13] = empty;
		your.picks[0] = new Card(0,0,"");
		your.picks[1] = new Card(0,0,"");
		their.picks[0] = new Card(0,0,"");
		their.picks[1] = new Card(0,0,"");
		for (int i = 0; i<3; i++){
			your.hand[i] = new Card(0,0,"");
			your.hand[i].Equals(cardIdentity[13]);
			their.hand[i] = new Card(0,0,"");
			their.hand[i].Equals(cardIdentity[13]);
		}
	}
	public static void refreshDeck(Player a, boolean safe){
		if (safe == false){
			a.health -= 25;
			System.out.println("\n" + a.name + "'s remaining health is " + a.health );
			}
			if (a.health <= 0){
				System.out.println("Game End");
				System.exit(0);
			}
			
		for (int i = 0; i < 12; i++){
			a.deck[i] = 2;
		}
		a.remainingDeck = 24;
		for (int i = 0; i<3; i++){	
			markedDiscard(a, i);
			a.hand[i].Equals(cardIdentity[13]);
			a.hand[i].revealed = false;
		}		
	}
	public static void startDraw(){
		for (int i = 0; i < 3; i++ ){
			if (your.remainingDeck != 0){
				while (your.hand[i].value == cardIdentity[13].value){
					random = (int)(Math.random() * 12);
					if (your.deck[random] > 0){
					your.hand[i].Equals(cardIdentity[random]);
					your.deck[random] -= 1;
					your.remainingDeck -= 1;
					}
				}
			}else if (your.remainingDeck == 0 && your.hand[0].value != cardIdentity[13].value && your.hand[1].value == cardIdentity[13].value){
				your.hand[1].Equals(cardIdentity[12]);
				i = 4;
			}else if (your.remainingDeck == 0 && your.hand[0].value != cardIdentity[13].value && your.hand[1].value != cardIdentity[13].value){
				i = 4;
			}else{
				refreshDeck(your, false);
				i = -1;
			}
		}
		for (int i = 0; i < 3; i++ ){
			if (their.remainingDeck != 0){
				while (their.hand[i].value == cardIdentity[13].value){
					random = (int)(Math.random() * 12);
					if (their.deck[random] > 0){
					their.hand[i].Equals(cardIdentity[random]);
					their.deck[random] -= 1;
					their.remainingDeck -= 1;
					}
				}
			}else if (their.remainingDeck == 0 && their.hand[0].value != cardIdentity[13].value && their.hand[1].value == cardIdentity[13].value){
				their.hand[1].Equals(cardIdentity[12]);
				i = 4;
			}else if (their.remainingDeck == 0 && their.hand[0].value != cardIdentity[13].value && their.hand[1].value != cardIdentity[13].value){
				i = 4;
			}else{
				refreshDeck(their, false);
				i = -1;
			}
		}
	}
	public static void drawRev(Player your, int a){
		if (your.remainingDeck == 0){
			refreshDeck(your, false);
		}
		if (a != 14){	
			if (your.deck[a] != 0){
			for (int i = 0; i < 3; i ++){
				if (your.hand[i].value == cardIdentity[13].value){			
						your.hand[i].Equals(cardIdentity[a]);
						your.hand[i].revealed = true;
						System.out.println(your.name + " draws " + your.hand[i].name);
						your.remainingDeck -= 1;
						your.deck[a] -= 1;
						i = 3;
					}
				}
			}
		}else{
			boolean cardDrawed = false;
			while (cardDrawed == false){
				a = (int)(Math.random() * 12);
				if (your.deck[a] != 0){
					for (int i = 0; i < 3; i ++){
						if (your.hand[i].value == cardIdentity[13].value){	
								your.hand[i].Equals(cardIdentity[a]);
								your.hand[i].revealed = true;
								System.out.println(your.name + " draws " + your.hand[i].name);
								your.remainingDeck -= 1;
								your.deck[a] -= 1;
								i = 3;
						}
					}
					cardDrawed = true;
				}
			}
		}
	}
	public static void mill(int damage, Player your){
			your.remainingDeck -= damage;
			System.out.println("\n" + your.name + " mills " + damage + " cards");
			for(int i = 0; i < damage;){
				if (your.remainingDeck > 0){
					random = (int) (Math.random() * 12);
					if (your.deck[random] != 0){
					your.deck[random] -= 1;
					i += 1;
					}
				}else{
					refreshDeck(your, false);
					damage = 0;
				}	
			}
		
	}
	public static void handOrganize(Player your){
		for (int i = 2; i > 0; i--){
			if (your.hand[i].value != cardIdentity[13].value && your.hand[0].value == cardIdentity[13].value){
				your.hand[0].Equals(your.hand[i]);
				your.hand[i].Equals(cardIdentity[13]);
			}else if (your.hand[2].value != cardIdentity[13].value && your.hand[1].value != cardIdentity[13].value){
				your.hand[1].Equals(your.hand[2]);
				your.hand[2].Equals(cardIdentity[13]);
			}
		}
	}
	public static void passiveActivate(Player your, Player their, int i){
		switch (your.picks[i].name){
		case "Hermit":
			System.out.println("\nMystic Knowledge Activates!");
			refreshDeck(your, true);
			break;
		case "Lovers":
			for (int j = 0; j < 2; j ++){
				if (their.picks[j].name.equals("Lovers")){
					System.out.println("\nFated Meeting Activates!");
					refreshDeck(your, true);
					refreshDeck(their, true);
				}
			}
			break;
		case "Empress":
			if (your.deck[7] == 0){
				your.picks[i].name = "Emperor";
			}
			break;
		case "HighPriestess": 
			for (int j = 0; j < 3; j ++){
				if (your.hand[j].name.equals("Hierophant")){
					System.out.println("\nFollower Activates!");
					your.hand[j].revealed = true;
				}
			}
			break;
		}
	}
	public static void activeActivate(Player your, Player their, String name){
		switch (name){
		case "Temperance":
			System.out.println("\nRestraint Activates!");
			for (int j = 0; j < 3; j ++){
				markedDiscard(your, j);
				their.hand[j].Equals(cardIdentity[13]);
				if (their.deck[0] != 2 || their.deck[1] != 2 || their.deck[2] != 2 || their.deck[3] != 2){
					for (int k = 0; k < 2;){
					random = (int) (Math.random() * 4);
					if (their.deck[random] != 2){
						System.out.println(cardIdentity[random].name + " is shuffled back!");
						their.deck[random]  += 1;
						k = 4;
					}
					}
				}
			}
			break;
		case "HangedMan":
			System.out.println("\nSoul Passing Activates!");
			drawRev(your, 9);
			break;
		case "Fool":
			System.out.println("\nFoolishness Activates!");
			amount = 0;
			for (int j = 0; j < 2; j++){
				if (your.picks[j].name.equals("Fool") || your.picks[j].name.equals("Emperor") || your.picks[j].name.equals("Empress")){
					amount += 1;
				}
				if (their.picks[j].name.equals("Fool") || their.picks[j].name.equals("Emperor") || their.picks[j].name.equals("Empress")){
					amount += 1;
				}
			}	
			mill(amount, your);
			mill(amount, their);
			break;
		case "Emperor":
			System.out.println("\nEmpire Activates!");
			for (int j = 0; j < 3; j ++){
				if (your.hand[j].value != 0){
					your.hand[j].revealed = true;
					your.hand[j].marked = true;
				}
			}
			counterActivate(your, their, "Emperor");
			break;
		case "Judgement":
			System.out.println("\nTrial Activates!");
			amount = 0;
			for (int j = 0; j < 3; j++){
				if (your.hand[j].revealed == true){
					amount -= your.hand[j].value;
				}
				if (their.hand[j].revealed == true){
					amount += their.hand[j].value;
				}
			}
			if (amount > 0){
				System.out.println("\nSentence Activates on " + your.name + "!");
				mill(3, your);
			}else if (amount < 0){
				System.out.println("\nSentence Activates on " + their.name + "!");
				mill(3, their);
			}
			break;
		case "Magician":
			System.out.println("\nAttunement Activates!");
			amount = 0;
			for (int j = 0; j < 3; j++){
				if (your.hand[j].revealed == true){
					amount += 1;
				}
			}
			for (int j = 0; j < amount; j++){
				System.out.println("Sorcery Activates!");
				mill(2, their);
			}
			break;
		case "Devil":
			System.out.println("\nTrickster Activates!");
			drawRev(their, 14);
			damageModifier = 2;
			counterActivate(your, their, "Devil");
			break;
		case "Death":
			System.out.println("\nAfterlife Activates!");
			drawRev(your, 14);
			drawRev(their, 14);
			mill(5, your);
			mill(5, their);
			counterActivate(your, their, "Death");
			break;
		case "High Priestess":
			System.out.println("\nWorship Activates!");
			for (int j = 0; j < 3; j ++){
				if (your.hand[j].revealed == true){
					activeActivate(your, their, your.hand[j].name);
				}
			}
			break;
		}
			
	}
	public static void counterActivate(Player your, Player their, String victim){
		if (victim.equals("Emperor")){
			for (int i = 0; i < 2; i ++){
				if (their.picks[i].name.equals("Empress")){
					System.out.println("\nManipulation Activates!");
					for (int j = 0; j < 3; j++){
						if (your.hand[j].revealed == true){
							markedDiscard(your, j);
							your.hand[j].Equals(cardIdentity[13]);
						}
					}
				}
			}
		}else{
			for (int i = 0; i < 2; i ++){
				if (their.picks[i].name.equals("Hierophant")){
					System.out.println("\nConsecration Activates!");
					for (int j = 0; j < 3; j++){
						if (their.hand[j].revealed == true){
							markedDiscard(your, j);
							their.hand[j].Equals(cardIdentity[13]);
							mill(3, your);
							j = 3;
						}
					}
				}
			}
			for (int i = 0; i < 3; i ++){
				if (their.hand[i].name.equals("Hierophant") && their.hand[i].revealed){
					System.out.println("\nConsecration Activates!");
					for (int j = 0; j < 3; j++){
						if (their.hand[j].revealed == true){
							markedDiscard(your, j);
							their.hand[j].Equals(cardIdentity[13]);
							mill(3, your);
							j = 3;
						}
					}
				}
			}
		}
	}
	public static void markedDiscard(Player your, int i){
		if (your.hand[i].marked){
			your.hand[0].marked = false;
			your.hand[1].marked = false;
			your.hand[2].marked = false;
			System.out.println(your.hand[i].name + " is discarded!");
			mill(3, your);
		}
	}
	public static void enemyHand(Player their){
		System.out.print(their.name + "'s Cards: ");
		for (int i = 0; i < 3; i ++){
			if (their.hand[i].revealed){
				System.out.print(their.hand[i].name + " ");
			}else{
				System.out.print("Hidden ");
			}
		}	
	}
	public static void enemyDeck(Player their){
		System.out.println("\n" + their.name + "'s Deck: ");
		for (int j = 0; j < 3; j ++){
			if (!their.hand[j].revealed){
				their.deck[(their.hand[j].faction - 1) * 4 + their.hand[j].value - 1] += 1;
			}
		}
		for (int i = 0; i < 12; i ++){
			System.out.println(cardIdentity[i].name + ": " + their.deck[i]);			
		}
		for (int j = 0; j < 3; j ++){
			if (!their.hand[j].revealed){
				their.deck[(their.hand[j].faction - 1) * 4 + their.hand[j].value - 1] -= 1;
			}
		}
	}
	public static void main(String[] args) {
		//one-time prep
		initialize();
		
		//new game starting prep
		your.health = 125;
		their.health = 125;
		refreshDeck(your, false);
		refreshDeck(their, false);
		
		//game starts
		do{
		//turn starts
		System.out.println("\nRound " + count + "\n");
		startDraw();
		
		//selection phase
		
		System.out.println("Player 1 Select Your Cards");		
		System.out.println("Remaining Cards: " + your.remainingDeck);
		System.out.println("Your Cards: " + your.hand[0].name + " " + your.hand[1].name + " " + your.hand[2].name);
		enemyHand(their);
		enemyDeck(their);
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		input[0] = In.getInt();
		//input[0] = 1;
		
		input[1] = In.getInt();
		//input[1] = 2;
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		System.out.println("Player 2 Select Your Cards");
		System.out.println("Remaining Cards: " + their.remainingDeck);
		System.out.println("Your Cards: " + their.hand[0].name + " " + their.hand[1].name + " " + their.hand[2].name);
		enemyHand(your);
		enemyDeck(your);
		//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		input[2] = In.getInt();
		//input[2] = 2;
		
		input[3] = In.getInt();
		//input[3] = 1;
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		your.picks[0].Equals(your.hand[input[0] - 1]);
		markedDiscard(your, input[0] - 1);
		your.hand[input[0] - 1].Equals(cardIdentity[13]);
		your.picks[1].Equals(your.hand[input[1] - 1]);
		markedDiscard(your, input[1] - 1);
		your.hand[input[1] - 1].Equals(cardIdentity[13]);
		their.picks[0].Equals(their.hand[input[2] - 1]);
		markedDiscard(their, input[2] - 1);
		their.hand[input[2] - 1].Equals(cardIdentity[13]);
		their.picks[1].Equals(their.hand[input[3] - 1]);
		markedDiscard(their, input[3] - 1);
		their.hand[input[3] - 1].Equals(cardIdentity[13]);
		handOrganize(your);
		handOrganize(their);
		
		//ability order
		temp = your.picks[0].OrderChange(your.picks[1]);
		your.picks[0] = temp.getFirst();
		your.picks[1] = temp.getSecond();
		temp = their.picks[0].OrderChange(their.picks[1]);
		their.picks[0] = temp.getFirst();
		their.picks[1] = temp.getSecond();
		System.out.println("Player 1 Picks: " + your.picks[0].name + " and " + your.picks[1].name);
		System.out.println("Player 2 Picks: " + their.picks[0].name + " and " + their.picks[1].name);
		
		//passive acc
		for (int i = 0; i < 2; i++){
			if (your.picks[i].Order(their.picks[i])){
				passiveActivate(your, their, i);
				passiveActivate(their, your, i);
			}else{
				passiveActivate(their, your, i);
				passiveActivate(your, their, i);
			}			
		}
		
		//active acc
		for (int i = 0; i < 2; i++){
			if (your.picks[i].Order(their.picks[i])){
				activeActivate(your, their, your.picks[i].name);
				activeActivate(their, your, their.picks[i].name);
			}else{
				activeActivate(their, your, their.picks[i].name);
				activeActivate(your, their, your.picks[i].name);
			}
		}
		
		for (int i = 0; i < 3; i++){
			if (your.hand[i].marked){
				System.out.println(your.name + "'s value is increased!");
				your.picks[i].value += 3;
				i = 3;
			}
		}
		for (int i = 0; i < 3; i++){
			if (their.hand[i].marked){
				System.out.println(their.name + "'s value is increased!");
				their.picks[i].value += 3;
				i = 3;
			}
		}
		
		//battle damage
		if (your.picks[0].faction == their.picks[0].faction) {
			damage = -(your.picks[0].value + your.picks[1].value - their.picks[0].value - their.picks[1].value);
		}else if (your.picks[0].Order(their.picks[0])){
			damage =  -(your.picks[0].value + your.picks[1].value + their.picks[0].value + their.picks[1].value);
		}else{
			damage =  (your.picks[0].value + your.picks[1].value + their.picks[0].value + their.picks[1].value);
		}
		if (damage > 0){
			mill(damage * damageModifier, your);
		}else if (damage < 0){
			mill(damage*-1*damageModifier, their);
		}
		damageModifier = 1;
		count += 1;
		
		}while (their.health > 0 && your.health > 0);
	}

}








































