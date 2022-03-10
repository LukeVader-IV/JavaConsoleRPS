#include "../headers/players.h"
#include "../headers/network.h"
#include <stdio.h>

int CLI_player() {
	client();
	return 0;
}

int success;

int play(int input) {
	do {
		success = scanf("%d", &input);
		switch (input) {
			case 0:

				break;
			case 1:

				break;
			case 2:
				break;
			default:
				printf("not a valid input, please try again");
				break;
		}
	} while (input != 3 && success == 1);
	return 0;
}
