import java.io.*;
import java.util.*; 
import processing.core.*; 

public class student extends PApplet {
	public int curTopic = -1;
	public int score = 0; 
	public PImage topic1; 
	public PImage topic2; 
	public String answer = "";
	public boolean correct = false; 
	public boolean incorrect = false; 
	public static void main(String[] args) {
		PApplet.main("student");
	}
	public void readProfile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("profile.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		score = Integer.parseInt(st.nextToken()); 
		br.close();
	}
	public void writeProfile() throws IOException{
		PrintWriter pw = new PrintWriter(new FileWriter("profile.txt"));
		pw.write("" + score + " ");
		pw.close();
	}
	public void settings() {
		size(1200, 625); //display pixel size
	}
	public void setup() {
		topic1 = loadImage("transportation.png");
		topic2 = loadImage("healthcare.png");
		try {
			readProfile();
		} catch (IOException e) {
			try {
				writeProfile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			setup();
		}
	}
	public int screen = 0; 
	public void draw() {
		if(screen == 0) topicSelect(); 
		else if(screen == 1) languageSelect();
		else if(screen == 2) recommended();
		else if(screen == 3) problem();
		else if(screen == 4) leaderboard();
		else if(screen == 5) achievements();
	}
	topic[] topics = {new topic("transportation", new String[]{"car.png"}, new String[]{"car"}), 
			new topic("healthcare", new String[]{"hospital.jpg"}, new String[]{"hospital"})};
	public void topicSelect() { //screen 0
		background(200, 200, 200);
		textAlign(CENTER);
		rectMode(CENTER);
		textSize(20);
		fill(100, 100, 100);
		rect(width/2, 75, 250, 100);
		fill(255, 255, 255);
		text("Welcome", width/2, 50);
		text("Please Select A Topic Below", width/2, 100);
		text("Your score: " + score, width-100, 50);
		imageMode(CENTER);
		image(topic1, width/2-100, height/2);
		image(topic2, width/2+100, height/2);
	}
	public void mousePressed() {
		if(screen == 0) {
			if(mouseX < width/2-50 && mouseX > width/2-150) {
				screen = 3; 
				curTopic = 0; 
			}else if(mouseX > width/2+50 && mouseX < width/2+150) {
				curTopic = 1; 
				screen = 3; 
			}
		}
	}
	public void languageSelect() {//screen 1
		background(200, 200, 200);
	}
	public void problem() {//screen 2
		background(200, 200, 200);
		String imageName; 
		if(curTopic == 0)  imageName = "car.png"; 
		else if(curTopic == 1) imageName = "hospital.jpg"; 
		else {
			System.out.println("Error");
			return; 
		}
		PImage curProblem; 
		imageMode(CENTER); 
		curProblem = loadImage(imageName); 
		image(curProblem, width/2, height/2);
		text(answer, width/2, height/2+200);
		if(correct) {
			correct();
		}
		if(incorrect) {
			incorrect();
		}
	}
	
	public void keyPressed() {
		if(screen == 3) {
			if(key == ENTER) {
				checkAnswer(); 
				answer = "";
			}else if(key == BACKSPACE) {
				answer = answer.substring(0, answer.length()-1);
			}else if(key == ' ') {
				screen = 0; 
				correct = false; 
				incorrect = false;
			}
			else {
				answer += key; 
			}
		}
		if(screen == 0) {
			if(key == 'l') {
				screen = 4; 
			}
		}
	}
	public void checkAnswer() {
		if(curTopic == 0) {
			if(answer.equals("car")) {
				score++; 
				correct = true; 
			}
			else incorrect = true; 
		}else if(curTopic == 1) {
			if(answer.equals("hospital")) {
				score++; 
				correct = true; 
			}
			else incorrect = true; 
		}
	}
	public void correct() {
		fill(0, 255, 0);
		rect(width/2, height/2, 1200, 625, 20);
		fill(0, 0, 0);
		text("Correct!", width/2, height/2);
	}
	public void incorrect() {
		fill(255, 0, 0);
		rect(width/2, height/2, 1200, 625, 20);
		fill(0, 0, 0);
		text("Wrong Answer", width/2, height/2);
		text("Correct Answer Is: " + topics[curTopic].cardsNames[0], width/2, height/2+100);
	}
	public void recommended() {//screen 3
		background(200, 200, 200);
	}
	public void leaderboard() {//screen 4
		background(200, 200, 200);
	}
	public void achievements() {//screen 5
		background(200, 200, 200);
	}
	class topic{
		String name; 
		String[] cards; 
		String[] cardsNames; 
		topic(String name, String[] str, String[] str1){
			this.name = name; 
			this.cards = str;
			this.cardsNames = str1; 
		}
	}
}
