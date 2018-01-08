
package questionGenerator;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class Questions {

	public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);
		System.out.println("Choose two integers in the range of 0 to 1,000,000 for the answer range. \nSpecify first integer: \n");
		int firstInt = userInput.nextInt();
		System.out.println("Specify second integer: \n");
		int secondInt = userInput.nextInt();


		if (firstInt > secondInt) {
			int swapNumbers = firstInt;
			firstInt = secondInt;
			secondInt = swapNumbers;
		}

		int answerRanging = ThreadLocalRandom.current().nextInt(firstInt, secondInt +1 );

		JSONObject root = new JSONObject();
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		if(answerRanging >= 0 && answerRanging <= 10000000) {
			Random numRange = new Random();

			int firstNumberAddition = numRange.nextInt(answerRanging) + 1;
			int secondNumberAddition = answerRanging - firstNumberAddition;
			System.out.println(firstNumberAddition + " + " + secondNumberAddition + " = ?");
			String question = (firstNumberAddition + " + " + secondNumberAddition + " = ?");
			root.put("question", question);

			ArrayList<Integer> allAnswers = new ArrayList<>();
			int correctAnswer= firstNumberAddition + secondNumberAddition;
			allAnswers.add(correctAnswer);


			JSONObject answerObject = new JSONObject();

			while(allAnswers.size() < 4) {
				Integer nextWrongAnswer = numRange.nextInt(answerRanging);
				if(!allAnswers.contains(nextWrongAnswer)) {
					allAnswers.add(nextWrongAnswer);
				}

				root.put("answers", gson.toJson(allAnswers));
				root.put("Correct answer", correctAnswer);
			}

			Collections.shuffle(allAnswers);
			System.out.println(allAnswers);
			int userAnswer = userInput.nextInt();
			if (userAnswer == correctAnswer) {
				System.out.println(correctAnswer + " is correct");
			} else {
				System.out.println(userAnswer + " is incorrect. \nThe correct answer is " + correctAnswer);
			}
			System.out.println(root.toString());
		}
		else {
			System.out.println("Numbers not in specified range. Make sure your answer range is 0 - 1,000,000!");

		}

		System.exit(0);

	}

}
