package Project3;
import java.util.*;



public class onlineExamination 
{
	private String username;
	private String password;
	private boolean isLoggedIn;
	private int timeRemaining;
	private int questionCount;
	private int[] userAnswer;
	private int[] correctAnswer;
	String[] questions=new String[] {"Python is a ______ ?",
			"Linux is _____ ?",
			"AIX is the operating system of which company ?",
			 "'Nibble' in computer terminology is also called ________ ?",
			 "Third generation computers used: ?",
			 "Trackball is which of the following devices ?",
			 "Which of the following is an input device of a computer ?",
			"What is the full form of PROM ?",
			 "_______ is the smallest unit of data in a computer ?",
			"Which of the following is NOT an anti-virus software ?",
			};
    String[][] options = {
            {"high level language ", "assembly language", "low level language", "machine language"},
            {"an interpreter", "an operating system", "an assembly language", "a high level language"},
            {"Apple", "Unisys", "IBM", "Microsoft"},
            {"Bit ", "Half bit", "Half Byte", "Byte"},
            {"vacuum tubes", "integrated circuits", "Transistor", "VLSI technique"},
            {"Input Device", "Touchpad", "output device", "Barcode reader"},
            {"Speaker ", "Printer", "Scanner", "Monitor"},
            {"Program", "Primary", "Programmable"},
            {"Giga bYte", "Bit", "Byte"},
            {"Linux", "norton", "Avast"}
    };
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your username and password");
		String uName=sc.nextLine();
		String pWord=sc.nextLine();
		onlineExamination examSystem = new onlineExamination(uName, pWord);
		examSystem.login();
		examSystem.startExam();
	}
	
	public onlineExamination(String username, String password) 
	{
		this.username = username;
		this.password = password;
		System.out.println("Succesfully You are registered! :)");
		this.isLoggedIn = false;
		this.timeRemaining=10;//in minutes
		this.questionCount=10;
		this.userAnswer=new int[questionCount];
		this.correctAnswer=new int[questionCount];
		// initialize correct answers with random values (0 or 1)
		for(int i=0; i<questionCount;i++) 
		{
			correctAnswer[i] = (int) Math.round(Math.random());
		}
	}
	
	public void login()
	{
		System.out.println("Log in to give the Exam ");
		Scanner scanner= new Scanner(System.in);
		System.out.print("Username: ");
		String inputUsername = scanner.nextLine();
		System.out.println("Password: ");
		String inputPassword=scanner.nextLine();
		if(inputUsername.equals(username)&& inputPassword.equals(password))
		{
			isLoggedIn =true;
			System.out.println("Login succesful Best of Luck Dear");
		}
		else {
			System.out.println("Login failed. Please try again.");
			
		}
	}
	public void logout()
	{
		isLoggedIn=false;
		System.out.println("Logout succesfull.");
	}
	
	public void startExam() 
	{
		if (!isLoggedIn) {
			System.out.println("Please login first.");
			return;
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("You have " + timeRemaining + " minutes to complete the exam.");
		for (int i = 0; i < questionCount; i++) 
		{
			System.out.println("Question"+ (i+1)+":" +questions[i]);
			for(int j=0;j<options[i].length;j++)
			{
				System.out.println(j+1+". "+options[i][j]);
			}
			System.out.println("Your answer (1 or 2 or 3 or 4):");
			int answer = scanner.nextInt();
			userAnswer[i]= answer;

		}
		
		System.out.println("Would you like to submit? \n1:Yes \n2:NO");
		int n = scanner.nextInt();
		if(n==1) 
		{
			submitExam();
		}
		else {
			try {
				Thread.sleep(timeRemaining*10*1000);
				
			}catch(InterruptedException e) {
				e.printStackTrace();
				submitExam();
				
			}
		}

	}
	
	public void submitExam()
	{
		if(!isLoggedIn)
		{
			System.out.println("Please login first");
			return;
		}
		int score=0;
		for(int i=0; i<questionCount; i++)
		{
			if(userAnswer[i]==correctAnswer[i]) {
				score++;
			}
		}
		System.out.println("Your score is " + score + " out of " + questionCount + ".");
		System.out.println("Best of luck :)");
		logout();
	}
}
