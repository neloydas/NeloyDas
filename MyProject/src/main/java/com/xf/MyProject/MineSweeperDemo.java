package com.xf.MyProject;

import java.util.StringTokenizer;

public class MineSweeperDemo implements MineSweeper {

	// Default constructor with 4x4 board
	public MineSweeperDemo() {
		numLines = 4;
		numCols = 4;
		this.mineField = new char[numLines + 2][numCols + 2];
	}

	// Parameterized constructor with (numLines) x (numCols) board
	public MineSweeperDemo(int numLines, int numCols) {
		this.numLines = numLines;
		this.numCols = numCols;
		this.mineField = new char[numLines + 2][numCols + 2];
	}

	int numLines, numCols;
	char[][] mineField;

	/**
	 * Initializes the field
	 *
	 * A mine-field of N x M squares is represented by N lines of exactly M
	 * characters each. The character '*' represents a mine * and the character
	 * '.' represents no-mine. Lines are separated by '\n'
	 * <p/>
	 * * Example mine-field string (as input to setMineField()):
	 * "*...\n..*.\n...." (a 3 x 4 mine-field of 12 squares, 2 of which are
	 * mines)
	 *
	 * @param mineField
	 *            string containing the mines
	 * @throws IllegalArgumentException
	 *             if mineField is not properly formatted
	 */
	public void setMineField(String mineFieldString) throws IllegalArgumentException {

		// Formatting check of the input string
		IllegalArgumentException excp = new IllegalArgumentException(
				"The input string provided is not properly formatted.");
		if (mineFieldString.length() > ((numLines * numCols) + (numLines - 1))
				|| mineFieldString.length() < ((numLines * numCols) + (numLines - 1))) {
			throw excp;
		}
		char[] cArray = mineFieldString.toCharArray();
		for (char c : cArray) {
			if (c != '.' && c != '*' && c != '\n')
				throw excp;
		}
		// Formatting check done

		// Tokenizing the string to a two dimensional char array to simulate a
		// mine field
		StringTokenizer stoken = new StringTokenizer(mineFieldString, "\n");
		String str;

		// Initialize the mine field with * for mines and 0 for possible hint
		// fields
		for (int i = 1; i <= numLines; i++) {
			str = stoken.nextToken().trim();
			for (int j = 1; j <= numCols; j++) {
				if (j - 1 < str.length())
					if (str.charAt(j - 1) == '*')
						mineField[i][j] = '*';
					else
						mineField[i][j] = '0';
				else
					mineField[i][j] = '0';
			}
		}
	}

	/**
	 * Produces a hint-field of identical dimensions as the mineFiled() where
	 * each square is a * for a mine or the number of adjacent mine-squares if
	 * the square does not contain a mine.
	 * <p/>
	 * Example hint-field (for the above input): "*211\n12*1\n0111"
	 *
	 * @return a string representation of the hint-field
	 * @throws IllegalStateException
	 *             if the mine-field has not been initialized (i.e.
	 *             setMineField() has not been called)
	 */
	public String getHintField() throws IllegalStateException {

		String hintField = new String();

		// Formatting check of the mine field
		IllegalStateException excp = new IllegalStateException("The mine field is not properly initialized.");
		for (int i = 1; i <= numLines; i++) {
			for (int j = 1; j <= numCols; j++) {
				if (mineField[i][j] != '*' && mineField[i][j] != '0') {
					throw excp;
				}
			}
		}
		// Formatting check done

		// Populating the hint numbers around the mines
		for (int i = 1; i <= numLines; i++) {
			for (int j = 1; j <= numCols; j++) {
				if (mineField[i][j] == '*') {
					for (int k = i - 1; k <= i + 1; k++)
						for (int l = j - 1; l <= j + 1; l++)
							if (k != i || l != j)
								mineField[k][l] = (char) ((int) mineField[k][l] + 1);
				}
			}
		}

		// Converting the two dimensional array to a string and returning it
		char[] temp;
		for (int k = 1; k <= numLines; k++) {
			temp = mineField[k];
			hintField = hintField.concat(new String(temp).trim());
			hintField = hintField.concat("\n");
		}

		return hintField;
	}
}
