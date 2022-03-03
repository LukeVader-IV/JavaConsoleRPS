#include "players.h"
#include "network.h"
#include <stdio.h>

int play(int move) {
	int status = client();
	return 0;
}

int CLI_player() {
	client();
	return 0;
}

int play() {
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
}
