#include "network.h"
#include "moves.h"
#include "main.h"

#include <stdio.h>
#include <stdlib.h>

int main () {

	printf("\t\tRock Paper Scissor  O N L I N E !\n\nOriginal idea in Java by:\nHugo Costa (Koraku),\n\nRebase in C by:\nLucas de Rooy\n\n\n");
	printf("0 - Host a game\n1 - Join a hosted game\n2 - Quit\n");

	int input;
	int inputcheck;
	int played;
	do {
		inputcheck = scanf("%d", &input);
		switch (input) {
			case 0:
				/* server(); */
				played = game();
				break;
			case 1:
				client();
			case 2:
				break;
			default:
				printf("not a valid input, please try again");
				break;
		}
	} while (input != 2 && inputcheck == 1);

	switch (inputcheck) {
		case 0:
			return 1;
		default:
			return 0;
			break;
	}
}

int game() {

	int success;
	int move;
	while (move != 4) {
		printf("Rock(1) / Paper(2) / Scissor(3) (or quit :4) : ");
		scanf("%d", &move);
		success = printstate(checkwin(move -1, rand() % 3));
	}

	return 0;

}

int printstate(int winstate) {
	switch (winstate)
		{
		case 0:
			printf("LOST\n");
			break;
		case 1:
			printf("DRAW\n");
			break;
		case 2:
			printf("WIN\n");
			break;
		case 3:
			printf("quitting...");
			return 1;
		case 5:
			printf("ILLEGAL MOVE MADE; network/communication issue?\n");
		default:
			printf("something went wrong\n");
		}
	return 0;
}
