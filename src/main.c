#include "network.h"
#include "moves.h"

#include <stdio.h>
#include <stdlib.h>

int main () {

	printf("\t\tRock Paper Scissor  O N L I N E !\n\nOriginal idea in Java by:\nHugo Costa (Koraku),\n\nRebase in C by:\nLucas de Rooy\n\n\n");
	printf("0 - Host a game\n1 - Join a hosted game\n2 - Quit\n");

	int input;
	int success;
	do {
		success = scanf("%d", &input);
		switch (input) {
			case 0:
				/* server(); */
				game();
				break;
			case 1:
				client();
			case 2:
				break;
			default:
				printf("not a valid input, please try again");
				break;
		}
	} while (input != 2 && success == 1);

	switch (success) {
		case 0:
			return 1;
		default:
			return 0;
			break;
	}
}

int game() {

	int move;
	while (move != 4) {
		printf("Rock(1) / Paper(2) / Scissor(3) (or quit :4) : ");
		scanf("%d", &move);
		checkwin(move, rand() % 4);
	}

	return 0;

}

int printstate(int winstate) {
	switch (winstate)
		{
		case 0:
			printf("LOST");
			break;
		case 1:
			printf("DRAW");
			break;
		case 2:
			printf("WIN");
			break;
		}
	return 0;
}
